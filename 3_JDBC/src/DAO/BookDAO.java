package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import vo.BookVO;

public class BookDAO {
	public List<BookVO> bookList() { // DB상의 Book객체들을 호출하여 booklist에 집어넣어 배열로서 호출
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book"; // 북 테이블에 있는 모든 정보를 호출

		Connection con = null; // DB연결도니 상태(세션)을 담은 객체
		PreparedStatement ps = null; // sql문을 나타내는 객체
		ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookVO bookVO = new BookVO(); // 새로운 책 객체 생성
				bookVO.setTitle(rs.getString("title")); // 만들어진 책 객체에 쿼리문에 대한 반환값중 title을 담음
				bookVO.setPrice(rs.getInt("price")); // 마찬가지로 price를 담음
				// 필요시 추가 로드 작업
				list.add(bookVO); // 만들어진 북 객체를 list에 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return list;
	}

	public int addBook(BookVO vo) throws Exception{ // DB에 Book객체 하나의 정보들을 삽입하는 메소드 

		String sql = "insert into book(bookno, title, author, price)\r\n"
				+ "values((select nvl(max(bookno),0)+1 from book),?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		con = JDBCUtil.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getAuthor());
		ps.setInt(3, vo.getPrice());
		
		row = ps.executeUpdate();
		/*
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setInt(3, vo.getPrice());
			
			row = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		*/
		return row;
	}
}
