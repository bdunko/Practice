package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import testing.Test;

public class Locks {
	
	private static class SharedResource {
		private Lock lock;
		private Queue<Integer> numbers;
		
		public SharedResource() {
			lock = new ReentrantLock();
			numbers = new LinkedList<Integer>();
		}
		
		public void add(int n) {
			lock.lock();
			numbers.add(n);
			lock.unlock();
		}
		
		public int consume() {
			int n = numbers.poll();
			return n;
		}
		
		public boolean tryAcquire() {
			lock.lock();
			if(numbers.size() != 0)
				return true;
			lock.unlock();
			return false;
		}
		
		public void release() {
			lock.unlock();
		}
		
		public boolean isEmpty() {
			lock.lock();
			boolean empty = numbers.size() == 0;
			lock.unlock();
			return empty;
		}
	}
	
	private static class EaterThread extends Thread {
		
		private volatile int score;
		private SharedResource shared;
		
		public EaterThread(SharedResource r) {
			this.score = 0;
			this.shared = r;
		}
		
		public int score() {
			return score;
		}
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				if(shared.tryAcquire()) {
					shared.consume();
					score++;
					shared.release();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		EaterThread eater1 = new EaterThread(resource);
		EaterThread eater2 = new EaterThread(resource);
		EaterThread eater3 = new EaterThread(resource);
		
		eater1.start();
		eater2.start();
		eater3.start();
		
		//verify scores are 0
		Test.equals(eater1.score(), 0);
		Test.equals(eater2.score(), 0);
		Test.equals(eater3.score(), 0);
		
		//feed in some data
		for(int i = 0; i < 10000; i++)
			resource.add(i);
		while(!resource.isEmpty());
		
		//verify scores are nonzero and sum to 10,000
		int score1 = eater1.score();
		int score2 = eater2.score();
		int score3 = eater3.score();
		
		Test.assertion(score1 != 0);
		Test.assertion(score2 != 0);
		Test.assertion(score3 != 0);
		Test.equals(score1 + score2 + score3, 10000);
		
		//feed in more data
		for(int i = 0; i < 100000; i++)
			resource.add(i);
		while(!resource.isEmpty());
		
		//verify scores have increased and sum to 110,000
		score1 = eater1.score();
		score2 = eater2.score();
		score3 = eater3.score();
		
		Test.assertion(score1 != 0);
		Test.assertion(score2 != 0);
		Test.assertion(score3 != 0);
		Test.equals(score1 + score2 + score3, 110000);
		
		//kill the threads
		eater1.interrupt();
		eater2.interrupt();
		eater3.interrupt();
		
		Test.results();
	}
	
}
