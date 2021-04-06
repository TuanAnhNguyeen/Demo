package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EntityClass.HocVien;
import EntityClass.NhanVien;

public class HocVienDao extends EduSysDAO<HocVien, String> {
	String SQL_INSERT = "INSERT HOCVIEN (MAKH, MANH) VALUES (?, ?)";
	String SQL_UPDATE = "UPDATE HOCVIEN set DIEM = ? WHERE MAHV = ?";
	String SQL_DELETE = "DELETE HOCVIEN WHERE MAHV = ?";
	String SQL_SELECT_BY_ID = "SELECT * FROM HOCVIEN WHERE MAHV = ?";
	String SQL_SELECT_ALL = "select hocvien.mahv,hocvien.makh,hocvien.manh,hocvien.diem,NguoiHoc.hote from hocvien join NguoiHoc"
			+ " on NguoiHoc.MaNH=hocvien.MaNH";

	@Override
	public void insert(HocVien entity) {
		DAOHelper.update(SQL_INSERT, entity.getMaKH(),entity.getMaNH());

	}

	@Override
	public void update(HocVien mahv) {
		DAOHelper.update(SQL_UPDATE, mahv.getDiem(),mahv.getMaHV());
	}

	@Override
	public void delete(String id) {
		DAOHelper.update(SQL_DELETE, id);

	}

	@Override
	public HocVien selectById(String id) {
		List<HocVien> list = this.selectBySql(SQL_SELECT_BY_ID, id);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<HocVien> selectAll() {
		return this.selectBySql(SQL_SELECT_ALL);
	}

	@Override
	protected List<HocVien> selectBySql(String sql, Object... args) {
		List<HocVien> list = new ArrayList<HocVien>();
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			while(rs.next()) {
				HocVien hv = new HocVien();
				hv.setMaHV(rs.getInt("MaHV"));
				hv.setMaKH(rs.getInt("MaKH"));
				hv.setMaNH(rs.getString("MaNH"));
				hv.setDiem(rs.getFloat("Diem"));
				hv.setTenNH(rs.getString("hote"));
				list.add(hv);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
