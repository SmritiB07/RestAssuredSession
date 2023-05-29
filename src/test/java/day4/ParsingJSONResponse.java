package day4;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponse {

	@Test(priority=2)
	void testJsonResBodyInfo() {
	
	// Approach1 adding validations in the then section

	  /* given()
	      .contentType(ContentType.JSON)// what we are sending as a request
	   .when()
	   .get("http://localhost:3000/store")
	   .then()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8") // assertions from matchers
		.body("book[3].title", equalTo("Golden Rule"))//-- json array in 0,1,2,3....
		.log().body();*/

	// Approach 2, it is more advantageous as here we capture entire response data into a var
	// get the response in the variable then validate each field using assertions	

/*	  Response res= given()  // assertions from Testng as we are holding values in res of type response
	                .contentType(ContentType.JSON) // here we can loop through, we can validate various fields bcoz with then we get restricted
	             .when()
	             .get("http://localhost:3000/store");
	          
	            Assert.assertEquals(res.getStatusCode(),200);   //Validation1
	            Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");// validation 2
	            String bookname=res.jsonPath().get("book[3].title").toString(); // value in string format
                Assert.assertEquals(bookname, "Golden Rule");*/

	 }
// parsing means traversing json data to get required field
	@Test
	void testJsonResBodyData() {// easy for static data not for dynamically changing data
		Response res = given()
				  .contentType(ContentType.JSON)
				.when()
				  .get("http://localhost:3000/store");
		// JSONObject class-- to traverse or to parse entire json response
		// we cannot pass res directly convert it to string first as it is response type

		JSONObject jo = new JSONObject(res.asString());// converting entire res into json object type
  // we can validate the res even if their order changes via for loop
	
		 /* for (int i=0;i<jo.getJSONArray("book").length();i++)
		   { 
		  String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString(); // String
		  System.out.println(bookTitle);
		  String bookauthor=jo.getJSONArray("book").getJSONObject(i).get("author").toString();
		  System.out.println(bookauthor);
		  System.out.println("Title of book"+" "+i+" is: "+" "+bookTitle);
		  }*/
		 
		
		  boolean status=false; // search for title of book in json
		  for (int i=0;i<jo.getJSONArray("book").length();i++) {
		  String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		  if(bookTitle.equals("Golden Rule"))
		  { status=true; 
		    break;
		  }
		  } // when we want to search for some particular field
		  if(!status) // not found-- dont use this 
		  { Assert.assertEquals(status,true); // only write this
		  }
		// validate total price of book

		double totalprice = 0;// validate total price of books
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) 
		{
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();

			totalprice = totalprice + Double.parseDouble(price);

		}
		System.out.println("total price of books:" + " " + totalprice);
		Assert.assertEquals(totalprice, 614.65);

	}

}
