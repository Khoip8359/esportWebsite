package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.HelpDetail;

@Repository
public interface HelpDetailDAO extends JpaRepository<HelpDetail, Integer>{
    java.util.List<HelpDetail> findByHelp_HelpId(Integer helpId);

    @Query("SELECT d FROM HelpDetail d WHERE d.sender = :userId")
    java.util.List<HelpDetail> findBySenderUserId(@Param("userId") String userId);

    java.util.List<HelpDetail> findByHelp_Merge(String merge);
    
    java.util.List<HelpDetail> findBySender(String sender);
}
