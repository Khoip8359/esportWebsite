package Esport_Website.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WakeUpController {
	@GetMapping("/ping")
	public String ping() {
		
		Date date = new Date();
		
		String mes = "Ứng dụng vẫn đang chạy lúc " + date;
		
		return mes;
	}
}
