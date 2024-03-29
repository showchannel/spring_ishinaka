package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.entity.Book;

public interface BookService {

	public Page<Book> findPagenatedList(String name, int pageNo, int pageSize, String sortField, String sortDirection);

	public Book findById(Long id);

	public void update(BookUpdateRequest bookUpdateRequest);

	public void create(BookRequest bookRequest);

	public void delete(Long id);
}