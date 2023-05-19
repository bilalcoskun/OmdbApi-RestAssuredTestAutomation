package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

public class OmdbApiConfig {
    @BeforeClass
    public static void setup(){
        RestAssured.requestSpecification=new RequestSpecBuilder()
                .setBaseUri("https://www.omdbapi.com/")
                .addQueryParam("apikey","4d824b87")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


}
