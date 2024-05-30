package api.test;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User user;
	Logger logger;
	
	@BeforeClass
	void setUpData() {
		 faker = new Faker();
		 user = new User();
		 user.setId(faker.idNumber().hashCode());
		 user.setUserName(faker.name().username());
		 System.out.println("set a user +++++++++" +this.user.getUserName());
		 user.setFirstName(faker.name().firstName());
		 user.setLastName(faker.name().lastName());
		 user.setEmail(faker.internet().emailAddress());
		 user.setPassword(faker.internet().password(5, 10));
		 user.setPhone(faker.phoneNumber().phoneNumber());
		 
		 //logs 
		 logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testCreateAUser() {
		logger.info("***********************creating a user******************************");
		Response res = UserEndPoints.createUser(user);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.body().jsonPath().getInt("message"), this.user.getId());
		logger.info("***********************user created r******************************");
	}
	
	@Test(priority = 2)
	void testGetAUserByName() {
		logger.info("***********************Reading user info through name******************************");
		System.out.println("get a user +++++++++" +this.user.getUserName());
		Response res = UserEndPoints.getUserByName(this.user.getUserName());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.body().jsonPath().getString("username"), this.user.getUserName());
		Assert.assertEquals(res.body().jsonPath().getString("firstName"), this.user.getFirstName());
		Assert.assertEquals(res.body().jsonPath().getString("lastName"), this.user.getLastName());
		Assert.assertEquals(res.body().jsonPath().getString("email"), this.user.getEmail());
		Assert.assertEquals(res.body().jsonPath().getString("password"), this.user.getPassword());
		Assert.assertEquals(res.body().jsonPath().getString("phone"), this.user.getPhone());
		Assert.assertEquals(res.body().jsonPath().getString("userStatus"), this.user.getUserStatus());
		logger.info("***********************Reading user info ended******************************");
	}
	
	@Test(priority = 3)
	void testUpdateAUser() {
		//update the data using payload 
		logger.info("***********************Updating a  user******************************");
		 user.setFirstName(faker.name().firstName());
		 user.setLastName(faker.name().lastName());
		 user.setEmail(faker.internet().emailAddress());
		 
		Response res = UserEndPoints.updateUser(this.user.getUserName(), user);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		
		//after update need to verify the response whether updated correctly or not
		Response resAfterUpdate = UserEndPoints.getUserByName(this.user.getUserName());
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
		Assert.assertEquals(resAfterUpdate.body().jsonPath().getString("firstName"), this.user.getFirstName());
		Assert.assertEquals(resAfterUpdate.body().jsonPath().getString("lastName"), this.user.getLastName());
		Assert.assertEquals(resAfterUpdate.body().jsonPath().getString("email"), this.user.getEmail());
		logger.info("***********************User is updated******************************");
	}
	
	@Test(priority = 4)
	void testDeleteAUser() {
		logger.info("***********************Deleteing a user******************************");
		Response res = UserEndPoints.deleteUser(this.user.getUserName());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("***********************user deleted******************************");
	}
}
