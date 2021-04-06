package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.HocVienDao;
import Dao.KhoaHocDao;
import Dao.ThongKeDao;
import EntityClass.HocVien;
import EntityClass.KhoaHoc;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class ThongKe extends JInternalFrame {

	private JPanel contentPane;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1 = new DefaultTableModel();
	DefaultTableModel model2 = new DefaultTableModel();
	DefaultTableModel model3 = new DefaultTableModel();
	private JTable tblBangDiem;
	private JTable tblNguoiHoc;
	private JTable tblDiemChuyenDe;
	private JTable tblDoanhThu;


	JComboBox<String> cboNamDoanhThu = new JComboBox<String>();
	JComboBox<String> cboKhoaHoc = new JComboBox<String>();
	List<KhoaHoc> listKH = new ArrayList();
	List<HocVien> listHV = new ArrayList();

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe frame = new ThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void loaComboxKhoaHoc() {
		listKH.clear();
		KhoaHocDao kh = new KhoaHocDao();
		listKH = kh.selectAll();
		Vector<String> vector = new Vector<String>();
		for (KhoaHoc x : listKH) {
				vector.add(x.getMaCD() + "(" + x.getNgayKG() + ")");
		}
		cboKhoaHoc.setModel(new DefaultComboBoxModel(vector));
	}

	public void fillTableBangDiem() {
		model.setRowCount(0);
		String khoahoc = cboKhoaHoc.getSelectedItem().toString();
		int makh = 0;
		for (KhoaHoc x : listKH) {
			if (khoahoc.contains(x.getMaCD()) && khoahoc.contains("" + x.getNgayKG())) {
				makh = x.getMaKH();
			}
		}
		listHV.clear();
		listHV = ThongKeDao.ThongKeBangDiem(makh);
		int stt = 0;
		for (HocVien x : listHV) {
			if (x.getMaKH() == makh) {
				stt++;
				if (x.getDiem() == -1) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), "","" });
				} else if(x.getDiem()>=8) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), x.getDiem(),"Giỏi" });
				}
				else if(x.getDiem()>=6.5) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), x.getDiem(),"Khá" });
				}else if(x.getDiem()>=5.0) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), x.getDiem(),"Trung Bình" });
				}else if(x.getDiem()>=4) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), x.getDiem(),"Yếu" });
				}else if(x.getDiem()>=0) {
					model.addRow(new Object[] { stt, x.getMaNH(), x.getTenNH(), x.getDiem(),"Kém" });
				}
			}
		}
	}
	public void fillThongKeNguoiHoc() {
		ThongKeDao.ThongKeNguoiHoc(model1);
	}
	public void fillThongKeChuyenDe() {
		ThongKeDao.thongKeChuyenDe(model2);
	}
	public void fillThongKeDoanhThu() {
		
	}
	/**
	 * Create the frame.
	 */
	public ThongKe() {
		setTitle("TỔNG HỢP THỐNG KÊ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblTngHpThng = new JLabel("TỔNG HỢP THỐNG KÊ");
		lblTngHpThng.setForeground(Color.BLUE);
		lblTngHpThng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTngHpThng.setBounds(10, 0, 257, 34);
		contentPane.add(lblTngHpThng, BorderLayout.NORTH);

		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("BẢNG ĐIỂM", null, panel, null);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 671, 40);
		panel.add(panel_1);
//
		JLabel lblNewLabel = new JLabel("  KHÓA HỌC     ");
		lblNewLabel.setBounds(10, 11, 85, 14);
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableBangDiem();
			}
		});

		cboKhoaHoc.setBounds(105, 7, 568, 22);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(5).addComponent(lblNewLabel).addGap(5)
						.addComponent(cboKhoaHoc, 0, 598, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(5).addComponent(cboKhoaHoc,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(5, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 40, 671, 322);
		panel.add(scrollPane);
		tblBangDiem = new JTable();
		scrollPane.setViewportView(tblBangDiem);
		model.addColumn("STT");
		model.addColumn("MÃ NH");
		model.addColumn("HỌ VÀ TÊN");
		model.addColumn("ĐIỂM");
		model.addColumn("XẾP LOẠI");
		tblBangDiem.setModel(model);
		
		JButton btnBieuDoBangDiem = new JButton("Xem Biểu Đồ");
		btnBieuDoBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BieuDo bieuDo = new BieuDo();
			    bieuDo.thongKeDiem(tblBangDiem);
			}
		});
		btnBieuDoBangDiem.setBounds(522, 375, 120, 25);
		panel.add(btnBieuDoBangDiem);

		tblBangDiem.getColumnModel().getColumn(0).setPreferredWidth(5);

		JPanel NguoiHoc = new JPanel();
		tabbedPane.addTab("NGƯỜI HỌC", null, NguoiHoc, null);
		NguoiHoc.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 671, 367);
		NguoiHoc.add(scrollPane_1);
//
		tblNguoiHoc = new JTable();
		scrollPane_1.setViewportView(tblNguoiHoc);
		model1.addColumn("NĂM");
		model1.addColumn("SỐ NGƯỜI HỌC");
		model1.addColumn("ĐK SỚM NHẤT");
		model1.addColumn("ĐK MUỘN NHẤT");
		tblNguoiHoc.setModel(model1);
		
		JButton btnBieuDoNguoiHoc = new JButton("Xem Biểu Đồ");
		btnBieuDoNguoiHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BieuDo bieuDo = new BieuDo();
				bieuDo.thongKeNguoiHoc(tblNguoiHoc);
			}
		});
		btnBieuDoNguoiHoc.setBounds(551, 380, 120, 25);
		NguoiHoc.add(btnBieuDoNguoiHoc);
//
		JPanel DiemChuyenDe = new JPanel();
		tabbedPane.addTab("ĐIỂM CHUYÊN ĐỀ", null, DiemChuyenDe, null);
		DiemChuyenDe.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 671, 371);
		DiemChuyenDe.add(scrollPane_2);

		tblDiemChuyenDe = new JTable();
		scrollPane_2.setViewportView(tblDiemChuyenDe);
		model2.addColumn("CHUYÊN ĐỀ");
		model2.addColumn("SL HV");
		model2.addColumn("ĐIỂM TN");
		model2.addColumn("ĐIỂM CN");
		model2.addColumn("ĐIỂM TB");
		tblDiemChuyenDe.setModel(model2);
		
		JButton btnBieuDoDiem = new JButton("Xem Biểu Đồ");
		btnBieuDoDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BieuDo bieudo = new  BieuDo();
				bieudo.thongKeDiemChuyenDe(tblDiemChuyenDe);
			}
		});
		btnBieuDoDiem.setBounds(539, 379, 120, 25);
		DiemChuyenDe.add(btnBieuDoDiem);
//
		JPanel DoanhThu = new JPanel();
		tabbedPane.addTab("DOANH THU", null, DoanhThu, null);
		DoanhThu.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 671, 33);
		DoanhThu.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("NĂM");
		lblNewLabel_1.setBounds(10, 11, 49, 14);
		cboNamDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Login.vaiTro==false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền xem doanh thu");
					return;
				}
				fillDoanhThu();
			}
		});

		cboNamDoanhThu.setBounds(48, 7, 625, 22);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(cboNamDoanhThu, 0, 322, Short.MAX_VALUE)
						.addGap(308)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(cboNamDoanhThu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))));
		panel_2.setLayout(gl_panel_2);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 33, 671, 336);
		DoanhThu.add(scrollPane_3);
		tblDoanhThu = new JTable();
		scrollPane_3.setViewportView(tblDoanhThu);
		model3.addColumn("CHUYÊN ĐỀ");
		model3.addColumn("SỐ KH");
		model3.addColumn("SỐ HV");
		model3.addColumn("DOANH THU");
		model3.addColumn("HP TN");
		model3.addColumn("HP CN");
		model3.addColumn("HP TB");
		tblDoanhThu.setModel(model3);
		
		JButton btnBieuDoDoanhThu = new JButton("Xem Biểu Đồ");
		btnBieuDoDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Login.vaiTro==false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền chức năng này");
					return;
				}
				BieuDo bieuDo = new BieuDo();
				bieuDo.thongKeDoanhThu(tblDoanhThu);
			}
		});
		btnBieuDoDoanhThu.setBounds(551, 379, 120, 25);
		DoanhThu.add(btnBieuDoDoanhThu);
		 loaComboxKhoaHoc();
		 loadCBONam();
		 fillTableBangDiem();
		 fillThongKeNguoiHoc();
		 fillThongKeChuyenDe();
		 if(Login.vaiTro==true) {
		 fillDoanhThu();
		 }
	}
	protected void fillDoanhThu() {
		model3.setRowCount(0);
		String nam = cboNamDoanhThu.getSelectedItem().toString();
		int year = Integer.parseInt(nam);
		ThongKeDao.thongkeDoanhThu(model3, year);
		
	}
	protected void loadCBONam() {
		ThongKeDao.loadCBO(cboNamDoanhThu);
		
	}
}
