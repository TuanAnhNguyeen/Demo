package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EntityClass.ChuyenDe;


public class ChuyenDeDao extends EduSysDAO<ChuyenDe, String> {
	String SQL_INSERT = "INSERT CHUYENDE (MACD, TENCD, HOCPHI, THOILUONG, HINH, MOTA) VALUES (?, ?, ?, ?, ?, ?)";
	String SQL_UPDATE = "UPDATE CHUYENDE SET TENCD = ?, HOCPHI = ?, THOILUONG = ?, HINH = ?, MOTA = ? WHERE MACD = ?";
	String SQL_DELETE_CHUYENDE = "DELETE FROM CHUYENDE WHERE MACD = ?";
	String SQL_DELETE_KHOAHOC = "DELETE FROM KHOAHOC WHERE MACD = ?";
	String SQL_SELECT_BY_ID = "SELECT MACD, TENCD, HOCPHI, THOILUONG, HINH, MOTA\n" + "FROM CHUYENDE WHERE MACD = ?";
	String SQL_SELECT_ALL = "SELECT MACD, TENCD, HOCPHI, THOILUONG, HINH, MOTA\n" + "FROM CHUYENDE";

	@Override
	public void insert(ChuyenDe entity) {
		DAOHelper.update(SQL_INSERT, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),
				entity.getHinh(), entity.getMoTa());

	}

	@Override
	public void update(ChuyenDe entity) {
		DAOHelper.update(SQL_UPDATE, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(),
				entity.getMoTa(), entity.getMaCD());
	}

	@Override
	public void delete(String id) {
		DAOHelper.update(SQL_DELETE_KHOAHOC, id);
		DAOHelper.update(SQL_DELETE_CHUYENDE, id);

	}

	@Override
	public ChuyenDe selectById(String id) {
		List<ChuyenDe> list = this.selectBySql(SQL_SELECT_BY_ID, id);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<ChuyenDe> selectAll() {
		return this.selectBySql(SQL_SELECT_ALL);
	}

	@Override
	protected List<ChuyenDe> selectBySql(String sql, Object... args) {
		List<ChuyenDe> list = new ArrayList();
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			while(rs.next()) {
				ChuyenDe cd = new ChuyenDe();
				cd.setMaCD(rs.getString("MACD"));
				cd.setTenCD(rs.getString("TENCD"));
				cd.setHocPhi(rs.getDouble("HOCPHI"));
				cd.setThoiLuong(rs.getInt("THOILUONG"));
				cd.setHinh(rs.getString("HINH"));
				cd.setMoTa(rs.getString("MOTA"));
				list.add(cd);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}


}
