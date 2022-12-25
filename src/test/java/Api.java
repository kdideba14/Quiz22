import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class Api {

  @Test
  public void getResponseStatus() {
    int statusCode = given()
        .when()

        .get("https://bookstore.toolsqa.com/BookStore/Books")
        .getStatusCode();
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void ValidateLastBookIsbn() {
    given()
        .when()
        .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
        .then().log().all()
        .assertThat()
        .body("books[-1].isbn", equalTo("9781593277574"));

  }

  @Test
  public void validateFirstTwoBookPages() {
    given()
        .when()
        .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
        .then().log().all()
        .assertThat()
        .body("books[0,1].isbn", hasItems(234, 254));
  }

}
