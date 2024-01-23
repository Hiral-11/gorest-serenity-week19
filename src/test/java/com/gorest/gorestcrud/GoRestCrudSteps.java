package com.gorest.gorestcrud;

import com.gorest.gorestinfo.GoRestSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GoRestCrudSteps extends TestBase {
    static int id;
    static String name = TestUtils.getRandomValue() + "Hiral1";
    static String email = TestUtils.getRandomValue() + "hiral1@gmail.com";
    static String gender = "female";
    static String status = "active";
    @Steps
    GoRestSteps goRestSteps;

    @Title ("Creating new user")//post
    @Test
    public void test001(){
        ValidatableResponse response = goRestSteps.createUser(name, email,gender,status);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Verify user was created") //get single user by id
    @Test
    public void test002(){
        HashMap<String, Object> usersMap = goRestSteps.getUserInfoById(id);
        Assert.assertThat(usersMap,hasValue(id));
        System.out.println(usersMap);
    }

    @Title("Updating the user") //put/patch by changing the requirement
    @Test
    public void test003(){
        name = "Hiral1"+ TestUtils.getRandomValue();
        email = "hiral1@gmail.com" + TestUtils.getRandomValue();
        goRestSteps.updateUser(id,name,email,gender,status).statusCode(200);
        HashMap<String,Object> userMap = goRestSteps.getUserInfoById(id);
        Assert.assertThat(userMap,hasValue(id));

    }
    @Title("Deleting the user and verifying user was deleted.") //delete same id
    @Test
    public void test004() {
        goRestSteps.deleteUser(id).statusCode(204);
        goRestSteps.gettingUserById(id).statusCode(404);
    }
}