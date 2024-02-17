package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.createUser);
        return response;
    }

    public static Response getUser(String username){
        Response response = given().pathParam("username", username)
                .when()
                .get(Routes.getUser);
        return response;
    }

    public static Response updateUser(String username, User payload){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.updateUser);
        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.deleteUser);
        return response;
    }
}
