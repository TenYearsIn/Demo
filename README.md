# Demo
My Demo For Practice
My sample project has three modules and they are Log service (exposed port is 8082) ,discovery service(exposed port is 8080 ) and gateway service (exposed port is 8081 ) .
I use undertow as container，JBoss 8.1.0.Final as application server . I deploy my three project as *.war and it can compile successfully.
and I can see three modules on eureka dashboard,I visit gateway's Controller(url:http://localhost:8081/hello/test),it return 404
what's more ,I visit http://localhost:8081/log/hello/mc on the browser, have same result.
