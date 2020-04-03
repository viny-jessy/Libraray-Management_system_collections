package com.capgemini.librarymanagementsystem.dao;

import java.util.Date;

import com.capgemini.librarymanagementsystem.database.Database;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;

public class UserDAOImplementation implements UserDAO {


	@Override
	public UserInformation userLogin(String userEmailId, String userPassword) {
		for (UserInformation user : Database.USERS)
			if (user.getUserEmailId().equals(userEmailId) && user.getUserPassword().equals(userPassword)) {
				return user;
			}
		throw new LibrarayManagementExceptions("Invalid user credentials");
	}

	@Override
	public BooksInformation bookSearch(int bookId) {
		for (BooksInformation book : Database.BOOK) {
			if (book.getBookid() == bookId) {
				return book;
			}
		}
		throw new LibrarayManagementExceptions("Invalid search");
	}

	@Override
	public RequestInformation bookReturn(UserInformation userInfo, BooksInformation bookInfo) {

		for (RequestInformation requestInfo : Database.REQUEST) {
			if (requestInfo.getBookInformation().getBookid() == bookInfo.getBookid()
					&& requestInfo.getUserInformation().getUserId() == userInfo.getUserId()
					&& requestInfo.isBookIssued() == true) {

				System.out.println("Returning Issued book only");
				requestInfo.setBookReturned(true);
				//requestInfo.setBookReturndate(bookReturndate);
				return requestInfo;
			}
		}
		throw new LibrarayManagementExceptions("Invalid return ");
	}

//	@Override
//	public RequestInformation bookRequest(UserInformation userInfo, BooksInformation bookInfo) {
//
//		boolean flag = false;
//		boolean isRequested = false;
//		RequestInformation request1 = new RequestInformation();
//		UserInformation user = new UserInformation();
//		BooksInformation book = new BooksInformation();
//		for (RequestInformation request2 : Database.REQUEST) {
//			if (bookInfo.getBookid() == request2.getBookInformation().getBookid()) {
//				isRequested = true;
//			}
//		}
//		if (!isRequested) {
//			for (UserInformation user1 : Database.USERS) {
//				if (userInfo.getUserId() == user.getUserId()) {
//					for (BooksInformation book1 : Database.BOOK) {
//						if (book1.getBookid() == bookInfo.getBookid()) {
//						    user=user1;
//							book=book1;
//							flag = true;
//						}
//					}
//				}
//			}
//			if (flag == true) {
//				request1.setBookInformation(book);
//				request1.setUserInformation(user);
//				Database.REQUEST.add(request1);
//				return request1;
//			}
//		}
//		throw new LibrarayManagementExceptions("Invalid request or you cannot request that book");
//	}




@Override
public RequestInformation bookRequest(UserInformation userInfo, BooksInformation bookInfo) {
	boolean flag = false, isRequestExists = false;
	RequestInformation requestInfo = new RequestInformation();
	UserInformation userInfo2 = new UserInformation();
	BooksInformation bookInfo2 = new BooksInformation();

	for (RequestInformation requestInfo2 : Database.REQUEST) {
		if (bookInfo.getBookid() == requestInfo2.getBookInformation().getBookid()) {
			isRequestExists = true;

		}

	}

	if (!isRequestExists) {
		for (UserInformation user : Database.USERS) {
			if (userInfo.getUserId() == user.getUserId()) {
				for (BooksInformation book : Database.BOOK) {
					if (book.getBookid() == bookInfo.getBookid()) {
						userInfo2 = user;
						bookInfo2 = book;
						flag = true;
					}
				}
			}
		}
		if (flag == true) {
			requestInfo.setBookInformation(bookInfo2);
			requestInfo.setUserInformation(userInfo2);
			Database.REQUEST.add(requestInfo);
			return requestInfo;
		}

	}

	throw new LibrarayManagementExceptions("Invalid request or you cannot request that book");
}
}