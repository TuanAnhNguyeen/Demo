package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import Dao.ChuyenDeDao;
import Dao.KhoaHocDao;
import EntityClass.ChuyenDe;
import EntityClass.KhoaHoc;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyKhoaHoc extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtChuyenDe;
	private JTextField txtHocPhi;
	private JTextField txtNguoiTao;
	private JTextField txtThoiLuong;
	private JTextField txtNgayTao;
	private JTable tblDanhSach;
	DefaultTableModel model = new DefaultTableModel();
	List<KhoaHoc> list = new ArrayList();
	List<KhoaHoc> listKH = new ArrayList();
	List<ChuyenDe> listCD = new ArrayList();
	StringBuilder sb = new StringBuilder();
	JComboBox cboChyenDe = new JComboBox();
	JDateChooser dateKhaiGiang = new JDateChooser();
	JButton btnUpdate = new JButton("Sửa");
	JTextArea txtGhiChu = new JTextArea();
	JButton btnADD = new JButton("Thêm");
	JButton btnDelete = new JButton("Xóa");
	JButton btnFrist = new JButton("|<");
	JButton btnBlack = new JButton("<<");
	JButton btnNext = new JButton(">>");
	JButton btnLast = new JButton(">|");
	int index;
	int makh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyKhoaHoc frame = new QuanLyKhoaHoc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadList() {
		list.clear();
		KhoaHocDao kh = new KhoaHocDao();
		list = kh.selectAll();
	}

	public void loadComboBox() {
		ChuyenDeDao cd = new ChuyenDeDao();
		listCD = cd.selectAll();
		Vector<String> vector = new Vector<String>();
		for (ChuyenDe x : listCD) {
			vector.add(x.getTenCD());
		}
		cboChyenDe.setModel(new DefaultComboBoxModel(vector));
	}

	public QuanLyKhoaHoc() {
		setTitle("Quản lý khóa học");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 32, 602, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		cboChyenDe.setBounds(10, 11, 582, 22);
		panel.add(cboChyenDe);

		JLabel lblChuyn = new JLabel("CHUYÊN ĐỀ");
		lblChuyn.setForeground(Color.RED);
		lblChuyn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChuyn.setBounds(10, 0, 257, 34);
		contentPane.add(lblChuyn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 86, 602, 345);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Danh sách", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 577, 295);
		panel_1.add(scrollPane_1);

		tblDanhSach = new JTable();
		tblDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = tblDanhSach.getSelectedRow();
				index = select;
				hienthi(select);
			}
		});
		scrollPane_1.setViewportView(tblDanhSach);
		model.addColumn("MÃ KH");
		model.addColumn("THỜI LƯỢNG");
		model.addColumn("HỌC PHÍ");
		model.addColumn("KHAI GIẢNG");
		model.addColumn("TẠO BỞI");
		model.addColumn("NGÀY TẠO");
		tblDanhSach.setModel(model);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Cập nhật", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Chuyên đề");
		lblNewLabel.setBounds(10, 11, 98, 14);
		panel_2.add(lblNewLabel);

		txtChuyenDe = new JTextField();
		txtChuyenDe.setEditable(false);
		txtChuyenDe.setColumns(10);
		txtChuyenDe.setBounds(10, 36, 285, 26);
		panel_2.add(txtChuyenDe);

		JLabel lblNewLabel_1 = new JLabel("Học phí");
		lblNewLabel_1.setBounds(10, 73, 98, 14);
		panel_2.add(lblNewLabel_1);

		txtHocPhi = new JTextField();
		txtHocPhi.setColumns(10);
		txtHocPhi.setBounds(10, 98, 285, 26);
		panel_2.add(txtHocPhi);

		JLabel lblNewLabel_1_1 = new JLabel("Người tạo");
		lblNewLabel_1_1.setBounds(10, 135, 98, 14);
		panel_2.add(lblNewLabel_1_1);

		txtNguoiTao = new JTextField();
		txtNguoiTao.setEnabled(false);
		txtNguoiTao.setColumns(10);
		txtNguoiTao.setBounds(10, 160, 285, 26);
		panel_2.add(txtNguoiTao);

		txtThoiLuong = new JTextField();
		txtThoiLuong.setColumns(10);
		txtThoiLuong.setBounds(305, 98, 282, 26);
		panel_2.add(txtThoiLuong);

		txtNgayTao = new JTextField();
		txtNgayTao.setEnabled(false);
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(305, 160, 282, 26);
		panel_2.add(txtNgayTao);

		JLabel lblKhaiGing = new JLabel("Khai giảng");
		lblKhaiGing.setBounds(305, 11, 98, 14);
		panel_2.add(lblKhaiGing);

		JLabel lblThiLnggi = new JLabel("Thời lượng (giờ)");
		lblThiLnggi.setBounds(305, 73, 98, 14);
		panel_2.add(lblThiLnggi);

		JLabel lblNgyTo = new JLabel("Ngày tạo");
		lblNgyTo.setBounds(305, 135, 98, 14);
		panel_2.add(lblNgyTo);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ghi chú");
		lblNewLabel_1_1_1.setBounds(10, 197, 98, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 577, 48);
		panel_2.add(scrollPane);

		scrollPane.setViewportView(txtGhiChu);

		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd();
			}
		});
		btnADD.setEnabled(false);
		btnADD.setBounds(10, 281, 73, 23);
		panel_2.add(btnADD);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSua();
			}
		});
		btnUpdate.setBounds(93, 281, 73, 23);
		panel_2.add(btnUpdate);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoaHocDao kh = new KhoaHocDao();
				kh.delete(String.valueOf(makh));
				Combobox();
				JOptionPane.showMessageDialog(null, "xóa thành công");
			}
		});
		btnDelete.setBounds(176, 281, 73, 23);
		panel_2.add(btnDelete);

		JButton btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMoi();
			}
		});
		btnNew.setBounds(259, 281, 73, 23);
		panel_2.add(btnNew);

		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listKH.size() > 0) {
					index = listKH.size() - 1;
					hienthi(index);
					makh = listKH.get(index).getMaKH();
				}
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnLast.setBounds(540, 281, 47, 23);
		panel_2.add(btnLast);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < (listKH.size() - 1)) {
					index++;
					hienthi(index);
					makh = listKH.get(index).getMaKH();
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNext.setBounds(483, 281, 47, 23);
		panel_2.add(btnNext);

		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					hienthi(index);
					makh = listKH.get(index).getMaKH();
				}
			}
		});
		btnBlack.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnBlack.setBounds(426, 281, 47, 23);
		panel_2.add(btnBlack);

		btnFrist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listKH.size() > 0) {
					index = 0;
					hienthi(0);
					makh = listKH.get(index).getMaKH();
				}
			}
		});
		btnFrist.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnFrist.setBounds(369, 281, 47, 23);
		panel_2.add(btnFrist);

		dateKhaiGiang.setBounds(307, 36, 280, 22);
		panel_2.add(dateKhaiGiang);
		loadComboBox();
		Combobox();
		cboChyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Combobox();
			}
		});
	}
	protected void btnSua() {
		vadidate();
		if(sb.length()>0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		KhoaHocDao kh = new KhoaHocDao();
		KhoaHoc khoahoc = new KhoaHoc();
		for (ChuyenDe x : listCD) {
			if (txtChuyenDe.getText().equalsIgnoreCase(x.getTenCD())) {
				khoahoc.setMaCD(x.getMaCD());
				break;
			}
		}
		khoahoc.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
		khoahoc.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(dateKhaiGiang.getDate());
		Date time = null;
		try {
			time = format.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		khoahoc.setNgayKG(time);
		khoahoc.setGhiChu(txtGhiChu.getText());
		khoahoc.setMaNV(Login.Manv);
		Date now = new Date();
		khoahoc.setNgayTao(now);
		khoahoc.setMaKH(makh);
		kh.update(khoahoc);
		Combobox();
		JOptionPane.showMessageDialog(null, "sửa thành công");
		
	}

	public void vadidate() {
		sb.setLength(0);
		if(dateKhaiGiang.getCalendar()==null) {
			sb.append("bạn chưa chon ngày khai giảng\n");
		}
		if (txtHocPhi.getText().isBlank()) {
			sb.append("bạn chưa nhập Học Phí \n");
		} else {
			try {
				double money = Double.parseDouble(txtHocPhi.getText());
				if (money <= 0) {
					sb.append("bạn phải nhập số tiền lớn hơn 0\n");
				}
			} catch (Exception e) {
				sb.append("bạn nhập sai Học phí \n");
			}
		}
		if (txtThoiLuong.getText().isBlank()) {
			sb.append("bạn chưa nhập thời lượng \n");
		} else {
			try {
				int clock = Integer.parseInt(txtThoiLuong.getText());
				if (clock <= 0) {
					sb.append("bạn phải nhập thời lượng lớn hơn 0 \n");
				}
			} catch (Exception e) {
				sb.append("bạn nhập sai thời lượng \n");
			}
		}
	}
	protected void btnAdd() {
		vadidate();
		if(sb.length()>0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		KhoaHocDao kh = new KhoaHocDao();
		KhoaHoc khoahoc = new KhoaHoc();
		for (ChuyenDe x : listCD) {
			if (txtChuyenDe.getText().equalsIgnoreCase(x.getTenCD())) {
				khoahoc.setMaCD(x.getMaCD());
				break;
			}
		}
		khoahoc.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
		khoahoc.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(dateKhaiGiang.getDate());
		Date time = null;
		try {
			time = format.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		khoahoc.setNgayKG(time);
		khoahoc.setGhiChu(txtGhiChu.getText());
		khoahoc.setMaNV(Login.Manv);
		Date now = new Date();
		khoahoc.setNgayTao(now);
		kh.insert(khoahoc);
		Combobox();
		JOptionPane.showMessageDialog(null, "thêm mới thành công");
		
	}

	protected void btnMoi() {
		btnADD.setEnabled(true);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnFrist.setEnabled(false);
		btnLast.setEnabled(false);
		btnBlack.setEnabled(false);
		btnNext.setEnabled(false);
		txtHocPhi.setText("");
		txtThoiLuong.setText("");
		txtGhiChu.setText("");
		txtNguoiTao.setText("");
		txtNgayTao.setText("");
		dateKhaiGiang.setCalendar(null);
		
	}

	protected void Combobox() {
		model.setRowCount(0);
		loadList();
		listKH.clear();
		String tencd = cboChyenDe.getSelectedItem().toString();
		for (KhoaHoc x : list) {
			if (tencd.equalsIgnoreCase(x.getTenCD())) {
				String hocphi =(""+x.getHocPhi()).replace(".0", "");
				model.addRow(new Object[] { x.getMaKH(), x.getThoiLuong(),hocphi, x.getNgayKG(), x.getMaNV(),
						x.getNgayTao() });
				listKH.add(x);
			}
		}
		if (listKH.size() > 0) {
			hienthi(0);
		} else {
			btnMoi();
			txtChuyenDe.setText(tencd);
		}
	}

	public void hienthi(int i) {
		loadList();
		txtChuyenDe.setText(listKH.get(i).getTenCD());
		txtHocPhi.setText(String.valueOf(listKH.get(i).getHocPhi()).replace(".0", ""));
		txtThoiLuong.setText(String.valueOf(listKH.get(i).getThoiLuong()));
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		String ngayTao = fomat.format(listKH.get(i).getNgayTao());
		txtNgayTao.setText(ngayTao);
		txtNguoiTao.setText(listKH.get(i).getMaNV());
		dateKhaiGiang.setDate(listKH.get(i).getNgayKG());
		txtGhiChu.setText(listKH.get(i).getGhiChu());
		makh = listKH.get(i).getMaKH();
		btnADD.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		btnFrist.setEnabled(true);
		btnLast.setEnabled(true);
		btnBlack.setEnabled(true);
		btnNext.setEnabled(true);
		tblDanhSach.setRowSelectionInterval(i, i);
	}
}
