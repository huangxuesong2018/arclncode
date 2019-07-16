package com.arcln.pattern.single;

/**
 * 双重检查锁-有问题的
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class SingleClass3 {
    private static SingleClass3 instance;
    private String initDate = "";
    private SingleClass3(String initDate){
        this.initDate = initDate;
    }

    public static SingleClass3 getInstance(){
        if (instance == null){//1
            synchronized (SingleClass3.class){//2
                /**
                 instance = new SingleClass3()
                 执行了下列伪代码(无序执行)：
                    mem = allocate();             //为singleton对象分配内存.
                    instance = mem;               //Note that instance is now non-null, but has not been initialized.
                    ctorSingleton(instance);      //Invoke constructor for Singleton passing instance

                 instance = mem 这个代码执行后，instance就不为null了，只是还没有调用ctorSingleton 进行初始化，
                 此时，另外一个线程执行到  代码1时，则会返回一个没有被初始化的实例，这个问题引发的问题更加无法预测
                 怎么办 {@link SingleClass4}
                 */
                if (instance == null){
                    instance = new SingleClass3("init");//3
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "SingleClass3{" +
                "initDate='" + initDate + '\'' +
                '}';
    }
}
