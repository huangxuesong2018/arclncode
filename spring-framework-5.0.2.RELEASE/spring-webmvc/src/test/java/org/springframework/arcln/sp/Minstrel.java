package org.springframework.arcln.sp;

import java.io.PrintStream;

/**
 * 定义切面的5个步骤
 * 1.定义切面  @Aspect
 * 2.定义切点 {@link Minstrel#embarkOnQuest()}
 * 3.定义通知 {@link Minstrel#singAfterQuest()}  {@link Minstrel#singBeforeQuest()}
 * 4.开启自动代理@EnableAspectJAutoProxy  {@link KnightConfig}
 * 5.装配成Spring中bean  {@link KnightConfig#minstrel()}
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
@Aspect
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    @Pointcut("execution(* com.springinaction.chapter_01.annotationMode.knights.BraveKnight.embarkOnQuest(..))")
    public void embarkOnQuest(){}

    @Before("embarkOnQuest()")
    public void singBeforeQuest() {
        stream.println("【使用注释】Before通知");
    }

    @After("embarkOnQuest()")
    public void singAfterQuest() {
        stream.println("【使用注释】After通知");
    }
}
