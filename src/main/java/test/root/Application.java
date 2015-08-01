package test.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import test.root.entities.User;
import test.root.entities.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Filip on 2015-07-31.
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
class Application{


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
