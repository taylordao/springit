package us.daofamily.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.daofamily.springit.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
