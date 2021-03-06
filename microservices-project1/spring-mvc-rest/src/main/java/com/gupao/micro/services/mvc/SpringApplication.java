package com.gupao.micro.services.mvc;

import com.gupao.micro.services.mvc.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

@ComponentScan(basePackages = "com.gupao.micro.services.mvc.service")
@EnableTransactionManagement
public class SpringApplication {

    @Component("myTxName")
    public static class MyPlatformTransactionManager implements PlatformTransactionManager{
        @Override
        public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
            return new DefaultTransactionStatus(null,true,
                    true,definition.isReadOnly(),true,null);
        }

        @Override
        public void commit(TransactionStatus transactionStatus) throws TransactionException {
            System.out.println("commit()...");
        }

        @Override
        public void rollback(TransactionStatus transactionStatus) throws TransactionException {
            System.out.println("rollback()...");
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringApplication.class);
        context.refresh();

        context.getBeansOfType(EchoService.class).forEach((beanName,bean)->{
            System.err.println("Bean Name:"+beanName+",Bean"+bean);
            bean.echo("Hello,he hie");
        });

        context.close();
    }
}
