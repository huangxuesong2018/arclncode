import com.gupao.micro.service.spring.cloud.ds.client.annotation.EnableRestClient;
import com.gupao.micro.service.spring.cloud.ds.client.annotation.RestClient;
import com.gupao.micro.service.spring.cloud.ds.client.service.rest.client.SayingRestService;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-08
 */
@RestClient(name = "a")
@EnableRestClient(clients = {SayingRestService.class,SayingRestService.class})
public class TestDemo {
    public static void main(String[] args) {

        RestClient restClient = AnnotationUtils.findAnnotation(TestDemo.class,RestClient.class);
        EnableRestClient enableRestClient = AnnotationUtils.findAnnotation(TestDemo.class,EnableRestClient.class);
        Class[] classes = enableRestClient.clients();
        String name = restClient.name();
        System.out.println(name);
        Class[]  params = new Class[]{};
        TestDemo testDemo = new TestDemo();


    }
    public void methodOne(){

    }
}
