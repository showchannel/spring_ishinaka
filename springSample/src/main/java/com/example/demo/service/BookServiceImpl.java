package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> findPagenatedList(String title, int pageNo, int pageSize, String sortField,
			String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		if (title == null) {
			return bookRepository.findAll(pageable);
		}

		return bookRepository.findByTitleContaining(title, pageable);
	}

	@Override
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public void update(BookUpdateRequest bookUpdateRequest) {
		Book book = findById(bookUpdateRequest.getId());
		book.setTitle(bookUpdateRequest.getTitle());
		book.setIsbn(bookUpdateRequest.getIsbn());
		book.setThumbnail(bookUpdateRequest.getThumbnail());
		book.setDescription(bookUpdateRequest.getDescription());
		book.setAuthor(bookUpdateRequest.getAuthor());
		/*book.setUpdateDate(new Date());*/
		bookRepository.save(book);
	}

	@Override
	public void create(BookRequest bookRequest) {
		Date now = new Date();
		Book book = new Book();
		book.setTitle(bookRequest.getTitle());
		book.setIsbn(bookRequest.getIsbn());
		book.setThumbnail(bookRequest.getThumbnail());
		book.setDescription(bookRequest.getDescription());
		book.setAuthor(bookRequest.getAuthor());
		book.setPublicationdate(bookRequest.getPublicationdate());
		/*book.setCreateDate(now);
		book.setUpdateDate(now);*/
		bookRepository.save(book);
	}

	@Override
	public void delete(Long id) {
		Book book = findById(id);
		bookRepository.delete(book);
	}
}
