package com.aidl.myutils.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 以下是一个简单的 Java 先进先出 (FIFO) 消息队列的例子：
 */
public class MessageQueue {
    private Queue<String> queue;

    public MessageQueue() {
        queue = new LinkedList<>();
    }

    public synchronized void sendMessage(String message) {
        queue.offer(message);
        notifyAll();
    }

    public synchronized String receiveMessage() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        // Sender thread sends messages to the queue
        Thread senderThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                String message = "Message " + i;
                messageQueue.sendMessage(message);
                System.out.println("Sent: " + message);
                try {
                    Thread.sleep(1000); // Wait for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Receiver thread receives messages from the queue
        Thread receiverThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String message = messageQueue.receiveMessage();
                    System.out.println("Received: " + message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        senderThread.start();
        receiverThread.start();
    }
}

