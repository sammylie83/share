package ist.challenge.lie_samsudin.challenge;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


import java.util.HashMap;
import java.util.Map;


@SpringBootApplication

@ComponentScan({"com.delivery.service","com.delivery.request"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class LieSamsudinChallengeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LieSamsudinChallengeApplication.class, args);
		

	    SpringApplication app = new SpringApplication(LieSamsudinChallengeApplication.class);
	        
		Map<String, Object> customConfig = new HashMap<>();
        customConfig.put("server.port", "8081");

        app.setDefaultProperties(customConfig);

        app.run(args);
	}

}
