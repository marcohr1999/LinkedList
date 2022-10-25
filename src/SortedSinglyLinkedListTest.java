import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortedSinglyLinkedListTest {

    // These are used for manual testing
    int[] strings = {1,2,1,4,3,6,2, 5}; // 1 2 3 4 5 6
    int[] strings2 = {1,2,1,4,3,2,7,8,9,10}; // 1 2 3 4 7 8 9 10

    // This is used for bulk testing
    int sizeOfTestLists = 50; // values of 2 or above or intersection will fail in bulk testing

    // This is used in all testing
    SortedSinglyLinkedList<Integer> nList = new SortedSinglyLinkedList<>();
    SortedSinglyLinkedList<Integer> nList2 = new SortedSinglyLinkedList<>();
    SortedSinglyLinkedList<Integer> nList3 = new SortedSinglyLinkedList<>();

    @BeforeEach
    public void setUp(){
        nList = new SortedSinglyLinkedList<>();
        nList2 = new SortedSinglyLinkedList<>();
        nList3 = new SortedSinglyLinkedList<>();

        //for(int string = sizeOfTestLists-1; string >= 0; string--){   // Bulk Testing
        for (int string : strings) {   // Manual testing
            nList.addNode(string);
        }

        //for(int string = sizeOfTestLists-1; string >= 0; string -= 2){  // Bulk testing
        for (int string : strings2){ // Manual testing
            nList2.addNode(string);
        }
    }

    @Test
    public void addNodeTest(){
        System.out.println("Performing add node Test");
        long runtime = System.currentTimeMillis();

        // Manual Testing
        nList.addNode(7); // 1 2 3 4 6
        assertEquals(7, nList.getSize()); // 1 2 3 4 6 7

        // Bulk Testing
        //nList.addNode(sizeOfTestLists + 1);
        //assertEquals(sizeOfTestLists + 1 ,nList.getSize());

        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of addNode is " + runtime + "ms. For size " + (nList.getSize() - 1));
    }

    @Test
    public void removeNodeTest(){
        System.out.println("Performing remove node Test");

        long runtime = System.currentTimeMillis();


        // Manual Testing
        nList.removeNode(1); // 1 2 3 4 5 6
        assertEquals(5, nList.getSize()); // 2 3 4 5 6

        // Bulk Testing
        //nList.removeNode(sizeOfTestLists - 1);
        //assertEquals(sizeOfTestLists - 1 ,nList.getSize());

        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of removeNode is " + runtime + "ms. For size " + (nList.getSize() + 1));
    }

    @Test
    public void inSetTest(){
        System.out.println("Performing inSet test");
        long runtime = System.currentTimeMillis();

        // Manual Testing
        assertFalse(nList.inSet(10)); // 1 2 3 4 6

        // Bulk Testing
        //assertFalse(nList.inSet(sizeOfTestLists + 1));

        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of inSet is " + runtime + "ms. For size " + nList.getSize());
    }

    @Test
    public void returnReferenceTest(){
        System.out.println("Performing Reference Test");
        long runtime = System.currentTimeMillis();

        Node<Integer> test = nList.returnReference(1);
        Node<Integer> test2 = nList.returnReference(3);

        // Manual Testing
        assertEquals(test, nList.returnReference(1)); // 1 2 3 4 5 6
        assertEquals(test2, nList.returnReference(3));

        // Bulk Testing
        //assertEquals(sizeOfTestLists - 1, nList.returnReference(sizeOfTestLists-1).getData());

        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of returnReference is " + runtime + "ms. For size " + nList.getSize());
    }

    @Test
    public void returnUnionTest(){
        System.out.println("Performing Union Test");
        long runtime = System.currentTimeMillis();

        nList3 = nList.union(nList2);

        // Manual Testing
        assertEquals(10, nList.union(nList2).getSize()); // 1 2 3 4 5 6 7 8 9 10
        assertTrue(nList3.inSet(4));
        assertTrue(nList3.inSet(7));

        // Bulk Testing
        //assertEquals(sizeOfTestLists, nList.union(nList2).getSize());

        nList.display();
        nList3.display();
        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of returnUnion is " + runtime + "ms. For size " + nList3.getSize());
    }

    @Test
    public void returnIntersectionTest(){
        System.out.println("Performing Intersection Test");
        long runtime = System.currentTimeMillis();

        nList3 = nList.intersection(nList2);

        // Manual Testing
        assertEquals(4, nList3.getSize()); // 1 2 3 4
        assertTrue(nList3.inSet(2));
        assertFalse(nList3.inSet(7));

        nList3.display();
        // Bulk Testing
        //assertEquals(sizeOfTestLists/2, nList3.getSize());

        runtime = System.currentTimeMillis() - runtime;
        System.out.println("Runtime of returnIntersection is " + runtime + "ms. For size " + nList3.getSize());
    }
}
