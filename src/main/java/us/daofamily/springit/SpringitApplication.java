package us.daofamily.springit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import us.daofamily.springit.config.SpringitProperties;
import us.daofamily.springit.domain.Comment;
import us.daofamily.springit.domain.Link;
import us.daofamily.springit.repository.CommentRepository;
import us.daofamily.springit.repository.LinkRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);
    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    //@Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args -> {
            Link link = new Link("Spring Boots 2", "https://github.com");
            linkRepository.save(link);

            Comment comment = new Comment("Spring boots 2 is very interesting!", link);
            commentRepository.save(comment);

        };
    }
}
