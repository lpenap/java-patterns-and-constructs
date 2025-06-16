# Factory Method Pattern

The factory method pattern defines an interface for creating an object but lets subclasses decide which class to instantiate.

## Class diagram
```plantuml
@startuml
abstract class GenericProduct {
    +factoryMethod()
    +build()
}
class ConcreteProductA extends GenericProduct
class ConcreteProductB extends GenericProduct
GenericProduct <|-- ConcreteProductA
GenericProduct <|-- ConcreteProductB
@enduml
```
