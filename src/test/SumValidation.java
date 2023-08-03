import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumofCourses() {
		
		JsonPath js=new JsonPath(Payload.complexjson());
		int count= js.getInt("courses.size()");
		for(int i=0; i>count; i++) 
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			System.out.println(amount);
		}
	}

}
