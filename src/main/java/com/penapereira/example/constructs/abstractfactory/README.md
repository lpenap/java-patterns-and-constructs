# Abstract Factory Pattern

The abstract factory pattern provides an interface for creating families of related objects without specifying their concrete classes. This example defines two factories that each create a pair of products.

## Class diagram

![Class diagram](/assets/images/abstractfactory.png)

```plantuml
@startuml
interface AbstractFactory {
    +createProductA()
    +createProductB()
}

interface ProductA
interface ProductB

class Factory1 implements AbstractFactory
class Factory2 implements AbstractFactory

Factory1 --> ProductA1
Factory1 --> ProductB1
Factory2 --> ProductA2
Factory2 --> ProductB2

class ProductA1 implements ProductA
class ProductA2 implements ProductA
class ProductB1 implements ProductB
class ProductB2 implements ProductB
@enduml
```
