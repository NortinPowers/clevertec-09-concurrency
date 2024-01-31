package by.clevertec.concurrency.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Client {

    @Getter
    private final List<Integer> data;
    private final ExecutorService executorService;
    private final List<Future<Integer>> futures;

    /**
     * Конструктор класса Client.
     *
     * @param server      Сервер, к которому будут отправляться запросы.
     * @param numRequests Количество запросов, которые будут отправлены на сервер.
     *                    Определяет исходный размер списка данных и количество потоков для выполнения запросов.
     */
    public Client(Server server, int numRequests) {
        data = new ArrayList<>();
        for (int i = 1; i <= numRequests; i++) {
            data.add(i);
        }
        int numThreads = data.size();
        executorService = Executors.newFixedThreadPool(numThreads);
        futures = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numThreads; i++) {
            int index = random.nextInt(data.size());
            int value = data.remove(index);
            Callable<Integer> callable = new Request(value, server);
            Future<Integer> future = executorService.submit(callable);
            futures.add(future);
        }
    }

    /**
     * Получает аккумулятор, суммируя результаты выполнения запросов от сервера.
     * Метод останавливает executorService, ожидает завершения выполнения всех задач и собирает результаты.
     *
     * @return Сумма значений, полученных от выполненных задач.
     * @throws InterruptedException если текущий поток был прерван во время ожидания завершения задач.
     * @throws ExecutionException   если одна из задач в executorService завершается с исключением.
     */
    public int getAccumulator() throws InterruptedException, ExecutionException {
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        int accumulator = 0;
        for (Future<Integer> future : futures) {
            accumulator += future.get();
        }
        return accumulator;
    }
}
