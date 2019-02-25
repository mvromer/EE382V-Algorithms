package hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntSetTests {
    @Test
    public void testDefaultState() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertEquals( 0, s.size() );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
    }

    @Test
    public void testAddSingleElement() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testAddDuplicateElement() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );

        assertFalse( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testRemoveOnEmptySet() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertFalse( s.remove( 42 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 0, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testAddFollowedByRemove() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.remove( 42 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 0, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testRemoveFirstItem() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.add( 43 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 43 ) );
        assertTrue( s.repOk() );
        assertEquals( 2, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.remove( 42 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 43 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testRemoveLastItem() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.add( 43 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 43 ) );
        assertTrue( s.repOk() );
        assertEquals( 2, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.remove( 43 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 43 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testRemoveItemTwice() {
        IntSet s = new IntSet();
        assertTrue( s.repOk() );
        assertTrue( s.add( 42 ) );
        assertTrue( s.repOk() );
        assertTrue( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 1, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.remove( 42 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 0, s.size() );
        assertTrue( s.repOk() );

        assertFalse( s.remove( 42 ) );
        assertTrue( s.repOk() );
        assertFalse( s.contains( 42 ) );
        assertTrue( s.repOk() );
        assertEquals( 0, s.size() );
        assertTrue( s.repOk() );
    }

    @Test
    public void testStorageReallocation() {
        IntSet s = new IntSet();
        assertTrue( s.add( 1 ) );
        assertTrue( s.add( 2 ) );
        assertTrue( s.add( 3 ) );
        assertTrue( s.add( 4 ) );
        assertTrue( s.add( 5 ) );
        assertTrue( s.add( 6 ) );
        assertTrue( s.add( 7 ) );
        assertTrue( s.add( 8 ) );
        assertTrue( s.add( 9 ) );
        assertTrue( s.add( 10 ) );
        assertTrue( s.repOk() );
        assertEquals( 10, s.size() );

        assertTrue( s.add( 11 ) );
        assertTrue( s.repOk() );
        assertEquals( 11, s.size() );
        assertTrue( s.repOk() );

        assertTrue( s.contains( 1 ) );
        assertTrue( s.contains( 2 ) );
        assertTrue( s.contains( 3 ) );
        assertTrue( s.contains( 4 ) );
        assertTrue( s.contains( 5 ) );
        assertTrue( s.contains( 6 ) );
        assertTrue( s.contains( 7 ) );
        assertTrue( s.contains( 8 ) );
        assertTrue( s.contains( 9 ) );
        assertTrue( s.contains( 10 ) );
        assertTrue( s.contains( 11 ) );
        assertTrue( s.repOk() );
    }
}
