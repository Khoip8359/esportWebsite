package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
