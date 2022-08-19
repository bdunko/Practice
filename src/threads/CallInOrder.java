package threads;

import java.util.concurrent.Semaphore;

import testing.Test;

//Using three threads started simultaneously, print out first second third in order
public class CallInOrder {

	public static class Order {
		private Semaphore forSecond, forThird, done;
		
		public Order() {
			forSecond = new Semaphore(1);
			forSecond.acquireUninterruptibly();
			forThird = new Semaphore(1);
			forThird.acquireUninterruptibly();
			done = new Semaphore(1);
			done.acquireUninterruptibly();
		}
		
		public void first() {
			System.out.print("first");
			forSecond.release();
		}
		
		public void second() {
			forSecond.acquireUninterruptibly();
			System.out.print("second");
			forThird.release();
		}
		
		public void third() {
			forThird.acquireUninterruptibly();
			System.out.print("third");
			done.release();
		}
		
		public void waitUntilDone() {
			done.acquireUninterruptibly();
		}
	}
	
	public static class OrderThread extends Thread {
		private int orderFunction;
		private Order order;
		
		public OrderThread(Order order, int orderFunction) {
			this.order = order;
			this.orderFunction = orderFunction;
		}
		
		@Override
		public void run() {
			switch(orderFunction) {
				case 1:
					order.first();
					break;
				case 2:
					order.second();
					break;
				case 3:
				default:
					order.third();
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		Test.header("CallInOrder");
		Order order = new Order();
		Thread first = new OrderThread(order, 1);
		Thread second = new OrderThread(order, 2);
		Thread third = new OrderThread(order, 3);
		
		Test.redirectStdoutToString();
		
		third.start();
		first.start();
		second.start();
		
		order.waitUntilDone();
		
		Test.restoreStdoutAndTestStdoutEquals("firstsecondthird");
		
		Test.results();
	}
}
