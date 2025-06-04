package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{

}
