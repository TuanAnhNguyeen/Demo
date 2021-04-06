package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EntityClass.HocVien;

public class ThongKeDao {
	static Connection con = null;

	public static List<HocVien> ThongKeBangDiem(int i) {
		List<HocVien> list = new ArrayList<HocVien>();
		try {
			con = DaoConnection.connection();
			String sql = "exec sp_bangdiem " + i;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HocVien hv = new HocVien();
				hv.setTenNH(rs.getString("hote"));
				hv.setMaNH(rs.getString("manh"));
				hv.setDiem(rs.getFloat("diem"));
				hv.setMaKH(rs.getInt("makh"));
				list.add(hv);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public static void ThongKeNguoiHoc(DefaultTableModel model) {
		try {
			con = DaoConnection.connection();
			String sql = "exec sp_nguoihoc ";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String nam = rs.getString("nam");
				String soluong = rs.getString("soluong");
				String dautien = rs.getString("dautien");
				String cuoicung = rs.getString("cuoicung");
				model.addRow(new Object[] { nam, soluong, dautien, cuoicung });
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void thongKeChuyenDe(DefaultTableModel model) {
		try {
			con = DaoConnection.connection();
			String sql = "exec sp_diemchuyende ";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tenchuyende = rs.getString("tenchuyende");
				String sohv = rs.getString("sohv");
				String thapnhat = rs.getString("thapnhat");
				String caonhat = rs.getString("caonhat");
				String trungbinh = rs.getString("trungbinh");
				model.addRow(new Object[] { tenchuyende, sohv, thapnhat, caonhat, trungbinh });
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void thongkeDoanhThu(DefaultTableModel model,int nam) {
		try {
			con = DaoConnection.connection();
			String sql = "exec sp_doanhthu "+nam;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tenchuyende = rs.getString("tenchuyende");
				String sokh=rs.getString("sokh");
				String sohv = rs.getString("sohv");
				String hocphi = rs.getString("hocphi");
				String thapnhat = rs.getString("thapnhat");
				String caonhat = rs.getString("caonhat");
				String trungbinh = rs.getString("trungbinh");
				model.addRow(new Object[] { tenchuyende,sokh, sohv,hocphi.replace(".0", ""), thapnhat.replace(".0", ""), caonhat.replace(".0", ""), trungbinh.replace(".0", "") });
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void loadCBO(JComboBox cbo) {
		try {
			con = DaoConnection.connection();
			String sql = "select YEAR(ngaykg) as 'nam' from khoahoc group by YEAR(ngaykg)";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Vector<String> vector = new Vector<String>();
			while (rs.next()) {
				String nam = rs.getString("nam");
				vector.add(nam);
			}
			cbo.setModel(new DefaultComboBoxModel(vector));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
