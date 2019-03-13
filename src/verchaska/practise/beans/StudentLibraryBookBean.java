package verchaska.practise.beans;

public class StudentLibraryBookBean {
	private int bookId;
	private String bookName;

	public StudentLibraryBookBean(int bookId, String bookName, String bookAuthor, int bookQuantity) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookQuantity = bookQuantity;
	}

	public StudentLibraryBookBean() {
		super();
	}

	@Override
	public String toString() {
		return "book id =" + bookId + ", book name =" + bookName + ", book author =" + bookAuthor
				+ ", book quantity =" + bookQuantity;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	private String bookAuthor;
	private int bookQuantity;

}
