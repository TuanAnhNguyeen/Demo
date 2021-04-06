package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import EntityClass.NguoiHoc;

public class NguoiHocDao extends EduSysDAO<NguoiHoc, String> {
	String SQL_INSERT = "INSERT NGUOIHOC VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String SQL_UPDATE = "UPDATE NGUOIHOC SET HOTE = ?, GIOITINH = ?, NGAYSINH = ?, DIENTHOAI = ?,EMAIL = ?, GHICHU = ?, MANV = ?, NGAYDK = ? WHERE MANH = ?";
	String SQL_DELETE = "DELETE FROM NGUOIHOC WHERE MANH = ?";
	String SQL_SELECT_BY_ID = "SELECT MANH, HOTE, GIOITINH, NGAYSINH, DIENTHOAI, EMAIL, GHICHU, MANV, NGAYDK\n"
			+ "FROM NGUOIHOC WHERE MANH = ?";
	String SQL_SELECT_ALL = "SELECT * FROM NGUOIHOC";

	@Override
	public void insert(NguoiHoc entity) {
		DAOHelper.update(SQL_INSERT, entity.getMaNH(), entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(),
				entity.getSoDT(), entity.getEmail(), entity.getGhiChu(), entity.getNgayDK(),entity.getMaNV());
	}

	@Override
	public void update(NguoiHoc entity) {
		DAOHelper.update(SQL_UPDATE, entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getSoDT(),
				entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK(), entity.getMaNH());

	}

	@Override
	public void delete(String id) {
		DAOHelper.update(SQL_DELETE, id);
	}

	@Override
	public NguoiHoc selectById(String id) {
		List<NguoiHoc> list = this.selectBySql(SQL_SELECT_BY_ID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NguoiHoc> selectAll() {
		return this.selectBySql(SQL_SELECT_ALL);
	}

	@Override
	protected List<NguoiHoc> selectBySql(String sql, Object... args) {
		List<NguoiHoc> list = new ArrayList<NguoiHoc>();
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			while (rs.next()) {
				NguoiHoc nh = new NguoiHoc();
				nh.setMaNH(rs.getString("MANH"));
				nh.setHoTen(rs.getString("HOTE"));
				nh.setGioiTinh(rs.getBoolean("GIOITINH"));
				nh.setNgaySinh(rs.getDate("NGAYSINH"));
				nh.setSoDT(rs.getString("DIENTHOAI"));
				nh.setEmail(rs.getString("EMAIL"));
				nh.setGhiChu(rs.getString("GHICHU"));
				nh.setMaNV(rs.getString("MANV"));
				nh.setNgayDK(rs.getDate("NGAYDK"));
				list.add(nh);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
