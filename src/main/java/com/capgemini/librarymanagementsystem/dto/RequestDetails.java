package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class RequestDetails implements Serializable {

	private BooksDetails bookDetails;
	private UserDetails userDetails;
	private boolean isBookIssued;
	private boolean isBookReturned;
	private Date bookIssuedDate;
	private Date bookReturndate;
	public BooksDetails getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(BooksDetails bookDetails) {
		this.bookDetails = bookDetails;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public boolean isBookIssued() {
		return isBookIssued;
	}
	public void setBookIssued(boolean isBookIssued) {
		this.isBookIssued = isBookIssued;
	}
	public boolean isBookReturned() {
		return isBookReturned;
	}
	public void setBookReturned(boolean isBookReturned) {
		this.isBookReturned = isBookReturned;
	}
	public Date getBookIssuedDate() {
		return bookIssuedDate;
	}
	public void setBookIssuedDate(Date bookIssuedDate) {
		this.bookIssuedDate = bookIssuedDate;
	}
	public Date getBookReturndate() {
		return bookReturndate;
	}
	public void setBookReturndate(Date bookReturndate) {
		this.bookReturndate = bookReturndate;
	}

	
}
