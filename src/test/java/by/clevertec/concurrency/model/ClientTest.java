package by.clevertec.concurrency.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    public void testClientServer() throws InterruptedException, ExecutionException {
        int numRequests = 10;
        Server server = new Server();
        Client client = new Client(server, numRequests);

        int accumulator = client.getAccumulator();

        int expectedAccumulator = (1 + numRequests) * (numRequests / 2);

        assertEquals(expectedAccumulator, accumulator);
    }
}
