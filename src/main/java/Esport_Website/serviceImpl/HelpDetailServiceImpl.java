package Esport_Website.serviceImpl;

import Esport_Website.entity.HelpDetail;
import Esport_Website.DAO.HelpDetailDAO;
import Esport_Website.service.HelpDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HelpDetailServiceImpl implements HelpDetailService{
    @Autowired
    private HelpDetailDAO helpDetailDAO;

    @Override
    public List<HelpDetail> findByHelpId(Integer helpId) {
        return helpDetailDAO.findByHelp_HelpId(helpId);
    }
    
    public HelpDetail save(HelpDetail detail) {
        return helpDetailDAO.save(detail);
    }

    @Override
    public List<HelpDetail> findBySenderUserId(String userId) {
        return helpDetailDAO.findBySenderUserId(userId);
    }

    @Override
    public List<HelpDetail> findByHelp_Merge(String merge) {
        return helpDetailDAO.findByHelp_Merge(merge);
    }
    
    @Override
    public List<HelpDetail> findBySender(String sender) {
        return helpDetailDAO.findBySender(sender);
    }
}
