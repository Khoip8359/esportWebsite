package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.UserTransaction;

@Repository
public interface UserTransactionDAO extends JpaRepository<UserTransaction, Integer>{

}
