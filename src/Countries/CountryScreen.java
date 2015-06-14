package Countries;

import Kohonen.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rudy on 14.06.15.
 */
public class CountryScreen extends JPanel {


    private int width;
    private int height;
    private int squareSize;
    private Kohonen network;

    /**
     * Constructor of the class.
     *
     * The window is initialized and create a Kohonen network with random colors.
     * Then, the SOM algorithm is applied and the window is shown.
     *
     * @param w     Window's width.
     * @param h     Window's height.
     * @param size  Window's size.
     */
    public CountryScreen(int w, int h, int size) {
        this.height = h;
        this.width = w;
        this.squareSize = size;

        this.network = new Kohonen("countries.dat", w / size, h / size);
        this.init();
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setVisible(true);
        this.network.SOM();
    }

    /**
     * Initialize the neurons network with random values.
     */
    public void init() {
        for (int i = 0; i < this.height / this.squareSize; i++) {
            for (int j = 0; j < this.width / this.squareSize; j++) {

                // Create the neuron and add it to the network.
                this.network.addNeuron(new ArrayList<Double>(Arrays.asList(Math.random(), Math.random(), Math.random(), Math.random(), Math.random(), Math.random())), j, i);
            }
        }
    }

    /**
     * Draw the rectangles on the window.
     *
     * @param g Graphics element.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < this.height / this.squareSize; i++) {
            for (int j = 0; j < this.width / this.squareSize; j++) {
                Neuron n = this.network.getNeuron(i, j);
                ArrayList<Double> colors = n.getWeights();
                //g.setColor(new Color(n.getWeightI(0), n.getWeightI(1), n.getWeightI(2)));
                g.setColor(new Color((int) (colors.get(0) * 255), (int) (colors.get(1) * 255), (int) (colors.get(2) * 255)));
                // Fill a rectangle with the color
                g.fillRect(j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
            }
        }

    }

}
