package plus.extvos.example;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
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
        DataSource postgres = null, clickhouse = null;
        if (dataSource instanceof DynamicRoutingDataSource) {
            DynamicRoutingDataSource dds = (DynamicRoutingDataSource) dataSource;
            postgres = dds.getDataSource("master");
            if (null == postgres) {
                throw new Exception("can not get master datasource");
            }
            clickhouse = dds.getDataSource("clickhouse");
            if (null == clickhouse) {
                throw new Exception("can not get clickhouse datasource");
            }
        } else {
            postgres = dataSource;
        }
        String[] tableNames = new String[]{
            Student.class.getAnnotation(TableName.class).value(),
            StudentScore.class.getAnnotation(TableName.class).value(),
            Teacher.class.getAnnotation(TableName.class).value(),
        };
        DatabaseHelper dh = DatabaseHelper.with(postgres);
        if (dh.tableAbsent(tableNames) > 0) {
            dh.runScriptsIfMySQL("sql/mysql/1.students.sql", "sql/mysql/2.student-scores.sql", "sql/mysql/3.teachers.sql");
            dh.runScriptsIfPostgreSQL("sql/pg/1.students.sql", "sql/pg/2.student-scores.sql", "sql/pg/3.teachers.sql");
        }
        if (null != clickhouse) {
            DatabaseHelper dhch = DatabaseHelper.with(clickhouse);
            dhch.runScripts("sql/clickhouse/1.demo_access.sql");
        }
        args.getOptionNames().forEach(k -> {
            args.getOptionValues(k).forEach(v -> {
                System.out.println(">>>> " + k + " = " + v);
            });
        });
    }
}
