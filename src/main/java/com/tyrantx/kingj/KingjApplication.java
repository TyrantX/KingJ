package com.tyrantx.kingj;

import com.tyrantx.kingj.common.XFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

// @MapperScan注解自动扫描Mapper包，无需在每个Mapper类上加@Mapper注解
@SpringBootApplication
@Configuration
@MapperScan("com.tyrantx.kingj.dao")
public class KingjApplication {

    public static void main(String[] args) {
        SpringApplication.run(KingjApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件上传大小
        factory.setMaxFileSize(DataSize.ofMegabytes(100L));
        // 总文件上传大小
        factory.setMaxRequestSize(DataSize.ofGigabytes(1L));
        return factory.createMultipartConfig();
    }

    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XFilter());
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }
}
