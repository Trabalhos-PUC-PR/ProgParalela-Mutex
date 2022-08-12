package util;

import java.util.concurrent.Semaphore;

public class OddGenerator extends Thread {

	private int array[];
	private Semaphore gate;
	private Counter count;
	private int OddCount;
	private int ID;

	private int max;
	private int min;
	private int maxSleep;
	private int minSleep;

	public OddGenerator(int ID, int array[], Semaphore gate, Counter count) {
		this.array = array;
		this.gate = gate;
		this.count = count;
		this.OddCount = 0;
		max = 9;
		min = 0;
		maxSleep = 300;
		minSleep = 100;
		this.ID = ID;
	}

	public int getOddCount() {
		return this.OddCount;
	}

	public void run() {
		try {
			while (count.getCount() != 100) {
				gate.acquire();
				if (count.getCount() == 100) {
					gate.release();
					System.out.printf("%d Exiting OddeGen (Count=%s)\n",ID , count);
					return;
				}
				int randomOdd = (int) ((Math.random() * (max - min)) + min);
				do {
					randomOdd = (int) ((Math.random() * (max - min)) + min);
				} while (randomOdd % 2 == 0);
				array[count.getCount()] = randomOdd;
				count.addCount();
				try {
					int sleepTime = (int) ((Math.random() * (maxSleep - minSleep)) + minSleep);
					Thread.sleep(sleepTime);
				} catch (Exception e) {
				}
				gate.release();
			}
			System.out.printf("%d Exiting OddGen (Count=%s)\n",ID, count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
