import config.OmdbApiConfig;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class OmdbApiTests extends OmdbApiConfig {

    String imdbId;

    @Before
    public void getImdbId(){
        Response response=given()
                .queryParams("s","Harry Potter")
        .when()
                .get()
        .then().extract().response();

        imdbId = response.path("Search.find{it.Title=='Harry Potter and the Sorcerer\\'s Stone'}.imdbID");
        System.out.println(imdbId);
    }

    @Test
    public void getFilm(){
        given()
                .queryParams("i",imdbId)
        .when()
                .get()
        .then().assertThat().body("Title", Matchers.equalTo("Harry Potter and the Sorcerer's Stone"))
                .body("Year",Matchers.equalTo("2001"))
                .body("Released",Matchers.equalTo("16 Nov 2001"));
    }

}
