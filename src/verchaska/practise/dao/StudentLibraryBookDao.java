package verchaska.practise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verchaska.practise.ConnectionClass;
import verchaska.practise.beans.StudentLibraryBookBean;
import verchaska.practise.queries.StudentLibraryJdbcQueries;

public class StudentLibraryBookDao {

	public boolean bookAlreadyExistsInDatabase(String bookName, String bookAuthor) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		String bookId = null;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.GET_BOOK_ID);
			ps.setString(++columnNo, bookName);
			ps.setString(++columnNo, bookAuthor);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookId = rs.getString("book_id");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

		if (bookId != null) {
			return true;
		}
		return false;
	}

	public int fetchBookId(String bookName, String bookAuthor) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int bookId = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.GET_BOOK_ID);
			ps.setString(++columnNo, bookName);
			ps.setString(++columnNo, bookAuthor);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookId = rs.getInt("book_id");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return bookId;

	}

	public void increamentBookQuantity(int bookId, int bookQuantity) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.INCREAMENT_BOOK_QUANTITY);
			ps.setInt(++columnNo, bookQuantity);
			ps.setInt(++columnNo, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}

	public int getTotalNumberOfBooks() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int totalNumberOfBooks = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.COUNT_OF_LIBRARY_BOOKS);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalNumberOfBooks = rs.getInt("book_count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return totalNumberOfBooks;
	}

	public void addNewBooks(ArrayList<StudentLibraryBookBean> libraryBookInfo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;

		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.ADD_LIBRARY_BOOKS);
			for (StudentLibraryBookBean object : libraryBookInfo) {
				columnNo = 0;
				ps.setString(++columnNo, object.getBookName());
				ps.setString(++columnNo, object.getBookAuthor());
				ps.setInt(++columnNo, object.getBookQuantity());
				ps.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

	}

	public boolean isBookInStock(int bookId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int totalNumberOfBooks = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CHECK_QUANTITY_OF_BOOK);
			ps.setInt(++columnNo, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalNumberOfBooks = rs.getInt("book_quantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return totalNumberOfBooks > 0;
	}

	public void updateBookQuantity(int bookId, String updateStatus) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;
		int updateValue = "decrement".equalsIgnoreCase(updateStatus) ? -1 : 1;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.UPDATE_BOOK_QUANTITY);
			ps.setInt(++columnNo, updateValue);
			ps.setInt(++columnNo, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

	}

	public boolean bookAlreadyExists(int bookId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int countOfEntries = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CHECK_IF_BOOK_EXISTS_IN_INVENTORY);
			ps.setInt(++columnNo, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				countOfEntries = rs.getInt("count_of_entries");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return countOfEntries > 0;
	}

	public ArrayList<StudentLibraryBookBean> getBookInfoFromDatabase() throws SQLException {
		ArrayList<StudentLibraryBookBean> libraryBookInfo = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int bookId = 0;
		String bookAuthor = null;
		String bookName = null;
		int bookQuantity = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.GET_LIBRARY_BOOK_DATA);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookId = rs.getInt("bpid");
				bookName = rs.getString("bname");
				bookAuthor = rs.getString("bauthor");
				bookQuantity = rs.getInt("bquantity");
				libraryBookInfo.add(new StudentLibraryBookBean(bookId, bookName, bookAuthor, bookQuantity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

		return libraryBookInfo;
	}

}
