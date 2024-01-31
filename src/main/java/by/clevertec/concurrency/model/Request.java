package by.clevertec.concurrency.model;

import java.util.Random;
import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class Request implements Callable<Integer> {

    private final int value;
    private final Server server;

    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(new Random().nextInt(401) + 100);
        return server.processRequest(value);
    }
}
