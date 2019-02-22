package com.springinaction.chapter_05;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    /**
     * getRootConfigClasses() 方法返回的带有 @Configuration 注解的类将
     * 会用来配置 ContextLoaderListener 创建的应用上下文中的 bean
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * GetServlet-ConfigClasses() 方法返回的带有 @Configuration 注解的
     * 类将会用来定义 DispatcherServlet 应用上下文中的 bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //
    }
}
