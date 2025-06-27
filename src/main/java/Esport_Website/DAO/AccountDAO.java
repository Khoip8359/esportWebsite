package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Account;
import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}

