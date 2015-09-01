package net.woniper.spring.board.mybatis.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by woniper on 15. 3. 25..
 */
@EnableWebMvc
@Configuration
@ComponentScan("net.woniper.spring.board.mybatis")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private final static String JDBC_CONFIG_PATH = "META-INF/jdbc.xml";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer pp = new PropertyPlaceholderConfigurer();
        pp.setLocations(new Resource[]{new ClassPathResource(JDBC_CONFIG_PATH)});
        return pp;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
