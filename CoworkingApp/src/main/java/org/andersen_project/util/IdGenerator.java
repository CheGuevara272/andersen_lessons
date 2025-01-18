package org.andersen_project.util;

import java.util.UUID;

public class IdGenerator {
    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return instance;
    }

    public UUID generateUniqueId() {
        return UUID.randomUUID();
    }
}
