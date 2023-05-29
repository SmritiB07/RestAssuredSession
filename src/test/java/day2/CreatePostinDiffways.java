package day2;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*We can create req body in different ways
 * HashMap
 * By using org.json
 * By using POJO (pain old java object)
 * By using external Jsonfile
 */

//"id": 1,
//"name": "John",
//"location": "india",
//"phone": "123456789",
//"courses": [
//  "Java",
//  "Selenium"

public class CreatePostinDiffways {

	// 1) to create the post request using hashmap
	// this is for small set of data
//@Test(priority=1)	
	void testPostusingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "John");
		data.put("location", "India");
		data.put("phone", "123456789");
		String courseArr[] = { "Java", "Selenium" };
		data.put("courses", courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("John")).body("location", equalTo("India"))
				.body("phone", equalTo("123456789")).body("courses[0]", equalTo("Java"))
				.body("courses[1]", equalTo("Selenium")).header("Content-Type", "application/json; charset=utf-8").log()
				.all();

	}

// 2) to create the post request using org.json lib
//@Test(priority=2)	
	void testPostusingOrgjsonlib() {
		// generating data
		JSONObject jdata = new JSONObject();
		jdata.put("name", "John");
		jdata.put("location", "India");
		jdata.put("phone", "123456789");
		String jcourseArr[] = { "Java", "Selenium" };
		jdata.put("courses", jcourseArr);

		given().contentType("application/json")// it will be converted into json
				.body(jdata.toString())// data should be in string format then
				.when().post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("John"))
				.body("location", equalTo("India")).body("phone", equalTo("123456789"))
				.body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("Selenium"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

//3) to create the post request using POJO class
	//@Test(priority = 3)
	void testPostusingPOJO() {
		// generating data from POJO class
		POJO_PostRequest pdata = new POJO_PostRequest();// creating pojo class object
		pdata.setName("John");
		pdata.setLocation("India");
		pdata.setPhone("123456789");
		String courseArr[] = { "Java", "Selenium" };
		pdata.setCourses(courseArr);

		given().contentType("application/json")// it will be converted into json
				.body(pdata).when().post("http://localhost:3000/students").then().statusCode(201)
				.body("name", equalTo("John")).body("location", equalTo("India")).body("phone", equalTo("123456789"))
				.body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("Selenium"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}
	//4) to create the post request using external json file
	@Test(priority = 4)
		void testPostusingexternalJsonFile() throws FileNotFoundException {
			// generating data from POJO class
		File f=new File(".\\bosy.json"); 
		FileReader fr= new FileReader(f);
		JSONTokener jt= new JSONTokener (fr);
		JSONObject jbdata= new JSONObject();
		

			given().contentType("application/json")// it will be converted into json
					.body(jbdata.toString()).when().post("http://localhost:3000/students").then().statusCode(201)
					.body("name", equalTo("John")).body("location", equalTo("India")).body("phone", equalTo("123456789"))
					.body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("Selenium"))
					.header("Content-Type", "application/json; charset=utf-8").log().all();}


	@Test(priority = 5)
	void testDelete() {

		given().when().delete("http://localhost:3000/students/4").then().statusCode(200);

	}

}
