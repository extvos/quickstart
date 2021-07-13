package plus.extvos.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Mingcai SHEN
 */
@EntityScan(value = {"plus.extvos.example.entity"})
@ComponentScan({"plus.extvos.example"})
@SpringBootApplication
@EnableConfigurationProperties
@EnableSwagger2
public class DevExamplePetiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevExamplePetiteApplication.class);
    }
}
