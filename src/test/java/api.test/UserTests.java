package api.test;

import api.endpoint.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//////cmd R can change a word in a one short
public class UserTests {
    Faker faker;
    User UserPayload;
    public Logger logger;
    @BeforeTest
    public void setup()
    {
        faker = new Faker();
        UserPayload=new User();
        UserPayload.setId(faker.number().numberBetween(1, 100000));
        UserPayload.setUsername(faker.name().username());
        UserPayload.setFirstName(faker.name().firstName());
        UserPayload.setLastName(faker.name().lastName());
        UserPayload.setEmail(faker.internet().emailAddress());
        UserPayload.setPassword(faker.internet().password(5, 10));
        UserPayload.setPhone(faker.phoneNumber().cellPhone());
        UserPayload.setUserStatus(1);

        ///logs
        logger= LogManager.getLogger(this.getClass());

    }

    @Test (priority = 1)
    public void testPostUser()
    {
        logger.debug("*****creating user*******");
        Response response= UserEndPoints.createUser(UserPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.debug("*****user created*******");

    }
    @Test (priority = 2)
    public void testGetUserByName()
    {
        logger.debug("*****reading user infomation*******");
     Response response=UserEndPoints.readUser(this.UserPayload.getUsername());
     response.then().log().all();
     Assert.assertEquals(response.getStatusCode(),200);
        logger.debug("*****user infomation is displaying*******");

    }
    @Test (priority = 3)
    public void testUpdateByUsername()
    {
        logger.debug("*****updating user infomation*******");
        ////update user name
        UserPayload.setFirstName(faker.name().firstName());
        UserPayload.setLastName(faker.name().lastName());
        UserPayload.setEmail(faker.internet().emailAddress());


        Response response= UserEndPoints.createUser(UserPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        ////checking after data update
        Response responseAfterupdate=UserEndPoints.readUser(this.UserPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.debug("*****user infomation updated*******");
    }
    @Test (priority = 4)
    public void deleteUserByName()
    {
        logger.debug("*****deleting user infomation*******");
        Response response= UserEndPoints.deleteUser(this.UserPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.debug("*****user deleted*******");
    }
}
