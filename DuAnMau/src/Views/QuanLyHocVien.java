package Views;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.ChuyenDeDao;
import Dao.HocVienDao;
import Dao.KhoaHocDao;
import Dao.NguoiHocDao;
import EntityClass.ChuyenDe;
import EntityClass.HocVien;
import EntityClass.KhoaHoc;
import EntityClass.NguoiHoc;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyHocVien extends JInternalFrame {

	private JPanel contentPane;
	private JTable tblHocVien;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1 = new DefaultTableModel();
	private JTextField txtSeach;
	private JTable tblNguoiHoc;
	JButton btnAddKhoaHoc = new JButton("Thêm vào khóa học");
	JButton btnUpdateDiem = new JButton("Cập nhật điểm");
	JButton btnDeleteKhoaHoc = new JButton("Xóa khỏi khóa học");
	StringBuilder sb = new StringBuilder();
	int makh = 0;
	String manh;
	String maHV;
	JComboBox cboChuyenDe = new JComboBox();
	JComboBox cboKhoaHoc = new JComboBox();
	List<ChuyenDe> listCD = new ArrayList();
	List<KhoaHoc> listKH = new ArrayList();
	List<HocVien> listHV = new ArrayList();
	List<NguoiHoc> listNH = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyHocVien frame = new QuanLyHocVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadComboboxChuyenDe() {
		listCD.clear();
		ChuyenDeDao cd = new ChuyenDeDao();
		listCD = cd.selectAll();
		Vector<String> vector = new Vector<String>();
		for (ChuyenDe x : listCD) {
			vector.add(x.getTenCD());
		}
		cboChuyenDe.setModel(new DefaultComboBoxModel(vector));
	}

	public void loaComboxKhoaHoc() {
		listKH.clear();
		KhoaHocDao kh = new KhoaHocDao();
		listKH = kh.selectAll();
		Vector<String> vector = new Vector<String>();
		String tencd = cboChuyenDe.getSelectedItem().toString();
		for (KhoaHoc x : listKH) {
			if (tencd.equalsIgnoreCase(x.getTenCD())) {
				vector.add(x.getMaCD() + "(" + x.getNgayKG() + ")");
			}
		}
		if (vector.size() > 0) {
			cboKhoaHoc.setModel(new DefaultComboBoxModel(vector));
		} else {
			JOptionPane.showMessageDialog(null, "Chuyên Đề Chưa Có Khóa Học");
			return;
		}

	}

	public void fillTableNguoiHoc() {
		listNH.clear();
		model1.setRowCount(0);
		NguoiHocDao nhd = new NguoiHocDao();
		listNH = nhd.selectAll();
		model.setRowCount(0);
		for (NguoiHoc nh : listNH) {
			String gioitinh;
			if (nh.getGioiTinh() == true) {
				gioitinh = "Nam";
			} else {
				gioitinh = "Nữ";
			}
			model1.addRow(new Object[] { nh.getMaNH(), nh.getHoTen(), gioitinh, nh.getNgaySinh(), nh.getSoDT(),
					nh.getEmail() });
		}
	}

	public void loadListHv() {
		listHV.clear();
		HocVienDao hv = new HocVienDao();
		listHV = hv.selectAll();
	}

	public void fillTableHocVien() {
		model.setRowCount(0);
		loadListHv();
		String khoahoc = cboKhoaHoc.getSelectedItem().toString();
		for (KhoaHoc x : listKH) {
			if (khoahoc.contains(x.getMaCD()) && khoahoc.contains("" + x.getNgayKG())) {
				makh = x.getMaKH();
			}
		}
		int stt = 0;
		for (HocVien x : listHV) {
			if (x.getMaKH() == makh) {
				stt++;
				if (x.getDiem() <= 0 || x.getDiem() > 10) {
					model.addRow(new Object[] { stt, x.getMaHV(), x.getMaNH(), x.getTenNH(), "" });
				} else {
					model.addRow(new Object[] { stt, x.getMaHV(), x.getMaNH(), x.getTenNH(), x.getDiem() });
				}
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public QuanLyHocVien() {
		setTitle("Quản Lý Học Viên");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHUYÊN ĐỀ");
		lblNewLabel.setBounds(10, 11, 154, 14);
		contentPane.add(lblNewLabel);

		JLabel lblKhaHc = new JLabel("kHÓA HỌC");
		lblKhaHc.setBounds(352, 11, 154, 14);
		contentPane.add(lblKhaHc);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 25, 324, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		cboChuyenDe.setBounds(10, 11, 304, 22);
		panel.add(cboChuyenDe);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(352, 25, 324, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		cboKhoaHoc.setBounds(10, 11, 304, 22);
		panel_1.add(cboKhoaHoc);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 77, 666, 405);
		contentPane.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Học viên", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 661, 332);
		panel_2.add(scrollPane);

		tblHocVien = new JTable();
		tblHocVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDeleteKhoaHoc.setEnabled(true);
				btnUpdateDiem.setEnabled(true);
				int luachon = tblHocVien.getSelectedRow();
				maHV = String.valueOf(tblHocVien.getValueAt(luachon, 1));
			}
		});
		scrollPane.setViewportView(tblHocVien);
		model.addColumn("TT");
		model.addColumn("MÃ HV");
		model.addColumn("MÃ NH");
		model.addColumn("HỌ TÊN");
		model.addColumn("ĐIỂM");
		tblHocVien.setModel(model);
		btnUpdateDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCapNhatDiem();
			}
		});
		btnUpdateDiem.setBounds(522, 343, 129, 23);
		panel_2.add(btnUpdateDiem);

		btnDeleteKhoaHoc.setEnabled(false);
		btnDeleteKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnxoakhoiKhoaHoc();
			}
		});
		btnDeleteKhoaHoc.setBounds(363, 343, 149, 23);
		panel_2.add(btnDeleteKhoaHoc);

		tblHocVien.getColumnModel().getColumn(0).setPreferredWidth(5);
		tblHocVien.getColumnModel().getColumn(3).setPreferredWidth(300);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Người học", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setBounds(10, 5, 49, 14);
		panel_3.add(lblNewLabel_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(10, 23, 641, 45);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		txtSeach = new JTextField();
		txtSeach.setBounds(10, 11, 522, 23);
		panel_4.add(txtSeach);
		txtSeach.setColumns(10);

		JButton btnSeach = new JButton("Tìm");
		btnSeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem();
			}
		});
		btnSeach.setBounds(542, 10, 89, 23);
		panel_4.add(btnSeach);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 79, 641, 253);
		panel_3.add(scrollPane_1);

		tblNguoiHoc = new JTable();
		tblNguoiHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAddKhoaHoc.setEnabled(true);
				int select = tblNguoiHoc.getSelectedRow();
				if (select >= 0) {
					manh = (String) tblNguoiHoc.getValueAt(select, 0);
				}
			}
		});
		scrollPane_1.setViewportView(tblNguoiHoc);
		model1.addColumn("MÃ NH");
		model1.addColumn("HỌ VÀ TÊN");
		model1.addColumn("GIỚI TÍNH");
		model1.addColumn("NGÀY SINH");
		model1.addColumn("ĐIỆN THOẠI");
		model1.addColumn("EMAIL");
		tblNguoiHoc.setModel(model1);

		btnAddKhoaHoc.setEnabled(false);
		btnAddKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThemVaoKhoaHoc();
			}
		});
		btnAddKhoaHoc.setBounds(467, 345, 171, 23);
		panel_3.add(btnAddKhoaHoc);
		loadComboboxChuyenDe();
		cboChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loaComboxKhoaHoc();
				fillTableHocVien();
			}
		});
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableHocVien();
			}
		});
		fillTableNguoiHoc();
		loaComboxKhoaHoc();
		fillTableHocVien();
	}

	protected void btnTimKiem() {
		for (NguoiHoc x : listNH) {
			if (txtSeach.getText().equalsIgnoreCase(x.getMaNH())) {
				int index;
				index = listNH.indexOf(x);
				tblNguoiHoc.setRowSelectionInterval(index, index);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Không tồn tại người học bạn cần tìm");

	}

	protected void btnThemVaoKhoaHoc() {
		loadListHv();
		for (HocVien x : listHV) {
			if (x.getMaKH() == makh && x.getMaNH().equalsIgnoreCase(manh)) {
				JOptionPane.showMessageDialog(null, "Người học đã có trong lớp");
				return;
			}
		}
		HocVienDao hocvien = new HocVienDao();
		HocVien hv = new HocVien();
		hv.setMaKH(makh);
		hv.setMaNH(manh);
		hocvien.insert(hv);
		btnAddKhoaHoc.setEnabled(false);
		fillTableHocVien();

	}

	protected void btnxoakhoiKhoaHoc() {
		HocVienDao hvd = new HocVienDao();
		if (maHV.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "học viên Không tồn tại");
		}
		hvd.delete(maHV);
		fillTableHocVien();
		btnDeleteKhoaHoc.setEnabled(false);

	}

	protected void btnCapNhatDiem() {
		sb.setLength(0);
		HocVienDao hvd = new HocVienDao();
		for (int i = 0; i < tblHocVien.getRowCount(); i++) {
			HocVien hv = new HocVien();
			int mahv = (Integer) tblHocVien.getValueAt(i, 1);
			hv.setMaHV(mahv);
			try {
				float diem = Float.parseFloat(String.valueOf(tblHocVien.getValueAt(i, 4)));
				if (diem >= 0 && diem <= 10) {
					hv.setDiem(diem);
					hvd.update(hv);
				} else {
					sb.append("bạn nhập sai ở dong"+(i+1)+"\n");
				}
			} catch (Exception e2) {
				sb.append("bạn nhập sai ở dong"+(i+1)+"\n");
			}
		}
		if(sb.length()>0) {
			JOptionPane.showMessageDialog(null, sb.toString());
		}
		fillTableHocVien();
	}

}
