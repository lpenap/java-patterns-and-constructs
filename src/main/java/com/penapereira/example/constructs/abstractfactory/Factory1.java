package com.penapereira.example.constructs.abstractfactory;

public class Factory1 implements AbstractFactory {

        @Override
        public ProductA createProductA() {
                return new ProductA1();
        }

        @Override
        public ProductB createProductB() {
                return new ProductB1();
        }
}
