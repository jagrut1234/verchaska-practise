package verchaska.practise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testing {

	public static void main(String[] args) throws IOException, ParseException, SQLException {

		System.out.println("enter input1");
		Date input1 = new DateInputClass().getDateInput("MM-dd-yyyy");
		System.out.println(input1);
		System.out.println(new SimpleDateFormat("dd-MM-yyyy").format(input1));

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = new ConnectionClass().getConnection();
			ps = con.prepareStatement("insert into dummy values(?)");
			ps.setString(1, "jd");
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

	}

}
