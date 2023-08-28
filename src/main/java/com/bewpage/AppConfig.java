package com.bewpage;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = "com.bewpage")
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:application-secret.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

  @Bean
  public LocalEntityManagerFactoryBean entityManagerFactory() {
    LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
    emfb.setPersistenceUnitName("weatherAppPersistenceUnit");
    return emfb;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager tm = new JpaTransactionManager(emf);
    return tm;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/views/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Weather API")
        .description("Sample API for Weather App")
        .termsOfServiceUrl("http://www.bewpage.com")
        .contact("BJ")
        .license("bewpage")
        .licenseUrl("http://www.bewpage.com")
        .version("1.0")
        .build();
  }

  @Bean
  public Docket postsApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("public-api")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  // as suggest in this https://github.com/springfox/springfox/issues/2396 by siengsotheara
  // @EnableWebMvc is not compatible with swagger-ui
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(
          org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/byCity/**").allowedOrigins("http://localhost:3000");
      }
    };
  }
}
