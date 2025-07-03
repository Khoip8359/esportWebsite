package Esport_Website.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.NewsLetter;

@Repository
public interface NewsLetterDAO extends JpaRepository<NewsLetter, Integer>{
	Optional<NewsLetter> findByEmail(String email);
}
