package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.constants.Path;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class GoRestSteps {
    @Step("Creating user with name : {0} , email : {1} , gender : {2} , status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .when()
                .body(userPojo)
                .post(Path.USER)
                .then().log().all();
    }

    @Step("Getting user information from the email : {0}")
    public HashMap<String, Object> getUserInfoById(int id) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .pathParam("user_id", id)
                .when()
                .get(Path.USER + EndPoints.GET_USER_BY_ID)
                .then().statusCode(200).extract().path("");
    }


    @Step("Updating user with id : {0} , name : {1} ,email : {2} , gender : {3}, status : {4}")
    public ValidatableResponse updateUser(int id, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "_Updated");
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .pathParam("user_id", id)
                .body(userPojo)
                .when()
                .put(Path.USER+EndPoints.UPDATE_A_USER_BY_ID)
                .then().statusCode(200);
    }

    @Step("Deleting user with id : {0}")
    public ValidatableResponse deleteUser(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .pathParam("user_id", id)
                .when()
                .delete(Path.USER+EndPoints.DELETE_A_USER_BY_ID)
                .then().log().all();
    }

    @Step("Getting user by id : {0}")
    public ValidatableResponse gettingUserById(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .pathParam("user_id", id)
                .when()
                .get(Path.USER+EndPoints.GET_USER_BY_ID)
                .then();

    }
}

