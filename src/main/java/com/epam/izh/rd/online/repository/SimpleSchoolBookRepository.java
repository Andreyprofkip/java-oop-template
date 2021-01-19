package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    public SchoolBook[] schoolBooks = new SchoolBook[0];


    @Override
    public boolean save(SchoolBook book) {
            SchoolBook[] temp;
            if (this.schoolBooks.length == 0) {
                this.schoolBooks = new SchoolBook[1];
                this.schoolBooks[0] = book;
                return true;
            } else {
                temp = schoolBooks;
                schoolBooks = new SchoolBook[temp.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    schoolBooks[i] = temp[i];
                }
                schoolBooks[schoolBooks.length-1] = book;
                return true;

            }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (schoolBooks.length > 0) {
            int j = 0;
            for (int i = 0; i < this.schoolBooks.length; i++) {
                 if (this.schoolBooks[i]!= null) {
                     if (name == this.schoolBooks[i].getName()) {
                         j++;
                     }
                 }
            }
            if (j > 0) {
                SchoolBook[] schoolBooksOneName = new SchoolBook[j];
                int temp = 0;
                for (int i = 0; i < this.schoolBooks.length; i++) {
                    if (this.schoolBooks[i]!= null) {
                        if (name == this.schoolBooks[i].getName()) {
                            schoolBooksOneName[temp] = schoolBooks[i];
                            temp++;
                        }
                    }
                }
                return schoolBooksOneName;

            }

        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        if( this.schoolBooks.length > 0 && findByName(name) != null  ) {
            SchoolBook[] temp = new SchoolBook[this.schoolBooks.length-findByName(name).length];
            for(int i = 0; i < this.schoolBooks.length; i++) {
                int j = 0;
                if(this.schoolBooks[i].getName() != name ) {
                    temp[j] = this.schoolBooks[i];
                    j++;
                };
            }
            this.schoolBooks = temp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}
