package org.extvos.example.config;

import org.extvos.builtin.config.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author shenmc
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.extvos"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(getClass().getPackage().getImplementationTitle())
            .description("Last commit by " + GitProperties.get(GitProperties.USERNAME) + " (" +
                GitProperties.get(GitProperties.COMMIT_MSG)
                + ")  \n" +
                "Branch: " + GitProperties.get(GitProperties.BRANCH) + ", Commit:" + GitProperties.get(GitProperties.COMMIT_ABBREV) + "  \n"
                + "@ " + GitProperties.get(GitProperties.COMMIT_TIME))
            .termsOfServiceUrl("http://github.com/extvos")
            .version(getClass().getPackage().getImplementationVersion())
            .build();
    }
}
