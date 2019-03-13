package verchaska.practise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verchaska.practise.ConnectionClass;
import verchaska.practise.beans.StudentLibraryJdbcIssueDetailBean;
import verchaska.practise.queries.StudentLibraryJdbcQueries;

public class StudentLibraryIssueDetailDao {

	public boolean isBookAlreadyIssued(int bookId, int memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int countOfEntries = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CHECK_IF_BOOK_IS_ISSUED);
			ps.setInt(++columnNo, memberId);
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

	public boolean threeBooksIssuedAlready(int memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int countOfEntries = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CHECK_IF_THREE_BOOKS_ISSUED);
			ps.setInt(++columnNo, memberId);
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

		return countOfEntries >= 3;
	}

	public void createEntryOfIssue(int memberId, int bookId, String bookIssueDate) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CREATE_ENTRY_OF_ISSUE);
			ps.setInt(++columnNo, memberId);
			ps.setInt(++columnNo, bookId);
			ps.setString(++columnNo, bookIssueDate);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

	}

	public void setReturnDateOfBook(String returnDate, int memberId, int bookId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CREATE_ENTRY_OF_RETURN);
			ps.setString(++columnNo, returnDate);
			ps.setInt(++columnNo, memberId);
			ps.setInt(++columnNo, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

	}

	public ArrayList<StudentLibraryJdbcIssueDetailBean> getTransactionDetail() throws SQLException {
		ArrayList<StudentLibraryJdbcIssueDetailBean> transactionDetail = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int studentId = 0;
		int bookId = 0;
		String issueDate = null;
		String returnDate = null;

		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.GET_TRANSACTION_DETAILS);
			rs = ps.executeQuery();
			while (rs.next()) {
				studentId = rs.getInt("sxid");
				bookId = rs.getInt("bxid");
				issueDate = rs.getString("issuedate");
				returnDate = rs.getString("returndate");
				transactionDetail.add(new StudentLibraryJdbcIssueDetailBean(studentId, bookId, issueDate, returnDate));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

		return transactionDetail;
	}

}
