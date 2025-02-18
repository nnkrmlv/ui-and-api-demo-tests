package api;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public static String email = "eve.holt@reqres.in";

    public static String generateRandomString() {
        int stringSize = ThreadLocalRandom.current().nextInt(3, 10);
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, stringSize);
    }
}
