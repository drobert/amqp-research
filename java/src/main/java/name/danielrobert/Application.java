package name.danielrobert.amqp;

import java.io.IOException;

import name.danielrobert.amqp.config.AmqpConfiguration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath*:name/danielrobert/amqp/config/integration-context.xml");
        
        System.out.println("Press Enter/Return in the console to exit");        
        //String msg = "Mista Dobalina";

        try { System.in.read(); }
        catch (IOException e) { /* meh */ }
        
        context.close();
    }

}
