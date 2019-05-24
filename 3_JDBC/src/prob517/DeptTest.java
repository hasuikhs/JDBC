package prob517;

public class DeptTest {
	public static void main(String[] args) {
		DeptManager dmr = new DeptManager();
				
		dmr.getDepts().forEach(i -> System.out.println(i));

	}
}
