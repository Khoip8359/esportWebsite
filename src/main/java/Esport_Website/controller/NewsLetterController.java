package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.entity.NewsLetter;
import Esport_Website.service.NewsLetterService;

@RestController
@CrossOrigin(origins = "*")
public class NewsLetterController {
	@Autowired
	NewsLetterService newsLetterService;
	
	@PostMapping("/api/newsLetter/add")
	public ResponseEntity<?> addNewsLetter(@RequestParam String email){
		try {
			NewsLetter nl = newsLetterService.create(email);
            return ResponseEntity.ok(nl);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
