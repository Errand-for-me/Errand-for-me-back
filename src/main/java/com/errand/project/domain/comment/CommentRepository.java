package com.errand.project.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.bulletTitle = :bulletTitle ORDER BY c.postTime DESC")
    List<Comment> findByBulletTitle(String bulletTitle);
}
