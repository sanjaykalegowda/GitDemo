import files.Payload;
import io.restassured.path.json.JsonPath;

public class Complexjsonparse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(Payload.complexjson());
		
	int count=js.getInt("courses.size()");
	System.out.println("No of courses returned by API "+count);
	
	int purchaseAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println("Total Purchase Amount "+purchaseAmount);
	
	String firstCourse=js.getString("courses[0].title");
	System.out.println("Title of first course is "+firstCourse);
	
	for(int i=0; i<count; i++) {
		System.out.print(js.get("courses["+i+"].title")+":");
		System.out.println(js.get("courses["+i+"].price").toString());
	}
	
	//No of copies sold by RPA course
	for(int i=0; i<count; i++) {
		String courseTitles=js.getString("courses["+i+"].title");
		if(courseTitles.equalsIgnoreCase("RPA")) {
			System.out.println("No of copies sold by "+courseTitles+" is "+js.get("courses["+i+"].copies"));
			break;
		}
	}
	
	}

}
