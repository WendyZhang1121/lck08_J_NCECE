package lck08_J_NCECE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Client {
	public void doSomething(File file) {
		final Lock lock = new ReentrantLock(); InputStream in = null;
		try {
			lock.lock();
			in = new FileInputStream(file);
			// Perform operations on the open file
			lock.unlock();
		} catch (FileNotFoundException x) {
			// Handle exception 
			} finally {
				if (in != null) { try {
					in.close();
					} catch (IOException x) {
					// Handle exception
					}
				}
			}
		}
	
	public  void testCase(final File file){
		Thread test = new Thread(new Runnable() {
			public void run() {
				doSomething(file);
				}
			});
			   test.start();
	}
	
	public void main(String[] args) throws IOException { 
		File file1 = new File("a.txt");
		file1.createNewFile();
		File file2 = new File("b.txt");
		file2.createNewFile();
		testCase(file1); // starts thread 1 
		testCase(file2); // starts thread 2
	}
}
