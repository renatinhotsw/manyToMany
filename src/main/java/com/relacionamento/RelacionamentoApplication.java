package com.relacionamento;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.relacionamento.models.Book;
import com.relacionamento.models.Publisher;
import com.relacionamento.repository.BookRepository;
import com.relacionamento.repository.PublisherRepository;

@SpringBootApplication
public class RelacionamentoApplication implements CommandLineRunner {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RelacionamentoApplication.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RelacionamentoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		
		Publisher publisherA = new Publisher("Publisher A");
		Publisher publisherB = new Publisher("Publisher B");
		Publisher publisherC = new Publisher("Publisher C");
	
		 bookRepository.save(new HashSet<Book>(){{
	            add(new Book("Book A", new HashSet<Publisher>(){{
	                add(publisherA);
	                add(publisherB);
	            }}));
		
	     add(new Book("Book B", new HashSet<Publisher>() {{
	    	 add(publisherA);
	    	 add(publisherC);
	     }}));
	   }}); //end bookRepository
		 
		 //percorre todos os livros
		 for(Book book: bookRepository.findAll()) {
			 logger.info(book.toString());
		 }
		 
		 Book bookA = new Book("Book A");
		 Book bookB = new Book("Book B");
		 
		 publisherRepository.save(new HashSet<Publisher>() {{
			 add(new Publisher("Publisher A", new HashSet<Book>() {{
				 add(bookA);
				 add(bookB);
		}}));
			 
			 add(new Publisher("Publisher B", new HashSet<Book>() {{
	                add(bookA);
	                add(bookB);
	            }}));
	    }});// end publsher repository	 
		 
		 //percorre todos as publicacoes
		 for(Publisher publisher: publisherRepository.findAll()) {
			 logger.info(publisher.toString());
		 }
		 
	}// end run
} // end class