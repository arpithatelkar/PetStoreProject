package api.endpoints;

/*
 * User end points java file , created to perform CRUD operations
 * 
 */
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User userPayload) {
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userPayload)
			.when()
				.post(Routes.post_url);
				
			return response;

	}
	
	public static Response getUserByName(String userName) {
		Response response = given().pathParam("username", userName).when().get(Routes.get_url);

		return response;

	}
	public static Response updateUser(String  userName,User userPayload) {
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(userPayload)
			.when()
				.put(Routes.update_url);
				
			return response;

	}
	public static Response deleteUser(String  userName) {
		Response response=given()
				          .pathParam("username",userName)
                          .when()
	                      .delete(Routes.delete_url);
	
           return response;

	}
}
