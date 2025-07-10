package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Help;

@Repository
public interface HelpDAO extends JpaRepository<Help, Integer>{

    Help findBySender_UserId(Integer userId);
    Help findByMerge(String merge);
    java.util.List<Help> findBySender_UserIdOrReceiver_UserId(Integer senderUserId, Integer receiverUserId);
}
