package app;

import java.util.concurrent.Semaphore;

import util.Counter;
import util.EvenGenerator;
import util.OddGenerator;

public class Main {

	public static void main(String[] args) {

		Counter count = new Counter();
		Semaphore oddGate = new Semaphore(1);
		Semaphore evenGate = new Semaphore(1);
		int chave[] = new int[100];

		OddGenerator odds1 = new OddGenerator(1, chave, oddGate, count);
		OddGenerator odds2 = new OddGenerator(2, chave, oddGate, count);
		EvenGenerator even1 = new EvenGenerator(3, chave, evenGate, count);
		EvenGenerator even2 = new EvenGenerator(4, chave, evenGate, count);

		odds1.start();
		odds2.start();
		even1.start();
		even2.start();

		try {
			odds1.join();
			odds2.join();
			even1.join();
			even2.join();
		} catch (Exception e) {
		}

		for (int i : chave) {
			System.out.println(i);
		}

	}

}
