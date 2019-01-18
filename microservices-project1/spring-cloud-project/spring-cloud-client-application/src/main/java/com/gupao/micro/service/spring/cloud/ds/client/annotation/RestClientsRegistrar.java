package com.gupao.micro.service.spring.cloud.ds.client.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 借助ImportBeanDefinitionRegistrar接口实现bean的动态注入
 * @author HXS
 * @copyright
 * @since 2019-01-07
 */
public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar,BeanFactoryAware,EnvironmentAware{
    private BeanFactory beanFactory;
    private Environment environment;
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassLoader classLoader = metadata.getClass().getClassLoader();

        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(EnableRestClient.class.getName());

        // attributes -> {clients:SayingRestService}
        // @EnableRestClient(clients = SayingRestService.class)
        Class<?>[] clientClasses = (Class<?>[])attributes.get("clients");
        //接口类对象数组
        //筛选
        Stream.of(clientClasses)
                .filter(Class::isInterface)//仅选择接口
                .filter(interfaceClass->{//查找带有 @RestClient 注解的接口
                    return AnnotationUtils.findAnnotation(interfaceClass,RestClient.class) != null;
                })
                .forEach(restClientClass -> {
                    //获取@RestClient元信息
                    RestClient restClient =  AnnotationUtils.findAnnotation(restClientClass,RestClient.class);
                    String serviceName = environment.resolvePlaceholders(restClient.name());
                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass},
                            new RequestMappingMethodInvocationHandle(serviceName,beanFactory));

                    RestClientsRegistrar.registerBeanByFactoryBean(serviceName,proxy,restClientClass,registry);
                });
    }

    /**
     * 第二种方法 注册 Bean
     * @param serviceName
     * @param proxy
     * @param registry
     */
    private static void registerBeanBySingletonBeanRegistry(String serviceName,Object proxy, BeanDefinitionRegistry registry){
        if(registry instanceof SingletonBeanRegistry){
            String beanName = "RestClient."+serviceName;
            SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry)registry;
            singletonBeanRegistry.registerSingleton(beanName,proxy);
        }
    }
    /**
     * 第一种方法 注册 Bean
     * //利用FactoryBean得到BeanDefinition 从而实现注册
     * @param serviceName
     * @param proxy
     * @param restClientClass
     * @param registry
     */
    private static void registerBeanByFactoryBean(String serviceName,Object proxy,Class<?>restClientClass, BeanDefinitionRegistry registry){
        String beanName = "RestClient."+serviceName;
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);
        beanDefinitionBuilder.addConstructorArgValue(proxy);
        beanDefinitionBuilder.addConstructorArgValue(restClientClass);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName,beanDefinition);
    }


    private static class RestClientClassFactoryBean implements FactoryBean{
        private final Class<?> restClientClass;
        private final Object proxy;
        private RestClientClassFactoryBean(Object proxy,Class<?> restClientClass) {
            this.restClientClass = restClientClass;
            this.proxy = proxy;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
