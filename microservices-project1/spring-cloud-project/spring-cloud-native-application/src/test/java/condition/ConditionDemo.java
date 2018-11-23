package condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

public class ConditionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        context.getBeansOfType(Person.class).forEach((beanName,bean)->{
            System.err.println("Bean Name:"+beanName+" ->"+bean);
            ;
        });

    }
}
