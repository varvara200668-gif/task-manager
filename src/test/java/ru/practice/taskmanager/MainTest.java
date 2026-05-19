package ru.practice.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {

    @Test
    void mainShouldRunWithoutErrors() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}