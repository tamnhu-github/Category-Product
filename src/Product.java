import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Product {
	private int ID;
	private String Name;
	private int Price;
	private int quantity;
	private int IDCategory;
	public Product(int iD, String name, int price, int quantity, int iDCategory) {
		ID = iD;
		Name = name;
		Price = price;
		this.quantity = quantity;
		IDCategory = iDCategory;
	}
	public static List<Product> getAllList() throws SQLException, ClassNotFoundException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		String sql = "select * from Product";
		ResultSet result = state.executeQuery(sql);	
		List<Product> list = new ArrayList<>();
		while(result.next()) {
			int ID = result.getInt("ID");
			String Name = result.getString("Name");
			int Price = result.getInt("Price");
			int Quantity = result.getInt("Quantity");
			int IDCategory = result.getInt("IDCategory");
			list.add(new Product(ID,Name, Price,Quantity,IDCategory));
		}
		conn.close();
		return list;
	}
	public static List<Product> getList(int idct) throws SQLException, ClassNotFoundException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		String sql = "select p.ID, p.Name, p.Price, p.Quantity, p.IDCategory\r\n"
				+ "from Product as p INNER JOIN Category as ct ON p.IDCategory = ct.ID\r\n"
				+ "where ct.ID = "+idct+"\r\n"
				+ "order by p.Price desc";
		ResultSet result = state.executeQuery(sql);	
		List<Product> list = new ArrayList<>();
		while(result.next()) {
			int ID = result.getInt("ID");
			String Name = result.getString("Name");
			int Price = result.getInt("Price");
			int Quantity = result.getInt("Quantity");
			int IDCategory = result.getInt("IDCategory");
			list.add(new Product(ID,Name, Price,Quantity,IDCategory));
		}
		conn.close();
		return list;
	}
	public static int insertProduct(int nid,String nname, int nprice, int nquantity, int idcategory) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		List<Category> listCt = Category.getList();
		//kiem tra danh muc cua san pham insert co ton tai chua
		for(Category item: listCt) {
			if(item.getID() == idcategory) {
				List<Product> listProd = Product.getAllList();
				for(Product itemProd: listProd) {
					if(!itemProd.Name.equals(nname)) {
						String sql = "insert into Product\r\n"
								+ "values ('"+nid+"','"+nname+"','"+nprice+"','"+nquantity+"','"+idcategory+"')";
						int rs = state.executeUpdate(sql);
						return rs;
					}
					else return 0; //trung ten nen khong add new 
				}
			}
		}
		conn.close();
		return -1; //category khong ton tai
	}
	
	public static int updateProduct(int uid, String uName, int uprice, int uquantity, int idcategory) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		List<Category> listCt = Category.getList();
		for(Category itemCt: listCt) {
			if(itemCt.getID()== idcategory) {
				List<Product> listProd = Product.getAllList();
				for(Product itemProd: listProd) {
					if((itemProd.ID==uid)) {
						String sql = "update Product\r\n"
								+ "set Name = '"+uName+"', Price = "+uprice+", Quantity = "+uquantity+", IDCategory = "+idcategory+"\r\n"
								+ "where ID = "+uid;
						int rs = state.executeUpdate(sql);
						return rs;
					}
				}
				return -1;  //id sp ko ton tai
			}
		}
		conn.close();
		return 0; //danh muc cua san pham ko ton tai
	}
	
	//delete product
	public static int deleteProduct(int delId) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		List<Product> list = Product.getAllList();
		for(Product item: list) {
			if(item.ID==delId) {
				String sql = "delete from Product\r\n"
						+ "where ID = "+delId;
				int rs = state.executeUpdate(sql);
				return rs;
			}
		}
		conn.close();
		return 0; //id san pham khong ton tai
	}

	@Override
	public String toString() {
		return "Product [ID=" + ID + ", Name=" + Name + ", Price=" + Price + ", quantity=" + quantity + ", IDCategory="
				+ IDCategory + "]";
	}
	
	
}
