import java.sql.*;
import java.util.*;
public class Category {
	private int ID;
	private String Name;
	public Category(int iD, String name) {
		super();
		setID(iD);
		Name = name;
	}
	
	//lay danh sach Category va sap xep theo thu tu Alphabet
	public static List<Category> getList() throws SQLException, ClassNotFoundException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		String sql = "SELECT * FROM Category ORDER BY Name ASC";
		ResultSet result = state.executeQuery(sql);	
		List<Category> list = new ArrayList<>();
		while(result.next()) {
			int ID = result.getInt("ID");
			String Name = result.getString("Name");
			list.add(new Category(ID,Name));
		}
		conn.close();
		return list;
	}
	public static int insertCategory(String newct) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		String sql = "insert into Category (Name)\r\n"
				+ "values ('"+newct+"')";
		int result = state.executeUpdate(sql);
		conn.close();
		return result;
	}
	public static int updateCategory(int id, String newName) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		List<Category> list = Category.getList();
		for(Category item: list) {
			if(item.getID() == id) {
				String sql = "update Category\r\n"
						+ "set Name = '"+newName+"'\r\n"
						+ "where ID = "+id+"";
				int result = state.executeUpdate(sql);
				return result;
			}
		}
		conn.close();
		return 0; //khong co dong nao thay doi
	}
	public static int deleteCategory(int id) throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		List<Category> list = Category.getList();
		int cntProduct = 0;
		for(Category item: list) {
			if(item.getID() == id) {
				String sql1 = "select COUNT(Product.IDCategory) as total\r\n"
						+ "from Product\r\n"
						+ "where Product.IDCategory = "+id+"";
				ResultSet cntresult = state.executeQuery(sql1);
				while(cntresult.next()) cntProduct += cntresult.getInt("total");
				if(cntProduct == 0) {
					String sql2 = "delete from Category\r\n"
							+ "where id = "+id+"";
					int result = state.executeUpdate(sql2);
					return result;
				}
				else return -1;
			}
		}
		conn.close();
		return 0;
	}
	
	@Override
	public String toString() {
		return "Category [ID=" + getID() + ", Name=" + Name + "]";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
	
}
