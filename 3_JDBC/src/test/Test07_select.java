package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.JDBCUtil;

public class Test07_select {
	public static void main(String[] args) {

		String sql = "select * from book";

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();

			ps = con.prepareStatement(sql);
			// ? 세팅

			// 실행
			rs = ps.executeQuery();
			// 결과값핸들링
			while (rs.next()) {
				System.out.print(rs.getString("bookno") + "  ");
				System.out.print(rs.getString("title") + "  ");
				System.out.print(rs.getString("price") + "  ");
				System.out.println(rs.getDate("pubdate") + "  ");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, st, rs);
		}

	}
}
