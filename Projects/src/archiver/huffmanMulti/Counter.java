package archiver.huffmanMulti;

public class Counter extends Thread {
	final public long[] frequenceTable = new long[256];
	final byte[] buffer;
	private static int id = 0;

	public Counter(byte[] buffer) {
		this.buffer = buffer;
		setName("Counter " + (id++));
	}

	public void run() {
		for (byte letter : buffer) {
			int letterValue = letter & 0x00FF;
			frequenceTable[letterValue]++;
		}
	}
}
