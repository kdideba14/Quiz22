import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Quiz2 {

  String uuid;
  String Token;

  @Test
  public void CreateUser() {
    this.uuid = given().accept(ContentType.JSON)
        .contentType(ContentType.JSON).queryParam("userName", "davita").queryParam("password", "Tchanturia@123")
        .when()
        .post("https://bookstore.toolsqa.com/Account/v1/User").then()
        // .assertThat()
        // .statusCode(201).
        .log().all()
        .body("$", hasKey("userID"))
        .extract().path("userID");

  }

  @Test
  public void AuthorizeUser() {
    this.Token = given().given().accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when().queryParam("userName", "daviti").queryParam("password", "Tchanturia@123")

        .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken").then()
        .assertThat()
        .statusCode(200)
        .body("$", hasKey("token"))
        .extract().path("token");

  }

  @Test
  public void GetUserData() {
    given().given().accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when().headers(
            "Authorization",
            "Bearer " + this.Token)
        .queryParam("UserId", this.uuid)
        .get("https://bookstore.toolsqa.com/Account/v1/User").then()
        .assertThat()
        .statusCode(200)

        .assertThat()
        .body("$", hasKey("username"))
        .body("$", hasKey("books"));
  }

  private ResponseAwareMatcher<Response> hasKey(String string) {
    return null;
  }

}
