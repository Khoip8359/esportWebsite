package Esport_Website.service;

import Esport_Website.entity.Help;

public interface HelpService {
    Help findById(Integer helpId);
    Help findBySenderId(Integer userId);
    Help save(Help help);
    Help findByMerge(String merge);
    java.util.List<Help> findByUserIdInMerges(Integer userId);
}
