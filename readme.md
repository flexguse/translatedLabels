Proof of concept if Vaadin component captions can be translated using spring-aop.

Prerequisites:
- Java 1.7
- Maven 3.1
- spring-boot-vaadin 0.0.2-SNAPSHOT installed locally

What we see in this prototype:
- components must be configured as @Scope(value="prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
- the aspect is called
- the UI is empty