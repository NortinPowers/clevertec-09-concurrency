package by.clevertec.concurrency;

import by.clevertec.concurrency.model.Client;
import by.clevertec.concurrency.model.Server;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numRequests = 100;
        Server server = new Server();
        Client client = new Client(server, numRequests);
        int accumulator = client.getAccumulator();
        int expectedAccumulator = (1 + numRequests) * numRequests / 2;
        if (accumulator == expectedAccumulator) {
            System.out.println("Тест пройден!");
            showInfo(accumulator, expectedAccumulator, client);
        } else {
            System.out.println("Тест провален!");
            showInfo(accumulator, expectedAccumulator, client);
        }
    }

    /**
     * Выводит информацию о результатах взаимодействия клиента и сервера.
     *
     * @param accumulator         Полученное значение аккумулятора от клиента.
     * @param expectedAccumulator Ожидаемое значение аккумулятора, рассчитанное на основе количества запросов.
     * @param client              Объект клиента, содержащий информацию о размере списка данных.
     */
    private static void showInfo(int accumulator, int expectedAccumulator, Client client) {
        System.out.println("accumulator = " + accumulator);
        System.out.println("expectedAccumulator = " + expectedAccumulator);
        System.out.println("Размер списка данных = " + client.getData().size());
    }
}
