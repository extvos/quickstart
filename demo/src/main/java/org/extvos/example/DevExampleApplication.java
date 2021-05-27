package org.extvos.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;


/**
 * @author shenmc
 */
@EntityScan(value = {"org.extvos.example.entity"})
@MapperScan("org.extvos.example.mapper")
@ComponentScan({"org.extvos.example"})
@SpringBootApplication
@EnableConfigurationProperties
@EnableSwagger2
public class DevExampleApplication implements CommandLineRunner {
    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/dev");
        SpringApplication.run(DevExampleApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Connection conn = dataSource.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(dataSource.getLogWriter());
        String[] sqlFiles = {"sql/1.students.sql"};
        for (String path : sqlFiles) {
            Reader reader = Resources.getResourceAsReader(path);
            //执行SQL脚本
            runner.runScript(reader);
            //关闭文件输入流
            reader.close();
        }
    }
}
