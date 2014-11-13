package name.danielrobert;

import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:name/danielrobert/amqp/config/prod-events-context.xml")
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(Application.class)
            .addCommandLineProperties(true)
            .registerShutdownHook(true)
            .listeners(new ApplicationPidListener())
            .run();
    }

}
