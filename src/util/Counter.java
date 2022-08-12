package util;

public class Counter {

	private int count;

	public Counter() {
		count = 0;
	}

	public Counter(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}

	public void addCount() {
		this.count++;
	}

	@Override
	public String toString() {
		return ""+count;
	}
	
}
