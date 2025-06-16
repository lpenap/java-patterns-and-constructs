# Template Method Pattern

The template method pattern defines the skeleton of an algorithm in a base class and lets subclasses override specific steps.

## Class diagram
```plantuml
@startuml
abstract class AbstractClass {
    +templateMethod()
    #stepOne()
    #stepTwo()
}
class ConcreteClassA extends AbstractClass
class ConcreteClassB extends AbstractClass
AbstractClass <|-- ConcreteClassA
AbstractClass <|-- ConcreteClassB
@enduml
```
