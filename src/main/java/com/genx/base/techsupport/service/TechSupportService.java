package com.genx.base.techsupport.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import com.genx.base.techsupport.model.*;
import com.genx.base.techsupport.repository.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.Document;
import org.springframework.util.StringUtils;
import com.genx.base.techsupport.model.SearchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author SD
 * @date 2021/07/06
 */
@Service
public class TechSupportService {

	@Autowired
	TechSupportRepository techsupportRepository;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public TechSupport findByUid(String uid) {
		return techsupportRepository.findByUidIs(uid).orElse(null);
	}
	
	public TechSupport save(TechSupport techsupport) {
		techsupport.setUid(UUID.randomUUID().toString());
		return techsupportRepository.save(techsupport);
	}
	
	public void delete(String uid) {
		techsupportRepository.deleteByUid(uid);
	}
	
	public Page<TechSupport> findByPage(SearchQuery searchQuery) {
		String tableName = StringUtils.uncapitalize(TechSupport.class.getSimpleName().toLowerCase());
		Document ob = new Document("find", tableName);
		ob.append("filter", searchQuery.getQuery());
		ob.append("skip", searchQuery.getPage() * searchQuery.getSize());
		ob.append("limit", searchQuery.getSize());
		Document result = mongoTemplate.executeCommand(ob);
		
		List<Document> documents = (List<Document>)(((Document)result.get("cursor")).get("firstBatch"));
		
		Document c = new Document();
		c.put("count", tableName);
		c.put("query", searchQuery.getQuery());
		
		Document countResult = mongoTemplate.getDb().runCommand(c);
		PageRequest p = PageRequest.of(searchQuery.getPage(), searchQuery.getSize());
		return PageableExecutionUtils.getPage(documents.stream()
				.map(d -> mapper.convertValue(d, TechSupport.class))
				.collect(Collectors.toList()), 
				p, () -> new Long(countResult.getInteger("n")));
		
	}
}