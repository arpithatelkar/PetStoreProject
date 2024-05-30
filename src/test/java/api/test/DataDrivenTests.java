package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.utlities.DataProviders;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class )
	public void testCreateAUserInDD(String userId, String userName, String firstName, String lastName, String emailId,
			String password, String phoneNumber) {

		User user = new User();
		user.setId(Integer.parseInt(userId));
		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(emailId);
		user.setPassword(password);
		user.setPhone(phoneNumber);

		Response res = UserEndPoints.createUser(user);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.body().jsonPath().getInt("message"), user.getId());
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response res = UserEndPoints.deleteUser(userName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
	}
}