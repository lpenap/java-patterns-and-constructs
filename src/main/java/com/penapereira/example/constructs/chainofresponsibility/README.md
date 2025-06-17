# Chain of Responsibility Pattern

The chain of responsibility pattern passes a request along a chain of handlers until one of them deals with it.

## Class diagram

![Class diagram](/assets/images/chainofresponsibility.png)

```plantuml
@startuml
interface Handler {
    +setNext(h)
    +handle(request)
}
abstract class AbstractHandler implements Handler {
    -next : Handler
}
class NegativeHandler extends AbstractHandler {
    +handle(request)
}
class ZeroHandler extends AbstractHandler {
    +handle(request)
}
class PositiveHandler extends AbstractHandler {
    +handle(request)
}
AbstractHandler --> Handler : next
@enduml
```
