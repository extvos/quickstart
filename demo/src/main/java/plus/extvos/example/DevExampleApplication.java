package plus.extvos.example;

import com.baomidou.mybatisplus.annotation.TableName;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import plus.extvos.common.utils.SpringContextHolder;
import plus.extvos.example.entity.Student;
import plus.extvos.example.entity.StudentScore;
import plus.extvos.example.entity.Teacher;
import plus.extvos.restlet.utils.DatabaseHelper;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;


/**
 * @author Mingcai SHEN
 */
@EntityScan(value = {"plus.extvos.example.entity"})
@MapperScan("plus.extvos.example.mapper")
@ComponentScan({"plus.extvos.example"})
@SpringBootApplication
@EnableConfigurationProperties
@EnableSwagger2
public class DevExampleApplication implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(DevExampleApplication.class);
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/dev");
        SpringApplication.run(DevExampleApplication.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("DevExampleApplication.run:> {}", args.toString());
        log.debug("DevExampleApplication.run:> context-path: {}", SpringContextHolder.getProperties("server.servlet.context-path"));
        String[] tableNames = new String[]{
            Student.class.getAnnotation(TableName.class).value(),
            StudentScore.class.getAnnotation(TableName.class).value(),
            Teacher.class.getAnnotation(TableName.class).value(),
        };
        DatabaseHelper dh = DatabaseHelper.with(dataSource);
        if (dh.tableAbsent(tableNames) > 0) {
            dh.runScriptsIfMySQL("sql/mysql/1.students.sql", "sql/mysql/2.student-scores.sql", "sql/mysql/3.teachers.sql");
            dh.runScriptsIfPostgreSQL("sql/pg/1.students.sql", "sql/pg/2.student-scores.sql", "sql/pg/3.teachers.sql");
        }
        args.getOptionNames().forEach(k -> {
            args.getOptionValues(k).forEach(v -> {
                System.out.println(">>>> " + k + " = " + v);
            });
        });
    }
}
