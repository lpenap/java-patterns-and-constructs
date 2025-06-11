[![maven build](https://github.com/lpenap/java-patterns-and-constructs/actions/workflows/merged_master.yml/badge.svg)](https://github.com/lpenap/java-patterns-and-constructs/actions/workflows/merged_master.yml)
[![GitHub release](https://img.shields.io/github/release/lpenap/java-patterns-and-constructs)](//github.com/lpenap/java-patterns-and-constructs/releases/latest)
[![codebeat badge](https://codebeat.co/badges/4c328c5d-1722-459f-aaa1-437a0ed6201f)](https://codebeat.co/projects/github-com-lpenap-java-patterns-and-constructs-master)
![Coverage](.github/badges/jacoco.svg)

# Java Design Patterns and Constructs
Collection of patterns and other constructs in Java for educational purposes. (See *Contributing* section if you would like to contribute).

## Quickstart
* Install a Java 21 implementation (like openjdk21)
* Clone and run the spring-boot maven goal:
```bash
git clone https://github.com/lpenap/java-patterns-and-constructs
cd java-patterns-and-constructs
./mvnw spring-boot:run
```
## Da List
### Patterns
* [Abstract Factory](src/main/java/com/penapereira/example/constructs/abstractfactory/)
* [Factory](src/main/java/com/penapereira/example/constructs/factory/)
* [Factory Method](src/main/java/com/penapereira/example/constructs/factorymethod/)
* [Observer](src/main/java/com/penapereira/example/constructs/observer/)
* [Singleton](src/main/java/com/penapereira/example/constructs/singleton/)
### Constructs and Problems
* [Producer/Consumer](src/main/java/com/penapereira/example/constructs/producerconsumer/), a multi-process synchronization problem.

## Caveats
Change the log level to TRACE in `application.properties` if you would like to see the output from examples:
```properties
logging.level.com.penapereira.example.*=TRACE
```
## Contributing
If you have an interesting idea but don't know how to implement it, feel free to open an issue to request a new feature.

If you would like to contribute additional constructs or patterns follow this steps to add an additional package with the additional example:

1. Create an additional nested package (i.e. `newexample`) as `com.penapereira.example.constructs.newexample`
2. Place the interfaces and classes you need there.
3. Implement an example runner by giving it a name ending in `ExampleRunner`, implementing the `ExampleRunnerInterface` and adding the `@Component` annotation:
```java
@Component
public class FooBarExampleRunner implements ExampleRunnerInterface {
	@Override
	public void runExample() {
		// place your code here
	}
}
```
Additional notes:
* Keep it simple, the idea is to implement the minimum from an educational point of view.
* Try to keep your runtime in less than 1000 milliseconds.
* If you need to output text to the console, try not to print more than 5 lines, you can add a slf4j logger this way and use `trace`:
```java
@Component
public class FooBarExampleRunner implements ExampleRunnerInterface {
	private static final Logger log = LoggerFactory.getLogger(FooBarExampleRunner.class);
	@Override
	public void runExample() {
		log.trace("Executing FooBar example:");
	}
}
```
