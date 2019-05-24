package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.JDBCUtil;

public class EmpTest {
	public static void main(String[] args) {

		String sql = "select to_char(count(*)) as \"직속부하직원수\"," + 
				" to_char(e.manager_id) as \"관리자사번\"," + 
				" m.last_name as \"관리자이름\"" + 
				" from employees e join employees m" + 
				" on e.manager_id = m.employee_id" + 
				" group by e.manager_id, m.last_name" + 
				" having count(*) >=8" + 
				" order by count(*)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅

			// 실행
			rs = ps.executeQuery();
			// 결과값핸들링
			while (rs.next()) {
				System.out.print(rs.getString("직속부하직원수") + "         ");
				System.out.print(rs.getString("관리자사번") + "          ");
				System.out.print(rs.getString("관리자이름") + "           ");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

	}
}