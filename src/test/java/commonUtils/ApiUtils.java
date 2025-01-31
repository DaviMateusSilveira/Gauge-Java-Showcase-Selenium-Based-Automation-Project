package commonUtils;

import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class ApiUtils {

    /**
     * This method is executed before each scenario to authenticate the user
     * and generate a token. The token is stored in the SpecDataStore for use
     * in subsequent API requests within the scenario.
     */
    @BeforeScenario
    public void authenticate() {
        RestAssured.baseURI = "https://demoqa.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String requestBody = "{ \"userName\": \"Davi Silveira\", \"password\": \"Password123!\" }";

        Response tokenResponse = request.body(requestBody).post("/Account/v1/GenerateToken");

        Assert.assertEquals(200, tokenResponse.getStatusCode());
        Assert.assertNotNull(tokenResponse.jsonPath().getString("token"));

        String token = tokenResponse.jsonPath().getString("token");

        SpecDataStore.put("token", token);
    }
}

