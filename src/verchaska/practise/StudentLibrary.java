package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import verchaska.practise.beans.StudentLibraryBookBean;
import verchaska.practise.beans.StudentLibraryIssueDetailBean;
import verchaska.practise.beans.StudentLibraryMemberBean;

public class StudentLibrary {
	public static void main(String args[]) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<StudentLibraryBookBean> libraryBookInfo = new ArrayList<>();
		ArrayList<StudentLibraryMemberBean> libraryMemberInfo = new ArrayList<>();
		ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail = new ArrayList<>();
		new StudentLibrary().performOperations(libraryBookInfo, libraryMemberInfo, libraryBookIssueDetail);
		br.close();
	}

	private void performOperations(ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) throws IOException, ParseException {
		int choiceOfOperation = 0;
		while (choiceOfOperation != 6) {
			System.out.println(
					"\n1.Add new Member.\n2.Add book.\n3.Issue a book.\n4.Return a book.\n5.Display database.\n6.Exit.\nWhich operation do you want to perform.");
			choiceOfOperation = new InputClass().getNaturalNumberInputInRange(7);
			switch (choiceOfOperation) {
			case 1:
				new StudentLibrary().addNewMember(libraryMemberInfo);
				break;
			case 2:
				new StudentLibrary().addBook(libraryBookInfo);
				break;
			case 3:
				new StudentLibrary().issueBook(libraryMemberInfo, libraryBookInfo, libraryBookIssueDetail);
				break;
			case 4:
				new StudentLibrary().returnBook(libraryMemberInfo, libraryBookInfo, libraryBookIssueDetail);
				break;
			case 5:
				new StudentLibrary().displayDatabase(libraryMemberInfo, libraryBookInfo, libraryBookIssueDetail);
				break;
			default:
				System.out.println("thank you for visiting.....");
				break;
			}
		}

	}

	private void displayDatabase(ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) {
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
			for (StudentLibraryIssueDetailBean object : libraryBookIssueDetail) {
				System.out.println(object);
			}
		}

	}

	private void returnBook(ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) throws IOException, ParseException {
		System.out.println("enter your member ID");
		int memberId = new InputClass().getNaturalNumberInput();
		if (new StudentLibrary().doesMemberIdExist(libraryMemberInfo, memberId)) {
			System.out.println("enter book ID of book which you want to return");
			int bookId = new InputClass().getNaturalNumberInput();
			if (new StudentLibrary().bookAlreadyExists(libraryBookInfo, bookId)) {
				if (new StudentLibrary().isBookAlreadyIssued(bookId, memberId, libraryBookIssueDetail)) {
					System.out.println("enter thr return date of the book in dd/mm/yyyy format");
					Date returnDate = new DateInputClass().getDDMMYYYYInput();
					new StudentLibrary().setReturnDateOfBook(returnDate, memberId, bookId, libraryBookIssueDetail);
					new StudentLibrary().updateBookQuantity(libraryBookInfo, bookId, "increment");
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

	private void setReturnDateOfBook(Date returnDate, int memberId, int bookId,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) {

		for (StudentLibraryIssueDetailBean object : libraryBookIssueDetail) {
			if ((object.getLibraryMemberId() == memberId && object.getLibraryBookId() == bookId)
					&& object.getLibraryBookReturnDate() == null) {
				object.setLibraryBookReturnDate(returnDate);
				break;
			}
		}

	}

	private void issueBook(ArrayList<StudentLibraryMemberBean> libraryMemberInfo,
			ArrayList<StudentLibraryBookBean> libraryBookInfo,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) throws IOException, ParseException {
		System.out.println("Enter your member ID");
		int memberId = new InputClass().getNaturalNumberInput();
		if (new StudentLibrary().doesMemberIdExist(libraryMemberInfo, memberId)) {
			System.out.println("enter name of book which you want to issue");
			String bookName = new InputClass().getStringInput();
			System.out.println("Enter name of book author which you want to issue");
			String bookAuthor = new InputClass().getStringInput();
			if (new StudentLibrary().bookAlreadyExists(libraryBookInfo, bookName, bookAuthor)) {
				int bookId = new StudentLibrary().fetchBookId(libraryBookInfo, bookName, bookAuthor);
				if (!(isBookAlreadyIssued(bookId, memberId, libraryBookIssueDetail)
						|| threeBooksIssuedAlready(memberId, libraryBookIssueDetail))) {

					if (new StudentLibrary().isBookInStock(bookId, libraryBookInfo)) {
						System.out.println("enter issue date in dd/mm/yyyy format");
						Date bookIssueDate = new DateInputClass().getDDMMYYYYInput();
						libraryBookIssueDetail
								.add(new StudentLibraryIssueDetailBean(memberId, bookId, bookIssueDate, null));
						new StudentLibrary().updateBookQuantity(libraryBookInfo, bookId, "decrement");
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

	private boolean isBookInStock(int bookId, ArrayList<StudentLibraryBookBean> libraryBookInfo) {
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if (object.getBookId() == bookId && object.getBookQuantity() > 0) {
				return true;
			}

		}

		return false;
	}

	private boolean threeBooksIssuedAlready(int memberId,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) {
		int booksNotReturnedCounter = 0;
		for (StudentLibraryIssueDetailBean object : libraryBookIssueDetail) {

			if (object.getLibraryMemberId() == memberId && object.getLibraryBookReturnDate() == null)
				booksNotReturnedCounter++;
		}
		return (booksNotReturnedCounter >= 3);

	}

	private boolean isBookAlreadyIssued(int bookId, int memberId,
			ArrayList<StudentLibraryIssueDetailBean> libraryBookIssueDetail) {

		for (StudentLibraryIssueDetailBean object : libraryBookIssueDetail) {
			if ((bookId == object.getLibraryBookId() && memberId == object.getLibraryMemberId())
					&& object.getLibraryBookReturnDate() == null) {
				return true;
			}
		}

		return false;
	}

	private boolean doesMemberIdExist(ArrayList<StudentLibraryMemberBean> libraryMemberInfo, int memberId) {
		for (StudentLibraryMemberBean object : libraryMemberInfo) {
			if (memberId == object.getLibraryMemberId())
				return true;
		}
		return false;
	}

	private void addBook(ArrayList<StudentLibraryBookBean> libraryBookInfo) throws IOException {
		System.out.println("how many books do you want to enter");
		int noOfBooks = new InputClass().getNaturalNumberInput();
		while (noOfBooks != 0) {
			System.out.println("Enter name of book");
			String bookName = new InputClass().getStringInput();
			System.out.println("Enter author of book");
			String bookAuthor = new InputClass().getStringInput();
			System.out.println("enter book quantity");
			int bookQuantity = new InputClass().getNaturalNumberInput();
			if (new StudentLibrary().bookAlreadyExists(libraryBookInfo, bookName, bookAuthor)) {
				int bookId = new StudentLibrary().fetchBookId(libraryBookInfo, bookName, bookAuthor);
				System.out.println("book already exists so updating quantity instead");
				new StudentLibrary().increamentBookQuantity(libraryBookInfo, bookId, bookQuantity);
			} else {
				libraryBookInfo.add(
						new StudentLibraryBookBean(libraryBookInfo.size() + 1, bookName, bookAuthor, bookQuantity));
			}
			noOfBooks--;
		}

	}

	private void increamentBookQuantity(ArrayList<StudentLibraryBookBean> libraryBookInfo, int bookId,
			int bookQuantity) {

		for (StudentLibraryBookBean object : libraryBookInfo) {
			if (bookId == object.getBookId()) {
				object.setBookQuantity(object.getBookQuantity() + bookQuantity);
				break;
			}
		}

	}

	private void updateBookQuantity(ArrayList<StudentLibraryBookBean> libraryBookInfo, int bookId,
			String updateStatus) {

		for (StudentLibraryBookBean object : libraryBookInfo) {
			if (bookId == object.getBookId()) {
				if ("decrement".equalsIgnoreCase(updateStatus)) {
					object.setBookQuantity(object.getBookQuantity() - 1);
				} else {
					object.setBookQuantity(object.getBookQuantity() + 1);
				}
				break;
			}
		}

	}

	private int fetchBookId(ArrayList<StudentLibraryBookBean> libraryBookInfo, String bookName, String bookAuthor) {
		int bookId = 0;
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if ((bookAuthor.equals(object.getBookAuthor())) && (bookName.equals(object.getBookName()))) {
				bookId = object.getBookId();
				break;
			}
		}
		return bookId;
	}

	private boolean bookAlreadyExists(ArrayList<StudentLibraryBookBean> libraryBookInfo, String bookName,
			String bookAuthor) {
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if ((bookAuthor.equals(object.getBookAuthor())) && (bookName.equals(object.getBookName()))) {
				return true;
			}
		}

		return false;
	}

	private boolean bookAlreadyExists(ArrayList<StudentLibraryBookBean> libraryBookInfo, int bookId) {
		for (StudentLibraryBookBean object : libraryBookInfo) {
			if (bookId == object.getBookId()) {
				return true;
			}
		}

		return false;
	}

	private void addNewMember(ArrayList<StudentLibraryMemberBean> libraryMemberInfo) throws IOException {

		System.out.println("how many members do you want to enter:");
		int noOfMembers = new InputClass().getNaturalNumberInput();
		while (noOfMembers != 0) {
			System.out.println("enter your name:");
			String nameOfMember = new InputClass().getStringInput();
			libraryMemberInfo.add(new StudentLibraryMemberBean(libraryMemberInfo.size() + 1, nameOfMember));
			noOfMembers--;
		}
	}

}
