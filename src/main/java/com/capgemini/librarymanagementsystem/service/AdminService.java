package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;

public interface AdminService {

	boolean register(UserInformation userInfo);

	Admininformation adminLogin(String adminEmailId, String adminPassword);

	boolean isBookAdded(BooksInformation bookInfo);

	boolean isBookRemoved(int bookId);

	BooksInformation searchBook(int bookId);

	boolean isBookIssued(UserInformation user, BooksInformation book);

	boolean isBookReceived(UserInformation userInfo, BooksInformation bookInfo);

	boolean isBookUpdated(int bookId);

	List<BooksInformation> showAllLibraryBooks();

	List<UserInformation> showAllUsers();

	List<RequestInformation> showAllUserRequest();

}
