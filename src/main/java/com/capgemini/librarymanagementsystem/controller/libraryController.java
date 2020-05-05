package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.capgemini.librarymanagementsystem.dto.BooksDetails;
import com.capgemini.librarymanagementsystem.dto.RequestDetails;
import com.capgemini.librarymanagementsystem.dto.UserDetails;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;
import com.capgemini.librarymanagementsystem.factory.LibraryManagmentSystemFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.LibraryManagementValidation;

public class libraryController {

	public static void main(String[] args) {

		libraryController librarycontroller = new libraryController();

		try {
			librarycontroller.Controller();
		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				librarycontroller.Controller();
			} catch (InputMismatchException e) {
				System.err.println("\nPlease enter the data in correct format : " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("\nPlease enter the data in correct format : " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("\nPlease enter the data in correct format : " + e.getMessage());
			} finally {
				librarycontroller.Controller();
			}
		}
	}

	@SuppressWarnings("resource")
	public void Controller() {

		AdminService service = LibraryManagmentSystemFactory.getAdminService();
		UserService userService = LibraryManagmentSystemFactory.getUserService();
		LibraryManagementValidation libraryValidations = LibraryManagmentSystemFactory.getLibraryManagementValidation();
		
		UserDetails userDetails = new UserDetails();
		BooksDetails bookDetails = new BooksDetails();
		
		Scanner scanner = new Scanner(System.in);
		
		int selectChoice, check, userChoice;
		
		do {

			System.out.println("-------------------------WELCOME TO DIGITAL LIBRARY----------------------------");
			System.out.println("\t\t\t1. To log in to Admin account");
			System.out.println("\t\t\t2. To log in to User account");
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("\nEnter your choice\n");
			
			selectChoice = scanner.nextInt();
			
			switch (selectChoice) {

			case 1:
				
				System.out.println("Please enter emailid in this format (vinitha@gmail.com)");
				System.out.println("\nEnter Admin Email id");
				String adminEmailId = scanner.next();
				boolean validateByEmailId = libraryValidations.useremailValidation(adminEmailId);
				while (!validateByEmailId) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid Email Id i.e (vinitha@gmail.com)");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println(".............................................................");
						System.err.println("please enter a valid email address i.e (vinitha@gmail.com)!!");
						System.out.println(".............................................................");
						adminEmailId = scanner.next();
						if (libraryValidations.useremailValidation(adminEmailId)) {
							break;
						}
					}
				}
				System.out.println("\nEnter Admin password in this format (Vinitha@123)");
				System.out.println(
						"\nPassword should contain (8-15)characters, atleast one uppercase,lower case,special character");
				String adminPassword = scanner.next();
				boolean validatePassword = libraryValidations.userpasswordValidation(adminPassword);
				while (!validatePassword) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid password");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println(
								".................................................................................");
						System.err.println(
								"Enter a combination atleast 8 (number,letters(upper & lower case),punctuation marks");
						System.out.println(
								"...................................................................................");

						adminPassword = scanner.next();
						if (libraryValidations.userpasswordValidation(adminPassword)) {
							break;
						}
					}
				}

				try {
					service.adminLogin(adminEmailId, adminPassword);
					System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("Admin logged in to Account Successfully!.......");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					do {

						System.out.println("\n-----------press below numbers to perform admin opertions---------");

						System.out.println("\n1. To Register user");
						System.out.println("2. To Add Book in to library");
						System.out.println("3. To Update Book to library");
						System.out.println("4. To Search book by bookid");
						System.out.println("5. To Search book by bookname");
						System.out.println("6. To search book by author name");
						System.out.println("7. To Show list of users enrolled in library");
						System.out.println("8. To Show list of books present in library");
						System.out.println("9. To Show list of Requests placed by users");
						System.out.println("10. To Issue book to user");
						System.out.println("11. To Recieve Returned book by user");
						System.out.println("12. To Remove Book from library");
						System.out.println("0. To Exit");

						System.out.println("\nEnter your choice");
						check = scanner.nextInt();

						switch (check) {
						
						case 1:
							System.out.println("-->Please enter below details to register");
							System.out.println("-->Entering User Id should be a 3 digit number");
							System.out.println("Enter user id");
							String registeredUserId = scanner.next();
							boolean validateUserId = libraryValidations.useridValidation(registeredUserId);
							while (!validateUserId) {
								try {
									throw new LibrarayManagementExceptions("please enter valid user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".......................................................");
									System.err.println("Please enter valid user id i.e (only 3 digit numbers)!!");
									System.out.println(".......................................................");
									registeredUserId = scanner.next();
									if (libraryValidations.useridValidation(registeredUserId)) {
										break;
									}
								}
							}
							System.out.println("Enter username");
							String userName = scanner.next();
							boolean ValidateByName = libraryValidations.nameValidation(userName);
							while (!ValidateByName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid user name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...................................................");
									System.err.println("Please enter valid user name i.e (only alphabets)!!");
									System.out.println("....................................................");
									userName = scanner.next();
									if (libraryValidations.nameValidation(userName)) {
										break;
									}
								}
							}
							System.out.println("\nPlease enter emailid in this format (vinitha@gmail.com)");
							System.out.println("\nEnter User Email id");
							String userEmailId = scanner.next();
							boolean validateByEmail = libraryValidations.useremailValidation(userEmailId);
							while (!validateByEmail) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid Email Id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("............................................................");
									System.err.println("please enter a valid email address i.e (vinitha@gmail.com)!!");
									System.out.println("............................................................");
									userEmailId = scanner.next();
									if (libraryValidations.useremailValidation(userEmailId)) {
										break;
									}
								}
							}
							System.out.println("\nEnter User password in this format (Vinitha@123)");
							System.out.println(
									"\nPassword should contain (8-15)characters, atleast one uppercase,lower case,special character");
							String userPassword = scanner.next();
							boolean validateByPassword = libraryValidations.userpasswordValidation(userPassword);
							while (!validateByPassword) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid password");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"...................................................................................................");
									System.err.println(
											"Enter a combination of atleast 8 number,letters,punctuation marks with upper and lower case letters");
									System.out.println(
											"...................................................................................................");
									userPassword = scanner.next();
									if (libraryValidations.userpasswordValidation(userPassword)) {
										break;
									}
								}
							}

							UserDetails userInformation = new UserDetails();

							userInformation.setUserId(Integer.parseInt(registeredUserId));
							userInformation.setUserName(userName);
							userInformation.setUserEmailId(userEmailId);
							userInformation.setUserPassword(userPassword);
							try {
								service.enrollUser(userInformation);

								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("user Registered Successfully!!!");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("...............................");
								System.err.println("sorry User already registered!!");
								System.out.println("...............................");
							}
							break;

						case 2:

							System.out.println("\n-->Enter Below details to add book in to library");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							String bookId = scanner.next();
							boolean validateBookId = libraryValidations.idValidation(bookId);
							while (!validateBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("Please enter valid book id i.e (only 6 digits)!!");
									System.out.println("................................................");
									bookId = scanner.next();
									if (libraryValidations.idValidation(bookId)) {
										break;
									}
								}
							}
							System.out.println("\nEnter Book Author name");
							String authourName = scanner.next();
							boolean ValidateByAuthorName = libraryValidations.nameValidation(authourName);
							while (!ValidateByAuthorName) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book author name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".........................................................");
									System.err.println("Please enter valid book author name i.e(only alphabets)!!");
									System.out.println(".........................................................");
									authourName = scanner.next();
									if (libraryValidations.nameValidation(authourName)) {
										break;
									}
								}
							}
							System.out.println("\nEnter Book Name");
							String bookTitle = scanner.next();
							boolean ValidateByBookName = libraryValidations.nameValidation(bookTitle);
							while (!ValidateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...................................................");
									System.err.println("Please enter valid book name i.e (only alphabets)!!");
									System.out.println("...................................................");
									bookTitle = scanner.next();
									if (libraryValidations.nameValidation(bookTitle)) {
										break;
									}
								}
							}
							System.out.println("\nEnter Book Category");
							String bookCategory = scanner.next();
							boolean ValidateByBookCategory = libraryValidations.nameValidation(bookCategory);
							while (!ValidateByBookCategory) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book category");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".......................................................");
									System.err.println("Please enter valid book category i.e (only alphabets)!!");
									System.out.println(".......................................................");
									bookCategory = scanner.next();
									if (libraryValidations.nameValidation(bookCategory)) {
										break;
									}
								}
							}

							BooksDetails bookInfo1 = new BooksDetails();

							bookInfo1.setBookid(Integer.parseInt(bookId));
							bookInfo1.setBookauthor(authourName);
							bookInfo1.setBookname(bookTitle);
							bookInfo1.setBookcategory(bookCategory);
							try {

								boolean bookAdded = service.isBookAdded(bookInfo1);
								System.out.println("Is Book Added in to account  : " + bookAdded);

								if (bookAdded) {
									System.out.println("");
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									System.out.println("Book is added in to account successfully!!");
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								}
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("...................................");
								System.out.println("Book is Added in to account already");
								System.out.println("...................................");
							}

							break;

							/// Code Changes for Modify Book Details
							
						case 3:

							System.out.println("\n-->Enter Below details to update book in to library");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							String updateBookId = scanner.next();
							boolean validateBookId1 = libraryValidations.idValidation(updateBookId);
							while (!validateBookId1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("Please enter valid book id i.e (only 6 digits)!!");
									System.out.println("................................................");
									bookId = scanner.next();
									if (libraryValidations.idValidation(bookId)) {
										break;
									}
								}
							}


							try {
								BooksDetails bookSearch = service.searchBook(Integer.parseInt(updateBookId));
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("\nHere is the book details      :");
								System.out.println("========================================================");
								System.out.println("Book Id                         : " + bookSearch.getBookid());
								System.out.println("Book name                       : " + bookSearch.getBookname());
								System.out.println("Author name                     : " + bookSearch.getBookauthor());
								System.out.println("Book Category                   : " + bookSearch.getBookcategory());
								System.out.println("========================================================");
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							
							System.out.println("\nEnter Book Name");
							String bookTitle1 = scanner.next();
							boolean ValidateByBookName1 = libraryValidations.nameValidation(bookTitle1);
							while (!ValidateByBookName1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...................................................");
									System.err.println("Please enter valid book name i.e (only alphabets)!!");
									System.out.println("...................................................");
									bookTitle = scanner.next();
									if (libraryValidations.nameValidation(bookTitle1)) {
										break;
									}
								}
							}
							System.out.println("\nEnter Book Author name");
							String authourName1 = scanner.next();
							boolean ValidateByAuthorName1 = libraryValidations.nameValidation(authourName1);
							while (!ValidateByAuthorName1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book author name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".........................................................");
									System.err.println("Please enter valid book author name i.e(only alphabets)!!");
									System.out.println(".........................................................");
									authourName1 = scanner.next();
									if (libraryValidations.nameValidation(authourName1)) {
										break;
									}
								}
							}
							
							System.out.println("\nEnter Book Category");
							String bookCategory1 = scanner.next();
							boolean ValidateByBookCategory1 = libraryValidations.nameValidation(bookCategory1);
							while (!ValidateByBookCategory1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book category");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".......................................................");
									System.err.println("Please enter valid book category i.e (only alphabets)!!");
									System.out.println(".......................................................");
									bookCategory = scanner.next();
									if (libraryValidations.nameValidation(bookCategory1)) {
										break;
									}
								}
							}

							BooksDetails bookInfo11 = new BooksDetails();

							bookInfo11.setBookid(Integer.parseInt(updateBookId));
							bookInfo11.setBookauthor(authourName1);
							bookInfo11.setBookname(bookTitle1);
							bookInfo11.setBookcategory(bookCategory1);
							
							try {

								boolean bookRemoved = service.isBookRemoved(Integer.parseInt(updateBookId));
								if (bookRemoved) {
									boolean bookAdded = service.isBookAdded(bookInfo11);
									System.out.println("Is Book updated in to account  : " + updateBookId);

									if (bookAdded) {
										System.out.println("");
										System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
										System.out.println("Book is updated in to account successfully!!");
										System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									}
								}
								
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("...................................");
								System.out.println("Book is failed to update in to account");
								System.out.println("...................................");
							}

							break;
							
							// Update Book details
							
							
						case 4:

							System.out.println("\n-->Enter Below Details to search a book by Id");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter book Id");
							String searchBookId = scanner.next();
							boolean validateByBookId = libraryValidations.idValidation(searchBookId);
							while (!validateByBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("Please enter valid book id i.e (only 6 digits)!!");
									System.out.println("................................................");
									searchBookId = scanner.next();
									if (libraryValidations.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BooksDetails bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("Search book Found");
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("\nHere is the book details      :");
								System.out.println("========================================================");
								System.out.println("Book Id                         : " + bookSearch.getBookid());
								System.out.println("Book name                       : " + bookSearch.getBookname());
								System.out.println("Author name                     : " + bookSearch.getBookauthor());
								System.out.println("Book Category                   : " + bookSearch.getBookcategory());
								System.out.println("========================================================");
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 5:

							System.out.println("\n-->Enter Below Details to search a book by book name");
							System.out.println("\n-->Enter book Name");
							String searchBookName = scanner.next();
							boolean validateByBookName = libraryValidations.nameValidation(searchBookName);
							while (!validateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...................................................");
									System.err.println("Please enter valid book Name i.e (only alphabets)!!");
									System.out.println("...................................................");
									searchBookName = scanner.next();
									if (libraryValidations.nameValidation(searchBookName)) {
										break;
									}
								}
							}
							try {
								BooksDetails searchBookName1 = service.searchBookByName(searchBookName);
								System.out.println("Search book Found");
								System.out.println("Here is the book details :");
								System.out.println("====================================================");
								System.out.println("Book Id             : " + searchBookName1.getBookid());
								System.out.println("Book name           : " + searchBookName1.getBookname());
								System.out.println("Author name         : " + searchBookName1.getBookauthor());
								System.out.println("Book Category		: " + searchBookName1.getBookcategory());
								System.out.println("====================================================");
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 6:

							System.out.println("\n-->Enter Below Details to search abook by book author name");
							System.out.println("\n-->Enter book author name");
							String bookAuthorName = scanner.next();
							boolean validateByBookauthorname = libraryValidations.nameValidation(bookAuthorName);
							while (!validateByBookauthorname) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book author name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................................................");
									System.err.println("Please enter valid book author name i.e (only alphabets)!!");
									System.out.println("..........................................................");
									bookAuthorName = scanner.next();
									if (libraryValidations.nameValidation(bookAuthorName)) {
										break;
									}
								}
							}
							try {
								BooksDetails bookSearchByAuthorName = service.searchBookByAuthorName(bookAuthorName);
								System.out.println("\n-->Search book Found");
								System.out.println("\n-->Here is the book details     : ");
								System.out.println("=======================================================");
								System.out.println("Book Id         : " + bookSearchByAuthorName.getBookid());
								System.out.println("Book name       : " + bookSearchByAuthorName.getBookname());
								System.out.println("Author name		: " + bookSearchByAuthorName.getBookauthor());
								System.out.println("=======================================================");

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 7:

							try {

								System.out.println("List of users              : ");
								List<UserDetails> userInfos = service.showAllUsers();

								for (UserDetails info : userInfos) {

									System.out.println("===========================================================");
									System.out.println("User id                    : " + info.getUserId());
									System.out.println("User Name                  : " + info.getUserName());
									System.out.println("User Email                 : " + info.getUserEmailId());
									System.out.println("User No Of Books Borrowed  : " + info.getNoOfBooksBorrowed());
									System.out.println("===========================================================");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println("..................");
								System.err.println("There are no users");
								System.out.println("..................");
							}
							break;

						case 8:

							try {
								System.out.println("List of Books in library are :");

								List<BooksDetails> allBooks = service.showAllLibraryBooks();
								for (BooksDetails book : allBooks) {

									System.out.println("=======================================================");
									System.out.println("Book id                      : " + book.getBookid());
									System.out.println("Book Name                    : " + book.getBookname());
									System.out.println("Book Authour                 : " + book.getBookauthor());
									System.out.println("Book Category                : " + book.getBookcategory());
									System.out.println("========================================================");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println(".....................................");
								System.err.println("sorry no books are present in library");
								System.out.println(".....................................");
							}
							break;

						case 9:

							try {

								System.out.println("List of request for book    : ");
								List<RequestDetails> requestInfos = service.showAllUserRequest();

								for (RequestDetails info : requestInfos) {

									System.out.println("=================================================");
									System.out.println("Book id             : " + info.getBookDetails().getBookid());
									System.out.println("Book Name           : " + info.getBookDetails().getBookname());
									System.out.println("User id             : " + info.getUserDetails().getUserId());
									System.out.println("User Name           : " + info.getUserDetails().getUserName());
									System.out.println("Book Issued         : " + info.isBookIssued());
									System.out.println("Book Returned       : " + info.isBookReturned());
									System.out.println("Book Issue Date     : " + info.getBookIssuedDate());
									System.out.println("Book returned Date	: " + info.getBookReturndate());
									System.out.println("=================================================");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println(".....................................");
								System.out.println("No requests are available right now!!");
								System.out.println(".....................................");
							}
							break;

						case 10:

							BooksDetails bookInfo2 = new BooksDetails();
							UserDetails userInfo2 = new UserDetails();
							System.out.println("\n-->Enter Below details to issue book to the user");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							String bookId1 = scanner.next();
							boolean validateByBookid1 = libraryValidations.idValidation(bookId1);
							while (!validateByBookid1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".................................................");
									System.err.println("Please enter valid book id i.e (only alphabets)!!");
									System.out.println(".................................................");
									bookId1 = scanner.next();
									if (libraryValidations.idValidation(bookId1)) {
										break;
									}
								}
							}
							System.out.println("\n-->Please enter below details to issue book");
							System.out.println("\n-->plesae make sure you enter 3 digits user id");
							System.out.println("\n-->Enter user id");
							String userId = scanner.next();
							boolean validateByUserId = libraryValidations.useridValidation(userId);
							while (!validateByUserId) {
								try {
									throw new LibrarayManagementExceptions("Please enter registered user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("Please enter valid user id i.e (only 3 digits)!!");
									System.out.println("................................................");
									userId = scanner.next();
									if (libraryValidations.useridValidation(userId)) {
										break;
									}
								}
							}

							bookInfo2.setBookid(Integer.parseInt(bookId1));
							userInfo2.setUserId(Integer.parseInt(userId));
							try {
								boolean isIssued = service.isBookIssued(userInfo2, bookInfo2);
								if (isIssued) {
									System.out.println("Book Issued Successfully!!");
								}

							} catch (LibrarayManagementExceptions e) {
								System.out.println(
										"....................................................................");
								System.err.println(
										"sorry book can't be issued, as you have exceeded your max limit(2)!!");
								System.out.println(
										"....................................................................");
							}
							break;

						case 11:

							System.out.println("\n-->Enter below details to recieve returned book");
							System.out.println("\n-->please make sure you enter 3 digits user id");
							System.out.println("\n-->Enter user id");
							String user1 = scanner.next();
							boolean validateByUserId1 = libraryValidations.useridValidation(user1);
							while (!validateByUserId1) {
								try {
									throw new LibrarayManagementExceptions("please enter registered user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...............................................");
									System.err.println("please enter valid user id i.e(only 3 digits)!!");
									System.out.println("...............................................");
									user1 = scanner.next();
									if (libraryValidations.useridValidation(user1)) {
										break;
									}
								}
							}
							System.out.println("\n-->Please make sure that Book Id must contain 6 digits");
							System.out.println("\n-->Enter Book id");
							String book1 = scanner.next();
							boolean validateByBid = libraryValidations.idValidation(book1);
							while (!validateByBid) {
								try {
									throw new LibrarayManagementExceptions("Please enter available  book id i.e");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".................................................");
									System.err.println("Please enter valid  book id i.e (only 6 digits)!!");
									System.out.println(".................................................");
									book1 = scanner.next();
									if (libraryValidations.idValidation(book1)) {
										break;
									}
								}
							}
							bookDetails.setBookid(Integer.parseInt(book1));
							userDetails.setUserId(Integer.parseInt(user1));
							try {
								boolean isReceived = service.isBookReceived(userDetails, bookDetails);
								if (isReceived) {
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									System.out.println(" successfully received book returned by the user");
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									System.out.println("Fine        :" + userDetails.getFine());
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println("............................");
								System.err.println("user haven't returned book!!");
								System.out.println("............................");
							}
							break;

						case 12:

							System.out.println("\n-->Enter Below details to remove book from library");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digits number");
							System.out.println("\n-->Enter Book id");
							String removeBookId = scanner.next();
							boolean validateByBookId1 = libraryValidations.idValidation(removeBookId);
							while (!validateByBookId1) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"......................................................................");
									System.err.println(
											"Please enter valid book id i.e (it should contain only 6 digits number");
									System.out.println(
											"......................................................................");
									removeBookId = scanner.next();
									if (libraryValidations.idValidation(removeBookId)) {
										break;
									}
								}
							}
							bookDetails.setBookid(Integer.parseInt(removeBookId));
							try {
								boolean bookRemoved = service.isBookRemoved(Integer.parseInt(removeBookId));
								if (bookRemoved) {
									System.out.println("Book Removed successfully from libraray!");
								}
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println(".....................................");
								System.err.println("Sorry book is not available to remove");
								System.out.println(".....................................");
							}
							break;
						}

					} while (check != 0);

				} catch (LibrarayManagementExceptions e) {
					System.out.println(e.getMessage());
				}
				break;
			
			case 2:
				
				System.out.println("\nPlease enter emailid in this format (vinitha@gmail.com)");
				System.out.println("\nEnter user Email id");
				String userEmailId1 = scanner.next();
				boolean validateByEmail = libraryValidations.useremailValidation(userEmailId1);
				while (!validateByEmail) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid Email Id i.e (vinitha@gmail.com)");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println(".................................................................");
						System.err.println("Please valid Email Address i.e in this format (vinitha@gmail.com)");
						System.out.println(".................................................................");
						userEmailId1 = scanner.next();
						if (libraryValidations.useremailValidation(userEmailId1)) {
							break;
						}
					}
				}
				System.out.println("\nEnter user password in this format (Vinitha@123)");
				System.out.println(
						"\nPassword should contain (8-15)characters, atleast one uppercase,lower case,special character");
				String userPassword1 = scanner.next();
				boolean validateByPassword = libraryValidations.userpasswordValidation(userPassword1);
				while (!validateByPassword) {
					try {
						throw new LibrarayManagementExceptions(
								"Please enter registered password i.e in this format((Vinitha@123))");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println("...............................................................");
						System.err.println("Please enter valid password i.e in this format((Vinitha@123))!!");
						System.out.println("...............................................................");
						userPassword1 = scanner.next();
						if (libraryValidations.userpasswordValidation(userPassword1)) {
							break;
						}
					}
				}

				try {
					
					UserDetails userInformation = userService.userLogin(userEmailId1, userPassword1);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("User logged in to account successfully!!");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					
					do {
						
						System.out.println("\n--------Press below numbers to perform user operations-------");
						System.out.println("\n1. To show list of Books in library");
						System.out.println("2. To Search Book by Book Id");
						System.out.println("3. To Search Book by Book Name");
						System.out.println("4. To Search Book by Book Author Name");
						System.out.println("5. To Place Book Request");
						System.out.println("6. To Return Book in library");
						System.out.println("0. To Exit");
						System.out.println("\nEnter your choice");
						
						userChoice = scanner.nextInt();
						
						switch (userChoice) {
						
						case 1:
							
							try {

								System.out.println("show List of Books in library :");
								List<BooksDetails> allBooks = service.showAllLibraryBooks();
								for (BooksDetails book : allBooks) {

									System.out.println("================================================");
									System.out.println("Book Id                 : " + book.getBookid());
									System.out.println("Book Name               : " + book.getBookname());
									System.out.println("Book Author name		: " + book.getBookauthor());
									System.out.println("Book Publisher          : " + book.getBookpublisher());
									System.out.println("Book Category           : " + book.getBookcategory());
									System.out.println("==================================================");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println(".........................");
								System.out.println("no books found in library");
								System.out.println(".........................");
							}
							break;
						
						case 2:
							
							System.out.println("\n-->Enter below details to search a book in library");
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							String searchBookId = scanner.next();
							
							boolean validateByBookId = libraryValidations.idValidation(searchBookId);
							while (!validateByBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("Please enter valid book id i.e (only 6 digits)!!");
									System.out.println("................................................");
									searchBookId = scanner.next();
									if (libraryValidations.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BooksDetails bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("search book found");
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("Here are the search book details  : ");
								System.out.println("===============================================");
								System.out.println("Book Id             : " + bookSearch.getBookid());
								System.out.println("Book Name           : " + bookSearch.getBookname());
								System.out.println("Book Author name    : " + bookSearch.getBookauthor());
								System.out.println("Book Category		: " + bookSearch.getBookcategory());
								System.out.println("===============================================");
							} catch (LibrarayManagementExceptions e) {
								System.out.println("..................................");
								System.out.println("sorry search book is not available");
								System.out.println("..................................");
							}
							break;

						case 3:
							
							System.out.println("\n-->Enter Below Details to search abook");
							System.out.println("\n-->Enter book Name");
							String bookName = scanner.next();
							
							boolean validateByBookName = libraryValidations.nameValidation(bookName);
							while (!validateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..................................................");
									System.err.println("Please enter valid book name i.e(only alphabets)!!");
									System.out.println("..................................................");
									bookName = scanner.next();
									if (libraryValidations.nameValidation(bookName)) {
										break;
									}
								}
							}
							try {
								BooksDetails searchBookName = userService.searchBookByName(bookName);
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("Search book Found");
								System.out.println("^^^^^^^^^^^^^^^^^^^");
								System.out.println("Here is the book details   :");
								System.out.println("==============================================================");
								System.out.println("Book Id                    : " + searchBookName.getBookid());
								System.out.println("Book name                  : " + searchBookName.getBookname());
								System.out.println("Author name                : " + searchBookName.getBookauthor());
								System.out.println("==============================================================");

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 4:
							
							System.out.println("\n-->Enter Below Details to search abook by book author name");
							System.out.println("\n-->Enter book author name");
							String bookAuthorName = scanner.next();
							
							boolean validateByBookauthorname = libraryValidations.nameValidation(bookAuthorName);
							while (!validateByBookauthorname) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book author name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................................................");
									System.err.println("Please enter valid book author name i.e (only alphabets)!!");
									System.out.println("..........................................................");
									bookAuthorName = scanner.next();
									if (libraryValidations.nameValidation(bookAuthorName)) {
										break;
									}
								}
							}
							try {
								BooksDetails bookSearchByAuthorName = userService
										.searchBookByAuthorName(bookAuthorName);
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("Search book Found");
								System.out.println("^^^^^^^^^^^^^^^^^");
								System.out.println("Here is the book details     : ");
								System.out.println("===========================================================");
								System.out.println("Book Id         : " + bookSearchByAuthorName.getBookid());
								System.out.println("Book name       : " + bookSearchByAuthorName.getBookname());
								System.out.println("Author name		: " + bookSearchByAuthorName.getBookauthor());
								System.out.println("===========================================================");

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 5:
							
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							String bookId = scanner.next();
							
							boolean validateByBookId1 = libraryValidations.idValidation(bookId);
							while (!validateByBookId1) {
								try {
									throw new LibrarayManagementExceptions("Please enter available book id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(".......................................................");
									System.err.println("Please enter valid book id i.e (only 6 digits number)!!");
									System.out.println(".......................................................");
									bookId = scanner.next();
									if (libraryValidations.idValidation(bookId)) {
										break;
									}
								}
							}
							bookDetails.setBookid(Integer.parseInt(bookId));

							System.out.println("\nEnter user id");
							String userId = scanner.next();
							
							boolean validateByUserId1 = libraryValidations.useridValidation(userId);
							while (!validateByUserId1) {
								try {
									throw new LibrarayManagementExceptions("please enter registered user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("please enter valid user id i.e (only 3 digits)!!");
									System.out.println("................................................");
									userId = scanner.next();
									if (libraryValidations.useridValidation(userId)) {
										break;
									}
								}
							}
							userInformation.setUserId(Integer.parseInt(userId));

							try {
								RequestDetails request = userService.bookRequest(userInformation, bookDetails);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Request has been placed to admin");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("\nBellow are the book request Details\n");
								System.out.println("================================================");
								System.out.println("User Id         : " + request.getUserDetails().getUserId());
								System.out.println("User name       : " + request.getUserDetails().getUserName());
								System.out.println("Book Id         : " + request.getBookDetails().getBookid());
								System.out.println("Book Name		: " + request.getBookDetails().getBookname());
								System.out.println("================================================");

							} catch (LibrarayManagementExceptions e) {
								System.out.println("............................................");
								System.err.println("sorry you are not allowed to place a request");
								System.out.println("............................................");
							}
							break;
						
						case 6:
							
							System.out.println("\nEnter bellow details to return a book to admin");
							System.out.println("\n-->Entering User Id should be a 3 digit number");
							System.out.println("\n-->Enter user id");
							String user = scanner.next();
							
							boolean validateByUser = libraryValidations.useridValidation(user);
							while (!validateByUser) {
								try {
									throw new LibrarayManagementExceptions("please enter registered user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................................");
									System.err.println("please enter valid user id i.e (only 3 digits)!!");
									System.out.println("................................................");
									user = scanner.next();
									if (libraryValidations.useridValidation(user)) {
										break;
									}
								}
							}
							System.out.println("\n-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("\n-->Enter Book id");
							int book = scanner.nextInt();
							userInformation.setUserId(Integer.parseInt(user));
							bookDetails.setBookid(book);

							try {
								RequestDetails returnInfo = userService.bookReturn(userInformation, bookDetails);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Returning book to admin");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Here are the book return details  :");
								System.out.println("================================================================");
								System.out.println("User Id           : " + returnInfo.getUserDetails().getUserId());
								System.out.println("Book Id           : " + returnInfo.getBookDetails().getBookid());
								System.out.println("Is book Retured   : " + returnInfo.isBookReturned());
								System.out.println("================================================================");
							} catch (LibrarayManagementExceptions e) {
								System.out.println("..........................");
								System.out.println("sorry invalid book request");
								System.out.println("..........................");
							}
							break;

						default:
							System.err.println("invalid data");
							break;
						}
						
					} while (userChoice != 0);

				} catch (LibrarayManagementExceptions e) {
					System.out.println(".............................");
					System.err.println("User cannot log in to account");
					System.out.println(".............................");
				}
			
			}
		
		} while (true);
	
	}
}