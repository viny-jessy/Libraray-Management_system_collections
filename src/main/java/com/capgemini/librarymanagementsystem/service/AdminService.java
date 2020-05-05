package com.capgemini.librarymanagementsystem.service;

import java.util.List;
import com.capgemini.librarymanagementsystem.dto.AdminDetails;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;

public interface AdminService {

	AdminDetails adminLogin(String adminEmailId, String adminPassword);

	boolean enrollUser(UserDetails userInfo);

	boolean isBookAdded(BooksDetails bookInfo);

	BooksDetails searchBook(int bookId);

	BooksDetails searchBookByName(String bookName);

	BooksDetails searchBookByAuthorName(String bookAuthor);

	List<UserDetails> showAllUsers();

	List<BooksDetails> showAllLibraryBooks();

	List<RequestDetails> showAllUserRequest();

	boolean isBookIssued(UserDetails user, BooksDetails book);

	boolean isBookReceived(UserDetails userInfo, BooksDetails bookInfo);

	boolean isBookRemoved(int bookId);

}
