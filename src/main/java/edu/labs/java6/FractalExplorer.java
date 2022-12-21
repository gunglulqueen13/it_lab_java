package edu.labs.java6;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


public class FractalExplorer {
    private int displaySize;
    private JImageDisplay image;
    private FractalGenerator[] fractalGenerators;
    private Rectangle2D.Double range;

    private int currentFractalIndex;
    private int rowsRemaining;

    private JComboBox<FractalGenerator> comboBox;
    private Button button1;
    private Button button2;

    class FractalWorker extends SwingWorker<Object, Object> {
        final int y;
        int[] lineColors;

        public FractalWorker(int y) {
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            lineColors = new int[image.getWidth()];
            for (int x = 0; x < image.getWidth(); x++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
                int numIters = fractalGenerators[currentFractalIndex].numIterations(xCoord, yCoord);
                if (numIters == -1) {
                    lineColors[x] = 0;
                } else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    lineColors[x] = rgbColor;
                }
            }
            return null;
        }

        @Override
        protected void done() {
            for (int x = 0; x < image.getWidth(); x++) {
                image.drawPixel(x, y, lineColors[x]);
            }
            image.repaint(0, 0, y, displaySize, 1);
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    FractalExplorer(int size) {
        this.displaySize = size;
        this.image = new JImageDisplay(size, size);
        this.fractalGenerators = new FractalGenerator[]{new Mandelbrot(), new Tricorn(), new BurningShip()};
        this.range = new Rectangle2D.Double();

        this.currentFractalIndex = 0;
        fractalGenerators[currentFractalIndex].getInitialRange(this.range);

        comboBox = new JComboBox<>();
        button1 = new Button();
        button2 = new Button();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");


        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("Fractal:");
        panel1.add(label);
        for (FractalGenerator fractalGenerator : fractalGenerators) {
            comboBox.addItem(fractalGenerator);
        }
        comboBox.addActionListener(e -> {
            currentFractalIndex = comboBox.getSelectedIndex();
            fractalGenerators[currentFractalIndex].getInitialRange(range);
            drawFractal();
        });
        panel1.add(comboBox);
        frame.add(panel1, BorderLayout.NORTH);

        image.addMouseListener(new MouseListenerScale());
        frame.add(this.image, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        button1.setLabel("Save image");
        button1.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(image.image, "png", file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        panel2.add(button1);
        button2.setLabel("Reset Display");
        button2.addActionListener(e -> {
            fractalGenerators[currentFractalIndex].getInitialRange(range);
            drawFractal();
        });
        panel2.add(button2);
        frame.add(panel2, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private class MouseListenerScale implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining == 0) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, e.getY());

                fractalGenerators[currentFractalIndex].recenterAndZoomRange(range, xCoord, yCoord, 0.5);

                drawFractal();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private void drawFractal() {
        enableUI(false);
        rowsRemaining = image.getHeight();
        for (int y = 0; y < image.getHeight(); y++) {
            FractalWorker fractalWorker = new FractalWorker(y);
            fractalWorker.execute();
        }
    }

    private void enableUI(boolean val) {
        comboBox.setEnabled(val);
        button1.setEnabled(val);
        button2.setEnabled(val);
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(600);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}
