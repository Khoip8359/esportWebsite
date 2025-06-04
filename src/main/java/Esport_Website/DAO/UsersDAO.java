package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {

}
