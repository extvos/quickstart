package plus.extvos.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.extvos.builtin.version.config.GitProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mingcai SHEN
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "spring.swagger", name = "disabled", havingValue = "false", matchIfMissing = true)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("DEMO程序")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("plus.extvos.example"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("DEMO程序")
            .description("Last commit by " + GitProperties.get(GitProperties.USERNAME) + " (" +
                GitProperties.get(GitProperties.COMMIT_MSG)
                + ")  \n" +
                "Branch: " + GitProperties.get(GitProperties.BRANCH) + ", Commit:" + GitProperties.get(GitProperties.COMMIT_ABBREV) + "  \n"
                + "@ " + GitProperties.get(GitProperties.COMMIT_TIME))
            .termsOfServiceUrl("https://github.com/extvos/quickstart/raw/develop/LICENSE")
            .version(getClass().getPackage().getImplementationVersion())
            .build();
    }
}
