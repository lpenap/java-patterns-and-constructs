# Factory Method Pattern

The factory method pattern defines an interface for creating an object but lets subclasses decide which class to instantiate.

## Class diagram

![Class diagram](/assets/images/factorymethod.png)

```plantuml
@startuml
abstract class GenericProduct {
    +factoryMethod() {abstract}
    +build()
}
class ConcreteProductA extends GenericProduct {
    +factoryMethod()
}
class ConcreteProductB extends GenericProduct {
    +factoryMethod()
}
@enduml
```
