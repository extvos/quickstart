
package plus.extvos.example.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Servlet;
import java.util.HashMap;

/**
 * @author shenmc
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.druid-ui.username:admin}")
    private String username;
    @Value("${spring.druid-ui.password:password}")
    private String password;
    @Value("${spring.druid-ui.reset-enabled:false}")
    private String resetEnabled;
    @Value("${spring.druid-ui.allow:}")
    private String allow;


    /**
     * 配置druid管理页面的访问控制
     * 访问网址: http://127.0.0.1:7070/druid
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.druid-ui", name = "enabled", havingValue = "true")
    public ServletRegistrationBean<Servlet> druidServlet() {

        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>();
        //配置一个拦截器
        servletRegistrationBean.setServlet(new StatViewServlet());
        //指定拦截器只拦截druid管理页面的请求
        servletRegistrationBean.addUrlMappings("/druid/*");
        HashMap<String, String> initParam = new HashMap<String, String>(4);
        //登录druid管理页面的用户名
        initParam.put("loginUsername", username);
        //登录druid管理页面的密码
        initParam.put("loginPassword", password);
        //是否允许重置druid的统计信息
        initParam.put("resetEnable", resetEnabled);
        //ip白名单，如果没有设置或为空，则表示允许所有访问
        initParam.put("allow", allow);
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.druid-ui", name = "enabled", havingValue = "true")
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
