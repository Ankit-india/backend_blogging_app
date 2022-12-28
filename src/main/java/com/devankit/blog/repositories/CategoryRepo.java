package com.devankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devankit.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
