package com.springinaction.chapter_03.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
public class MagicBeanCondition implements Condition {
    /**
     * 检查环境中是否存在名为 magic 的环境属性,只要属性存在即可满足要求
     * magic 的配置 由 {@link BeanConfig} 加载进来 @PropertySource(value = "classpath:condition.properties")
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return "1".equals(environment.getProperty("magic"));
    }
}
