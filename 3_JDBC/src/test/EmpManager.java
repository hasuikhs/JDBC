package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.JDBCUtil;

public class EmpManager {
	public static void main(String[] args) {
		String title1 = JOptionPane.showInputDialog("첫번째 업무명을 입력하세요");
		String title2 = JOptionPane.showInputDialog("두번재 업무명을 입력하세요");
		
		String[] jobs = {title1, title2};
		
		String sql="select e.employee_id as 사번, e.first_name as 이름, e.salary as 월급" + 
				" from employees e join jobs j" + 
				" on e.job_id = j.job_id" + 
				" where 	lower(j.job_title) = ? or lower(j.job_title) = ?"; // 입력받는것은 preparedstatement
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int row = 0;
		
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