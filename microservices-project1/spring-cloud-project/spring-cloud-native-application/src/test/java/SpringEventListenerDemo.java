import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Properties;

public class SpringEventListenerDemo {
    public static void main(String[] args) {
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringEventListenerDemo.class);
    }

}
