import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Display {

	private static int width;
	private static int height;

	// private static int[] xValues;
	// private static int[] yValues;
	// private static boolean[] values;
	private static int[][] screenRip;
	private static int numberOfTrials;

	public static int size;

	private Random r;

	public Display(int width, int height) {
		this.width = width;
		this.height = height;
		r = new Random();
	}

	public static void setParams(int numberOfTrials, int size) {
		Display.numberOfTrials = numberOfTrials;
		Display.size = size;
		screenRip = new int[size][size];
	}

	public void update() {

	}

	public void plot(int counter, double x, double y, boolean inCirc) {

		int a = r.nextInt(2);
		if (a == 0)
			a = -1;

		int b = r.nextInt(2);
		if (b == 0)
			b = -1;
		// xValues[counter] = (int) ((size / 2) + (a * ((size / 2) * x)));

		int i = (int) ((size / 2) + (a * ((size / 2) * x)));
		int j = (int) ((size / 2) + (b * ((size / 2) * y)));
		if (inCirc)
			screenRip[i][j] = 1;
		else
			screenRip[i][j] = 2;
		// yValues[counter] = (int) ((size / 2) + (b * ((size / 2) * y)));
		// values[counter] = inCirc;
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.black);
		g.drawOval(0, 0, size, size);
		g.fillRect(0, size, size, size * (80 / 500));
		g.fillRect(0, size, size, 80);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Simulated value of Pi: " + Main.pi, 5, size + 22);
		g.drawString("Number of trials: " + (Main.counter - 1), 5, size + 45);

		for (int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {

				if (screenRip[i][j] == 1) {
					g.setColor(Color.red);
				} else if (screenRip[i][j] == 2) {
					g.setColor(Color.blue);
				}
				if (screenRip[i][j] != 0) {
					g.fillRect(i, j, 1, 1);
				}
			}
		}
	}
}
