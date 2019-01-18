package org.jwolfe.quetzal.test;

import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.list.AuxiliaryLinkedListNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class QuetzalAssertions {
    public static void assertLinkedListEquals(LinkedListNode expected, LinkedListNode actuals) {
        while (true) {
            if (expected == null
                    && actuals == null) {
                return;
            }

            if (expected == null
                    || actuals == null) {
                fail("Null encountered, expected node.");
            }

            assertEquals(expected.getData(), actuals.getData());
            expected = expected.getNext();
            actuals = actuals.getNext();
        }
    }

    public static void assertLinkedLists(LinkedListNode head, LinkedListNode expected) {
        while (head != null && expected != null) {
            assertEquals(expected.getData(), head.getData());

            head = head.getNext();
            expected = expected.getNext();
        }

        if (head != null) {
            fail("expected ran to completion; head still left.");
        }

        if (expected != null) {
            fail("head ran to completion; expected still left.");
        }
    }

    public static void assertAuxiliaryLinkedListEquals(AuxiliaryLinkedListNode expected, AuxiliaryLinkedListNode actuals) {
        while (expected != null && actuals != null) {
            assertEquals(expected.getData(), actuals.getData());
            assertEquals(expected.getAuxiliary().getData(), actuals.getAuxiliary().getData());

            expected = expected.getNext();
            actuals = actuals.getNext();
        }

        if (expected != null) {
            fail("actuals ran to completion; expected still left.");
        }

        if (actuals != null) {
            fail("expected ran to completion; actuals still left.");
        }
    }

    public static void assertStackEquals(Stack<Integer> expected, Stack<Integer> actual) {
        if (expected == null && actual == null) {
            return;
        }

        if (expected == null || actual == null) {
            fail("Expected or actual is null.");
        }

        if (expected.size() != actual.size()) {
            fail("Expected and actual have different sizes.");
        }

        Stack<Integer> expectedClone = (Stack<Integer>) expected.clone();
        Stack<Integer> actualClone = (Stack<Integer>) actual.clone();

        while (!expectedClone.isEmpty()) {
            int e = expectedClone.pop();
            int a = actualClone.pop();

            assertEquals(e, a);
        }
    }

    public static <T> void assertSetEquals(Set<T> expected, Set<T> actual) {
        if (expected == null) {
            assertNull(actual);
        }

        assertNotNull(actual);

        assertEquals(expected.size(), actual.size());
        for (var item : expected) {
            assertTrue(actual.contains(item));
        }
    }

    public static <T> void assertQueueEquals(Queue<T> expected, Queue<T> actual) {
        if (expected == null) {
            assertNull(actual);
        }

        assertNotNull(actual);

        assertEquals(expected.size(), actual.size());

        var iter1 = expected.iterator();
        var iter2 = actual.iterator();

        while (iter1.hasNext()) {
            T item1 = iter1.next();
            T item2 = iter2.next();

            assertEquals(item1, item2);
        }
    }

    public static void assertTwoDimensionalArrayEquals(int[][] arr1, int[][] arr2) {
        if (arr1 == null && arr2 == null) {
            return;
        }

        assertNotNull(arr1);
        assertNotNull(arr2);

        assertEquals(arr1.length, arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            assertArrayEquals(arr1[i], arr2[i]);
        }
    }

    public static void assertTwoDimensionalArrayEquals(char[][] arr1, char[][] arr2) {
        if (arr1 == null && arr2 == null) {
            return;
        }

        assertNotNull(arr1);
        assertNotNull(arr2);

        assertEquals(arr1.length, arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            assertArrayEquals(arr1[i], arr2[i]);
        }
    }

    public static <T> void assertListEquals(List<T> expected, List<T> actual) {
        if (expected == null) {
            assertNull(actual);
        }

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (var item : expected) {
            assertTrue(actual.contains(item));
        }
    }

    public static <T> void assertListStrictEquals(List<T> expected, List<T> actual) {
        if (expected == null) {
            assertNull(actual);
        }

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    public static <T> void assertListOfListEquals(List<List<T>> expected, List<List<T>> actual) {
        if (expected == null) {
            assertNull(actual);
        }

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertListEquals(expected.get(i), actual.get(i));
        }
    }

    public static <T> void assertListOfListStrictlyContainsList(List<List<T>> expectedInList, List<T> actual) {
        assertNotNull(expectedInList);
        assertNotNull(actual);

        boolean foundInList = false;
        for(var list : expectedInList) {
            if(list.size() != actual.size()) {
                continue;
            }

            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).equals(actual.get(i))) {
                    continue;
                }
            }

            foundInList = true;
            break;
        }

        if(!foundInList) {
            fail("actual not found in expected list");
        }
    }

    public static <K, V> void assertMapEquals(Map<K, V> expected, Map<K, V> actual) {
        assertEquals(expected.size(), actual.size());
        for (var key : expected.keySet()) {
            assertTrue(actual.containsKey(key));
            assertEquals(expected.get(key), actual.get(key));
        }
    }
}
