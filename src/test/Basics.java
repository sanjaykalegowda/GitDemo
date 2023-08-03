import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Add place and update place with new address and get place to validate new address in response
		
		//Add place
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath js=new JsonPath(response);//for converting the string to json for parsing json
		String place_id = js.getString("place_id");
		System.out.println("place_id: "+place_id);
		
		//Update Place
		String new_address="70 Summer walk, USA";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+new_address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String get_place_response = given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().asString();
		JsonPath js1 = new JsonPath(get_place_response);
		String actual_address = js1.getString("address");
		System.out.println("Address updated to "+ actual_address);
		Assert.assertEquals(actual_address,new_address);
		// Junit , testNg

	}

}
