package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import verchaska.practise.beans.StudentLibraryBookBean;
import verchaska.practise.beans.StudentLibraryJdbcIssueDetailBean;
import verchaska.practise.beans.StudentLibraryMemberBean;
import verchaska.practise.dao.StudentLibraryBookDao;
import verchaska.practise.dao.StudentLibraryIssueDetailDao;
import verchaska.practise.dao.StudentLibraryMemberDao;

public class StudentLibraryJdbc {
	public static void main(String args[]) throws IOException, SQLException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<StudentLibraryBookBean> libraryBookInfo = new ArrayList<>();
		ArrayList<StudentLibraryMemberBean> libraryMemberInfo = new ArrayList<>();
		ArrayList<StudentLibraryJdbcIssueDetailBean> libraryBookIssueDetail = new ArrayList<>();
		new StudentLibraryJdbc().performOperations(libraryBookInfo, libraryMemberInfo, libraryBookIssueDetail);
		br.close();
	}

	private void performOperations(ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryJdbcIssueDetailBean> libraryBookIssueDetail)
			throws IOException, SQLException, ParseException {
		int choiceOfOperation = 0;
		while (choiceOfOperation != 6) {
			System.out.println(
					"\n1.Add new Member.\n2.Add book.\n3.Issue a book.\n4.Return a book.\n5.Display database.\n6.Exit.\nWhich operation do you want to perform.");
			choiceOfOperation = new InputClass().getNaturalNumberInputInRange(7);
			switch (choiceOfOperation) {
			case 1:
				new StudentLibraryJdbc().addNewMember(libraryMemberInfo);
				break;
			case 2:
				new StudentLibraryJdbc().addBook(libraryBookInfo);
				break;
			case 3:
				new StudentLibraryJdbc().issueBook();
				break;
			case 4:
				new StudentLibraryJdbc().returnBook();
				break;
			case 5:
				new StudentLibraryJdbc().displayDatabase(libraryMemberInfo, libraryBookInfo, libraryBookIssueDetail);
				break;
			default:
				System.out.println("thank you for visiting.....");
				break;
			}
		}

	}

	private void displayDatabase(ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryJdbcIssueDetailBean> libraryBookIssueDetail) throws SQLException {
		libraryMemberInfo = new StudentLibraryMemberDao().getMemberDataFromDatabase();
		libraryBookInfo = new StudentLibraryBookDao().getBookInfoFromDatabase();
		libraryBookIssueDetail = new StudentLibraryIssueDetailDao().getTransactionDetail();
		System.out.println("\n--------------------Library member information---------------------\n");
		if (libraryMemberInfo.isEmpty()) {
			System.out.println("no data present");
		} else {
			for (StudentLibraryMemberBean object : libraryMemberInfo) {
				System.out.println(object);
			}
		}
		System.out.println("\n--------------------Library books information---------------------\n");
		if (libraryBookInfo.isEmpty()) {
			System.out.println("no data present");
		} else {
			for (StudentLibraryBookBean object : libraryBookInfo) {
				System.out.println(object);
			}
		}
		System.out.println("\n--------------------Library transactions information---------------------\n");
		if (libraryBookIssueDetail.isEmpty()) {
			System.out.println("no data present\n");
		} else {
			for (StudentLibraryJdbcIssueDetailBean object : libraryBookIssueDetail) {
				System.out.println(object);
			}
		}
	}

	private void returnBook() throws SQLException, IOException, ParseException {
		System.out.println("enter your member ID");
		int memberId = new InputClass().getNaturalNumberInput();
		if (new StudentLibraryMemberDao().doesMemberIdExist(memberId)) {
			System.out.println("enter book ID of book which you want to return");
			int bookId = new InputClass().getNaturalNumberInput();
			if (new StudentLibraryBookDao().bookAlreadyExists(bookId)) {
				if (new StudentLibraryIssueDetailDao().isBookAlreadyIssued(bookId, memberId)) {
					System.out.println("enter thr return date of the book in dd/mm/yyyy format");
					String returnDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new DateInputClass().getDDMMYYYYInput());
					new StudentLibraryIssueDetailDao().setReturnDateOfBook(returnDate, memberId, bookId);
					new StudentLibraryBookDao().updateBookQuantity(bookId, "increment");
					System.out.println("\nbook returned successfully\n");
				} else {
					System.out.println("sorry book cannot be returned");
				}
			} else {
				System.out.println("book ID of the book you want to return does not exist in inventory");
			}
		} else {
			System.out.println("sorry but you are not a member of library");
		}
	}

	private void issueBook() throws SQLException, IOException, ParseException {
		System.out.println("Enter your member ID");
		int memberId = new InputClass().getNaturalNumberInput();
		if (new StudentLibraryMemberDao().doesMemberIdExist(memberId)) {
			System.out.println("enter name of book which you want to issue");
			String bookName = new InputClass().getStringInput();
			System.out.println("Enter name of book author which you want to issue");
			String bookAuthor = new InputClass().getStringInput();
			if (new StudentLibraryBookDao().bookAlreadyExistsInDatabase(bookName, bookAuthor)) {
				int bookId = new StudentLibraryBookDao().fetchBookId(bookName, bookAuthor);
				if (!(new StudentLibraryIssueDetailDao().isBookAlreadyIssued(bookId, memberId)
						|| new StudentLibraryIssueDetailDao().threeBooksIssuedAlready(memberId))) {

					if (new StudentLibraryBookDao().isBookInStock(bookId)) {
						System.out.println("enter issue date in dd/mm/yyyy format");
						String bookIssueDate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new DateInputClass().getDDMMYYYYInput());
						new StudentLibraryIssueDetailDao().createEntryOfIssue(memberId, bookId, bookIssueDate);
						new StudentLibraryBookDao().updateBookQuantity(bookId, "decrement");
						System.out.println("\nbook has been issued successsfully\n");

					} else {
						System.out.println("sorry book is out of stock");
					}

				} else {
					System.out.println("book cannot be issued");
				}
			} else {
				System.out.println("no such book exists");
			}
		} else {
			System.out.println("sorry you are not a member of library");
		}

	}

	private void addBook(ArrayList<StudentLibraryBookBean> libraryBookInfo) throws SQLException, IOException {

		System.out.println("how many books do you want to enter");
		int noOfBooks = new InputClass().getNaturalNumberInput();
		while (noOfBooks != 0) {
			System.out.println("Enter name of book");
			String bookName = new InputClass().getStringInput();
			System.out.println("Enter author of book");
			String bookAuthor = new InputClass().getStringInput();
			System.out.println("enter book quantity");
			int bookQuantity = new InputClass().getNaturalNumberInput();
			if (new StudentLibraryBookDao().bookAlreadyExistsInDatabase(bookName, bookAuthor)) {
				int bookId = new StudentLibraryBookDao().fetchBookId(bookName, bookAuthor);
				System.out.println("book already exists so updating quantity instead");
				new StudentLibraryBookDao().increamentBookQuantity(bookId, bookQuantity);
			} else {

				if (new StudentLibraryJdbc().bookAlreadyExistsInList(libraryBookInfo, bookName, bookAuthor)) {
					int bookId = new StudentLibraryJdbc().fetchBookIdFromList(libraryBookInfo, bookName, bookAuthor);
					System.out.println("book already exists so updating quantity instead");
					new StudentLibraryJdbc().increamentBookQuantityInList(libraryBookInfo, bookId, bookQuantity);
				} else {

					int totalBooksInLibrary = new StudentLibraryBookDao().getTotalNumberOfBooks();
					libraryBookInfo
							.add(new StudentLibraryBookBean(++totalBooksInLibrary, bookName, bookAuthor, bookQuantity));
				}
			}
			noOfBooks--;
		}
		new StudentLibraryBookDao().addNewBooks(libraryBookInfo);
		System.out.println("operation successful");
		libraryBookInfo.clear();

	}

	private void increamentBookQuantityInList(ArrayList<StudentLibraryBookBean> libraryBookInfo, int bookId,
			int bookQuantity) {
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if (bookId == object.getBookId()) {
				object.setBookQuantity(object.getBookQuantity() + bookQuantity);
				break;
			}
		}
	}

	private int fetchBookIdFromList(ArrayList<StudentLibraryBookBean> libraryBookInfo, String bookName,
			String bookAuthor) {
		int bookId = 0;
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if ((bookAuthor.equals(object.getBookAuthor())) && (bookName.equals(object.getBookName()))) {
				bookId = object.getBookId();
				break;
			}
		}
		return bookId;
	}

	private boolean bookAlreadyExistsInList(ArrayList<StudentLibraryBookBean> libraryBookInfo, String bookName,
			String bookAuthor) {
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if ((bookAuthor.equals(object.getBookAuthor())) && (bookName.equals(object.getBookName()))) {
				return true;
			}
		}

		return false;
	}

	private void addNewMember(ArrayList<StudentLibraryMemberBean> libraryMemberInfo) throws IOException, SQLException {
		System.out.println("how many members do you want to enter:");
		int noOfMembers = new InputClass().getNaturalNumberInput();
		int totalMembersOfLibrary = new StudentLibraryMemberDao().getTotalNumberOfMembers();
		while (noOfMembers != 0) {
			System.out.println("enter your name:");
			String nameOfMember = new InputClass().getStringInput();
			libraryMemberInfo.add(new StudentLibraryMemberBean(++totalMembersOfLibrary, nameOfMember));
			noOfMembers--;
		}
		new StudentLibraryMemberDao().addNewMembers(libraryMemberInfo);
		System.out.println("operation successful");
		libraryMemberInfo.clear();
	}

}
