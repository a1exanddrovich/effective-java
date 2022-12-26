package com.epam.tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;

class TreeExecutorTest {

    private final TreeExecutor sut = new TreeExecutor();

    @Test
    void shouldTraverseTree() {
        Tree root = new Tree(2,
                new Tree(1,
                        new Tree(10),
                        new Tree(50)),
                new Tree(18));

        List<Integer> expected = ImmutableList.of(10, 50, 1, 18, 2);

        List<Integer> actual = sut.traverse(root);


        assertThat(actual, is(expected));
    }

}
