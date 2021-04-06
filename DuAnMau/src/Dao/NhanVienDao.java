package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EntityClass.NhanVien;

public class NhanVienDao extends EduSysDAO<NhanVien, String>{
	String INSERT_SQL ="INSERT INTO NHANVIEN (MANV,MATKHAU,HOTEN,VAITRO) VALUES(?,?,?,?)";
	String UPDATE_SQL="UPDATE NHANVIEN SET MATKHAU=?,HOTEN=?,VAITRO=? WHERE MANV=?";
	String DELETE_SQL_NHANVIEN="DELETE FROM NHANVIEN WHERE MANV=?";
	String DELETE_SQL_KHOAHOC="DELETE FROM KHOAHOC WHERE MANV=?";
	String SELECT_ALL_SQL="SELECT * FROM NHANVIEN";
	String SELECT_BY_ID_SQL="SELECT * FROM NHANVIEN WHERE MANV =?";

	@Override
	public void insert(NhanVien entity) {
		DAOHelper.update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro());
	}

	@Override
	public void update(NhanVien entity) {
		DAOHelper.update(UPDATE_SQL, entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro(),entity.getMaNV());
	
	}

	@Override
	public void delete(String id) {
		DAOHelper.update(DELETE_SQL_KHOAHOC, id);
		DAOHelper.update(DELETE_SQL_NHANVIEN, id);
	}

	@Override
	public NhanVien selectById(String id) {
		List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NhanVien> selectAll() {
		return this.selectBySql(SELECT_ALL_SQL);
	}

	@Override
	protected List<NhanVien> selectBySql(String sql, Object... args) {
		List<NhanVien> list = new ArrayList();
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			while(rs.next()) {
				NhanVien entity = new NhanVien();
				entity.setMaNV(rs.getString("manv"));
				entity.setHoTen(rs.getString("hoten"));
				entity.setMatKhau(rs.getString("matkhau"));
				entity.setVaiTro(rs.getBoolean("VaiTro"));
				list.add(entity);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
