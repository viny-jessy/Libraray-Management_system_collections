package com.capgemini.librarymanagementsystem.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.capgemini.librarymanagementsystem.database.DataBase;
import com.capgemini.librarymanagementsystem.dto.AdminDetails;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;

public class AdminDAOImplementation implements AdminDAO {
	
	Calendar calendar = Calendar.getInstance();
	Date issueDate = new Date();
	Date actualReturnDate = calendar.getTime();
	Date expectedReturnedDate = new Date();
	Date bookReturnDate = new Date();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String todayDate = simpleDateFormat.format(calendar.getTime());
	String returnDate = simpleDateFormat.format(actualReturnDate);

	@Override
	public AdminDetails adminLogin(String adminEmailId, String adminPassword) {

		AdminDetails admin = new AdminDetails();

		if (admin.getAdminEmailId().equals(adminEmailId) && admin.getAdminPassword().equals(adminPassword)) {
			return admin;
		}
		throw new LibrarayManagementExceptions("Admin invlaid login credentials!");
	}

	@Override
	public boolean enrollUser(UserDetails user) {

		for (UserDetails user1 : DataBase.USERDATABASE) {
			if (user1.getUserId() == user.getUserId()) {
				return false;
			}

		}
		DataBase.USERDATABASE.add(user);

		return true;
	}

	@Override
	public boolean isBookAdded(BooksDetails bookInfo) {

		for (BooksDetails bookBean : DataBase.BOOKDATABASE) {
			if (bookBean.getBookid() == bookInfo.getBookid()) {
				return false;
			}
		}
		DataBase.BOOKDATABASE.add(bookInfo);
		return true;
	}
	
	@Override
	public boolean isBookUpdated(BooksDetails bookInfo) {

		for (BooksDetails bookBean : DataBase.BOOKDATABASE) {
			if (bookBean.getBookid() == bookInfo.getBookid()) {
				return false;
			}
		}
		DataBase.BOOKDATABASE.add(bookInfo);
		return true;
	}
	
	@Override
	public BooksDetails searchBook(int bookId) {
		for (BooksDetails bookBean : DataBase.BOOKDATABASE) {
			if (bookBean.getBookid() == bookId) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}

	@Override
	public BooksDetails searchBookByName(String bookName) {

		for (BooksDetails bookInfo : DataBase.BOOKDATABASE) {
			if (bookInfo.getBookname().equals(bookName)) {

				return bookInfo;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}

	@Override
	public BooksDetails searchBookByAuthorName(String bookAuthor) {

		for (BooksDetails bookInformation : DataBase.BOOKDATABASE) {
			if (bookInformation.getBookauthor().equals(bookAuthor)) {

				return bookInformation;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}

	@Override
	public List<UserDetails> showAllUsers() {
		List<UserDetails> userList = new LinkedList<UserDetails>();
		for (UserDetails userInfo : DataBase.USERDATABASE) {
			userInfo.getUserId();
			userInfo.getUserName();
			userInfo.getUserEmailId();
			userInfo.getNoOfBooksBorrowed();
			userList.add(userInfo);
		}
		return userList;
	}

	@Override
	public List<BooksDetails> showAllLibraryBooks() {
		List<BooksDetails> booksList = new LinkedList<BooksDetails>();
		for (BooksDetails book : DataBase.BOOKDATABASE) {

			book.getBookid();
			book.getBookauthor();
			book.getBookname();
			booksList.add(book);
		}
		return booksList;
	}

	@Override
	public List<RequestDetails> showAllUserRequest() {
		List<RequestDetails> requestsList = new LinkedList<RequestDetails>();
		for (RequestDetails requestInformation : DataBase.REQUESTDATABASE) {
			requestInformation.getBookDetails();
			requestInformation.getUserDetails();
			requestInformation.isBookIssued();
			requestInformation.isBookReturned();
			requestsList.add(requestInformation);
		}
		return requestsList;
	}

	@Override
	public boolean isBookIssued(UserDetails user, BooksDetails book) {

		boolean correct = false;
		calendar.add(Calendar.DATE, 15);
		expectedReturnedDate = calendar.getTime();
		
		RequestDetails requestdetails = new RequestDetails();
		int noOfBooksBorrowed = user.getNoOfBooksBorrowed();
		for (RequestDetails details : DataBase.REQUESTDATABASE) {
			if (details.getUserDetails().getUserId() == user.getUserId()) {
				if (details.getBookDetails().getBookid() == book.getBookid()) {
					requestdetails = details;
					correct = true;
				}
			}
		}

		if (correct)

		{
			for (BooksDetails info2 : DataBase.BOOKDATABASE) {
				if (info2.getBookid() == book.getBookid()) {
					book = info2;
				}

			}
			for (UserDetails userInfo2 : DataBase.USERDATABASE) {
				if (userInfo2.getUserId() == user.getUserId()) {
					user = userInfo2;
					noOfBooksBorrowed = user.getNoOfBooksBorrowed();
				}
			}
			if (noOfBooksBorrowed < 2) {
				boolean isRemoved = DataBase.BOOKDATABASE.remove(book);
				if (isRemoved) {
					++noOfBooksBorrowed;
					System.out.println(noOfBooksBorrowed);
					user.setNoOfBooksBorrowed(noOfBooksBorrowed);
					requestdetails.setBookIssued(true);
					requestdetails.setBookIssuedDate(issueDate);
					requestdetails.setBookReturndate(expectedReturnedDate);
					return true;
				}

			}

		}
		throw new LibrarayManagementExceptions("you might have enetered in valid user or book details");
	}

	@Override
	public boolean isBookReceived(UserDetails userInfo, BooksDetails bookInfo) {

		boolean correct = false;
		RequestDetails requestDetails = new RequestDetails();
		for (RequestDetails details : DataBase.REQUESTDATABASE) {

			if (details.getBookDetails().getBookid() == bookInfo.getBookid()
					&& details.getUserDetails().getUserId() == userInfo.getUserId()
					&& details.isBookReturned() == true) {
				correct = true;
				requestDetails = details;

			}
		}
		if (correct) {
			calendar.add(Calendar.DATE, 15);
			actualReturnDate = calendar.getTime();

			bookInfo.setBookauthor(requestDetails.getBookDetails().getBookauthor());
			bookInfo.setBookname(requestDetails.getBookDetails().getBookname());
			DataBase.BOOKDATABASE.add(bookInfo);
			DataBase.REQUESTDATABASE.remove(requestDetails);

			for (UserDetails userInfo2 : DataBase.USERDATABASE) {
				if (userInfo2.getUserId() == userInfo.getUserId()) {
					userInfo = userInfo2;
				}
			}
			int noOfBooksBorrowed = userInfo.getNoOfBooksBorrowed();
			noOfBooksBorrowed--;
			userInfo.setNoOfBooksBorrowed(noOfBooksBorrowed);
			if (actualReturnDate.after(issueDate)) {
				userInfo.setFine(5.0);
			}
			return true;
		}
		throw new LibrarayManagementExceptions("Book not received");
	}

	@Override
	public boolean isBookRemoved(int bookId) {

		for (BooksDetails bookInfo : DataBase.BOOKDATABASE) {
			if (bookInfo.getBookid() == bookId) {
				DataBase.BOOKDATABASE.remove(bookInfo);
				return true;
			}
		}
		return true;
	}

}
