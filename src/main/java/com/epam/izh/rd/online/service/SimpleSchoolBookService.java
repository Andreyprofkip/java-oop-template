package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService{
    BookRepository<SchoolBook> schoolBookBookRepository;
    AuthorService authorService;


    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {

        if(authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName())!=null ){
            return schoolBookBookRepository.save(book);
        } else {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        SchoolBook[] temp = new SchoolBook[0];
        temp = schoolBookBookRepository.findByName(name);


        return temp.length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        int j = schoolBookBookRepository.findByName(name).length;

        if (j != 0) {
            SchoolBook[] temp = new SchoolBook[j];
            temp = schoolBookBookRepository.findByName(name);
            return authorService.findByFullName(temp[0].getAuthorName(), temp[0].getAuthorLastName());
        } else {
            return null;
        }
    }
}
