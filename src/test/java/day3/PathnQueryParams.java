package day3;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathnQueryParams {
	//https://reqres.in/api/users?page=2&id=5
	
    @Test
	void testQuerynPathParam() {
		given()
		.pathParam("mypath","users")//path parameters-- key & Value pair, it is like a variable
		.queryParam("page", 2)//query param
		.queryParam("id", 5)//query param
		.when()
		.get("https://reqres.in/api/{mypath}")// no query para here as they go along with the request
		.then()
		.statusCode(200)
		.log().all();
		
		
		
	}
    
	
	
	
	
	
}
