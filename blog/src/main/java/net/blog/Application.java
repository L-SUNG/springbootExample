package net.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// EnableJpaAuditing와 SpringBootApplication를 분리시키기 위해 주석처리
//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
// 외장톰캣 사용시 SpringBootServletInitializer 상속필요
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
