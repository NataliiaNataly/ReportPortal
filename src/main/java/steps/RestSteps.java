package steps;

import static org.testng.AssertJUnit.assertEquals;

import org.jbehave.core.annotations.When;
import utils.CustomLogger;
import utils.dto.Post;
import java.util.List;

public class RestSteps extends BaseSteps {

    @When("via Rest request to '$enpoint' endpoint, user verifies that the number of posts is '$number'")
    public void getLaunchesViaRest(String endpoint, int number) {
        List<Post> posts = apiClient().getResponse(endpoint).body()
            .jsonPath().getList(".", Post.class);
        CustomLogger.getLogger().info("The actual number of posts is: {}", posts.size());
        assertEquals(number, posts.size());
    }
}
