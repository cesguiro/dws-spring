package es.cesguiro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LambdaFunctionsTest {

    LambdaFunctions lambdaFunctions = new LambdaFunctions();

    @Test
    void testAddOperation() {
        assertEquals(3, lambdaFunctions.addOperation.add(1, 2));
    }

    @Test
    void testAddOperationFunctional() {
        assertEquals(3, lambdaFunctions.addOperationFunctional.add(1, 2));
    }

    @Test
    void testAdd() {
        assertEquals(3, lambdaFunctions.add(1, 2, lambdaFunctions.addOperation));
    }
}