package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;


public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if( findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] temp;
            if(authors.length == 0) {
                authors = new Author[1];
                authors[0] = author;
                return  true;
            } else {
                temp = authors;
                authors = new Author[temp.length+1];
                for (int i = 0; i < temp.length; i++){
                    authors[i] = temp[i];
                }
                authors[authors.length-1] = author;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length ; i++) {
            if (name == authors[i].getName() && lastname == authors[i].getLastName()){
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if( findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] temp = new Author[authors.length-1];
            for(int i = 0; i < authors.length; i++) {
                int j = 0;
                if(authors[i] != author ) {
                    temp[j] = authors[i];
                    j++;
                };
            }
            authors = temp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
