package testcases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.pages.P01_CreateNewUser;
import org.example.pages.PageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TC01_CreateNewUser extends TestBase{
    // TODO: define page object
    P01_CreateNewUser createNewUser=new P01_CreateNewUser();
    // TODO: define query parameters
    HashMap<String,Object> queryParameters =new HashMap<>();


    // given : all input details
    // when  : submit API
    // then : Validate the Response
    @Test(priority = 1, description = "Create New User with Valid Data")
    public void createNewUser() throws JsonProcessingException {
        // initialize body
        createNewUser.setName("Shady").setJob("Engineer");

        // define object mapper
        ObjectMapper Obj = new ObjectMapper();
        System.out.println(Obj.writeValueAsString(createNewUser));

        // format post request
        Response res=given()
                .log().all()
                .queryParams(queryParameters)
                .contentType(ContentType.JSON)
                .body(createNewUser)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .body("name",equalTo("Shady"))
                .assertThat()
                .statusCode(201)
                .body("name",equalTo("Shady"))
                .header("Content-Type","Application/Json")
                .extract().response();
        Assert.assertTrue(PageBase.checkResponseStatusCode(res,201));
        Assert.assertEquals("Shady",res.jsonPath().getString("name"));
        System.out.println(res.jsonPath().getString("createdAt"));

        // extract response body using JSON PAth
        JsonPath path=new JsonPath(res.asString());
        System.out.println(path.getString("name"));
    }

}
