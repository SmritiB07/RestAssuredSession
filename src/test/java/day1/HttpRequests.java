package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * gerkin language:keywords restassured support (bydefault) BDD style of 
given()
content type, set cookies, add auth, add params, set headers info etc.... (pre-request)

when()
get, put, post ,delete..(request)

then()+ and():- for multiple keywords
status code, response , header, cookies...(validation)

*/
public class HttpRequests {
	
	int id;//global variable
	
  @Test(priority=1) 
	void getUsers()
	{
    	given()
    	
    	.when()
    	  .get("https://reqres.in/api/users?page=2")
    	.then()
    	.statusCode(200)// we are expecting 200
    	.body("total_pages",equalTo(2))
          .log().all();
    	
	}
    
    @Test(priority=2)
    void createUser() {
    	HashMap<String,String> hm= new HashMap<String, String>();
    	hm.put("name","smriti");
    	hm.put("job","SDET");
    	
       id=given()
         .contentType("application/json")// request payload
         .body(hm)
       .when()
         .post("https://reqres.in/api/users")
          .jsonPath().getInt("id");// on top of response,json body in that get id(field) value which is int type
//       .then()   i don't want to validate response here so 
//         .statusCode(201)
//        // .body("id",equalTo(88))
//     .log().all();
    }
   @Test (priority=3,dependsOnMethods={"createUser"}) 
   void updateUser() {
		HashMap<String,String> hm= new HashMap<String, String>();
    	hm.put("name","Smriti Bhandari");
    	hm.put("job","SDET");
    	
       given()
          .contentType("application/json")
          .body(hm)
       .when()
           .put("https://reqres.in/api/users/"+id)
           
     .then()
           .statusCode(200)
           .log().all();
   }
   
   @Test(priority=4)
   void deleteUser() {
	   given()
	   .when()
	   .delete("https://reqres.in/api/users/"+id)
	   .then()
	   .statusCode(204)
	   .log().all();
   }
    
	/*
	get
	https://reqres.in/api/users/2
	post
	https://reqres.in/api/users
	{
	    "name": "morpheus",
	    "job": "leader"
	}
	put
	https://reqres.in/api/users/2
	551
	{
	    "name": "morpheus",
	    "job": "zion resident"
	}
	delete
	https://reqres.in/api/users/2

	*/
}
