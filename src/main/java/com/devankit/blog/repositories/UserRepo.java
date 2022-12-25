package com.devankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devankit.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
