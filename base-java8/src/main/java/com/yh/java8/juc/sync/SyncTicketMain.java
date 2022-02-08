package com.yh.java8.juc.sync;

public class SyncTicketMain {

    private static int THREAD_SIZE = 3;

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 0; i < THREAD_SIZE; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    ticket.sellTicketSync();
                }
            }, "sell-ticket-" + i);
            thread.start();
        }
    }

}
