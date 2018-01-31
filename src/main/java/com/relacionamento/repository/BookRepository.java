package com.relacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relacionamento.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
