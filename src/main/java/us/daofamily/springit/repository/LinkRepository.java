package us.daofamily.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.daofamily.springit.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByTitle(String title);
    Link findByUrl(String url);
}
