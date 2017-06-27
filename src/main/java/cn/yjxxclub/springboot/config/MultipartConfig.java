package cn.yjxxclub.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.MultipartConfigElement;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-26
 * Time: 上午11:09
 * Describe:
 */
@Configuration
public class MultipartConfig {

    @Autowired
    private Environment ev;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(ev.getProperty("spring.http.multipart.max-file-size"));
        multipartConfigFactory.setMaxRequestSize(ev.getProperty("spring.http.multipart.max-request-size"));
        return multipartConfigFactory.createMultipartConfig();
    }
}
