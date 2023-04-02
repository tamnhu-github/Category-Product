import java.sql.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DoMenuQuery();
	}
	static void MenuQuery() {
		System.out.printf("%-6s%s\n", "[1]", "In danh muc");
		System.out.printf("%-6s%s\n", "[2]", "In danh sach san pham");
		System.out.printf("%-6s%s\n", "[3]", "In danh sach san pham theo danh muc");
		System.out.printf("%-6s%s\n", "[4]", "Them moi danh muc");
		System.out.printf("%-6s%s\n", "[5]", "Chinh sua danh muc");
		System.out.printf("%-6s%s\n", "[6]", "Xoa danh muc");
		System.out.printf("%-6s%s\n", "[7]", "Them moi san pham");
		System.out.printf("%-6s%s\n", "[8]", "Chinh sua san pham");
		System.out.printf("%-6s%s\n", "[9]", "Xoa san pham");
		System.out.printf("%-6s%s\n", "[10]", "Thong ke danh muc");
	}
	static void DoMenuQuery() throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		do {
			MenuQuery();
			System.out.print("Nhap lua chon: ");
			int luachon = input.nextInt();
			switch (luachon) {
			case 1: 
				List<Category> ls = Category.getList();
				for(Category item : ls) {
					System.out.println(item.toString());
				}
				System.out.println();
				break;
			case 2:
				List<Product> lsProd = Product.getAllList();
				for(Product item: lsProd) {
					System.out.println(item.toString());
				}
				System.out.println();
				break;
			case 3: 
				System.out.print("Nhap id cua danh muc: ");
				int i3 = input.nextInt();
				List<Product> list = Product.getList(i3);
				for (Product item: list) {
					System.out.println(item.toString());
				}
				System.out.println();
				break;
			case 4:
				System.out.print("Nhap ten danh muc can tao: ");
				String s4 = input.nextLine();
				try {
					Category.insertCategory(s4);
				} catch (Exception e) {
					System.out.println("Danh muc da ton tai trong danh sach!!");
				}
				break;
			case 5:
				System.out.print("Nhap id cua danh muc: ");
				int i5 = input.nextInt();
				System.out.println("Nhap ten moi cua danh muc: ");
				String s5 = input.nextLine();
				int result = Category.updateCategory(i5, s5);
				if(result == 0) System.out.println("Danh muc khong ton tai!!");
				else System.out.println("Cap nhat thanh cong!");
				break;
			case 6:
				System.out.print("Nhap id cua danh muc can xoa: ");
				int i6 = input.nextInt();
				int result6 = Category.deleteCategory(i6);
				if(result6==0) System.out.println("Xoa khong thanh cong do danh muc khong ton tai!");
				else if(result6==-1) System.out.println("Khong the xoa do danh muc van con san pham!");
				else System.out.println("Xoa danh muc thanh cong!");
				break;
			case 7:
				System.out.print("Nhap id cua san pham: ");
				int i7 = input.nextInt();
				System.out.println("Nhap ten cua san pham: ");
				String s7 = input.nextLine();
				System.out.println("Nhap gia cua san pham: ");
				int s7p = input.nextInt();
				System.out.println("Nhap so luong cua san pham: ");
				int s7q = input.nextInt();
				System.out.println("Nhap id danh muc cua san pham: ");
				int s7i = input.nextInt();
				try {
					int rs7 = Product.insertProduct(i7, s7, s7p, s7q, s7i);
					if(rs7==0) System.out.println("Da ton tai san pham!");
					else if(rs7 == -1) System.out.println("Danh muc khong ton tai!");
					else System.out.println("Them moi san pham thanh cong!");
				} catch (Exception e) {
					System.out.println("ID san pham bi trung!!!");
				}
				break;
			case 8: 
				System.out.print("Nhap id cua san pham: ");
				int i8 = input.nextInt();
				System.out.println("Nhap ten moi cua san pham: ");
				String s8 = input.nextLine();
				System.out.println("Nhap gia moi cua san pham: ");
				int s8p = input.nextInt();
				System.out.println("Nhap so luong moi cua san pham: ");
				int s8q = input.nextInt();
				System.out.println("Nhap id danh muc cua san pham: ");
				int s8i = input.nextInt();
				int rs8 = Product.updateProduct(i8, s8, s8p, s8q, s8i);
				if(rs8==0) System.out.println("Danh muc khong ton tai!");
				else if(rs8==-1) System.out.println("ID san pham khong ton tai hoac ten san pham da ton tai!");
				else System.out.println("Cap nhat san pham thanh cong!");
				break;
			case 9:
				System.out.print("Nhap id san pham can xoa: ");
				int id9 = input.nextInt();
				int rs9 = Product.deleteProduct(id9);
				if(rs9 == 0) System.out.println("ID san pham khong ton tai!!");
				else System.out.println("Xoa san pham thanh cong!");
				break;
			case 10:
				List<CategoryThongKe> lstk = CategoryThongKe.ThongkeDanhmuc();
				for(CategoryThongKe item: lstk) {
					System.out.println(item.toString());
				}
				System.out.println();
				break;
			default:
				flag = false;
				break;
			}
		}while(flag);
		
	}
}
