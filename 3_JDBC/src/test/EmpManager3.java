package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.JDBCUtil;

public class EmpManager3 {
	public void printEmp(String[] jobs) {
		String sql="select e.employee_id as 사번, e.first_name as 이름, e.salary as 월급" + 
				" from employees e join jobs j" + 
				" on e.job_id = j.job_id" + 
				" where 	lower(j.job_title) = ? or lower(j.job_title) = ?"; // 입력받는것은 preparedstatement
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		printEmployee(jobs);
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			//? 세팅
			ps.setString(1, jobs[0].toLowerCase());
			ps.setString(2, jobs[1].toLowerCase());
			//실행
			rs = ps.executeQuery();
			// 결과값핸들링
			while(rs.next()) {
				System.out.print(rs.getString("사번")+ "  ");
				System.out.print(rs.getString("이름")+ "  ");
				System.out.print(rs.getString("월급")+"  ");
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
	static void printEmployee(String jobs[]) {
		
	}
}
