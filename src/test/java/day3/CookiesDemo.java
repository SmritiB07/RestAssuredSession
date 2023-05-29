package day3;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
   // @Test(priority=1)
	void testCookies() {
	
    	given()
    	.when()
    	  .get("https://www.google.com/")
    	.then()
    	   .cookie("AEC","AUEFqZeYUjwwyQEU9XO_fxIvgsvBEHvTlVr7LlKH8HzF0WnZDWDPPvajfyo")// it must fail shows value of cookie keeps changing.
    	   .log().all();// failure mean test is passed--- we got cookie value from postman
	}
    @Test(priority=2)
   	void getCookiesInfo() {
   	
       Response res=given() //data into response now try to extract cookies info out of it
       	.when()
       	  .get("https://www.google.com/");//returns response
    
       //get single cookie info
      //String  cookie_value=res.getCookie("AEC");//value of this particular cookie
      //System.out.println("Value of cookie info======>"+cookie_value);
       
       //get all cookies info
     Map<String,String>  cookies_values=res.getCookies();
     System.out.println(cookies_values.keySet());
     System.out.println("above are keys only ======= below is key and value pair");
     for(String k:cookies_values.keySet()) {// key name
    	 String cookie_value=res.getCookie(k);// value of key
    	 System.out.println("cookie name"+" "+k+" "+"cookie value is===>"+" "+cookie_value);
     }
   	}
	
	
	
	
	
}
