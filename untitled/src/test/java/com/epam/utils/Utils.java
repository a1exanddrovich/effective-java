package com.epam.utils;

import lombok.extern.java.Log;

import java.util.Arrays;

@Log
public class Utils {

    public static void measureTime(Runnable task, String taskName) {
        LOG.info("Task " + taskName + " started at " + System.currentTimeMillis());
        task.run();
        LOG.info("Task " + taskName + " finished at " + System.currentTimeMillis());
    }

    public static int[] generateRandomArrayWithLengthAndAppendGuaranteedElement(int length, int guaranteedElement, boolean isSorted) {
        int[] array = new int[length];

        for (int i = 0; i < length - 1; i++) {
            array[i] = (int) Math.round(Math.random() * 10000);
        }

        array[length - 1] = guaranteedElement;

        if (isSorted) {
            Arrays.sort(array);
        }

        return array;
    }

}
