package verchaska.practise.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentLibraryIssueDetailBean {
	private int libraryMemberId;
	private int libraryBookId;
	private Date libraryBookIssueDate;
	private Date libraryBookReturnDate;
	public StudentLibraryIssueDetailBean() {
		super();
	
	}
	public StudentLibraryIssueDetailBean(int libraryMemberId, int libraryBookId, Date libraryBookIssueDate,
			Date libraryBookReturnDate) {
		super();
		this.libraryMemberId = libraryMemberId;
		this.libraryBookId = libraryBookId;
		this.libraryBookIssueDate = libraryBookIssueDate;
		this.libraryBookReturnDate = libraryBookReturnDate;
	}
	@Override
	public String toString() {
		return "library member ID =" + libraryMemberId + ", library book ID =" + libraryBookId
				+ ", library book issue date =" + new SimpleDateFormat("dd/MM/yyyy").format(libraryBookIssueDate) + ", library book return date =" + libraryBookReturnDate;
	}
	public int getLibraryMemberId() {
		return libraryMemberId;
	}
	public void setLibraryMemberId(int libraryMemberId) {
		this.libraryMemberId = libraryMemberId;
	}
	public int getLibraryBookId() {
		return libraryBookId;
	}
	public void setLibraryBookId(int libraryBookId) {
		this.libraryBookId = libraryBookId;
	}
	public Date getLibraryBookIssueDate() {
		return libraryBookIssueDate;
	}
	public void setLibraryBookIssueDate(Date libraryBookIssueDate) {
		this.libraryBookIssueDate = libraryBookIssueDate;
	}
	public Date getLibraryBookReturnDate() {
		return libraryBookReturnDate;
	}
	public void setLibraryBookReturnDate(Date libraryBookReturnDate) {
		this.libraryBookReturnDate = libraryBookReturnDate;
	}

	
}
