package org.jwolfe.quetzal.test;

import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class QuetzalAssertions {
    public static void assertLinkedListEquals(LinkedListNode expected, LinkedListNode actuals) {
        while(true) {
            if(expected == null
                    && actuals == null) {
                return;
            }

            if(expected == null
                    || actuals == null) {
                fail("Null encountered, expected node.");
            }

            assertEquals(expected.getData(), actuals.getData());
            expected = expected.getNext();
            actuals = actuals.getNext();
        }
    }

    public static void assertLinkedLists(LinkedListNode head, LinkedListNode expected) {
        while(head != null && expected != null) {
            assertEquals(expected.getData(), head.getData());

            head = head.getNext();
            expected = expected.getNext();
        }

        if(head != null) {
            fail("expected ran to completion; head still left.");
        }

        if(expected != null) {
            fail("head ran to completion; expected still left.");
        }
    }

    public static void assertStackEquals(Stack<Integer> expected, Stack<Integer> actual) {
        if(expected == null && actual == null) {
            return;
        }

        if(expected == null || actual == null) {
            fail("Expected or actual is null.");
        }

        if(expected.size() != actual.size()) {
            fail("Expected and actual have different sizes.");
        }

        Stack<Integer> expectedClone = (Stack<Integer>) expected.clone();
        Stack<Integer> actualClone = (Stack<Integer>) actual.clone();

        while(!expectedClone.isEmpty()) {
            int e = expectedClone.pop();
            int a = actualClone.pop();

            assertEquals(e, a);
        }
    }

    public static <T> void assertSetEquals(Set<T> set1, Set<T> set2) {
        assertEquals(set1.size(), set2.size());
        for(var item : set1) {
            assertTrue(set2.contains(item));
        }
    }

    public static <T> void assertQueueEquals(Queue<T> expectedQueue, Queue<T> queue) {
        assertEquals(expectedQueue.size(), queue.size());

        var iter1 = expectedQueue.iterator();
        var iter2 = queue.iterator();

        while(iter1.hasNext()) {
            T item1 = iter1.next();
            T item2 = iter2.next();

            assertEquals(item1, item2);
        }
    }

    public static void assertTwoDimensionalArrayEquals(int[][] arr1, int[][] arr2) {
        if(arr1 == null && arr2 == null) {
            return;
        }

        assertNotNull(arr1);
        assertNotNull(arr2);

        assertEquals(arr1.length, arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            assertArrayEquals(arr1[i], arr2[i]);
        }
    }
}
