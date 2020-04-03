package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;
import com.capgemini.librarymanagementsystem.factory.LMSFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.LibraryManagementValidation;

public class LMSController {

	public static void main(String[] args) {
		LMSController lmsController = new LMSController();
		try {
			lmsController.lmsController();
		} catch (InputMismatchException ime) {
			System.err.println("please enter a correct number as your choice!!");
		} finally {
			try {
				lmsController.lmsController();
			} catch (InputMismatchException ime) {
				System.err.println("please enter a correct number as your choice!!");
			} finally {
				lmsController.lmsController();
			}
		}
	}

	public void lmsController() {

		AdminService service = LMSFactory.getAdminService();
		UserService userService = LMSFactory.getUserService();
		Scanner scanner = new Scanner(System.in);
		UserInformation userBean = new UserInformation();
		BooksInformation bookInfo = new BooksInformation();
		Admininformation adminBean = new Admininformation();
		LibraryManagementValidation validation = new LibraryManagementValidation();

		int choice, check, userChoice;
		do {
			System.out.println("press 1 to login in to admin account");
			System.out.println("press 2 to Enter int o user account");
			System.out.println("Enter your choice");
			choice = scanner.nextInt();
			switch (choice) {

			case 1:
				System.out.println("Enter Admin Email id");
				String adminEmailId = scanner.next();
				System.out.println("Enter Admin password");
				String adminPassword = scanner.next();

				try {
					Admininformation adminInfo = service.adminLogin(adminEmailId, adminPassword);
					System.out.println("******************************************");
					System.out.println("Admin logged in to account.");
					System.out.println("******************************************");
					do {
						System.out.println("1. Register");
						System.out.println("2. Search Book");
						System.out.println("3. Add Book");
						System.out.println("4. Remove Book");
						System.out.println("5. Show All Books In The Libraray");
						System.out.println("6. Issue Book");
						System.out.println("7. Show All Users In The Libarary");
						System.out.println("8. Show All Requests");
						System.out.println("9. Receive Returned Book");
						System.out.println("0. Exit");

						System.out.println("Enter your choice");
						check = scanner.nextInt();
						switch (check) {
						case 1:
							System.out.println("Enter your Registration Details");
							System.out.println("Enter  3 digit User Id");
							String regId = scanner.next();
							boolean useridValidation = validation.useridValidation(regId);
							while (!useridValidation) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user id");
									regId = scanner.next();
									if (validation.useridValidation(regId)) {
										break;
									}

								}
							}
							System.out.println("Enter User Name");
							String regName = scanner.next();
							boolean nameValidation = validation.nameValidation(regName);
							while (!nameValidation) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid username");
									regName = scanner.next();
									if (validation.nameValidation(regName)) {
										break;
									}
								}
							}
							System.out.println("Enter Email Id");
							String regEmailId = scanner.next();
							boolean useremailValidation = validation.useremailValidation(regEmailId);
							while (!useremailValidation) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user emailId");
									regEmailId = scanner.next();
									if (validation.useremailValidation(regEmailId)) {
										break;
									}
								}
							}
							System.out.println("Enter Password");
							String regPassword = scanner.next();
							boolean userpasswordValidation = validation.userpasswordValidation(regPassword);
							while (!userpasswordValidation) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println(
											"Enter password thet should contain atleast one lowercase, uppercase,number,special character and minimun 8 characters");
									System.err.println("Enter valid Password");
									regPassword = scanner.next();
									if (validation.userpasswordValidation(regPassword)) {
										break;
									}
								}
							}

							UserInformation userInfo = new UserInformation();
							userInfo.setUserId(Integer.parseInt(regId));
							userInfo.setUserName(regName);
							userInfo.setUserEmailId(regEmailId);
							userInfo.setUserPassword(regPassword);
							boolean result = service.register(userInfo);

							if (result) {
								System.out.println("******************************************");
								System.out.println("User Registered Sucessfully");
								System.out.println("******************************************");
							} else {
								System.out.println("******************************************");
								System.out.println("User already Exsits");
								System.out.println("******************************************");
							}
							break;

						case 2:
							System.out.println("Search a Book");
							System.out.println("Enter book Id");
							String searchBookId = scanner.next();
							boolean idValidation = validation.idValidation(searchBookId);
							while (!idValidation) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									searchBookId = scanner.next();
									if (validation.idValidation(searchBookId)) {
										break;
									}
								}
							}

							try {
								BooksInformation Search = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("Book found");
								System.out.println("book id is: " + Search.getBookid());
								System.out.println("book name is: " + Search.getBookname());
								System.out.println("book author name is: " + Search.getBookauthor());

							} catch (Exception e) {
								System.out.println("******************************************");
								System.out.println("Search Book not found");
								System.out.println("******************************************");
							}
							break;

						case 3:
							System.out.println("Add Book Details");
							System.out.println("Enter Book id");
							String bookId = scanner.next();
							boolean idValidation1 = validation.idValidation(bookId);
							while (!idValidation1) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									bookId = scanner.next();
									if (validation.idValidation(bookId)) {
										break;
									}
								}
							}
							System.out.println("Enter Author name");
							String authourName = scanner.next();
							boolean nameValidation1 = validation.nameValidation(authourName);
							while (!nameValidation1) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("******************************************");
									System.err.println("Enter valid book author name");
									System.out.println("******************************************");
									authourName = scanner.next();
									if (validation.nameValidation(authourName)) {
										break;
									}
								}
							}

							System.out.println("Enter Book Name");
							String bookName = scanner.next();
							boolean nameValidation2 = validation.nameValidation(bookName);
							while (!nameValidation2) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("******************************************");
									System.err.println("Enter valid book name");
									System.out.println("******************************************");
									bookName = scanner.next();
									if (validation.nameValidation(bookName)) {
										break;
									}
								}
							}

							BooksInformation book = new BooksInformation();

							book.setBookid(Integer.parseInt(bookId));
							book.setBookauthor(authourName);
							book.setBookname(bookName);
							boolean bookAdded = service.isBookAdded(book);
							System.out.println("is book Added : " + bookAdded);

							if (bookAdded) {
								System.out.println("******************************************");
								System.out.println("book is added in to your Account");
								System.out.println("******************************************");
							} else {
								System.out.println("******************************************");
								System.out.println("Book is Not available");
								System.out.println("******************************************");
							}

							break;
						case 4:
							System.out.println("Enter book id to remove ");
							String removeBook = scanner.next();
							boolean idValidation2 = validation.idValidation(removeBook);
							while (!idValidation2) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									removeBook = scanner.next();
									if (validation.idValidation(removeBook)) {
										break;
									}
								}
							}

							bookInfo.setBookid(Integer.parseInt(removeBook));
							boolean bookRemoved = service.isBookRemoved(Integer.parseInt(removeBook));
							if (bookRemoved) {
								System.out.println("******************************************");
								System.out.println("Book Removed Successfully");
								System.out.println("******************************************");
							} else {
								System.out.println("******************************************");
								System.out.println("Book is already removed");
								System.out.println("******************************************");
							}
							break;

						case 5:

							try {
								System.out.println("Books present in library are :");
								System.out.println("-------------------------------");

								List<BooksInformation> allBooks = service.showAllLibraryBooks();
								for (BooksInformation book1 : allBooks) {

									System.out.println("Book id ---------- " + book1.getBookid());
									System.out.println("Book Name -------- " + book1.getBookname());
									System.out.println("Book Authour------ " + book1.getBookauthor());
									System.out.println("-------------------------------");
								}
							} catch (Exception e) {
								System.out.println("******************************************");
								System.out.println("no books are available in the library");
								System.out.println("******************************************");
							}
							break;

						case 6:
							UserInformation user2 = new UserInformation();
							BooksInformation book2 = new BooksInformation();

							System.out.println("enter Book Id");
							String bookId1 = scanner.next();
							boolean idValidation3 = validation.idValidation(bookId1);
							while (!idValidation3) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									bookId1 = scanner.next();
									if (validation.idValidation(bookId1)) {
										break;
									}
								}
							}

							System.out.println("enter User Id");
							String userId = scanner.next();
							boolean useridValidation1 = validation.useridValidation(userId);
							while (!useridValidation1) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user id");
									userId = scanner.next();
									if (validation.useridValidation(userId)) {
										break;
									}
								}
							}
							book2.setBookid(Integer.parseInt(bookId1));
							user2.setUserId(Integer.parseInt(userId));
							try {
								boolean isIssued = service.isBookIssued(user2, book2);
								if (isIssued) {
									System.out.println("Book Issued Sucessfully");
								} else {
									System.out.println("Book cannot be issued");
								}

							} catch (Exception e) {
								System.out.println("******************************************");
								System.err.println("Invalid Book Request sorry book can not be issued");
								System.out.println("******************************************");
							}
							break;

						case 7:
							try {
								System.out.println("To Show all the users in library :");
								System.out.println("-------------------------------");

								List<UserInformation> userInfo1 = service.showAllUsers();
								for (UserInformation info : userInfo1) {

									System.out.println("User id ---------- " + info.getUserId());
									System.out.println("User Name -------- " + info.getUserName());
									System.out.println("User Email------ " + info.getUserEmailId());
									System.out.println(
											"User No Of Books Borrowed ------- " + info.getNoOfBooksBorrowed());
									System.out.println("-------------------------------");
								}
							} catch (Exception e) {
								System.out.println("no books present in library");
							}
							break;
						case 8:
							try {
								System.out.println("Requests for Books are :");
								System.out.println("-------------------------------");

								List<RequestInformation> requestInfo = service.showAllUserRequest();
								for (RequestInformation info : requestInfo) {

									System.out.println("Book id ---------- " + info.getBookInformation().getBookid());
									System.out.println("Book Name -------- " + info.getBookInformation().getBookname());
									System.out.println("User id----------- " + info.getUserInformation().getUserId());
									System.out.println("User Name -------- " + info.getUserInformation().getUserName());
									System.out.println("Book Issued ------" + info.isBookIssued());
									System.out.println("Book Returned------" + info.isBookReturned());
									System.out.println("-------------------------------");
								}
							} catch (Exception e) {
								System.out.println("no books present in library");
							}
							break;
						case 9:
							System.out.println("Receive Returned Book");
							System.out.println("-----------------------");
							System.out.println("Enter User Id");
							String user1 = scanner.next();
							boolean useridValidation2 = validation.useridValidation(user1);
							while (!useridValidation2) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user id");
									user1 = scanner.next();
									if (validation.useridValidation(user1)) {
										break;
									}
								}
							}
							System.out.println("Enter Book Id");
							String book1 = scanner.next();
							boolean idValidation4 = validation.idValidation(book1);
							while (!idValidation4) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									book1 = scanner.next();
									if (validation.idValidation(book1)) {
										break;
									}
								}
							}

							bookInfo.setBookid(Integer.parseInt(book1));
							userBean.setUserId(Integer.parseInt(user1));
							boolean isReceive = service.isBookReceived(userBean, bookInfo);
							if (isReceive) {
								System.out.println(" Received book that is returned by the user");
							} else {
								System.out.println("Admin unable to receive");
							}

						}

					} while (check != 0);

				} catch (Exception e) {
					System.out.println("****************************************************");
					System.err.println("Enter valid credentials");
					System.out.println("****************************************************");
				}
				break;

			case 2:
				System.out.println("-----------------");
				System.out.println("Enter User Email id");

				String userEmailId1 = scanner.next();
				System.out.println("Enter User password");
				String userPassword1 = scanner.next();

				try {
					UserInformation userInfo = userService.userLogin(userEmailId1, userPassword1);
					System.out.println("User logged in to account Successfully");
					do {
						System.out.println("1. To Show All Books in Library");
						System.out.println("2. Search a Book");
						System.out.println("3. Request a Book");
						System.out.println("4. Return Book");
						System.out.println("0. Exit");
						System.out.println("Enter your choice");
						userChoice = scanner.nextInt();
						switch (userChoice) {
						case 1:
							try {
								System.out.println("Books present in library are :");
								System.out.println("-------------------------------");

								List<BooksInformation> allBooks = service.showAllLibraryBooks();
								for (BooksInformation book : allBooks) {

									System.out.println(book.getBookid());
									System.out.println(book.getBookname());
									System.out.println(book.getBookauthor());
									System.out.println("-------------------------------");
								}
							} catch (Exception e) {
								System.out.println("no books present in library");
							}
							break;
						case 2:
							System.out.println("Search a Book");
							System.out.println("Enter book Id");
							String searchBookId = scanner.next();
							boolean idValidation3 = validation.idValidation(searchBookId);
							while (!idValidation3) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									searchBookId = scanner.next();
									if (validation.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BooksInformation bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("Book found");
								System.out.println(bookSearch.getBookid());
								System.out.println(bookSearch.getBookname());
								System.out.println(bookSearch.getBookauthor());

							} catch (Exception e) {
								System.out.println("Book not found");

							}
							break;

						case 3:
							System.out.println("Enter book id");
							String bookId = scanner.next();
							boolean idValidation4 = validation.idValidation(bookId);
							while (!idValidation4) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									bookId = scanner.next();
									if (validation.idValidation(bookId)) {
										break;
									}
								}
							}
							bookInfo.setBookid(Integer.parseInt(bookId));
							System.out.println("Enter user id");
							String userId = scanner.next();
							boolean useridValidation2 = validation.useridValidation(userId);
							while (!useridValidation2) {
								try {									
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user id");
									userId = scanner.next();
									if (validation.useridValidation(userId)) {
										break;
									}								}
							}

							userBean.setUserId(Integer.parseInt(userId));
					
//							bookInfo.setBookid(bookId);
//							System.out.println("Enter user id");
//							int userId = scanner.nextInt();
//							userBean.setUserId(userId);
							try {
								RequestInformation request = userService.bookRequest(userBean, bookInfo);
								System.out.println("Request placed to admin");
								System.out.println("User Id-----" + request.getUserInformation().getUserId());
								System.out.println("User name---" + request.getUserInformation().getUserName());
								System.out.println("Book Id-----" + request.getBookInformation().getBookid());
								System.out.println("Book Name---" + request.getBookInformation().getBookname());

							} catch (Exception e) {

								System.out.println("Enter valid data or Invalid Request");
							}
							break;

						case 4:
							System.out.println("Returning a book:");
							System.out.println("------------------");
							System.out.println("Enter User Id");
							String user = scanner.next();
							boolean useridValidation3 = validation.useridValidation(user);
							while (!useridValidation3) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.err.println("Enter valid user id");
									user = scanner.next();
									if (validation.useridValidation(user)) {
										break;
									}
								}
							}

							System.out.println("Enter Book Id");
							String book = scanner.next();
							boolean idValidation5 = validation.idValidation(book);
							while (!idValidation5) {
								try {
									throw new LibrarayManagementExceptions("Enter valid information");
								} catch (LibrarayManagementExceptions lme) {
									System.out.println("Enetr 6 digit bookId");
									System.err.println("Enter valid BookId");
									book = scanner.next();
									if (validation.idValidation(book)) {
										break;
									}
								}
							}

							userBean.setUserId(Integer.parseInt(user));
							bookInfo.setBookid(Integer.parseInt(book));
							;

							try {
								RequestInformation requestInfo = userService.bookReturn(userBean, bookInfo);
								System.out.println("Return the book to admin");
								System.out.println("User Id ------" + requestInfo.getUserInformation().getUserId());
								System.out.println("Book Id ------" + requestInfo.getBookInformation().getBookname());
								System.out.println("Returning  Book--" + requestInfo.isBookReturned());

							} catch (Exception e) {
								System.out.println("Invalid Return");
							}
							break;

						default:
							System.out.println("Invalid option");
							break;
						}
					} while (userChoice != 0);

				} catch (Exception e) {

					System.out.println("*****************************************************************");
					System.err.println("User cannot log in first please  complete registration process!");
					System.out.println("*****************************************************************");
				}

			}

		} while (true);
	}

}
