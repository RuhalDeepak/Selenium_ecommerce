package Api_test_cases;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Rest_api_testing 
{
	@Test
	void test_01()
	{
		Response response =RestAssured.get("https://reqres.in/api/users");
		System.out.println("status code is :" + response.getStatusCode());
		System.out.println("Response time is :" + response.getTime());
		System.out.println("status line is :" + response.getStatusLine());
		System.out.println("Body is :" + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	  @Test
	    public void testGetUsersPage2() {
	        given()
	            .header("Content-Type", "application/json")
	        .when()
	            .get("/api/users?page=2")
	        .then()
	            .statusCode(200)
	            .body("data.id[0]", equalTo(7))
	            .body("data.email", hasItems("michael.lawson@reqres.in"))
	            .body("data.first_name", hasItems("Michael", "Tobias"))
	            .body("data.last_name", hasItems("Lawson"))
	            .body("data", not(empty())) // Check that the data list is not empty
	            .log().all();

}
}










