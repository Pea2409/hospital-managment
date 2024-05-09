package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import bean.xetNghiembean;

public class xetNghiemdao {
	public int soLuongXetNghiem() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(DISTINCT MaBHYT) AS SoLuong FROM KETQUA;";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}
	public ArrayList<xetNghiembean> getXN() throws Exception {
		ArrayList<xetNghiembean>  ds = new ArrayList<xetNghiembean>();
		// b1: Kết nối vào csdl : QlBenhNhan
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "select * from KETQUA";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		
		// b6: Duyệt rs
		while (rs.next()) {
			String maXetNghiem = rs.getString("MaKQ");
			String maBn = rs.getString("MaBHYT");
			Date ngayXN = rs.getDate("NgayTH");
			String loaiXN = rs.getString("LoaiXN");
			String ketQua = rs.getString("KetQua");
			
			ds.add(new xetNghiembean(maXetNghiem, maBn, ngayXN, loaiXN, ketQua));
		}
		
		// b7: Đóng các đối tượng
		rs.close();
		kn.cn.close();
		
		return ds;
	}
	
	public void addXetNghiem(String maBn, String ngayTH, String loaiXN, String ketQua) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO KETQUA (MaBHYT, NgayTH, LoaiXN, KetQua) VALUES(?, ?, ?, ?)";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, ngayTH);
	    ps.setString(3, loaiXN);
	    ps.setString(4, ketQua);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
	
	public void XoaXetNghiem(String maXetNghiem) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete from KETQUA where MaKQ = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1,maXetNghiem);
		ps.executeUpdate();
		kn.cn.close();
	}
	
	public xetNghiembean getInfoXN(String maXetNghiem) throws Exception {
	    // Bước 1: Kết nối vào cơ sở dữ liệu: QlBenNhan
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // Bước 2: Tạo câu lệnh SQL
	    String sql = "select * from KETQUA WHERE MaKQ = ?";

	    // Bước 3: Tạo câu lệnh
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    // Bước 4: Truyền tham số vào câu lệnh nếu có
	    ps.setString(1, maXetNghiem);

	    // Bước 5: Thực hiện câu lệnh
	    ResultSet rs = ps.executeQuery();

	    // Bước 6: Xử lý kết quả
	    if (rs.next()) {
	    	xetNghiembean xn = new xetNghiembean();
	    	xn.setMaXetNghiem(rs.getString("MaKQ"));
	    	xn.setMaBn(rs.getString("MaBHYT"));
	    	xn.setNgayXN(rs.getDate("NgayTH"));
	    	xn.setLoaiXN(rs.getString("LoaiXN"));
	    	xn.setKetQua(rs.getString("KetQua"));
	        // Đóng kết nối và trả về đối tượng 
	        kn.cn.close();
	        return xn;
	    } else {
	        // Nếu không có dữ liệu, đóng kết nối và trả về null
	        kn.cn.close();
	        return null;
	    }
	}

	public void SuaXN(String maBn, String ngayTH, String loaiXN, String ketQua, String maXN) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "update KETQUA set MaBHYT =? , NgayTH = ?, LoaiXN = ?, KetQua = ? WHERE MaKQ = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, ngayTH);
	    ps.setString(3, loaiXN);
	    ps.setString(4, ketQua);
	    ps.setString(5, maXN);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
}
