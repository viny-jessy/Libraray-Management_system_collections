package com.capgemini.librarymanagementsystem.dao;

import java.util.List;
import com.capgemini.librarymanagementsystem.dto.AdminDetails;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;

public interface AdminDAO {

	AdminDetails adminLogin(String adminEmailId, String adminPassword);

	public boolean enrollUser(UserDetails user);

	boolean isBookAdded(BooksDetails bookInfo);
	
	boolean isBookUpdated(BooksDetails bookInfo);	

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
