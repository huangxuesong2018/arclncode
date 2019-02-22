package com.springinaction.chapter_03.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
public class MyProfileCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //取出环境变量中配置的
        String myProfilesValue = context.getEnvironment().getProperty("spring.myProfile.default");
        if(!StringUtils.hasText(myProfilesValue)){
            return false;
        }
        /**
         * 当前要实例的Bean({@link BeanConfig#magicBeanA()},或者{@link BeanConfig#magicBeanB()})
         * 的@MyProfile属性值
         */
        MultiValueMap<String,Object> attr =  metadata.getAllAnnotationAttributes(MyProfile.class.getName());
        if (attr != null){
            for (Object value : attr.get("value")) {
                if(myProfilesValue.equals((String) value)){
                    return true;
                }
            }
        }
        return false;
    }
}
