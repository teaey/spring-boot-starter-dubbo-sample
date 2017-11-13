package cn.teaey.springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xiaofei.wxf(teaey)
 * @since 0.0.0
 */
@SpringBootApplication
public class Client {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Client.class, args);
        AbcService bean = run.getBean(AbcService.class);
        String result = bean.echoService.echo( "abccc" );
        System.out.println( result );
    }
}
