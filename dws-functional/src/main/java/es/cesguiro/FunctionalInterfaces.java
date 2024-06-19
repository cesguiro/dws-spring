package es.cesguiro;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Component
public class FunctionalInterfaces {

    Consumer<String> print = s -> System.out.println(s);
    Predicate<Integer> isPositive = i -> i > 0;
    Function<Integer, Integer> square = i -> i * i;
    Supplier<String> hello = () -> "Hello, World!";
}
