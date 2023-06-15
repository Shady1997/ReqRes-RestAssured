package testcases;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    @BeforeSuite
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
    }
}
