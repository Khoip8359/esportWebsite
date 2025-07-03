package Esport_Website.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.dto.ReactRequest;
import Esport_Website.entity.React;
import Esport_Website.service.ReactService;

@RestController
@CrossOrigin(origins = "*")
public class ReactController {
	@Autowired
	ReactService reactService;
	
	@PostMapping("/api/react/check")
	public Boolean checkReact(@RequestBody ReactRequest request) {
		Optional<React> react = reactService.checkReact(request);
		if(react.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	@PostMapping("/api/react/change")
	public ResponseEntity<?> updateReact(@RequestBody ReactRequest request){
		try {
			React react = reactService.changeReact(request);
            return ResponseEntity.ok(react);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
