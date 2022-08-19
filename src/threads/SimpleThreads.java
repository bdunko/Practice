package threads;

import testing.Test;
import utility.Util;

public class SimpleThreads {

	private static class SharedData {
		private String data;
		
		public SharedData() {
			this.data = "";
		}
		
		public synchronized boolean hasData() {
			return data.length() != 0;
		}
		
		public synchronized void putData(String data) {
			this.data = data;
		}
		
		public synchronized String getData() {
			String toReturn = data;
			data = "";
			return toReturn;
		}
	}
	
	private static class WriterThread extends Thread {
		
		private SharedData shared;
		
		public WriterThread(SharedData shared) {
			this.shared = shared;
		}
		
		@Override
		public void run() {
			int c = 0;
			while(!isInterrupted()) {
				if(!shared.hasData()) {
					shared.putData(((Integer)c).toString());
					c++;
				}
			}
		}
	}
	
	private static class ReaderThread extends Thread {
		
		private SharedData shared;
		
		public ReaderThread(SharedData shared) {
			this.shared = shared;
		}
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				if(shared.hasData()) {
					System.out.println(shared.getData());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Test.header("SimpleThreads");
		
		//set up threads
		SharedData share = new SharedData();
		Thread writer = new WriterThread(share);
		Thread reader = new ReaderThread(share);
		
		//start both threads
		writer.start();
		reader.start();
		
		//view for awhile...
		Util.sleep(30);
		
		//redirect stdout to collect
		Test.redirectStdoutToString();
		Util.sleep(30);
		
		//interrupt threads
		reader.interrupt();
		writer.interrupt();
		Util.sleep(30);
		
		//verify data was sent
		Test.assertion(Test.restoreStdoutAndGet().length() != 0);
		
		//verify both stopped
		Test.assertion(!reader.isAlive());
		Test.assertion(!writer.isAlive());
		
		Test.results();
	}
}
