package group.Controller;

import group.Entity.DTO.RequestUserDTO;
import group.Entity.DTO.ResponseUserDTO;
import group.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PutMapping("/setinfo")
	public ResponseEntity<Void> setInfo(@RequestBody RequestUserDTO user) {
		userService.store(user);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/levelinfo/{level_id}")
	public ResponseEntity<List<ResponseUserDTO>> getLevelInfo(@PathVariable Integer level_id) {
		return ResponseEntity.ok(userService.getLevelInfo(level_id));
	}

	@GetMapping("/userinfo/{user_id}")
	public ResponseEntity<List<ResponseUserDTO>> getUserInfo(@PathVariable Integer user_id) {
		return ResponseEntity.ok(userService.getUserInfo(user_id));
	}

}
