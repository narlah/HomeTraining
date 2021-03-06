package learnConcurency;

import java.util.Vector;

class Producer extends Thread {

    private static final int MAXQUEUE = 5;
    private Vector messages = new Vector();

    @Override
    public void run() {
        try {
            while (true) {
                putMessage();
                Thread.sleep(200);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private synchronized void putMessage() throws InterruptedException {
        while (messages.size() == MAXQUEUE) {
            wait();
        }
        messages.addElement(new java.util.Date().toString());
        System.out.println("put message");
        notify();
        //Later, when the necessary event happens, the thread that is running it calls notify() from a block
        //synchronized on the same object.
    }

    // Called by Consumer
    synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0) {
            wait();//By executing wait() from a synchronized block,
            // a thread gives up its hold on the lock and goes to sleep.
        }
        String message = (String) messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}

class Consumer extends Thread {

    private Producer producer;

    private Consumer(Producer p) {
        producer = p;
    }

    public static void main(String args[]) {
        Producer producer = new Producer();
        producer.start();
        new Consumer(producer).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = producer.getMessage();
                System.out.println("Got message: " + message);
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}