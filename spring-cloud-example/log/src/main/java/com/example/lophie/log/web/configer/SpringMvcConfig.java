package com.example.lophie.log.web.configer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义springmvc的配置，相当于spring-servlet.xml
 * Created by 17020258 on 2017/11/5.
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer{
    //log
    private static final Logger logger = LoggerFactory.getLogger(SpringMvcConfig.class);

    /**
     * 使用默认的静态资源处理
     * 相当于 <mvc:default-servlet-handler/>
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //配置文件上传处理器
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxUploadSizePerFile(300*1024*1024l);//单个文件上传的大小
        resolver.setMaxUploadSize(1000*1024*1024l);//上传文件大小 1000M 50*1024*1024
        return resolver;
    }


    /**
     * 配置消息转换器
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        byteArrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());

        // <!-- 将StringHttpMessageConverter的默认编码设为UTF-8 -->
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //  <!--将Jackson2HttpMessageConverter的默认格式化输出设为true -->
        jackson2HttpMessageConverter.setPrettyPrint(true);

        converters.add(byteArrayHttpMessageConverter);
        converters.add(stringHttpMessageConverter);
        converters.add(jackson2HttpMessageConverter);
        //add other converters here
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<MediaType>();
        //Add whatever media types you want to support here.
        list.add(MediaType.MULTIPART_FORM_DATA);
        return list;
    }


    /**
     * 配置静态资源的访问
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        //添加对swagger ui 页面的访问
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
