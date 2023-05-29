package day3;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Loggingdemo {
    @Test
	void testlogs() {
    	
    	given()
    	
    	   .when()
    	  .get("https://reqres.in/api/users?page=2")
    	.then()
	        .log().all();// everything will be printed
    	//log().body();--only body will be printed
    	//log().headers(); only headers will be printed
    	//log().cookies();---only cookies will be printed
	}
	
	
	
}
