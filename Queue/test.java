package Queue;

import java.util.Random;

public class test {

	
	public static double testQueue(Queue<Integer> q, int count) {
		
		long startTime = System.nanoTime();
		Random random = new Random();
		
		for(int i = 0; i < count; i ++)
			q.enQueue(random.nextInt(Integer.MAX_VALUE));
		
		for(int i = 0 ; i < count ; i ++)
            q.deQueue();
		
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 100000;
//		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//		double time1 = test.testQueue(arrayQueue, count);
//		System.out.println("ArrayQueue time: " +time1+ "s");
		
		LoopQueue<Integer> loopQueue = new LoopQueue<>();
		double time2 = test.testQueue(loopQueue, count);
		System.out.println("LoopQueue time: " +time2+ "s");
		
		LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
		double time3 = test.testQueue(listQueue, count);
		System.out.println("LinkedListQueue time: " +time3+ "s");
	}

}
