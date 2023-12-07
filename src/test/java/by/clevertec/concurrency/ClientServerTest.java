package by.clevertec.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import by.clevertec.concurrency.model.Client;
import by.clevertec.concurrency.model.Server;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ClientServerTest {

    @ParameterizedTest
    @MethodSource("provideNumRequests")
    public void clientServerInteractionShouldReturnCorrectAccumulator_whenDifferentNumberOfRequest(Integer numRequests) throws InterruptedException, ExecutionException {
        Server server = new Server();
        Client client = new Client(server, numRequests);
        int accumulator = client.getAccumulator();

        int expectedAccumulator = (1 + numRequests) * numRequests / 2;

        assertEquals(expectedAccumulator, accumulator);
        showInfo(accumulator, expectedAccumulator, client);
    }

    private static Stream<Integer> provideNumRequests() {
        return IntStream.rangeClosed(1, 100).boxed();
    }

    private void showInfo(int accumulator, int expectedAccumulator, Client client) {
        System.out.println("accumulator = " + accumulator);
        System.out.println("expectedAccumulator = " + expectedAccumulator);
        System.out.println("Размер списка данных = " + client.getData().size());
    }
}
