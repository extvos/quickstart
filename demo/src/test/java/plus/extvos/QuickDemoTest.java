package plus.extvos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuickDemoTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testQuick() {
        try {
            System.out.println("DevExampleApplication.run:> ");
            Connection conn = dataSource.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("DevExampleApplication.run:>  database product: " + meta.getDatabaseProductName());
            System.out.println("DevExampleApplication.run:>  database driver name: " + meta.getDriverName());
            System.out.println("DevExampleApplication.run:>  database driver version: " + meta.getDriverVersion());
            ResultSet ts = meta.getTables(null, "", null,
                new String[]{"TABLE", "VIEW"});
            while (ts.next()) {
                System.out.println(ts.getString("TABLE_NAME") + "  "
                    + ts.getString("TABLE_TYPE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
