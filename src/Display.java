import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Display {

	private static int width;
	private static int height;

	private static int[] xValues;
	private static int[] yValues;
	private static boolean[] values;
	private static int numberOfTrials;
	private Random r;

	public Display(int width, int height) {
		this.width = width;
		this.height = height;
		r = new Random();
	}

	public static void setParams(int numberOfTrials) {
		Display.numberOfTrials = numberOfTrials;
		xValues = new int[numberOfTrials + 1];
		yValues = new int[numberOfTrials + 1];
		values = new boolean[numberOfTrials + 1];
	}

	public void update() {

	}

	public void plot(int counter, double x, double y, boolean inCirc) {
		int a = r.nextInt(2);
		if (a == 0)
			a = -1;
		xValues[counter] = (int) (250 + (a * (250 * x)));
		int b = r.nextInt(2);
		if (b == 0)
			b = -1;
		yValues[counter] = (int) (250 + (b * (250 * y)));
		values[counter] = inCirc;
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 580);
		g.setColor(Color.black);
		g.drawOval(0, 0, 500, 500);
		g.fillRect(0, 500, 500, 80);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Simulated value of Pi: " + Main.pi, 5, 522);
		g.drawString("Number of trials: " + Main.numberOfTrials, 5, 545);

		for (int i = 0; i < numberOfTrials; i++) {
			if (values[i]) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.blue);
			}
			g.drawRect(xValues[i], yValues[i], 1, 1);
		}
	}
}
