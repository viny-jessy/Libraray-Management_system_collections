package com.capgemini.librarymanagementsystem.database;

import java.util.LinkedList;
import java.util.List;
import com.capgemini.librarymanagementsystem.dto.AdminDetails;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;

public class DataBase {

	public static final List<UserDetails> USERDATABASE = new LinkedList<UserDetails>();
	public static final List<AdminDetails> ADMINDATABASE = new LinkedList<AdminDetails>();
	public static final List<BooksDetails> BOOKDATABASE = new LinkedList<BooksDetails>();
	public static final List<RequestDetails> REQUESTDATABASE = new LinkedList<RequestDetails>();

}
