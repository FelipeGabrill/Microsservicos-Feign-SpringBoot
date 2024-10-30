package com.erudio.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erudio.model.Book;
import com.erudio.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;

	//http://localhost:8000/book-service/1/BRL
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not Found"));
		
		var port = environment.getProperty("local.server.port");
		book.setEnviroment(port);
		return book;
	}
}
