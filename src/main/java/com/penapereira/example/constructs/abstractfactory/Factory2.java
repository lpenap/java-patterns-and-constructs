package com.penapereira.example.constructs.abstractfactory;

public class Factory2 implements AbstractFactory {

        @Override
        public ProductA createProductA() {
                return new ProductA2();
        }

        @Override
        public ProductB createProductB() {
                return new ProductB2();
        }
}
