package com.yh.java8.juc.lock;

public class LTicketMain {

    private static int THREAD_SIZE = 3;

    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        for (int i = 0; i < THREAD_SIZE; i++) {
            new Thread(() -> {
                for (int j = 0; j < 15; j++) {
                    ticket.sellTicketLock();
                }
            }, "sell-lticket-" + i).start();
        }
    }

}
