package com.together1.together1_api.repository;

import com.together1.together1_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User , Long> {
}
