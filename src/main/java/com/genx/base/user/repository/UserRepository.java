package com.genx.base.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.genx.base.user.model.User;

/**
 * @author SD
 * @date 2021/07/06
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	Optional<User> findByUidIs(String uid);
	
	void deleteByUid(String uid);
}