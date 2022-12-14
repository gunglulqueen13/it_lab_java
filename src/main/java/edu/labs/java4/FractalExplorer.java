package edu.labs.java4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {

    private final int displaySize;
    private final JImageDisplay jImageDisplay;
    private FractalGenerator fractal;
    private final Rectangle2D.Double range;

    public FractalExplorer(int displaySize){
        this.displaySize = displaySize;
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        jImageDisplay = new JImageDisplay(displaySize, displaySize);
    }

    public void createAndShowGUI(){
        jImageDisplay.setLayout(new BorderLayout());
        JFrame jFrame = new JFrame("Fractal Explorer");
        jFrame.add(jImageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        jFrame.add(resetButton, BorderLayout.SOUTH);

        ButtonHandler buttonHandler = new ButtonHandler();
        MouseHandler click = new MouseHandler();

        resetButton.addActionListener(buttonHandler);
        jImageDisplay.addMouseListener(click);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack ();
        jFrame.setVisible (true);
        jFrame.setResizable (false);

    }

    private void drawFractal(){
        for (int x = 0; x < jImageDisplay.getWidth(); x++) {
            for (int y = 0; y < jImageDisplay.getHeight(); y++) {
                double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width,
                        displaySize, x);
                double yCoord = FractalGenerator.getCoord (range.y, range.y + range.width,
                        displaySize, y);
                int numIterations = fractal.numIterations(xCoord, yCoord);
                if (numIterations == -1) {
                    jImageDisplay.drawPixel(x, y, 0);
                } else {
                    float hue = 0.723f + (float)numIterations/200f;
                    jImageDisplay.drawPixel(x, y, Color.HSBtoRGB(hue, 1f, 1f));
                }
            }
        }
        jImageDisplay.repaint();
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action  = e.getActionCommand();
            /**
             * Если источником является кнопка сброса, сбросить дисплей и нарисовать
             * фрактал.
             */
            if (action.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
        }
    }

    private class MouseHandler extends MouseAdapter {
        /**
         * Когда обработчик получает событие щелчка мыши, он отображает пиксель-
          по которым был выполнен щелчок,
         */
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            double xCoord = FractalGenerator.getCoord(range.x,
                    range.x + range.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(range.y,
                    range.y + range.height, displaySize, y);
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer fractalExplorer = new FractalExplorer(700);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }


}
