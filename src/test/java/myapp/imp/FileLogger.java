package myapp.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import myapp.services.ILogger;

public class FileLogger implements ILogger {

	// paramètre : the writer
	private final PrintWriter writer;
	
	public FileLogger(String fileName) {
		try {
			this.writer = new PrintWriter(new FileOutputStream(fileName, true));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("bad fileName");
		}
	}
	
	public void start() {
		System.err.println("Start " + this);
	}
	
	public void stop() {
		writer.close();
		System.err.println("Stop " + this);
	}
	
	@Override
	public void log(String message) {
		writer.printf("%tF %1$tR | %s\n", new Date(), message);
	}
}
