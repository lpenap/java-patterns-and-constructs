package com.penapereira.example.constructs.producerconsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue<Integer> queue;

	public Producer(BlockingQueue<Integer> blockingQueue) {
		this.queue = blockingQueue;
	}

	@Override
	public void run() {
		for (var i = 1; i < 4; i++) {
			try {
				queue.put(i);
			} catch (InterruptedException finish) {
			}
		}
	}
}
