package name.danielrobert.amqp;

import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:name/danielrobert/amqp/config/producer-context.xml")
public class ProducerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(ProducerApp.class)
            .addCommandLineProperties(true)
            .registerShutdownHook(true)
            .listeners(new ApplicationPidListener("producer.pid"))
            .run();
    }

}
