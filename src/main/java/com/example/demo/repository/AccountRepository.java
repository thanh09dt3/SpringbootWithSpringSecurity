package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TAccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<TAccountEntity, String>{
	

	/**
	 * Find user by name
	 * @param username
	 * @return
	 */
	@Query("select account from tAccountEntity account where account.username = :username and account.deleteTime is null")
	public Optional<TAccountEntity> findByUsername(@RequestParam("username") String username);
}
