package com.github.soilex.xshop.doc;

import com.github.soilex.xshop.jwt.JwtAuthenticationConfig;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@ConfigurationProperties(prefix = "info")
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Getter
@Setter
public class SwaggerConfig {
    private String name;
    private String version;
    private String description;
    private String developer;
    private String email;
    private String terms;

    @Autowired
    private JwtAuthenticationConfig jwtConfig;

    @Bean
    public Docket apiDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(name)
                .version(version)
                .description(description)
                .contact(new Contact(developer, "", email))
                .termsOfServiceUrl(terms)
                .build();

        Parameter tokenParameter = new ParameterBuilder() {{
            name(jwtConfig.getTokenHeaderName());
            description("jwt token");
            modelRef(new ModelRef("string"));
            parameterType("header");
            required(false);
        }}.build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Arrays.asList(tokenParameter))
                .apiInfo(apiInfo);
    }
}
