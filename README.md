# Java Design Patterns and Constructs
Collection of patterns and other constructs in Java (See *Contributing* section if you would like to contribute)

## Quickstart
* Install a Java 11 implementation (like openjdk11)
* Clone and run the spring-boot maven goal:
```bash
git clone https://github.com/lpenap/java-patterns-and-constructs
cd java-patterns-and-constructs
./mvnw spring-boot:run
```
## Da List
### Patterns
* Factory Method
* Observer
* Singleton
### Constructs and Problems
* [Producer/Consumer](src/main/java/com/penapereira/example/constructs/observer/), a multi-process synchronization problem.
* Monitor, a synchronization construct that allows threads to have both mutual exclusion and wait for conditions.

## Caveats
Change the log level to TRACE in `application.properties` if you would like to see the output from examples:
```properties
logging.level.com.penapereira.example.*=TRACE
```
## Contributing
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
