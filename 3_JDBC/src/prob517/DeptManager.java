package prob517;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptManager {
	public List<DeptVO> getDepts() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DeptVO> deptVOs = new ArrayList<DeptVO>();

		String sql = "select * from dept";

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				DeptVO deptVO = new DeptVO();
				deptVO.setDeptno(rs.getInt("deptno"));
				deptVO.setDname(rs.getString("dname"));
				deptVO.setLoc(rs.getString("loc"));
				deptVOs.add(deptVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		
		return deptVOs;
	}
}
