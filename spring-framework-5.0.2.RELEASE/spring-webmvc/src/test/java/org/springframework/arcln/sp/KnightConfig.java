package org.springframework.arcln.sp;

import com.springinaction.chapter_01.Knight;
import com.springinaction.chapter_01.Quest;
import com.springinaction.chapter_01.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用 Java 来描述配置实例
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
@Configuration
@EnableAspectJAutoProxy
public class KnightConfig {

    @Bean
    public Knight knight_a(){
        return new BraveKnight(quest());
    }


    @Bean
    public Quest quest(){
        return new SlayDragonQuest(System.out);
    }

    @Bean
    public Minstrel minstrel(){
        return new Minstrel(System.out);
    }

}
