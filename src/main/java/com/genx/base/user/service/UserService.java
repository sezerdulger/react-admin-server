package com.genx.base.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genx.base.user.model.SearchQuery;
import com.genx.base.user.model.User;
import com.genx.base.user.repository.UserRepository;

/**
 * @author SD
 * @date 2021/06/29
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public List<User> findByUids(List<String> uids) {
		return userRepository.findByUidIn(uids).orElse(null);
	}
	
	public User save(User user) {
		user.setUid(UUID.randomUUID().toString());
		return userRepository.save(user);
	}
	
	public Page<User> findByPage(SearchQuery searchQuery) {
		String tableName = StringUtils.uncapitalize(User.class.getSimpleName().toLowerCase());
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
				.map(d -> mapper.convertValue(d, User.class))
				.collect(Collectors.toList()), 
				p, () -> new Long(countResult.getInteger("n")));
		
	}
}