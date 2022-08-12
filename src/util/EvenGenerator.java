package util;

import java.util.concurrent.Semaphore;

public class EvenGenerator extends Thread {

	private int array[];
	private Semaphore gate;
	private Counter count;
	private int EvenCount;
	private int ID;

	private int max;
	private int min;
	private int maxSleep;
	private int minSleep;

	public EvenGenerator(int ID, int array[], Semaphore gate, Counter count) {
		this.array = array;
		this.gate = gate;
		this.count = count;
		this.EvenCount = 0;
		max = 9;
		min = 0;
		maxSleep = 300;
		minSleep = 100;
		this.ID = ID;
	}

	public int getEvenCount() {
		return this.EvenCount;
	}

	public void run() {
		try {
			while (count.getCount() != 100) {
				gate.acquire();
				if (count.getCount() == 100) {
					gate.release();
					System.out.printf("%d Exiting EveneGen (Count=%s)\n", ID, count);
					return;
				}
				int randomEven = (int) ((Math.random() * (max - min)) + min);
				do {
					randomEven = (int) ((Math.random() * (max - min)) + min);
				} while (randomEven % 2 != 0);
				array[count.getCount()] = randomEven;
				count.addCount();
				try {
					int sleepTime = (int) ((Math.random() * (maxSleep - minSleep)) + minSleep);
					Thread.sleep(sleepTime);
				} catch (Exception e) {
				}
				gate.release();
			}
			System.out.printf("%d Exiting EvenGen (Count=%s)\n",ID, count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
