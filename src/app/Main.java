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

		System.out.printf("Entering while at count: %s\n",count);
		while(count.getCount()<100) {
//			System.out.println(count);
		}
		System.out.println("Exiting while");
		
		for (int i : chave) {
			System.out.println(i);
		}

	}

}
