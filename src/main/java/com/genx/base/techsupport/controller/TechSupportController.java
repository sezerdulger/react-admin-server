package com.genx.base.techsupport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.base.techsupport.model.*;
import com.genx.base.techsupport.service.*;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 * @author SD
 * @date 2021/06/29
 */
@RestController
@RequestMapping("/techsupport")
public class TechSupportController {

	@Autowired
	TechSupportService techsupportService;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(techsupportService.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TechSupport techsupport) {
		return ResponseEntity.ok(techsupportService.save(techsupport));
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(techsupportService.findByPage(searchQuery));
	}
}