package name.danielrobert;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:name/danielrobert/amqp/config/integration-context.xml")
public class Application {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Press Enter/Return in the console to exit");

        try { System.in.read(); }
        catch (IOException e) { /* meh */ }
        
        SpringApplication.exit(ctx);
    }

}
