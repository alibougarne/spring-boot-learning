package com.real360.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    void itShouldAddTwoNumbers() {
        Calculator calculatorUnderTest = new Calculator();

        // given
        int numberOne = 1;
        int numberTwo = 10;

        // when
        int underTestResult = calculatorUnderTest.add(numberOne, numberTwo);

        // then
        assertThat(underTestResult).isEqualTo(11);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }

}
