package verchaska.practise.beans;

public class StudentLibraryJdbcIssueDetailBean {
	private int libraryMemberId;
	private int libraryBookId;
	private String libraryBookIssueDate;
	private String libraryBookReturnDate;

	public StudentLibraryJdbcIssueDetailBean() {
		super();
	}

	public StudentLibraryJdbcIssueDetailBean(int libraryMemberId, int libraryBookId, String libraryBookIssueDate,
			String libraryBookReturnDate) {
		super();
		this.libraryMemberId = libraryMemberId;
		this.libraryBookId = libraryBookId;
		this.libraryBookIssueDate = libraryBookIssueDate;
		this.libraryBookReturnDate = libraryBookReturnDate;
	}

	@Override
	public String toString() {
		return "library member ID =" + libraryMemberId + ", library book ID =" + libraryBookId
				+ ", library book issue date =" + libraryBookIssueDate + ", library book return date ="
				+ libraryBookReturnDate;
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

	public String getLibraryBookIssueDate() {
		return libraryBookIssueDate;
	}

	public void setLibraryBookIssueDate(String libraryBookIssueDate) {
		this.libraryBookIssueDate = libraryBookIssueDate;
	}

	public String getLibraryBookReturnDate() {
		return libraryBookReturnDate;
	}

	public void setLibraryBookReturnDate(String libraryBookReturnDate) {
		this.libraryBookReturnDate = libraryBookReturnDate;
	}
}
