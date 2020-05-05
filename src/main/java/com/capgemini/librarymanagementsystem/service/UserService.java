package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;

public interface UserService {

	UserDetails userLogin(String userEmailId, String userPassword);

	BooksDetails bookSearch(int bookId);

	BooksDetails searchBookByName(String bookName);

	BooksDetails searchBookByAuthorName(String bookAuthorName);

	RequestDetails bookRequest(UserDetails userInfo, BooksDetails bookInfo);

	RequestDetails bookReturn(UserDetails userInfo, BooksDetails bookInfo);

}
