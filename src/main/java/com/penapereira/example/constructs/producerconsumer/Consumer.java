package com.penapereira.example.constructs.producerconsumer;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private BlockingQueue<Integer> queue;

	private int myId;

	public Consumer(BlockingQueue<Integer> blockingQueue, int myId) {
		this.queue = blockingQueue;
		this.myId = myId;
	}

	@Override
	public void run() {
		while (true) {
			try {
				var consumed = queue.take();
				log.trace(String.format("  %d: Consumed [%2d]", myId, consumed));
			} catch (InterruptedException finish) {
			}
		}
	}

}
