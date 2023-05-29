package day3;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class HeadersDemo {
	
	
	//@Test(priority=1)
	void testHeaders() {
	
    	given()
    	.when()
    	  .get("https://www.google.com/")
    	.then()
    	.header("Content-Type", "text/html; charset=ISO-8859-1")
    	.and()
    	.header("Content-Encoding","gzip" )
    	.and()
    	.header("Server", "gws");
    	//.log().all().headers(null);
    	   
	}
	@Test(priority=2)
	void getheader() {
		    Response res= given()
		      .when()
		       .get("https://www.google.com/");
		    //get single header info
		    //String headervalue=res.getHeader("Content-Type");--- pass header to get value
		   // System.out.println("The value of header is:"+headervalue);
		    
		    
		    //get all headers info
		    Headers myheaders=res.getHeaders();// will give all the headers
		    for (Header hd:myheaders) {// hd has headers info
		    	System.out.println(hd.getName()+"   "+hd.getValue());// from info, can accesss its value and name
		    }
		    
		    
	}
	
	
	
	
	
	
	
	
	

}
