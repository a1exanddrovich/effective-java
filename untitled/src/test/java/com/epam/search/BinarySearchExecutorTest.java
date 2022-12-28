package com.epam.search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.sort.SortExecutor;
import com.epam.utils.Utils;

@ExtendWith(MockitoExtension.class)
class BinarySearchExecutorTest {

    @Spy
    private SortExecutor sortExecutor;

    @InjectMocks
    private BinarySearchExecutor sut;

    @Test
    void shouldCompareBinarySortRecursivelyAndNonRecursively() {
        int[] array = Utils.generateRandomArrayWithLengthAndAppendGuaranteedElement(1_000_000, 20000, true);
        Utils.measureTime(() -> sut.searchRecursively(array, 20000, 0, array.length - 1), "Recursive binary search");
        Utils.measureTime(() -> sut.searchNonRecursively(array, 20000), "Non recursive binary search");
    }

    @Test
    void shouldSearchElementRecursively() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 9};

        int actual = sut.searchRecursively(array, 6, 0, array.length - 1);

        assertThat(actual, is(5));
    }

    @Test
    void shouldSearchElementNonRecursively() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 9};

        int actual = sut.searchNonRecursively(array, 6);

        assertThat(actual, is(5));
    }

}
