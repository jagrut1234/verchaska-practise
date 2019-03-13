package verchaska.practise.queries;

public class StudentLibraryJdbcQueries {
	public static final String COUNT_OF_LIBRARY_MEMBERS = "select count(*) as member_count from student_info";
	public static final String ADD_LIBRARY_MEMBERS = "insert into student_info(sname) values(?)";
	public static final String GET_BOOK_ID = "select bpid as book_id from book_info where bname=? and bauthor=?";
	public static final String INCREAMENT_BOOK_QUANTITY = "update book_info set bquantity=bquantity+? where bpid=?";
	public static final String COUNT_OF_LIBRARY_BOOKS = "select count(*) as book_count from book_info";
	public static final String ADD_LIBRARY_BOOKS = "insert into book_info(bname,bauthor,bquantity) values(?,?,?)";
	public static final String CHECK_MEMBERSHIP_OF_LIBRARY = "select count(spid) as member_count from student_info where spid=?";
	public static final String CHECK_IF_BOOK_IS_ISSUED = "select count(*) as count_of_entries from library_transaction_info where sxid=? and bxid=? and returndate is null";
	public static final String CHECK_IF_THREE_BOOKS_ISSUED = "select count(*) as  count_of_entries from library_transaction_info where sxid=? and returndate is null";
	public static final String CHECK_QUANTITY_OF_BOOK = "select bquantity as book_quantity from book_info where bpid=?";
	public static final String CREATE_ENTRY_OF_ISSUE = "insert into library_transaction_info(sxid,bxid,issuedate) values(?,?,?)";
	public static final String UPDATE_BOOK_QUANTITY = "update book_info set bquantity=bquantity+? where bpid=?";
	public static final String CHECK_IF_BOOK_EXISTS_IN_INVENTORY = "select count(bpid) as count_of_entries from book_info where bpid=?";
	public static final String CREATE_ENTRY_OF_RETURN = "update library_transaction_info set returndate=? where sxid=? and bxid=?";
	public static final String GET_MEMBERS_DATA = "select spid,sname from student_info";
	public static final String GET_LIBRARY_BOOK_DATA ="select bpid,bname,bauthor,bquantity from book_info";
	public static final String GET_TRANSACTION_DETAILS ="select sxid,bxid,issuedate,returndate from library_transaction_info";

}
