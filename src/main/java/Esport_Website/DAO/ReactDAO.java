package Esport_Website.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.React;

@Repository
public interface ReactDAO extends JpaRepository<React, Integer>{
	Optional<Integer> countByNews_NewsId(Integer newsId);
	
	Optional<React> findByUser_UserIdAndNews_NewsId(Integer userId, Integer newsId);
}
