package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EntityClass.KhoaHoc;
import EntityClass.NhanVien;

public class KhoaHocDao extends EduSysDAO<KhoaHoc, String> {
	String SQL_INSERT = "INSERT KHOAHOC (MACD, HOCPHI, THOILUONG, NGAYKG, GHICHU, MANV, NGAYTAO)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	String SQL_UPDATE = "UPDATE KHOAHOC SET MACD = ?, HOCPHI = ?, THOILUONG = ?, NGAYKG = ?, \n"
			+ "GHICHU = ?, MANV = ?, NGAYTAO = ? WHERE MAKH = ?";
	String SQL_DELETE = "DELETE KHOAHOC WHERE MAKH = ?";
	String SQL_SELECT_BY_ID = "SELECT MAKH, MACD, HOCPHI, THOILUONG, NGAYKG, GHICHU, MANV, NGAYTAO\n"
			+ "FROM KHOAHOC WHERE MAKH = ?";
	String SQL_SELECT_ALL = "sELECT MAKH, khoahoc.MACD, khoahoc.HOCPHI, khoahoc.THOILUONG, NGAYKG, "
			+ "GHICHU, MANV, NGAYTAO,tencd FROM KHOAHOC join ChuyenDe on ChuyenDe.macd = khoahoc.macd;";

	@Override
	public void insert(KhoaHoc entity) {
		DAOHelper.update(SQL_INSERT, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),
				entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());

	}

	@Override
	public void update(KhoaHoc entity) {
		DAOHelper.update(SQL_UPDATE, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),
				entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());

	}

	@Override
	public void delete(String id) {
		DAOHelper.update(SQL_DELETE, id);

	}

	@Override
	public KhoaHoc selectById(String id) {
		List<KhoaHoc> list = this.selectBySql(SQL_SELECT_BY_ID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<KhoaHoc> selectAll() {
		return this.selectBySql(SQL_SELECT_ALL);
	}

	@Override
	protected List<KhoaHoc> selectBySql(String sql, Object... args) {
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			while (rs.next()) {
				KhoaHoc kh = new KhoaHoc();
				kh.setMaKH(rs.getInt("MAKH"));
				kh.setMaCD(rs.getString("MaCD"));
				kh.setHocPhi(rs.getDouble("HOCPHI"));
				kh.setThoiLuong(rs.getInt("THOILUONG"));
				kh.setNgayKG(rs.getDate("NGAYKG"));
				kh.setGhiChu(rs.getString("GHICHU"));
				kh.setMaNV(rs.getString("MANV"));
				kh.setNgayTao(rs.getDate("NGAYTAO"));
				kh.setTenCD(rs.getString("tencd"));
				list.add(kh);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
