package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.donThuocbean;
import bean.nhapVienbean;

public class donThuocdao {
	public ArrayList<donThuocbean> getDT() throws Exception {
		ArrayList<donThuocbean>  ds = new ArrayList<donThuocbean>();
		// b1: Kết nối vào csdl : QlBenhNhan
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "select * from ViewDT";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		
		// b6: Duyệt rs
		while (rs.next()) {
			String maDt = rs.getString("MaDT");
			String maBn = rs.getString("MaBHYT");
			Date ngayKD = rs.getDate("NgayKD");
			String tenThuoc = rs.getString("TenThuoc");
			String lieuDung = rs.getString("LieuDung");
			String cachSD = rs.getString("CachSD");
			String maBs = rs.getString("TenBS");
			ds.add(new donThuocbean(maDt, maBn, ngayKD, maBs, tenThuoc, lieuDung, cachSD));
		}
		
		// b7: Đóng các đối tượng
		rs.close();
		kn.cn.close();
		
		return ds;
	}
	
	public void addDonThuoc(String maBn, String ngayKD, String maBs, String tenThuoc, String lieuDung, String cachSd) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO DONTHUOC (MaBHYT, MaBS, NgayKD, TenThuoc, LieuDung, CachSD) values ( ?, ?, ?, ?, ?, ? )";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, maBs);
	    ps.setString(3, ngayKD);
	    ps.setString(4, tenThuoc);
	    ps.setString(5, lieuDung);
	    ps.setString(6, cachSd);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
	
	public void XoaDonThuoc(String madt) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete FROM DONTHUOC where MaDT = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, madt);
		ps.executeUpdate();
		kn.cn.close();
	}
	
	public donThuocbean getInfoDT(String madt) throws Exception {
	    // Bước 1: Kết nối vào cơ sở dữ liệu: QlBenNhan
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // Bước 2: Tạo câu lệnh SQL
	    String sql = "select * from DONTHUOC WHERE MaDT = ?";

	    // Bước 3: Tạo câu lệnh
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    // Bước 4: Truyền tham số vào câu lệnh nếu có
	    ps.setString(1, madt);

	    // Bước 5: Thực hiện câu lệnh
	    ResultSet rs = ps.executeQuery();

	    // Bước 6: Xử lý kết quả
	    if (rs.next()) {
	    	donThuocbean dt = new donThuocbean();
	    	dt.setMaDT(rs.getString("MaDT"));
	    	dt.setMaBn(rs.getString("MaBHYT"));
	    	dt.setMaBS(rs.getString("MaBS"));
	    	dt.setNgayKeDon(rs.getDate("NgayKD"));
	    	dt.setTenThuoc(rs.getString("TenThuoc"));
	    	dt.setLieuDung(rs.getString("LieuDung"));
	    	dt.setCachSD(rs.getString("CachSD"));

	        // Đóng kết nối và trả về đối tượng bacSibean
	        kn.cn.close();
	        return dt;
	    } else {
	        // Nếu không có dữ liệu, đóng kết nối và trả về null
	        kn.cn.close();
	        return null;
	    }
	}
	
	public void SuaDT(String maBn, String ngayKD, String maBs, String tenThuoc, String lieuDung, String cachSd, String madt) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE DONTHUOC SET MaBHYT = ?, MaBS = ? , NgayKD = ?, TenThuoc = ?, LieuDung =? , CachSD = ? WHERE MaDT = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, maBs);
	    ps.setString(3, ngayKD);
	    ps.setString(4, tenThuoc);
	    ps.setString(5, lieuDung);
	    ps.setString(6, cachSd);
	    ps.setString(7, madt);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	   	}
}
