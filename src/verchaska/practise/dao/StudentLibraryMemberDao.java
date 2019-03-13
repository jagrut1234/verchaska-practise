package verchaska.practise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verchaska.practise.ConnectionClass;
import verchaska.practise.beans.StudentLibraryMemberBean;
import verchaska.practise.queries.StudentLibraryJdbcQueries;

public class StudentLibraryMemberDao {

	public int getTotalNumberOfMembers() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int totalNumberOfEntries = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.COUNT_OF_LIBRARY_MEMBERS);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalNumberOfEntries = rs.getInt("member_count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return totalNumberOfEntries;
	}

	public void addNewMembers(ArrayList<StudentLibraryMemberBean> libraryMemberInfo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int columnNo = 0;

		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.ADD_LIBRARY_MEMBERS);
			for (StudentLibraryMemberBean object : libraryMemberInfo) {
				columnNo = 0;
				ps.setString(++columnNo, object.getLibraryMemberName());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}

	public boolean doesMemberIdExist(int memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int columnNo = 0;
		int countOfMemberId = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.CHECK_MEMBERSHIP_OF_LIBRARY);
			ps.setInt(++columnNo, memberId);
			rs = ps.executeQuery();
			if (rs.next()) {
				countOfMemberId = rs.getInt("member_count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return countOfMemberId > 0;
	}

	public ArrayList<StudentLibraryMemberBean> getMemberDataFromDatabase() throws SQLException {
		ArrayList<StudentLibraryMemberBean> memberData = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String studentName = null;
		int studentId = 0;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement(StudentLibraryJdbcQueries.GET_MEMBERS_DATA);
			rs = ps.executeQuery();
			while (rs.next()) {
				studentId = rs.getInt("spid");
				studentName = rs.getString("sname");
				memberData.add(new StudentLibraryMemberBean(studentId, studentName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

		return memberData;
	}

}
