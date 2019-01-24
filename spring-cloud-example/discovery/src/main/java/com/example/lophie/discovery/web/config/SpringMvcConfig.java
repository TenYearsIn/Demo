package com.example.lophie.discovery.web.config;

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

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer{
    /**
     * log
     */
    private static final Logger logger = LoggerFactory.getLogger(SpringMvcConfig.class);

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
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

    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<MediaType>();
        //Add whatever media types you want to support here.
        list.add(MediaType.MULTIPART_FORM_DATA);
        return list;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
