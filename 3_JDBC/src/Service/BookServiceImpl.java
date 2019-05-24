package Service;

import java.util.List;

import DAO.BookDAO;
import vo.BookVO;

public class BookServiceImpl implements BookService{
	
	private BookDAO dao = null;
	
	public BookServiceImpl() {
		super();
	}

	public BookServiceImpl(BookDAO dao) {
		super();
		this.dao = dao;
	}

	public BookDAO getDao() {
		return dao;
	}

	public void setDao(BookDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<BookVO> bookList() {
		return dao.bookList();
	}

	@Override
	public int addBook(BookVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.addBook(vo);
	}
}