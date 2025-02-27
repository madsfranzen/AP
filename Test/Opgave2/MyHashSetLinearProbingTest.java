import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Opgave2.MyHashSetLinearProbing;

public class MyHashSetLinearProbingTest {
    private MyHashSetLinearProbing<String> set;

    @BeforeEach
    void setUp() {
        set = new MyHashSetLinearProbing<>(10);
    }

    @Test
    void testAddAndContains() {
        assertTrue(set.add("Aa"));
        assertTrue(set.contains("Aa"));
        assertTrue(set.add("BB")); // same hashcode as "Aa"
        assertTrue(set.contains("BB"));
        assertFalse(set.add("Aa")); // duplicate
        assertEquals(2, set.size());
    }

    @Test
    void testRemove() {
        set.add("Aa");
        set.add("BB"); // collision
        assertTrue(set.remove("Aa"));
        assertFalse(set.contains("Aa"));
        assertTrue(set.contains("BB")); // BB should still be there
        assertEquals(1, set.size());
        assertFalse(set.remove("Aa")); // already removed
    }

    @Test
    void testClear() {
        set.add("Aa");
        set.add("BB");
        set.clear();
        assertEquals(0, set.size());
        assertFalse(set.contains("Aa"));
        assertFalse(set.contains("BB"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(set.isEmpty());
        set.add("Aa");
        set.add("BB");
        assertFalse(set.isEmpty());
        set.remove("Aa");
        assertFalse(set.isEmpty());
        set.remove("BB");
        assertTrue(set.isEmpty());
    }

    @Test
    void testMultipleOperations() {
        assertTrue(set.add("Aa"));
        assertTrue(set.add("BB"));
        assertTrue(set.add("CC")); // different hashcode
        assertEquals(3, set.size());
        assertTrue(set.remove("BB"));
        assertEquals(2, set.size());
        assertTrue(set.contains("Aa"));
        assertTrue(set.contains("CC"));
        assertFalse(set.contains("BB"));
        assertTrue(set.add("BB")); // re-add after remove
        assertTrue(set.contains("BB"));
    }

    @Test
    void testTableFull() {
        // Fill the table completely
        for (int i = 0; i < 10; i++) {
            assertTrue(set.add("Aa" + i));
        }
        assertEquals(10, set.size());
        // Should fail to add when full
        assertFalse(set.add("overflow"));
    }

    @Test
    void testManyCollisions() {
        // These all have same hashcode
        assertTrue(set.add("Aa"));
        assertTrue(set.add("BB"));
        assertTrue(set.add("CC")); // Different hashcode
        assertTrue(set.remove("BB")); // Remove from middle
        assertTrue(set.add("DD")); // Should reuse deleted spot
        assertTrue(set.contains("Aa"));
        assertTrue(set.contains("DD"));
        assertTrue(set.contains("CC"));
    }

    @Test
    void testSameHashCodeDifferentObjects() {
        // "FB" has same hashcode as "Aa"
        assertTrue(set.add("Aa"));
        assertTrue(set.add("FB"));
        assertEquals(2, set.size());
        assertTrue(set.remove("Aa"));
        assertTrue(set.contains("FB"));
        assertFalse(set.contains("Aa"));
    }

    @Test
    void testDeletedSpots() {
        assertTrue(set.add("Aa"));
        assertTrue(set.add("BB"));
        assertTrue(set.add("CC"));
        assertTrue(set.remove("BB")); // Create a DELETED spot
        assertTrue(set.add("DD")); // Should use the deleted spot
        assertTrue(set.contains("Aa"));
        assertTrue(set.contains("CC"));
        assertTrue(set.contains("DD"));
        assertEquals(3, set.size());
    }

    @Test
    void testWraparound() {
        // Fill most of table to force wraparound
        for (int i = 0; i < 8; i++) {
            set.add("Element" + i);
        }
        assertTrue(set.add("Aa")); // These two have same hashcode
        assertTrue(set.add("BB")); // Should handle wraparound
        assertEquals(10, set.size());
    }
} 