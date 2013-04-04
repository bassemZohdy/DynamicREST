DynamicREST
===========

This application to create resource names at runtime and manage its fields,
then the application with provide restful service for this created resource,

you can try it here http://dynamicrest.cloudfoundry.com/

Technologies
------------
Technologies used while development of this application:

* Spring
  Spring framework is providing multiple features and APIs to deal with JDBC,JPA and NoSQL in domain layer,
also it is providing robust and powerful enviroment "DI, AOP transaction managment".
* Spring MVC
  Spring MVC framework give you the flexibility to design your URI pattern to survive,
and the HTTP Methods also it is providing an easy way to handle exceptions
* JSON & XML
  Currently I'm working on JSON did not test it on xml yet but expect problem of using List with JAXB,
soon planning to handel xml too.
* Domain Layer
  Using JDBC becuase it is providing more flexibility which is required for dynamic of REST,
but I think NoSQL could be better alternative using it under study.

Planning
--------
we are planning to add more feature to this Application:

* Relation between Resources

* Security using OAuth 2.0 check "https://github.com/bassemZohdy/Spring_REST_OAuth_Demo"


