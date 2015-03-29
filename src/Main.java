import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {

	private JFrame frame = new JFrame();
	private Thread thread;
	private Display display;
	private Config config;

	private boolean running;
	private final int WIDTH = 506;
	private final int HEIGHT = 580;

	public static int numberOfTrials = 1;
	public static double counter = 1;

	public static boolean simulating;

	private static double runningSum;
	public static double pi;

	private Random r;

	public static void main(String args[]) {
		new Main().start();
	}

	public Main() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Monte Carlo Pi Simulation");
		frame.setVisible(true);
		display = new Display(WIDTH, HEIGHT);
		config = new Config();
		r = new Random();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double tps = 1000000000D / 30;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			boolean shouldRender = false;
			delta += (now - lastTime) / tps;
			lastTime = now;
			while (delta >= 1) {
				ticks++;
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender) {
				frames++;
				//
				// update();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(frames + ":" + ticks);
				frames = 0;
				ticks = 0;
			}
			render();
			runAfap();
		}
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
	}

	public void reset() {

	}

	public static void setSimulationParams(int numberOfTrials, boolean simulating) {
		Main.numberOfTrials = numberOfTrials;
		Main.simulating = simulating;
		Main.counter = 0;
		runningSum = 0;
		Display.setParams(numberOfTrials);
	}

	public void runAfap() {
		if (simulating) {
			if (counter - 1 < numberOfTrials) {
				runningSum += trial();
				System.out.println("Counter: " + counter);
			} else {
				simulating = false;
				Config.bSimulate.setText("Run Simulation");
			}
			counter++;
			pi = 4 * runningSum / counter;
			System.out.println("Pi=" + pi);
		}
	}

	public void update() {
		if (simulating) {
			if (counter - 1 < numberOfTrials) {
				runningSum += trial();
				System.out.println("Counter: " + counter);
			} else {
				simulating = false;
				Config.bSimulate.setText("Run Simulation");
			}
			counter++;
			pi = 4 * runningSum / counter;
			System.out.println("Pi=" + pi);
		}
	}

	public int trial() {
		double x = r.nextDouble();
		double y = r.nextDouble();
		double h = Math.sqrt((x * x) + (y * y));
		if (h < 1) {
			display.plot((int) counter, x, y, true);
			return 1;
		} else {
			display.plot((int) counter, x, y, false);
			return 0;
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		{
			display.paint(g);
		}
		g.dispose();
		bs.show();
	}
}
