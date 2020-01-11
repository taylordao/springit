package us.daofamily.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.daofamily.springit.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
