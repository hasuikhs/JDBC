package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.JDBCUtil;
import vo.BookVO;

public class Test09_select_map {
	public static void main(String[] args) {

		String sql = "select * from book";

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookVO> bookVOs = new ArrayList<BookVO>();
		Map<Integer, BookVO> map = new HashMap<Integer, BookVO>();
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅

			// 실행
			rs = ps.executeQuery();
			// 결과값핸들링
			while (rs.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setPrice(rs.getInt("price"));
				bookVO.setAuthor(rs.getString("author"));
				bookVO.setBookno(rs.getInt("bookno"));
				bookVO.setTitle(rs.getString("title"));
				bookVO.setPubdate(rs.getDate("pubdate").toString());// book.setPubdate(rs.getString("pubdate"));
				bookVOs.add(bookVO);
				map.put(bookVO.getBookno(), bookVO);
				//System.out.println(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, st, rs);
		}
		
		System.out.println("Book List");
		for (BookVO d :bookVOs) {
			System.out.println(d); // 자원 반납 후에도 자바에 객체가 남아있음을 확인
		}
		System.out.println("-------------------------------------");
		map.forEach((k,v) ->System.out.println(v));
	}
}
