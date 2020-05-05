package com.capgemini.librarymanagementsystem.service;

import java.util.List;
import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.AdminDetails;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;
import com.capgemini.librarymanagementsystem.factory.LibraryManagmentSystemFactory;

public class AdminServiceImplementation implements AdminService {

	private AdminDAO admindao = LibraryManagmentSystemFactory.getadminDao();

	@Override
	public AdminDetails adminLogin(String adminEmailId, String adminPassword) {

		return admindao.adminLogin(adminEmailId, adminPassword);
	}

	@Override
	public boolean enrollUser(UserDetails userInfo) {

		return admindao.enrollUser(userInfo);
	}

	@Override
	public boolean isBookAdded(BooksDetails bookInfo) {

		return admindao.isBookAdded(bookInfo);
	}

	@Override
	public BooksDetails searchBook(int bookId) {

		return admindao.searchBook(bookId);
	}

	@Override
	public BooksDetails searchBookByName(String bookName) {

		return admindao.searchBookByName(bookName);
	}

	@Override
	public BooksDetails searchBookByAuthorName(String bookAuthor) {

		return admindao.searchBookByAuthorName(bookAuthor);
	}

	@Override
	public List<UserDetails> showAllUsers() {

		return admindao.showAllUsers();
	}

	@Override
	public List<BooksDetails> showAllLibraryBooks() {

		return admindao.showAllLibraryBooks();
	}

	@Override
	public List<RequestDetails> showAllUserRequest() {

		return admindao.showAllUserRequest();
	}

	@Override
	public boolean isBookIssued(UserDetails user, BooksDetails book) {

		return admindao.isBookIssued(user, book);
	}

	@Override
	public boolean isBookReceived(UserDetails userInfo, BooksDetails bookInfo) {

		return admindao.isBookReceived(userInfo, bookInfo);
	}

	@Override
	public boolean isBookRemoved(int bookId) {

		return admindao.isBookRemoved(bookId);
	}

}
