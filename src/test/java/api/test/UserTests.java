package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.Matchers.equalTo;

public class UserTests {
    User userPayload;

    @BeforeClass
    public void setupData(){
        userPayload = new User();
        userPayload.setId(UUID.randomUUID().toString().hashCode());
        userPayload.setEmail("abc@gmail.com");
        userPayload.setFirstname("first name");
        userPayload.setLastname("last name");
        userPayload.setPhone("123456789");
        userPayload.setUsername("abc");
        userPayload.setPassword("abc");
    }

    @Test(priority = 1)
    public void testCreateUser(){
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUser() throws ExecutionException, InterruptedException, TimeoutException {
        userPayload.setEmail("email2@gmail.com");

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        // Use CompletableFuture to query the user asynchronously
        CompletableFuture<Response> getUserFuture = CompletableFuture.supplyAsync(() ->
                UserEndPoints.getUser("abc"));

        // Wait for the getUser operation to complete
        Response response2 = getUserFuture.get(10, TimeUnit.SECONDS);

        response2.then().log().all();
        // Extract JSON response using JSONPath
        JsonPath jsonPath = response2.jsonPath();

        // Assert the values against expected values
        Assert.assertEquals(jsonPath.getString("email"), "email2@gmail.com", "email does not match");
//        Assert.assertEquals(actualLastName, "last name 2", "Last name does not match");
    }
}
