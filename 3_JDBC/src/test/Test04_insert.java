package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.JDBCUtil;

public class Test04_insert {
	public static void main(String[] args) {

		String sql = "insert into book(bookno, title, price)\r\n"
				+ "values((select nvl(max(bookno),0)+1 from book),?,?)";

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int row = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setString(1, "jquery");
			ps.setInt(2, 22000);
			// 실행
			row = ps.executeUpdate();
			// 결과값핸들링
			System.out.println("입력행 row : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
	}
}