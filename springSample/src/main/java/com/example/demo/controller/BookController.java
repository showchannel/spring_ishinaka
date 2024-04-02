package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public String findPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "sortField", defaultValue = "title") String sortField,
			@RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
			@RequestParam(name = "searchName", required = false) String searchName, Model model) {
		int pageSize = 3;
		if (pageNo < 1) {
			pageNo = 1;
		}

		Page<Book> page = bookService.findPagenatedList(searchName, pageNo, pageSize, sortField, sortDir);

		model.addAttribute("page", page);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("searchName", searchName == null ? "" : searchName);

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("booklist", page.getContent());

		return "book/list";
	}

	@GetMapping(value = "/book/add")
	public String displayAdd(Model model) {
		model.addAttribute("bookRequest", new BookRequest());
		return "book/add";
	}

	@RequestMapping(value = "/book/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute BookRequest bookRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "book/add";
		}

		bookService.create(bookRequest);
		return "redirect:/books";
	}

	@GetMapping("/book/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		Book book = bookService.findById(id);
		model.addAttribute("bookData", book);
		return "book/view";
	}

	@GetMapping("/book/{id}/edit")
	public String displayEdit(@PathVariable Long id, Model model) {
		Book book = bookService.findById(id);
		BookUpdateRequest bookUpdateRequest = new BookUpdateRequest();
		bookUpdateRequest.setId(book.getId());
		bookUpdateRequest.setTitle(book.getTitle());
		bookUpdateRequest.setIsbn(book.getIsbn());
		bookUpdateRequest.setThumbnail(book.getThumbnail());
		bookUpdateRequest.setDescription(book.getDescription());
		bookUpdateRequest.setAuthor(book.getAuthor());
		model.addAttribute("bookUpdateRequest", bookUpdateRequest);
		return "book/edit";
	}

	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute BookUpdateRequest bookUpdateRequest, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();

			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "book/edit";
		}

		bookService.update(bookUpdateRequest);
		return String.format("redirect:/book/%d", bookUpdateRequest.getId());
	}

	@GetMapping("/book/{id}/delete")
	public String delete(@PathVariable Long id, Model model) {
		bookService.delete(id);
		return "redirect:/books";
	}
}