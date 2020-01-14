package us.daofamily.springit;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import us.daofamily.springit.config.SpringitProperties;
import us.daofamily.springit.domain.Link;
import us.daofamily.springit.repository.CommentRepository;
import us.daofamily.springit.repository.LinkRepository;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

@Bean
    CommandLineRunner runner (LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            Map<String, String> links = new HashMap<>();
            links.put("Securing Spring Boot APIs and SPAs with OAuth 2.0", "https://auth0.com/blog/securing-spring-boot-apis-and-spas-with-oauth2/?utm_source=reddit&utm_medium=sc&utm_campaign=springboot_spa_securing");
            links.put("Easy way to detect Device in Java Web Application using Spring Mobile - Source code to download from GitHub", "https://www.opencodez.com/java/device-detection-using-spring-mobile.htm");
            links.put("Tutorial series about building microservices with SpringBoot (with Netflix OSS)", "https://medium.com/@marcus.eisele/implementing-a-microservice-architecture-with-spring-boot-intro-cdb6ad16806c");
            links.put("Detailed steps to send encrypted email using Java / Spring Boot - Source code to download from GitHub", "https://www.opencodez.com/java/send-encrypted-email-using-java.htm");
            links.put("Build a Secure Progressive Web App With Spring Boot and React", "https://dzone.com/articles/build-a-secure-progressive-web-app-with-spring-boo");
            links.put("Building Your First Spring Boot Web Application - DZone Java", "https://dzone.com/articles/building-your-first-spring-boot-web-application-ex");
            links.put("Building Microservices with Spring Boot Fat (Uber) Jar", "https://jelastic.com/blog/building-microservices-with-spring-boot-fat-uber-jar/");
            links.put("Spring Cloud GCP 1.0 Released", "https://cloud.google.com/blog/products/gcp/calling-java-developers-spring-cloud-gcp-1-0-is-now-generally-available");
            links.put("Simplest way to Upload and Download Files in Java with Spring Boot - Code to download from Github", "https://www.opencodez.com/uncategorized/file-upload-and-download-in-java-spring-boot.htm");
            links.put("Add Social Login to Your Spring Boot 2.0 app", "https://developer.okta.com/blog/2018/07/24/social-spring-boot");
            links.put("File download example using Spring REST Controller", "https://www.jeejava.com/file-download-example-using-spring-rest-controller/");

            links.forEach((k, v) -> {
                linkRepository.save(new Link(k, v));
                // we will do something with comments later
            });

            long linkCount = linkRepository.count();
            System.out.println("Number of links in the database: " + linkCount);
        };
    }

    @Bean
    PrettyTime prettyTime(){
        return new PrettyTime();
    }
}
