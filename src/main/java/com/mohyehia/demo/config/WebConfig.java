package com.mohyehia.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private final MessageSource messageSource;
    private final String[] RESOURCE_HANDLERS = {
            "/webjars/**", "/css/**", "/js/**", "/img/**"
    };
    private final String[] RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/webjars/",
            "classpath:/static/css/",
            "classpath:/static/js/",
            "classpath:/static/img/"
    };

    public WebConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCE_HANDLERS)
                .addResourceLocations(RESOURCE_LOCATIONS);
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }
}
