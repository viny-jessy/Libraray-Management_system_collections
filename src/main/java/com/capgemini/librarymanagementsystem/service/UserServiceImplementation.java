package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;
import com.capgemini.librarymanagementsystem.factory.LibraryManagmentSystemFactory;

public class UserServiceImplementation implements UserService {

	private UserDAO dao = LibraryManagmentSystemFactory.getuserDao();

	@Override
	public UserDetails userLogin(String userEmailId, String userPassword) {

		return dao.userLogin(userEmailId, userPassword);
	}

	@Override
	public BooksDetails bookSearch(int bookId) {

		return dao.bookSearch(bookId);
	}

	@Override
	public BooksDetails searchBookByName(String bookName) {

		return dao.searchBookByName(bookName);
	}

	@Override
	public BooksDetails searchBookByAuthorName(String bookAuthorName) {

		return dao.searchBookByAuthorName(bookAuthorName);
	}

	@Override
	public RequestDetails bookRequest(UserDetails userInfo, BooksDetails bookInfo) {

		return dao.bookRequest(userInfo, bookInfo);
	}

	@Override
	public RequestDetails bookReturn(UserDetails userInfo, BooksDetails bookInfo) {

		return dao.bookReturn(userInfo, bookInfo);
	}

}
