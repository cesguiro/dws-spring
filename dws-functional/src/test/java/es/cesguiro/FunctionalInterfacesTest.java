package es.cesguiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalInterfacesTest {

    FunctionalInterfaces functionalInterfaces = new FunctionalInterfaces();

    @Test
    void testPrint() {
        functionalInterfaces.print.accept("Hello, World!");
    }

    @Test
    void testIsPositive() {
        assertTrue(functionalInterfaces.isPositive.test(1));
    }

    @Test
    void testSquare() {
        assertEquals(4, functionalInterfaces.square.apply(2));
    }

    @Test
    void testHello() {
        assertEquals("Hello, World!", functionalInterfaces.hello.get());
    }

}