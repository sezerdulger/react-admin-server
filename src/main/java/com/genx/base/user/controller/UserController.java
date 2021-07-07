package com.genx.base.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.base.user.model.*;
import com.genx.base.user.service.*;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 * @author SD
 * @date 2021/07/06
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(userService.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
		userService.delete(uid);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(userService.findByPage(searchQuery));
	}
}