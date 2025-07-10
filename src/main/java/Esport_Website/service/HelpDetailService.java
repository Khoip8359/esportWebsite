package Esport_Website.service;

import Esport_Website.entity.HelpDetail;
import java.util.List;

public interface HelpDetailService {
    List<HelpDetail> findByHelpId(Integer helpId);
    HelpDetail save(HelpDetail detail);
    List<HelpDetail> findBySenderUserId(String userId);
    List<HelpDetail> findByHelp_Merge(String merge);
    List<HelpDetail> findBySender(String sender);
}
