package huffman;

import java.io.EOFException;
import java.io.IOException;
import java.util.Random;
import junit.framework.TestCase;

public abstract class HuffmanCodingTest extends TestCase {

	public void testEmpty() {
		test(new byte[0]);
	}

	public void testOneSymbol() {
		test(new byte[10]);
	}

	public void testSimple() {
		test(new byte[] { 0, 3, 1, 2 });
	}

	public void testEveryByteValue() {
		byte[] b = new byte[256];
		for (int i = 0; i < b.length; i++)
			b[i] = (byte) i;
		test(b);
	}

	public void testFibonacciFrequencies() {
		byte[] b = new byte[87];
		int i = 0;
		for (int j = 0; j < 1; j++, i++)
			b[i] = 0;
		for (int j = 0; j < 2; j++, i++)
			b[i] = 1;
		for (int j = 0; j < 3; j++, i++)
			b[i] = 2;
		for (int j = 0; j < 5; j++, i++)
			b[i] = 3;
		for (int j = 0; j < 8; j++, i++)
			b[i] = 4;
		for (int j = 0; j < 13; j++, i++)
			b[i] = 5;
		for (int j = 0; j < 21; j++, i++)
			b[i] = 6;
		for (int j = 0; j < 34; j++, i++)
			b[i] = 7;
		test(b);
	}

	public void testRandomShort() {
		for (int i = 0; i < 100; i++) {
			byte[] b = new byte[random.nextInt(1000)];
			random.nextBytes(b);
			test(b);
		}
	}

	public void testRandomLong() {
		for (int i = 0; i < 3; i++) {
			byte[] b = new byte[random.nextInt(1000000)];
			random.nextBytes(b);
			test(b);
		}
	}

	private static Random random = new Random();

	private void test(byte[] b) {
		try {
			byte[] compressed = compress(b);
			byte[] decompressed = decompress(compressed);
			assertEquals(b.length, decompressed.length);
		} catch (EOFException e) {
			fail("Unexpected EOF");
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

	protected abstract byte[] compress(byte[] b) throws IOException;

	protected abstract byte[] decompress(byte[] b) throws IOException;

}
