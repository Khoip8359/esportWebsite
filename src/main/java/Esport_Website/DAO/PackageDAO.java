package Esport_Website.DAO;

import Esport_Website.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDAO extends JpaRepository<Package, Integer>{

}
