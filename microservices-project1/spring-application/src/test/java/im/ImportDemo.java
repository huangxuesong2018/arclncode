package im;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-21Student.class
 */
public class ImportDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
        Student s =  context.getBean(Student.class.getName(),Student.class);
        System.out.println(s);

    }
}
