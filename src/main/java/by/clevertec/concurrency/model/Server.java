package by.clevertec.concurrency.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private final List<Integer> list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    /**
     * Обрабатывает запрос, добавляя значение в общий ресурс (список данных).
     * Метод имеет случайную задержку перед обработкой запроса и использует блокировку для обеспечения потокобезопасности.
     *
     * @param value Значение запроса, которое будет добавлено в общий ресурс.
     * @return Размер списка данных после добавления значения.
     * @throws InterruptedException если текущий поток был прерван во время ожидания задержки или во время попытки получить блокировку.
     */
    public int processRequest(int value) throws InterruptedException {
        Thread.sleep(new Random().nextInt(901) + 100);
        lock.lock();
        try {
            list.add(value);
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
