package Esport_Website.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import Esport_Website.service.UserTransactionService;

@Component
public class CheckTheBill {
	
	@Autowired
	UserTransactionService userTransactionService;
	
	@Scheduled(fixedRate = 1000 * 60)
	@Async
	public void checkTheBill() {
		userTransactionService.checkAllBill();
	}
}
