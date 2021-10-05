package hantczak.studentDataStorage;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentDataStorageApplicationTests {

    protected RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    protected int port;

    protected String buildUrl(String endpointName) {
        String urlBase = "http://localhost:" + port;
        String wholeUrl = urlBase + "/" + endpointName;
        return wholeUrl;
    }

    protected String buildUrl(String endpointName, String parameterName, String parameterValue) {
        String urlBase = "http://localhost:" + port;
        String wholeUrl = urlBase + "/" + endpointName;
        wholeUrl = wholeUrl + "?" + parameterName + "=" + parameterValue;
        return wholeUrl;
    }

    protected String buildUrl(String endpointName, String parameterName, String parameterValue, String parameter2Name, String parameter2Value) {
        String urlBase = "http://localhost:" + port;
        String wholeUrl = urlBase + "/" + endpointName;
        wholeUrl = wholeUrl + "?" + parameterName + "=" + parameterValue + "&" + parameter2Name + "=" + parameter2Value;
        return wholeUrl;
    }

    protected String buildUrlWithPathArgumentForStudent(Long id) {
        return buildUrl("students") + "/" + String.valueOf(id);
    }

    protected String buildUrlWithPathArgumentForGrade(Long id) {
        return buildUrl("students") + "/" + String.valueOf(id) + "/grades";
    }

    protected String buildUrlWithPathArgumentForAverage(Long id) {
        return buildUrl("averages") + "/" + String.valueOf(id);
    }
}
