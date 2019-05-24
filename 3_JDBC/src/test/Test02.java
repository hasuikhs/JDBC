package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.JDBCUtil;

public class Test02 {
	public static void main(String[] args) {
		//emp table에서 부서별 평균 급여와 인원수를 구해서 출력
		System.out.println("JDBC Test");
		String sql = "select deptno, round(avg(sal),2) 평균, count(*) 인원수 "
				+ " from emp group by deptno";
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection(); // 1.2. 드라이버 연결, db 연결
			st = con.createStatement(); // 3. sql 관리 객체 statement 생성
			rs =st.executeQuery(sql); // 4. sql 구문 실행
			
			while (rs.next()) { // 5. 결과값 처리
				System.out.print(rs.getString("deptno") + "    ");
				System.out.print(rs.getString("평균") + "    ");
				System.out.print(rs.getString("인원수") + "    ");
				//System.out.print(rs.getString(4) + "    ");
				System.out.println();
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}
		System.out.println("** end **");
	}
}
