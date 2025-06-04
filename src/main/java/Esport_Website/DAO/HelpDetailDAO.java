package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.HelpDetail;

@Repository
public interface HelpDetailDAO extends JpaRepository<HelpDetail, Integer>{

}
