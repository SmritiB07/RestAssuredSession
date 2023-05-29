package day5;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponse {

	@Test
	void testXMLResponse() {
		// Approach 1
		/*
		  given() .when() .get("") .then() .statusCode(200) .header("Content-Type",
		  "application/xml; charset=utf-8")// index from[0]
		  .body("TravelerinformationResponse.page",equalTo("1"))// in xml every line is
		  called as a node
		  .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",
		  equalTo("Smriti Bhandari"));
		 */
//approach2 :Response variable help us to more validations , even if the order changes this approach is useful
		Response res = given().when().get("");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		String name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		//here we are expecting only one node thats y specified index [0].name
		Assert.assertEquals(name, "Smriti Bhandari");

	}
	
	@Test
	void testXMLResponseBody() {
	
	Response res= 
			given()
			.when()
			.get("");
	
	XmlPath xmlObj= new XmlPath(res.asString());
	
	List<String> travellers=xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation"); 
	//here we need all the nodes and whole body so we didn't specified index[0].name
	//Verify total no of travelers
	Assert.assertEquals(travellers.size(),10);
	// verify traveler name is present in the response-- this will return all the names in the variable
	List<String> travellersname=xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name"); 
    
	boolean status= false;
	for (String travellersinfo:travellersname) {
    	if(travellersinfo.equals("Smriti Bhandari")) {
    		status= true;
    		break;
    	}
    	//System.out.println(travellersinfo);
    	Assert.assertEquals(status, true);
    }
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	

}
