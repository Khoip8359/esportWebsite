package Esport_Website.controller;

import Esport_Website.entity.HelpDetail;
import Esport_Website.service.HelpDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/help-detail")
public class HelpDetailController {
    @Autowired
    private HelpDetailService helpDetailService;

    @GetMapping("/history/{helpId}")
    public List<HelpDetail> getChatHistory(@PathVariable Integer helpId) {
        return helpDetailService.findByHelpId(helpId);
    }

    @GetMapping("/history/user/{userId}")
    public List<HelpDetail> getChatHistoryByUser(@PathVariable Integer userId) {
        return helpDetailService.findBySenderUserId(userId);
    }
} 