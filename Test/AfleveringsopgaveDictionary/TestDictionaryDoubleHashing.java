import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDictionaryDoubleHashing {
    private DictionaryDoubleHashing<String, Integer> dictionary;
    private final int INITIAL_CAPACITY = 11;
    
    @BeforeEach
    public void setUp() {
        dictionary = new DictionaryDoubleHashing<>(INITIAL_CAPACITY);
    }
    
    @Test
    public void testNewDictionaryIsEmpty() {
        assertTrue(dictionary.isEmpty());
        assertEquals(0, dictionary.size());
    }
    
    @Test
    public void testPutAndGet() {
        // Test basic put and get
        assertNull(dictionary.put("one", 1));
        assertEquals(1, dictionary.get("one"));
        assertEquals(1, dictionary.size());
        
        // Test put with existing key
        assertEquals(1, dictionary.put("one", 11));
        assertEquals(11, dictionary.get("one"));
        assertEquals(1, dictionary.size());
        
        // Test multiple entries
        assertNull(dictionary.put("two", 2));
        assertNull(dictionary.put("three", 3));
        assertEquals(3, dictionary.size());
        assertEquals(11, dictionary.get("one"));
        assertEquals(2, dictionary.get("two"));
        assertEquals(3, dictionary.get("three"));
    }
    
    @Test
    public void testGetNonExistentKey() {
        assertNull(dictionary.get("nonexistent"));
        
        dictionary.put("key", 42);
        assertNull(dictionary.get("nonexistent"));
    }
    
    @Test
    public void testRemove() {
        // Test remove on empty dictionary
        assertNull(dictionary.remove("one"));
        
        // Test basic remove
        dictionary.put("one", 1);
        assertEquals(1, dictionary.remove("one"));
        assertEquals(0, dictionary.size());
        assertTrue(dictionary.isEmpty());
        assertNull(dictionary.get("one"));
        
        // Test remove with multiple entries
        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);
        
        assertEquals(2, dictionary.remove("two"));
        assertEquals(2, dictionary.size());
        assertNull(dictionary.get("two"));
        assertEquals(1, dictionary.get("one"));
        assertEquals(3, dictionary.get("three"));
    }
    
    @Test
    public void testCollisionHandling() {
        // Create keys that would collide in a small hash table
        // This test assumes the hash function is based on hashCode() % table.length
        
        // Fill the dictionary to force collisions
        for (int i = 0; i < INITIAL_CAPACITY - 2; i++) {
            dictionary.put("key" + i, i);
        }
        
        // Add two more entries that might collide
        dictionary.put("collisionKey1", 100);
        dictionary.put("collisionKey2", 200);
        
        // Verify all entries are retrievable
        for (int i = 0; i < INITIAL_CAPACITY - 2; i++) {
            assertEquals(i, dictionary.get("key" + i));
        }
        assertEquals(100, dictionary.get("collisionKey1"));
        assertEquals(200, dictionary.get("collisionKey2"));
    }
    
    @Test
    public void testDeletedSlotHandling() {
        // Test that the implementation correctly handles deleted slots
        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);
        
        // Remove middle element
        dictionary.remove("two");
        
        // Add new element - should work even with deleted slot
        dictionary.put("four", 4);
        
        // Verify all entries
        assertEquals(1, dictionary.get("one"));
        assertNull(dictionary.get("two"));
        assertEquals(3, dictionary.get("three"));
        assertEquals(4, dictionary.get("four"));
        assertEquals(3, dictionary.size());
    }
    
    @Test
    public void testNearFullCapacity() {
        // Fill the dictionary close to capacity
        for (int i = 0; i < INITIAL_CAPACITY - 1; i++) {
            dictionary.put("key" + i, i);
        }
        
        assertEquals(INITIAL_CAPACITY - 1, dictionary.size());
        
        // Verify all entries
        for (int i = 0; i < INITIAL_CAPACITY - 1; i++) {
            assertEquals(i, dictionary.get("key" + i));
        }
    }
    
    @Test
    public void testNullKeyHandling() {
        // Test that null keys are handled appropriately
        // Typically, dictionaries throw NullPointerException for null keys
        assertThrows(NullPointerException.class, () -> dictionary.put(null, 1));
        assertThrows(NullPointerException.class, () -> dictionary.get(null));
        assertThrows(NullPointerException.class, () -> dictionary.remove(null));
    }
    
    @Test
    public void testNullValueHandling() {
        // Test that null values are handled appropriately
        assertThrows(NullPointerException.class, () -> dictionary.put("key", null));
        
        // Put a valid entry and then try to update with null
        dictionary.put("key", 1);
        assertThrows(NullPointerException.class, () -> dictionary.put("key", null));
    }
}