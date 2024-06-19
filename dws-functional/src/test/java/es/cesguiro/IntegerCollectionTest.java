package es.cesguiro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerCollectionTest {

    @Test
    void sum() {
        int expected = 409;
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(expected, integerCollection.sum());
    }

    @Test
    void sumEvenValues() {
        int expected = 142;
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(expected, integerCollection.sumEvenValues());
    }

    @Test
    void max() {
        int expected = 98;
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(expected, integerCollection.max());
    }

    @Test
    void deleteRepeated() {
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(9, integerCollection.deleteRepeated().size());
    }

    @Test
    void order() {
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(2, integerCollection.order().get(0));
        assertEquals(98, integerCollection.order().get(11));
    }

    @Test
    void squareList() {
        IntegerCollection integerCollection = new IntegerCollection();
        assertEquals(1156, integerCollection.squareList().get(0));
        assertEquals(1089, integerCollection.squareList().get(3));
    }
}