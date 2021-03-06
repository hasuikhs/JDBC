package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.JDBCUtil;

public class EmpManager2 {
	public static void main(String[] args) {
		String title1 = JOptionPane.showInputDialog("첫번째 업무명을 입력하세요");
		String title2 = JOptionPane.showInputDialog("두번재 업무명을 입력하세요");
		
		String[] jobs = {title1, title2};
		
		printEmployee(jobs); 
		
	}
	static void printEmployee(String[] jobs) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql="select e.employee_id as 사번, e.first_name as 이름, e.salary as 월급" + 
				" from employees e join jobs j" + 
				" on e.job_id = j.job_id" + 
				" where lower(j.job_title) = ? or lower(j.job_title) = ?";
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); 
			for (int i = 1; i <= jobs.length; i++) { 
				ps.setString((i), jobs[i-1].toLowerCase());
			}
			//실행
			rs = ps.executeQuery();
			// 결과값핸들링
			while(rs.next()) {
				System.out.print(rs.getString(1)+ "  "); 
				System.out.print(rs.getString(2)+ "  "); 
				System.out.print(rs.getString(3)+"  ");
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