package verchaska.practise.beans;

public class StudentLibraryMemberBean {
	private int libraryMemberId;

	public StudentLibraryMemberBean() {
		super();

	}

	public StudentLibraryMemberBean(int libraryMemberId, String libraryMemberName) {
		super();
		this.libraryMemberId = libraryMemberId;
		this.libraryMemberName = libraryMemberName;
	}

	@Override
	public String toString() {
		return "library member ID =" + libraryMemberId + ", library member name ="
				+ libraryMemberName;
	}

	public int getLibraryMemberId() {
		return libraryMemberId;
	}

	public void setLibraryMemberId(int libraryMemberId) {
		this.libraryMemberId = libraryMemberId;
	}

	public String getLibraryMemberName() {
		return libraryMemberName;
	}

	public void setLibraryMemberName(String libraryMemberName) {
		this.libraryMemberName = libraryMemberName;
	}

	private String libraryMemberName;

}
