package day5;

import java.io.File;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUpload {
	
    @Test
	void singleFileUpload()
    {
    	File myFile= new File("C:\\AutomationPrac");
	given()
	.multiPart("file",myFile)//for form-data we need multipart
	.contentType("multiPart/form-data")
	.when()
	.post("")
	.then().statusCode(200).body("fileName",equalTo("Test1.text")).log().all();
    	
    	
	}
    //@Test
   	void multipleFileUpload()
       {
       	File myFile1= new File("C:\\AutomationPrac");//wont work for all APIs
       	File myFile2= new File("C:\\AutomationPrac");
       	// for n no of files.....
     File filearr[]= {myFile1,myFile2};
       	

   	given()
   	.multiPart("files",myFile1)//for form-data we need multipart
   	.multiPart("files",myFile2).contentType("multiPart/form-data")
   	.when()
   	.post("")
   	.then().statusCode(200)
   	.body("[0].fileName",equalTo("Test1.text"))
   	.body("[1].fileName",equalTo("Test2.text"))

   	.log().all();  	
   	}
	//@Test
	void fileDownload() {
		given()
		.when()
		.get("http://localhost:8080/downloadFile/Test1.txt")
		.then()
		.statusCode(200)
		.log().body();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
