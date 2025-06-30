package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.entity.Users;
import Esport_Website.service.UsersService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/profile/{username}")
	public ResponseEntity<?> getProfile(@PathVariable String username) {
		try {
			Users user = usersService.getUserByUsername(username);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId) {
		try {
			Users user = usersService.getUserById(userId);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
		}
	}
	
	@PutMapping("/profile/update")
	public ResponseEntity<?> updateProfile(@RequestBody Users user) {
		try {
			Users updatedUser = usersService.updateUser(user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
		}
	}
}
	