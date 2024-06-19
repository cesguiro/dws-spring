package es.cesguiro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerCollectionFunctionalTest {

    @Test
    void sum() {
        int expected = 409;
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(expected, integerCollectionFunctional.sum());
    }

    @Test
    void sumEvenValues() {
        int expected = 142;
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(expected, integerCollectionFunctional.sumEvenValues());
    }

    @Test
    void max() {
        int expected = 98;
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(expected, integerCollectionFunctional.max());
    }

    @Test
    void deleteRepeated() {
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(9, integerCollectionFunctional.deleteRepeated().size());
    }

    @Test
    void order() {
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(2, integerCollectionFunctional.order().get(0));
        assertEquals(98, integerCollectionFunctional.order().get(11));
    }

    @Test
    void squareList() {
        IntegerCollectionFunctional integerCollectionFunctional = new IntegerCollectionFunctional();
        assertEquals(1156, integerCollectionFunctional.squareList().get(0));
        assertEquals(1089, integerCollectionFunctional.squareList().get(3));
    }

}