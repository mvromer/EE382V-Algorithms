package hw1;

import java.util.Arrays;

public class IntSet {
    private int size;
    private int[] set;

    // class invariant: set[0..(size-1)] contains unique elements and no repetitions;
    // 0 <= size <= set.length
    public boolean repOk() {
        // postcondition: returns true if and only <this> satisfies the class invariant

        // For the record, I would probably not implement a uniqueness check like what is below in
        // practice. I would more likely use a HashMap to maintain a count of the elements seen in
        // set and then ensure each count was 1. That would have linear time at the expense of more
        // memory as opposed to the following code, which is quadratic (though in-place).
        //
        // That said, I thought perhaps the point of the homework was to NOT use data structures
        // from java.util in implementing our IntSet, so that's why I went with the more naive
        // implementation below.
        //
        for( int iRef = 0; iRef < size - 1; ++iRef ) {
            for( int iCompare = iRef + 1; iCompare < size; ++iCompare ) {
                if( set[iRef] == set[iCompare] )
                    return false;
            }
        }
        return true;
    }

    public IntSet() { // constructor
        size = 0;
        set = new int[10]; // default maximum number of elements in the set
    }

    public int size() { // getter for size
        return size;
    }

    public boolean contains( int x ) {
        // postcondition: returns true if set contains x and false otherwise
        return find( x ) < size;
    }

    public boolean add( int x ) {
        // postcondition: adds x to set; returns true if x was not
        // already in set (in pre-state) and false otherwise;
        // allocates a larger underlying set array if it does not
        // have capacity to add <x>
        int iItem = find( x );

        // Return if x was already in set.
        if( iItem < size )
            return false;

        // Do we need to allocate more space for the new item?
        if( size == set.length )
            set = Arrays.copyOf( set, 2 * set.length );

        // Append the new item to the end of set and increment size.
        set[size] = x;
        ++size;
        return true;
    }

    public boolean remove( int x ) {
        // postcondition: removes x from set; returns true if x was
        // in set in pre-state and false otherwise
        int iItem = find( x );

        // Return if x was not in set.
        if( iItem == size )
            return false;

        // Overwrite the item we're removing with the last item in set. Then decrement size.
        set[iItem] = set[size - 1];
        --size;
        return true;
    }

    // Returns the index of x if set contains x. Returns size otherwise.
    private int find( int x ) {
        int iItem = 0;
        for( ; iItem < size; ++iItem ) {
            if( set[iItem] == x )
                return iItem;
        }
        return iItem;
    }
}
