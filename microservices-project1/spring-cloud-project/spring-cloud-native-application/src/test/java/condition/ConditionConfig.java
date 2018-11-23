package condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ConditionConfig {
    @Bean("man")
    @Conditional(ManCondition.class)
    public Man getMan(){
        return new Man();
    }

    /**
     * 只有 WomanCondition 中 matches方法返回true 才会启用配置
     * @return
     */
    @Bean("woMan")
    @Conditional(WomanCondition.class)
    public Woman getWoMan(){
        return new Woman();
    }
}
