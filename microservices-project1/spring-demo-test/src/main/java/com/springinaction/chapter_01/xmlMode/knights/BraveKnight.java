package com.springinaction.chapter_01.xmlMode.knights;

import com.springinaction.chapter_01.Knight;
import com.springinaction.chapter_01.Quest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class BraveKnight implements Knight,
        BeanNameAware,BeanFactoryAware,ApplicationContextAware {
    //这个地方使用构造注入
    private Quest quest;

    /**
     * AnnotationConfigApplicationContextStarter 方式启动时 会调用
     */
    @PostConstruct
    public void init(){
        System.out.println("BraveKnight 使用 @PostConstruct 初始");
    }

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest(){
        quest.embark();
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BraveKnight 实现了 BeanNameAware 接口--"+name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BraveKnight实现了 BeanFactoryAware 接口--");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("BraveKnight实现了 ApplicationContextAware 接口--");
    }

}
