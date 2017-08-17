package archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public final class GZipStreamCompresor extends Compressor {
	private final static int BUFFER_SIZE = 2048;

	public GZipStreamCompresor(String inFile, String outFile) {
		super(inFile, outFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encode() throws IOException {

		try {
			InputStream fis = new FileInputStream(new File(inFile));
			GZIPOutputStream gzip = new GZIPOutputStream(new FileOutputStream(new File(outFile)));

			byte[] b = new byte[BUFFER_SIZE];
			int count;
			while ((count = fis.read(b)) > 0) {
				gzip.write(b, 0, count);
			}
			fis.close();
			gzip.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return inFile + " -> " + "GZIP Stream Compressor" + " -> " + outFile;
	}

	@Override
	public HashMap<?, ?> getDataStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}