package com.mh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
