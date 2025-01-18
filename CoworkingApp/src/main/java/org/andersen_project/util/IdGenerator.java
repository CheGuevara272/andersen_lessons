package org.andersen_project.util;

public class IdGenerator {
    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return instance;
    }

    public String generateUniqueId() {
        long uniqueId = System.currentTimeMillis();
        return Long.toString(uniqueId);
    }
}
