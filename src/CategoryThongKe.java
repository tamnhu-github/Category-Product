import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryThongKe {
	private int id;
	private String name;
	private int total;
	public CategoryThongKe(int id, String name, int total) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
	}
	public static List<CategoryThongKe> ThongkeDanhmuc() throws ClassNotFoundException, SQLException {
		Connection conn = BaseConnection.getConnection();
		Statement state = conn.createStatement();
		String sql = "select a.ID, a.Name, count(p.IDCategory) as Total\r\n"
				+ "from Category as a left join Product as p on a.ID = p.IDCategory\r\n"
				+ "group by a.ID, a.Name";
		ResultSet rs = state.executeQuery(sql);
		List<CategoryThongKe> list = new ArrayList<>();
		while(rs.next()) {
			int id1 = rs.getInt("ID");
			String name = rs.getString("Name");
			int total = rs.getInt("total");
			list.add(new CategoryThongKe(id1, name, total));
		}
		conn.close();
		return list;
	}
	@Override
	public String toString() {
		return "CategoryThongKe [ID=" + id + ", Name=" + name + ", totalProduct=" + total + "]";
	}
	
}
