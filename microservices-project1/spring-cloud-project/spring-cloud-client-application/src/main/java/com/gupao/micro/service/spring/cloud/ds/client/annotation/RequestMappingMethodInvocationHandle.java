package com.gupao.micro.service.spring.cloud.ds.client.annotation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-08
 */
public class RequestMappingMethodInvocationHandle implements InvocationHandler{
    private final BeanFactory beanFactory;
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private final String serviceName;

    public RequestMappingMethodInvocationHandle(String serviceName,BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.serviceName = serviceName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤RequestMapping 方法
        GetMapping requestMapping = AnnotationUtils.findAnnotation(method,GetMapping.class);
        if(requestMapping != null){
            //得到URI
            String[] uri = requestMapping.value();
            //http://${serviceName}/${uri}
            StringBuilder urlBuilder = new StringBuilder("http://").append(serviceName).append("/").append(uri[0]);
            //攻取方法参数数量
            int count = method.getParameterCount();
            //方法参数是有顺序的 这里取不到，只能在取类上方法的参数，接口不行
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
            //方法参数类型集合
            Class<?>[] paramTypes = method.getParameterTypes();
            //方法注解集合
            Annotation[][] annotations =  method.getParameterAnnotations();
            StringBuilder queryStringBuilder = new StringBuilder();
            for (int i = 0 ; i < count; i++){
                Annotation[] paramAnnotation = annotations[i];
                Class<?> paramType = paramTypes[i];
                RequestParam requestParam = (RequestParam)paramAnnotation[0];
                if(requestParam != null){
                    String paramName = "";
                    //Http请求参数  public String say(@RequestParam(value = "message") String message)
                    // -> @RequestParam(value = "message")
                    String requestParamNames = StringUtils.hasText(requestParam.value()) ? requestParam.value() : paramName;
                    String requestParamValue =  String.class.equals(paramType) ? (String)args[i] : String.valueOf(args[i]);
                    queryStringBuilder.append("&").append(requestParamNames).append("=").append(requestParamValue);
                }
            }
            String queryString = queryStringBuilder.toString();
            if(StringUtils.hasText(queryString)){
                urlBuilder.append("?").append(queryString);
            }
            //http://${serviceName}/${uri}？${queryString}

            String url = urlBuilder.toString();

            //RestTemplate,Bean
            RestTemplate lbRestTemplate = beanFactory.getBean("loadBalancedRestTemplate",RestTemplate.class);
            return lbRestTemplate.getForObject(url,method.getReturnType());
        }
        return null;
    }
}
