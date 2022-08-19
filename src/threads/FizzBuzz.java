package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import testing.Test;

//Write a multithreaded fizzbuzz, where one thread does fizz, another buzz, another fizzbuzz, and another the numbers
//Also the output should be in order like a normal fizzbuzz
public class FizzBuzz {

	private static class Shared {
		private int goal;
		private int n;
		private Lock lock;
		private Condition next;
		
		public Shared(int goal) {
			this.goal = goal;
			this.n = 1;
			this.lock = new ReentrantLock();
			this.next = lock.newCondition();
		}
	}
	
	private static class FizzBuzzThread extends Thread {
		
		public enum Type {
			FIZZ, BUZZ, FIZZBUZZ, NUMBER
		}
		
		private Type type;
		protected Shared shared;
		protected boolean done;
		
		public FizzBuzzThread(Shared shared, Type type) {
			this.shared = shared;
			this.done = false;
			this.type = type;
		}

		@Override
		public void run() {
			while(!done) {
				try {
					shared.lock.lock();
					if(shared.n > shared.goal) {
						done = true; //exit condition
					}
					else  {
						boolean divBy3 = shared.n % 3 == 0;
						boolean divBy5 = shared.n % 5 == 0;
						boolean success = false;
								
						if(type == Type.FIZZBUZZ && divBy3 && divBy5) {
							System.out.print("FizzBuzz");
							success = true;
						} else if (type == Type.FIZZ && divBy3 && !divBy5) {
							System.out.print("Fizz");
							success = true;
						} else if (type == Type.BUZZ && divBy5 && !divBy3) {
							System.out.print("Buzz");
							success = true;
						} else if (type == Type.NUMBER && !divBy3 && !divBy5) {
							System.out.print(shared.n);
							success = true;
						}
						
						if(success) {
							shared.n++;
							shared.next.signalAll();
						} else {
							int previous = shared.n;
							while(previous == shared.n) //in case of spurious wakeup
								shared.next.await();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					shared.lock.unlock();
				}
			}
		}
		
	}
	
	private static void fizzbuzz(int n) {
		for(int i = 1; i <= n; i++) {
			if(i % 5 == 0 && i % 3 == 0)
				System.out.print("FizzBuzz");
			else if(i % 3 == 0)
				System.out.print("Fizz");
			else if(i % 5 == 0)
				System.out.print("Buzz");
			else
				System.out.print(i);
		}
	}
	
	public static void main(String[] args) {
		Test.header("FizzBuzz");
		
		//standard version
		Test.redirectStdoutToString();
		fizzbuzz(16);
		Test.restoreStdoutAndTestStdoutEquals("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz16");
		
		//multithreaded version
		
		//create shared lock/condition and data
		Shared shared = new Shared(16);
		
		//create the 4 threads
		Thread[] threads = { 
				new FizzBuzzThread(shared, FizzBuzzThread.Type.FIZZ),
				new FizzBuzzThread(shared, FizzBuzzThread.Type.BUZZ),
				new FizzBuzzThread(shared, FizzBuzzThread.Type.FIZZBUZZ),
				new FizzBuzzThread(shared, FizzBuzzThread.Type.NUMBER)
				};
		
		Test.redirectStdoutToString();
		
		for(Thread t : threads) 
			t.start();
		
		boolean done = false;
		while(!done) {
			done = true;
			for(Thread t : threads)
				if(t.isAlive())
					done = false;
		}
		
		Test.restoreStdoutAndTestStdoutEquals("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz16");
		
		Test.results();
	}
}
