package threads;

import testing.Test;

public class SimpleRunnable implements Runnable {

	private int goal;
	
	private SimpleRunnable(int goal) {
		this.goal = goal;
	}
	
	public boolean isDone() {
		return goal <= 0;
	}
	
	@Override
	public void run() {
		while(goal > 0) {
			try {
				Thread.sleep(10);
				goal -= 1;
			} catch (InterruptedException e) {
				System.out.print("Interrupted...");
			}
		}
		System.out.print("Done!");
	}
	
	private static Thread create() {
		return new Thread(new SimpleRunnable(5));
	}
	
	public static void main(String[] args) {
		Test.header("SimpleRunnable");
		
		Test.redirectStdoutToString();
		Thread thread = create();
		thread.start();
		try {
			Thread.sleep(20 * 10);
		} catch (InterruptedException e) {
			Test.fail("Unexpected interrupt");
		}
		Test.restoreStdoutAndTestStdoutEquals("Done!");
		
		Test.header("Interrupt");
		
		Test.assertion(!thread.isInterrupted());
		Test.assertion(!thread.isAlive());
		
		Test.redirectStdoutToString();
		thread = create();
		thread.start();
		thread.interrupt();
		try {
			Thread.sleep(5 * 10);
		} catch (InterruptedException e) {
			Test.fail("Unexpected interrupt");
		}
		Test.restoreStdoutAndTestStdoutEquals("Interrupted...");
		Test.assertion(thread.isAlive());
		
		Test.results();
	}
}
