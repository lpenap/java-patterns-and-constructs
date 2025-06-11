package com.penapereira.example.constructs.factory;

public class ProductFactory {

        public Product createProduct(String type) {
                return switch (type) {
                case "A" -> new ConcreteProductA();
                case "B" -> new ConcreteProductB();
                default -> throw new IllegalArgumentException("Unknown type: " + type);
                };
        }
}
