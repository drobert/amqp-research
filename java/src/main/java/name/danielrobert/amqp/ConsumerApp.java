package name.danielrobert.amqp;

import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:name/danielrobert/amqp/config/consumer-context.xml")
public class ConsumerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(ConsumerApp.class)
            .addCommandLineProperties(true)
            .registerShutdownHook(true)
            .listeners(new ApplicationPidListener("consumer.pid"))
            .run();
    }

}
