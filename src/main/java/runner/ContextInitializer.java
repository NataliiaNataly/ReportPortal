package runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextInitializer {
    public ApplicationContext initialize(String[] locations) {
        return new ClassPathXmlApplicationContext(locations, true);
    }
}
