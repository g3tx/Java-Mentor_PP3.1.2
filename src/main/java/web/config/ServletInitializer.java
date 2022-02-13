package web.config;

import web.SpringCrudApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringCrudApplication.class);
	}
}
//https://betacode.net/11903/deploy-spring-boot-application-on-tomcat-server