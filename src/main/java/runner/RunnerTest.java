package runner;

import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import reporter.CustomStoryReporter;
import java.util.List;

public class RunnerTest extends JUnitStories {

    private static final String APPLICATION_CONFIGURATION_FOR_SPRING = "applicationContext.xml";
    private static final String MAX_TIMEOUT_STORY_IN_SECS = "35000";
    private static final String THREADS_COUNT = getProperty("threads.count", "1");

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(getStoryReporterBuilder());
    }

    private StoryReporterBuilder getStoryReporterBuilder() {
        return new StoryReporterBuilder() {
            @Override
            public StoryReporter build(String storyPath) {
                return new CustomStoryReporter();
            }
        };
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        String[] locations = {APPLICATION_CONFIGURATION_FOR_SPRING};
        ContextInitializer initializer = new ContextInitializer();
        return new SpringStepsFactory(configuration(), initializer.initialize(locations));
    }

//    @org.testng.annotations.Test(invocationCount = 0, threadPoolSize = 1)
    @Override
    public void run() {
        final Embedder embedder = configuredEmbedder();
        embedder.useMetaFilters(List.of("+to_run"));
        embedder.embedderControls()
            .useStoryTimeouts(MAX_TIMEOUT_STORY_IN_SECS)
            .useThreads(parseInt(THREADS_COUNT))
            .doIgnoreFailureInStories(true)
            .doIgnoreFailureInView(true);
        embedder.runStoriesAsPaths(storyPaths());
    }

    @Override
    public List<String> storyPaths() {
        return List.of("./stories/Login.story", "stories/Main_Page.story",
            "stories/Rest_Endpoint.story");
    }
}

