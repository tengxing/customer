package cn.yjxxclub.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-5-10
 * Time: 上午10:40
 * Describe: 程序入口
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Mainspringboot extends SpringBootServletInitializer {

    /*Mainspringboot(){
        super();
        setRegisterErrorPageFilter(false); // <- this one
    }*/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Mainspringboot.class);
    }

    public static void main(String[] args){
        System.out.println("--------exe------------");
        SpringApplication.run(Mainspringboot.class,args);
    }
}
