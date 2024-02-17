package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "data", dataProviderClass = api.utilities.DataProviderUtils.class)
    public void test(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone){
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setEmail(Email);
        userPayload.setFirstname(FirstName);
        userPayload.setLastname(LastName);
        userPayload.setPhone(Phone);
        userPayload.setUsername(UserName);
        userPayload.setPassword(Password);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "usernames", dataProviderClass = api.utilities.DataProviderUtils.class)
    public void deleteData(String username){
        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}