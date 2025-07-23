package Esport_Website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.dto.Webhook;
import Esport_Website.entity.UserTransaction;
import Esport_Website.service.PaymentService;

@RestController
@CrossOrigin("*")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
    @PostMapping("/api/hook/payment")
    public ResponseEntity<String> receiveWebhook(@RequestBody Webhook webhook) {
    	if(webhook != null) {
    		paymentService.handleWebhook(webhook);
    		System.out.println("Received webhook: " + webhook);
            return ResponseEntity.ok("Webhook received");
    	}else {
    		System.out.println("Error,webhook is not received!");
            return ResponseEntity.ok("Error,webhook is not received!");
    	}
    }
    
    @GetMapping("/api/transaction/get")
    public List<UserTransaction> getTransaction(@RequestParam Integer userId){
    	List<UserTransaction> trans = paymentService.getTransaction(userId);
    	return trans;
    }
    
    @PostMapping("/api/transaction/add")
    public ResponseEntity<?> addTransaction(@RequestParam Integer total, @RequestParam Integer userId) {
    	try {
			UserTransaction trans = paymentService.createNewTrans(total,userId);
			return ResponseEntity.ok(trans);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
    }
    
}
