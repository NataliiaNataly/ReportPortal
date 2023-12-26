package reporter;

import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.NullStoryReporter;
import utils.CustomLogger;
import java.util.Map;

public class CustomStoryReporter extends NullStoryReporter {

    @Override
    public void successful(String step) {
        super.successful(step);
        CustomLogger.getLogger().info("Finished: {}", step);
    }

    @Override
    public void failed(String step, Throwable cause) {
        super.failed(step, cause);
        CustomLogger.getLogger().error("Failed: {}", step);
        CustomLogger.getLogger().error("Cause: {}", cause.getCause().getMessage());
    }

    @Override
    public void notPerformed(String step) {
        CustomLogger.getLogger().warn("Skipped: {}", step);
        super.notPerformed(step);
    }

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        clearEmptySteps(story, givenStory);
    }

    @Override
    public void example(Map<String, String> tableRow, int exampleIndex) {
        CustomLogger.getLogger().info("--------------------------------------------------");
        CustomLogger.getLogger().info("EXAMPLE'{}' IS RUNNING...", exampleIndex + 1);
        CustomLogger.getLogger().info("--------------------------------------------------");
        super.example(tableRow, exampleIndex);
    }

    private void clearEmptySteps(Story story, boolean givenStory) {
        if (!"BeforeStories".equals(story.getPath()) && !"AfterStories".equals(story.getPath())) {
            super.beforeStory(story, givenStory);
        }
    }
}
