package com.technicalsand.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameAndDisabled(String username, boolean disabled);
}