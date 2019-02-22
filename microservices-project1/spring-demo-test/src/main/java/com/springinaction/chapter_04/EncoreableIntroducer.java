package com.springinaction.chapter_04;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Aspect
@Component
public class EncoreableIntroducer {
    /**
     *  @DeclareParents 注解由三部分组成：
     *   1. value 属性指定了哪种类型的 bean 要引入该接口。在本例中，也就是所有实现 Performance 的类型。（标记符后面的加号表示
     *    是 Performance 的所有子类型，而不是 Performance 本身。）
     *   2. defaultImpl 属性指定了为引入功能提供实现的类。在这里，我们指定的是 DefaultEncoreable 提供实现。
     *   3. @DeclareParents 注解所标注的静态属性指明了要引入了接口。在这里，我们所引入的是 Encoreable 接口。
     */
    @DeclareParents(value = "com.springinaction.chapter_04.Performance+",
        defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
