package Esport_Website.controller;

import Esport_Website.entity.Help;
import Esport_Website.entity.HelpDetail;
import Esport_Website.service.HelpDetailService;
import Esport_Website.service.HelpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/help-detail")
public class HelpDetailController {
    @Autowired
    private HelpDetailService helpDetailService;
    
    @Autowired
    private HelpService helpService;

    @GetMapping("/history/{helpId}")
    public List<HelpDetail> getChatHistory(@PathVariable Integer helpId) {
        return helpDetailService.findByHelpId(helpId);
    }

    @GetMapping("/history/user/{userId}")
    public List<HelpDetail> getChatHistoryByUser(@PathVariable String userId) {
        return helpDetailService.findBySenderUserId(userId);
    }

    @GetMapping("/history/conversation/{merge}")
    public List<HelpDetail> getChatHistoryByConversation(@PathVariable String merge, @RequestParam Integer currentUserId) {
        // Kiểm tra quyền truy cập
        if (!isUserAuthorizedForConversation(merge, currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bạn không có quyền truy cập hội thoại này");
        }
        return helpDetailService.findByHelp_Merge(merge);
    }
    
    @GetMapping("/conversations/{userId}")
    public List<Map<String, String>> getConversationsByUserId(@PathVariable Integer userId) {
        List<Help> helps = helpService.findByUserIdInMerges(userId);
        return helps.stream()
            .map(help -> {
                Map<String, String> result = new HashMap<>();
                result.put("merge", help.getMerge());
                return result;
            })
            .collect(Collectors.toList());
    }
    
    // Kiểm tra xem user có quyền truy cập hội thoại không
    private boolean isUserAuthorizedForConversation(String merge, Integer currentUserId) {
        String[] userIds = merge.split("-");
        int userId1 = Integer.parseInt(userIds[0]);
        int userId2 = Integer.parseInt(userIds[1]);
        return currentUserId == userId1 || currentUserId == userId2;
    }
} 