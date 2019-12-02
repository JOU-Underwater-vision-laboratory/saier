package com.hhit.anali.service;

import com.hhit.anali.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface BookService {

    Book save(Book book);

    void delete(Book book);

    Optional<Book> findOne(String id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, PageRequest pageRequest);

    Page<Book> findByTitle(String title, PageRequest pageRequest);

}
