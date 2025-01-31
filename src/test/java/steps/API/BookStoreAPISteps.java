package steps.API;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class BookStoreAPISteps {

    private static final String BASE_URL = "https://demoqa.com";
    private Response response;

    @Step("Make a POST request to <endpoint> with userId <userId> and ISBN <isbn>")
    public void makePostRequest(String endpoint, String userId, String isbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getToken());

        String requestBody = "{ \"userId\": \"" + userId + "\", \"collectionOfIsbns\": [ { \"isbn\": \"" + isbn + "\" } ] }";
        response = request.body(requestBody).post(endpoint);
    }

    @Step("Make a POST request to <endpoint> with userId <userId> and ISBN <isbn> without the Authorization header")
    public void makePostRequestWithNoAuth(String endpoint, String userId, String isbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String requestBody = "{ \"userId\": \"" + userId + "\", \"collectionOfIsbns\": [ { \"isbn\": \"" + isbn + "\" } ] }";
        response = request.body(requestBody).post(endpoint);
    }

    @Step("Verify the response status code is <statusCode>")
    public void verifyStatusCode(String statusCode) {
        int expectedStatusCode = Integer.parseInt(statusCode);
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Step("Verify the response contains the message <message>")
    public void verifyResponseMessage(String message) {
        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(message, actualMessage);
    }

    @Step("Make a GET request to <endpoint> with ISBN <isbn>")
    public void makeGetRequest(String endpoint, String isbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.queryParam("ISBN", isbn);
        response = request.get(endpoint);
    }

    @Step("Verify the response contains the book title <title>")
    public void verifyBookTitle(String title) {
        String actualTitle = response.jsonPath().getString("books[0].title");
        if (actualTitle == null) {
            actualTitle = response.jsonPath().getString("books.title");
        }
        if (actualTitle == null) {
            actualTitle = response.jsonPath().getString("title");
        }
        Assert.assertEquals("The book title '" + title + "' was not found in the response.", title, actualTitle);
    }

    @Step("Make a PUT request to <endpoint> with userId <userId>, with ISBN <isbn> and new ISBN <newIsbn>")
    public void makePutRequest(String endpoint, String userId, String isbn, String newIsbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getToken());

        String requestBody = "{ \"userId\": \"" + userId + "\", \"isbn\": \"" + newIsbn + "\" }";
        String updatedEndpoint = endpoint.replace("{ISBN}", isbn);
        response = request.body(requestBody).put(updatedEndpoint);
    }

    @Step("Make a PUT request to <endpoint> with userId <userId>, with ISBN <isbn> and new ISBN <newIsbn> without the Authorization header")
    public void makePutRequestWithNoAuth(String endpoint, String userId, String isbn, String newIsbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String requestBody = "{ \"userId\": \"" + userId + "\", \"isbn\": \"" + newIsbn + "\" }";
        String updatedEndpoint = endpoint.replace("{ISBN}", isbn);
        response = request.body(requestBody).put(updatedEndpoint);
    }

    @Step("Make a DELETE request to <endpoint> with userId <userId> and ISBN <isbn>")
    public void makeDeleteRequest(String endpoint, String userId, String isbn) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getToken());

        String requestBody = "{ \"isbn\": \"" + isbn + "\", \"userId\": \"" + userId + "\" }";
        response = request.body(requestBody).delete(endpoint);
    }

    private String getToken() {
        return (String) SpecDataStore.get("token");
    }
}
