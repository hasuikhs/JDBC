package App;

import java.util.List;

import DAO.BookDAO;
import Service.BookService;
import Service.BookServiceImpl;
import vo.BookVO;

public class BookApp { // bookapp class의 역할
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAO(); // 직접 dao를 사용하면 강력한 커플링이지만
		BookService service = new BookServiceImpl(dao); // service를 통해 우회해서 약한 커플링으로 호출한다
		
		BookVO user = new BookVO("spring", "kim", 7712100); // BookVO에 선언된 3개 매개변수를 받는 생성자를 호출하여 생성
		try {
			service.addBook(user); // user에 저장된 Book 객체를 list에 넣는다
		} catch (Exception e) {
			System.out.println("등록 데이터 확인 필요"); // 위 객체를 넣다가 중복이나 오류가 날때 예외처리를 한다
		}
		
		if (service != null) { // service가 null이 아닐시
			List<BookVO> list = service.bookList();  // 북리스트를 부르고
			list.forEach(i -> System.out.println(i)); // 북리스트에 저장된 정보들을 보여준다
		}
		
		
	}
}
