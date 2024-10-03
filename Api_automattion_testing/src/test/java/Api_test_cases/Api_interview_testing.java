package Api_test_cases;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class Api_interview_testing {


		
	 static {
	        RestAssured.baseURI = "https://reqres.in";
	    }

	    @Test
	    public void testGetUsers() {
	        Response response = given()
	            .when()
	            .get("/api/users")
	            .then()
	            .statusCode(200)
	            .extract().response();

	        // Validate JSON schema (example, you may refine it as per your requirements)
	        response.then().assertThat().body("data", notNullValue());
	    }

	    @Test
	    public void testGetUserById() {
	        int userId = 2; // Example user ID
	        Response response = given()
	            .when()
	            .get("/api/users/" + userId)
	            .then()
	            .statusCode(200)
	            .extract().response();

	        // Validate user details
	        response.then().assertThat()
	            .body("data.id", equalTo(userId))
	            .body("data.email", containsString("@reqres.in"));
	    }

	    @Test
	    public void testCreateUser() {
	        String requestBody = "{ \"name\": \"John\", \"job\": \"Developer\" }";

	        Response response = given()
	            .contentType("application/json")
	            .body(requestBody)
	            .when()
	            .post("/api/users")
	            .then()
	            .statusCode(201)
	            .extract().response();

	        // Validate response
	        response.then().assertThat()
	            .body("name", equalTo("John"))
	            .body("job", equalTo("Developer"));
	    }

	    @Test
	    public void testUpdateUser() {
	        int userId = 2; // Example user ID
	        String requestBody = "{ \"name\": \"Jane\", \"job\": \"Manager\" }";

	        Response response = given()
	            .contentType("application/json")
	            .body(requestBody)
	            .when()
	            .put("/api/users/" + userId)
	            .then()
	            .statusCode(200)
	            .extract().response();

	        // Validate response
	        response.then().assertThat()
	            .body("name", equalTo("Jane"))
	            .body("job", equalTo("Manager"));
	    }

	   
}


