package com.epam.sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.epam.utils.Utils;

class SortExecutorTest {

    private final SortExecutor sut = new SortExecutor();

    @Test
    void shouldCompareMergeSortAndInsertionSortWhenArraySizeLarge() {
        int[] array = Utils.generateRandomArrayWithLengthAndAppendGuaranteedElement(1_000_000, 20000, false);
        Utils.measureTime(() -> sut.mergeSort(array), "Merge sort");
        Utils.measureTime(() -> sut.mergeSort(array), "Insertion sort");
    }

    @Test
    void shouldCompareMergeSortAndInsertionSortWhenArraySizeSmall() {
        int[] array = Utils.generateRandomArrayWithLengthAndAppendGuaranteedElement(45, 20000, false);
        Utils.measureTime(() -> sut.mergeSort(array), "Merge sort");
        Utils.measureTime(() -> sut.mergeSort(array), "Insertion sort");
    }

    @Test
    void shouldSortViaMergeSort() {
        int[] array = new int[] {8, 43, 1, 5, 49, 30, 200, 0, 8};
        int[] expected = new int[] {0, 1, 5, 8, 8, 30, 43, 49, 200};

        int[] actual = sut.mergeSort(array);

        assertThat(actual, is(expected));
    }

    @Test
    void shouldSortViaInsertionSort() {
        int[] array = new int[] {8, 43, 1, 5, 49, 30, 200, 0, 8};
        int[] expected = new int[] {0, 1, 5, 8, 8, 30, 43, 49, 200};

        sut.insertionSort(array);

        assertThat(array, is(expected));
    }

}
