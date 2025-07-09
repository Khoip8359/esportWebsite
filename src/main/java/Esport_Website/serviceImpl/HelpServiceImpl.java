package Esport_Website.serviceImpl;

import Esport_Website.entity.Help;
import Esport_Website.DAO.HelpDAO;
import Esport_Website.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HelpServiceImpl implements HelpService{
    @Autowired
    private HelpDAO helpDAO;

    @Override
    public Help findById(Integer helpId) {
        Optional<Help> help = helpDAO.findById(helpId);
        return help.orElse(null);
    }

    @Override
    public Help findBySenderId(Integer userId) {
        return helpDAO.findBySender_UserId(userId);
    }

    @Override
    public Help save(Help help) {
        return helpDAO.save(help);
    }
}
