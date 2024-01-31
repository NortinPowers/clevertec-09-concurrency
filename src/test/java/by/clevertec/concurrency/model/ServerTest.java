package by.clevertec.concurrency.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ServerTest {

    @Test
    void processRequest() {

    }

    @Test
    public void testServer() throws InterruptedException {
        Server server = new Server();
        int value = 5;
        int expectedSize = 1;

        int actualSize = server.processRequest(value);

        assertEquals(expectedSize, actualSize);
    }
}
