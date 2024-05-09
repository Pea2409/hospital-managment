package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.bacSibean;

public class bacSidao {
	public ArrayList<bacSibean> getBS() throws Exception {
		ArrayList<bacSibean>  ds = new ArrayList<bacSibean>();
		// b1: Kết nối vào csdl : QlBenhNhan
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "select * from BACSI";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		
		// b6: Duyệt rs
		while (rs.next()) {
			String maBacSi = rs.getString("MaBS");
			String tenBacSi = rs.getString("TenBS");
			String nganh = rs.getString("Nganh");
			String anhBacSi = rs.getString("Anh");
			ds.add(new bacSibean(maBacSi, tenBacSi, nganh, anhBacSi));
		}
		
		// b7: Đóng các đối tượng
		rs.close();
		kn.cn.close();
		
		return ds;
	}
	
	public bacSibean getInfoBS(String mabs) throws Exception {
	    // Bước 1: Kết nối vào cơ sở dữ liệu: QlSach
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // Bước 2: Tạo câu lệnh SQL
	    String sql = "SELECT * FROM BACSI WHERE MaBS = ?";

	    // Bước 3: Tạo câu lệnh
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    // Bước 4: Truyền tham số vào câu lệnh nếu có
	    ps.setString(1, mabs);

	    // Bước 5: Thực hiện câu lệnh
	    ResultSet rs = ps.executeQuery();

	    // Bước 6: Xử lý kết quả
	    if (rs.next()) {
	        // Nếu có dữ liệu, tạo một đối tượng bacSibean và set giá trị
	        bacSibean bs = new bacSibean();
	        bs.setMaBacSi(rs.getString("MaBS"));
	        bs.setTenBacSi(rs.getString("TenBS"));
	        bs.setNganh(rs.getString("Nganh"));
	        bs.setAnhBacSi(rs.getString("Anh"));

	        // Đóng kết nối và trả về đối tượng bacSibean
	        kn.cn.close();
	        return bs;
	    } else {
	        // Nếu không có dữ liệu, đóng kết nối và trả về null
	        kn.cn.close();
	        return null;
	    }
	}

	
	public int soLuongBacSi() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BACSI;";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}

	public void addBacSi(String mabs, String tenbs, String nganh,String anh) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "insert into BACSI(MaBS, TenBS, Nganh, Anh) VALUES (?, ?, ?, ?) ";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, mabs);
	    ps.setString(2, tenbs);
	    ps.setString(3, nganh);
	    ps.setString(4, anh);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
	
	public Boolean checkMaBS(String mabs) throws Exception {
		// b1: Kết nối vào csdl : QlSach
		KetNoi kn =new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql= "SELECT * FROM BACSI where MaBS = ?";
		
		// b3: Tạo câu lệnh
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		ps.setString(1, mabs);
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	public void XoaBS(String maBs) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete FROM BACSI where MaBS = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1,maBs);
		ps.executeUpdate();
		kn.cn.close();
	}
	
	public void SuaBS(String maBs, String tenBs, String Nganh, String anh) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE BACSI SET TenBS = ?, Nganh = ?, Anh = ? WHERE MaBS = ?;";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, tenBs);
	    ps.setString(2, Nganh);
	    ps.setString(3, anh);
	    ps.setString(4, maBs);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
}
