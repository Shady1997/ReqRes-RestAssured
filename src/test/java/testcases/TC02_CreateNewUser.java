package testcases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.pages.P01_CreateNewUser;
import org.example.pages.PageBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC02_CreateNewUser extends TestBase{
    // TODO: define page object
    P01_CreateNewUser createNewUser=new P01_CreateNewUser();
    // TODO: define query parameters
    HashMap<String,Object> queryParameters =new HashMap<>();
    // TODO: define request specifications
    RequestSpecification httpRequest;


    // given : all input details
    // when  : submit API
    // then : Validate the Response

    @BeforeMethod
    public void setRequest(){
        RestAssured.basePath="/api/users";

        List<Header> acceptHeaders=new ArrayList<>();
        acceptHeaders.add(new Header("Accept","application/json"));
        acceptHeaders.add(new Header("User-Agent", "Mozilla/5.0"));
        acceptHeaders.add(new Header("Accept-Language", "en-US"));

        Headers headers=new Headers();

        httpRequest=RestAssured.given().auth().basic("","").headers(headers).queryParams(queryParameters);
    }

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
                .body(createNewUser)
                .when()
                .post()
                .then()
                .log().all()
                .body("name",equalTo("Shady"))
                .assertThat()
                .statusCode(201)
                .body("name",equalTo("Shady"))
                //.body(new String(Files.readAllBytes(Paths.get(path)));)
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
