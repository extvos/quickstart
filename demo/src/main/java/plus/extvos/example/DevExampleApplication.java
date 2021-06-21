package plus.extvos.example;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import plus.extvos.builtin.geo.entity.Address;
import plus.extvos.builtin.geo.service.AddressService;
import plus.extvos.common.pinyin.Pinyin;
import plus.extvos.example.entity.Student;
import plus.extvos.example.entity.StudentScore;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;


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
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AddressService addressService;

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/dev");
        SpringApplication.run(DevExampleApplication.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("DevExampleApplication.run:> " + args.toString());
        Connection conn = dataSource.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(dataSource.getLogWriter());
        String[] tableNames = new String[]{
                Student.class.getAnnotation(TableName.class).value(),
                StudentScore.class.getAnnotation(TableName.class).value(),
        };
        for (int i = 0; i < tableNames.length; i++) {
            tableNames[i] = "'" + tableNames[i] + "'";
        }
        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*)  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = Database() AND TABLE_NAME IN (" + String.join(",", tableNames) + ");");
        ResultSet rs = statement.executeQuery();
        rs.next();
        int n = rs.getInt(1);
        rs.close();
        if (n < tableNames.length) {
            String[] sqlFiles = {"sql/1.students.sql", "sql/2.student-scores.sql"};
            for (String path : sqlFiles) {
                Reader reader = Resources.getResourceAsReader(path);
                //执行SQL脚本
                runner.runScript(reader);
                //关闭文件输入流
                reader.close();
            }
        }
        args.getOptionNames().forEach(k -> {
            args.getOptionValues(k).forEach(v -> {
                System.out.println(">>>> " + k + " = " + v);
            });
        });
        if (false) {
            Pinyin py = new Pinyin();
            QueryWrapper<Address> qw = new QueryWrapper<>();
            qw.orderByAsc("id");
            long total = addressService.countByWrapper(qw);
            long offset = 0L;
            long limit = 100L;
            for (offset = 0L; offset + limit < total; offset += limit) {
                QueryWrapper<Address> qw1 = new QueryWrapper<>();
                qw1.orderByAsc("id");
                qw1.last("LIMIT " + limit + " OFFSET " + offset);
                List<Address> addressList = addressService.selectByWrapper(qw1);
                for (Address addr : addressList) {
                    addr.setPinyin(py.translateNoMark(addr.getName()));
                    addr.setPinyinInitial(py.translateFirstChar(addr.getName()));
                    System.out.println(">> Update " + addr.getId() + " " + addr.getName() +
                            " -> " + addr.getPinyin() + "," + addr.getPinyinInitial());
                    addressService.updateById(addr.getId(), addr);
                }
            }
        }
    }
}
