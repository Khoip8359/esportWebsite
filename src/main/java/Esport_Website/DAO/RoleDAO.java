package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer>{

}
