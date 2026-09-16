[![CircleCI](https://dl.circleci.com/status-badge/img/gh/lpenap/java-patterns-and-constructs/tree/master.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/gh/lpenap/java-patterns-and-constructs/tree/master)
[![Build](https://github.com/lpenap/java-patterns-and-constructs/actions/workflows/maven.yml/badge.svg)](https://github.com/lpenap/java-patterns-and-constructs/actions/workflows/maven.yml)
[![GitHub release](https://img.shields.io/github/release/lpenap/java-patterns-and-constructs)](//github.com/lpenap/java-patterns-and-constructs/releases/latest)
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# Java Design Patterns and Constructs

A curated collection of object-oriented design patterns and concurrency constructs implemented in modern Java, intended as teaching material. Each example is reduced to the smallest set of classes that still exhibits the essential structure of the pattern, and is accompanied by a written explanation, a class diagram and a unit test. The examples are hosted in a small Spring Boot application with a Swing front end, so that they can be executed one at a time and their output observed.

## Contents

1. [Quickstart](#quickstart)
2. [Catalogue](#catalogue)
3. [Design of the host application](#design-of-the-host-application)
4. [Building and quality gates](#building-and-quality-gates)
5. [Adding a new example](#adding-a-new-example)
6. [Documentation conventions](#documentation-conventions)
7. [Related projects](#related-projects)
8. [References](#references)
9. [Contributing](#contributing)

## Quickstart

### Requirements

* A Java 25 JDK. The repository ships an `.sdkmanrc` file, so `sdk env install` will fetch a matching distribution if you use [SDKMAN!](https://sdkman.io/).
* Nothing else: the Maven wrapper (`./mvnw`) downloads the build tool on first use.

### Running the examples

```bash
git clone https://github.com/lpenap/java-patterns-and-constructs
cd java-patterns-and-constructs
./mvnw spring-boot:run
```

A window opens with one button per example and a **Run All** button. Clicking a button runs that example on a background thread and writes its trace output to the **Logger Output** panel. Tick **Preserve log** to keep the output of previous runs instead of clearing the panel before each one.

The examples can also be run without the window by enabling the console runner in `src/main/resources/application.properties`:

```properties
app.enableCommandLineRunner=true
```

Example output is written at `TRACE` level. The default configuration already enables it for the project packages:

```properties
logging.level.com.penapereira.example.*=TRACE
```

## Catalogue

The patterns follow the classification of Gamma, Helm, Johnson and Vlissides [1]. Each entry links to a folder containing the source code and a README that discusses intent, structure, participants, consequences and references.

### Creational patterns

| Pattern | Intent |
|---|---|
| [Abstract Factory](src/main/java/com/penapereira/example/constructs/abstractfactory/) | Provide an interface for creating families of related objects without naming their concrete classes. |
| [Factory Method](src/main/java/com/penapereira/example/constructs/factorymethod/) | Define an interface for creating an object, but let subclasses decide which class to instantiate. |
| [Factory (Simple Factory)](src/main/java/com/penapereira/example/constructs/factory/) | Centralise the creation of related products behind a single method that selects the concrete class. |
| [Singleton](src/main/java/com/penapereira/example/constructs/singleton/) | Ensure a class has exactly one instance and provide a global point of access to it. |

### Structural patterns

| Pattern | Intent |
|---|---|
| [Adapter](src/main/java/com/penapereira/example/constructs/adapter/) | Convert the interface of a class into another interface clients expect. |
| [Decorator](src/main/java/com/penapereira/example/constructs/decorator/) | Attach additional responsibilities to an object dynamically. |

### Behavioural patterns

| Pattern | Intent |
|---|---|
| [Chain of Responsibility](src/main/java/com/penapereira/example/constructs/chainofresponsibility/) | Pass a request along a chain of handlers until one of them handles it. |
| [Observer](src/main/java/com/penapereira/example/constructs/observer/) | Define a one-to-many dependency so that dependents are notified when a subject changes state. |
| [Strategy](src/main/java/com/penapereira/example/constructs/strategy/) | Define a family of interchangeable algorithms and let the client choose one at run time. |
| [Template Method](src/main/java/com/penapereira/example/constructs/templatemethod/) | Define the skeleton of an algorithm and defer some steps to subclasses. |

### Concurrency constructs

| Construct | Problem |
|---|---|
| [Producer/Consumer](src/main/java/com/penapereira/example/constructs/producerconsumer/) | Coordinate threads that generate data with threads that process it through a bounded, thread-safe buffer. |

## Design of the host application

The application exists only to discover, run and display the examples. Its design is deliberately small so that it does not distract from the patterns themselves.

```
com.penapereira.example.constructs
├── JavaPatternsAndConstructsApplication   Spring Boot entry point (headless mode disabled)
├── app
│   ├── ExampleRunnerInterface             Contract every example implements
│   ├── AppCommandLineRunner               Opens the main window on the AWT event thread
│   ├── ExamplesCommandLineRunner          Optional console runner (app.enableCommandLineRunner)
│   ├── properties
│   │   ├── ApplicationProperties          Typed binding of the app.* properties
│   │   └── Messages                       Typed binding of the msg.* user-facing strings
│   └── ui
│       ├── MainWindow                     Swing frame: one button per example, output panel
│       ├── OutputSink                     Interface through which text reaches the window
│       ├── GuiAppender                    Logback appender that forwards log events to an OutputSink
│       └── HyperlinkMouseListener         Opens the project URL in the system browser
├── abstractfactory, adapter, ...          One package per pattern or construct
└── producerconsumer
```

The moving parts and how they fit together:

* **Discovery by type.** Every example is a Spring `@Component` implementing `ExampleRunnerInterface`, whose single method is `runExample()`. Both runners ask the `ApplicationContext` for all beans of that type, so adding an example never requires touching the host. The bean name, minus the `ExampleRunner` suffix, becomes the button label.
* **Output through logging.** Examples do not print; they log at `TRACE` through SLF4J. `GuiAppender` registers itself on the Logback root logger at start-up and forwards each formatted message to an `OutputSink`. `MainWindow` implements `OutputSink` by appending to its text area on the AWT event thread. The indirection through an interface keeps the appender testable without a display.
* **Configuration binding.** Margins, colours and all user-visible strings live in `application.properties` and are bound to `ApplicationProperties` and `Messages` through `@ConfigurationProperties`. Lombok's `@Data` generates the accessors.
* **Dependency injection.** Beans receive their collaborators through constructors generated by Lombok's `@RequiredArgsConstructor`. There is no field injection.
* **Threading.** Each example runs on its own thread so a slow example cannot freeze the window. The producer/consumer example is the only one that creates threads of its own, and it shuts them down before returning.

## Building and quality gates

| Tool | Role |
|---|---|
| Maven Wrapper 3.9 | Reproducible builds without a local Maven installation |
| Spring Boot 4.1 parent | Dependency management and the `spring-boot:run` goal |
| JUnit Jupiter | Unit tests, one test class per pattern plus one per example runner |
| JaCoCo | Coverage report and enforcement |
| Lombok | Boilerplate generation (`@Data`, `@RequiredArgsConstructor`) |

`./mvnw verify` compiles, runs the tests and fails the build if instruction coverage drops below 95% or branch coverage below 90%. Only the Swing shell (`MainWindow`, `AppCommandLineRunner` and the application class) is excluded from the measurement, because it cannot be exercised without a display.

Continuous integration runs on two services:

* **GitHub Actions** (`.github/workflows/maven.yml`) builds every pull request. On pushes to `master` it additionally refreshes the dependency graph and regenerates the coverage badges in `.github/badges/`, committing them through a short-lived pull request that the workflow merges itself.
* **CircleCI** (`.circleci/config.yml`) runs `mvn verify` on every push as an independent check.

If you open the project in an IDE, install its Lombok plugin so the generated constructors and accessors resolve.

## Adding a new example

The host discovers examples by type, so a new example is a self-contained package plus a test. Follow the existing packages as templates.

1. **Create the package** `com.penapereira.example.constructs.<name>` under `src/main/java`, using a single lower-case word or compound (for example `chainofresponsibility`).
2. **Implement the pattern** with the fewest classes that still show its structure. Name the classes after the roles in the reference literature (`Product`, `ConcreteProductA`, `Handler`, and so on) so the README can map them directly to the participants.
3. **Add the runner.** A class named `<Name>ExampleRunner`, annotated `@Component`, implementing `ExampleRunnerInterface`. Log at `TRACE`, start with a one-line heading and indent the details by two spaces. Keep total output to a handful of lines and the run time under a second.

   ```java
   @Component
   public class FooBarExampleRunner implements ExampleRunnerInterface {

       private static final Logger log = LoggerFactory.getLogger(FooBarExampleRunner.class);

       @Override
       public void runExample() throws Exception {
           log.trace("Executing FooBar Pattern Implementation:");
           log.trace("  " + new FooBar().operation());
       }
   }
   ```

4. **Write the tests** under the matching package in `src/test/java`: one class exercising the pattern's behaviour and one asserting that the runner executes without error. The coverage gate applies to new code.
5. **Document it** with a `README.md` in the package following the [documentation conventions](#documentation-conventions), and a class diagram: add the PlantUML source to the README and export it as `assets/images/<name>.png`.
6. **List it** in the [Catalogue](#catalogue) above under the appropriate category.

If the example needs threads, make sure every thread it starts has finished or been interrupted before `runExample()` returns, so that repeated runs from the window do not leak resources.

## Documentation conventions

Each pattern README follows the same outline, adapted from the pattern template of Gamma et al. [1]:

* **Intent**: the problem the pattern solves, in one or two sentences.
* **Motivation**: the forces that make the naive solution inadequate.
* **Structure**: the class diagram, as an image and as PlantUML source.
* **Participants**: the roles in the pattern and the class in this package that plays each one.
* **The example**: what the runner does and what output to expect.
* **Consequences**: benefits, costs and common misuses.
* **Related patterns** and **References**, with numbered citations in the text.

## Related projects

**[java-monitor-example](https://github.com/lpenap/java-monitor-example)** is a companion repository by the same author dedicated to a single concurrency construct: the *monitor*, as formulated by Hoare [5] and Brinch Hansen [6], and its realisation in Java through intrinsic locks, `synchronized` and `wait`/`notifyAll`. It works through mutual exclusion and condition synchronisation on a shared pool of integers consumed exactly once each by competing threads, and visualises the contention in real time. Readers who want to understand what happens inside the `BlockingQueue` used by the [Producer/Consumer](src/main/java/com/penapereira/example/constructs/producerconsumer/) example here should start there.

## References

1. E. Gamma, R. Helm, R. Johnson and J. Vlissides, *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley, 1994.
2. J. Bloch, *Effective Java*, 3rd ed. Addison-Wesley, 2018.
3. E. Freeman and E. Robson, *Head First Design Patterns*, 2nd ed. O'Reilly, 2020.
4. B. Goetz, T. Peierls, J. Bloch, J. Bowbeer, D. Holmes and D. Lea, *Java Concurrency in Practice*. Addison-Wesley, 2006.
5. C. A. R. Hoare, "Monitors: An Operating System Structuring Concept," *Communications of the ACM*, vol. 17, no. 10, pp. 549–557, 1974.
6. P. Brinch Hansen, *Operating System Principles*. Prentice-Hall, 1973.

## Contributing

Issues are welcome for new examples, corrections to the explanations or additional references. Pull requests should target `master`, include tests, keep the coverage gate green and follow the conventions above. If you have an idea but are unsure how to implement it, open an issue describing the pattern or construct and the source you learned it from.
