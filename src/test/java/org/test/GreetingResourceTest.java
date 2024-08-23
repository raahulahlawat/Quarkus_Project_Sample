// package org.test;

// import io.quarkus.test.junit.QuarkusTest;
// import org.junit.jupiter.api.Test;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;

// @QuarkusTest
// class GreetingResourceTest {
//     @Test
//     void testHelloEndpoint() {
//         given()
//           .when().get("/hello")
//           .then()
//              .statusCode(200)
//              .body(is("Hello from Quarkus REST"));
//     }

// }


package org.test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
// import io.restassured.RestAssured;
// import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/greeting/doctors")
            .then()
            .statusCode(200);
    }
}
