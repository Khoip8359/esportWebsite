package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.React;

@Repository
public interface ReactDAO extends JpaRepository<React, Integer>{

}
