package interviewPickings.uber;
//Our server is calling the external API using call_api that is doing the HTTP call.
//The API provider charges us if we make more than 10 calls per second. How would you implement something that keeps us below the limit?

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class interviewWithUberZoom {
    private static final int LIMIT = 10;
    private Vector<Transaction> lastTransactions = new Vector<>();

    public static void main(String args[]) throws Exception {
        new interviewWithUberZoom().balancer();
    }

    private void balancer() throws InterruptedException {
//        Thread thread = new Thread(new Timer());
//        thread.start();
        Random r = new Random(50);
        for (int i = 0; i < 100; i++) {
            receive("Transaction with id : " + i, lastTransactions);
            if (i < 50) {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(500));
            }
        }
    }

    private void receive(String s, Vector lastTransactions) {
        long currentMillisecond = System.currentTimeMillis();

        if (lastTransactions.size() > LIMIT) {
            long difference = currentMillisecond - ((Transaction) lastTransactions.get(lastTransactions.size() - LIMIT)).time;
            System.out.println(difference);
            if (difference < 1000) {
                System.out.println("----- WARNING -----");
                throw new RuntimeException("Limit reached, try again later.");
            }
        }
        System.out.println(s); //sending it
        lastTransactions.add(new Transaction(s, System.currentTimeMillis()));
    }

//    class Timer implements Runnable {
//        public void run() {
//            int currentTick = 0;
//            while (true) {
//                if (currentTick > 5) break;
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("ticker : " + currentTick);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                currentTick++;
//                //trimArrayList should be implemented in case we run long list of course, but we can keep it as a log - lets say
//                //create empty array list while sending an old one to a new Thread that will save the content to a db or log file.
//            }
//        }
//    }

    class Transaction {
        Transaction(String data, long time) {
            this.data = data;
            this.time = time;
        }

        private String data;

        public long getTime() {
            return time;
        }

        private long time;
    }

}