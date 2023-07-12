package com.eworkgroup.automationdb;

public enum Result {
    SUCCESS, FAILURE, SKIPPED, PARTIAL;

    public static Result fromInt(int result) {
        return switch (result) {
            case 1 -> SUCCESS;
            case 2 -> FAILURE;
            case 3 -> SKIPPED;
            case 4 -> PARTIAL;
            default -> throw new IllegalArgumentException("Invalid result value: " + result);
        };
    }
}
