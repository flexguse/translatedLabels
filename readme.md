Proof of concept if Vaadin component captions can be translated using spring-aop.

Prerequisites:
- Java 1.7
- Maven 3.1
- spring-boot-vaadin 0.0.2-SNAPSHOT installed locally

What we see in this prototype:
- components must be configured as @Scope(value="ui",proxyMode=ScopedProxyMode.TARGET_CLASS)
- the aspect is called
- the UI is filled

Current problems:
- prototype scope does not work, so in the application there only is one label instead of two expected (one translated, one untranslated)

See discussion https://github.com/peholmst/vaadin4spring/issues/18