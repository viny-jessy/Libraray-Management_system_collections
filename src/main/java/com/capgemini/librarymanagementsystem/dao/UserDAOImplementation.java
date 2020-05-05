package com.capgemini.librarymanagementsystem.dao;

import com.capgemini.librarymanagementsystem.database.DataBase;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;


public class UserDAOImplementation implements UserDAO {

	@Override
	public UserDetails userLogin(String userEmailId, String userPassword) {
		for (UserDetails user : DataBase.USERDATABASE)
			if (user.getUserEmailId().equals(userEmailId) && user.getUserPassword().equals(userPassword)) {
				return user;
			}
		throw new LibrarayManagementExceptions("User Invalid login credentials");
	}

	@Override
	public BooksDetails bookSearch(int bookId) {
		for (BooksDetails book : DataBase.BOOKDATABASE) {
			if (book.getBookid() == bookId) {
				return book;
			}
		}
		throw new LibrarayManagementExceptions("search book not found");
	}

	@Override
	public BooksDetails searchBookByName(String bookName) {
		
		for (BooksDetails bookBean : DataBase.BOOKDATABASE) {
			if (bookBean.getBookname() .equals(bookName)) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}


	@Override
	public BooksDetails searchBookByAuthorName(String bookAuthorName) {
			
		for (BooksDetails bookBean : DataBase.BOOKDATABASE) {
			if (bookBean.getBookauthor() .equals(bookAuthorName) ) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}
	
	
	@Override
	public RequestDetails bookReturn(UserDetails userInfo, BooksDetails bookInfo) {

		for (RequestDetails requestInfo : DataBase.REQUESTDATABASE) {
			if (requestInfo.getBookDetails().getBookid() == bookInfo.getBookid()
					&& requestInfo.getUserDetails().getUserId() == userInfo.getUserId()
					&& requestInfo.isBookIssued() == true) {

				System.out.println("Returning Issued book only");
				requestInfo.setBookReturned(true);
				return requestInfo;
			}
		}
		throw new LibrarayManagementExceptions("book is not available to return");
	}

		
@Override
public RequestDetails bookRequest(UserDetails userInfo, BooksDetails bookInfo) {
	
	boolean flag = false, isRequestExists = false;
	RequestDetails requestInfo = new RequestDetails();
	UserDetails userInfo1 = new UserDetails();
	BooksDetails bookInfo1 = new BooksDetails();

	for (RequestDetails requestInfo2 : DataBase.REQUESTDATABASE) {
		if (bookInfo.getBookid() == requestInfo2.getBookDetails().getBookid()) {
			isRequestExists = true;
		}
	}
	if (!isRequestExists) {
		for (UserDetails user : DataBase.USERDATABASE) {
			if (userInfo.getUserId() == user.getUserId()) {
				for (BooksDetails book : DataBase.BOOKDATABASE) {
					if (book.getBookid() == bookInfo.getBookid()) {
						userInfo1 = user;
						bookInfo1 = book;
						flag = true;
					}
				}
			}
		}
		if (flag == true) {
			requestInfo.setBookDetails(bookInfo1);
			requestInfo.setUserDetails(userInfo1);
			DataBase.REQUESTDATABASE.add(requestInfo);
			return requestInfo;
		}
	}
	throw new LibrarayManagementExceptions("Invalid request or you cannot request that book");
}

}