package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.HocVienDao;
import Dao.NhanVienDao;
import EntityClass.NhanVien;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyNhanVien extends JInternalFrame {

	private JPanel contentPane;
	private JTable tblNhanVien;
	DefaultTableModel model = new DefaultTableModel();
	private JTextField txtUser;
	private JTextField txtPass;
	private JTextField txtHoTen;
	JButton btnSua = new JButton("Sửa");
	JButton btnMoi = new JButton("Mới");
	JButton btnXoa = new JButton("Xóa");
	JButton btnThem = new JButton("Thêm");
	JButton btnLast = new JButton(">|");
	JButton btnNext = new JButton(">>");
	JButton btnPre = new JButton("<<");
	JButton btnFirst = new JButton("|<");
	List<NhanVien> list = new ArrayList();
	StringBuilder sb = new StringBuilder();
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVien frame = new QuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JRadioButton rdbtnTrgPhg = new JRadioButton("Trưởng phòng");
	JRadioButton rdbtnNhanVien = new JRadioButton("Nhân viên");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public void loadList() {
		list.clear();
		NhanVienDao nv = new NhanVienDao();
		list= nv.selectAll();
	}
	public void fillTable() {
		loadList();
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			String pass="";
			for (int i = 0; i < nhanVien.getMatKhau().length(); i++) {
				pass = pass+"*";
			}
			String vaitro;
			if(nhanVien.isVaiTro()==false) {
				vaitro="Nhân Viên";
			}else {
				vaitro="Trưởng Phòng";
			}
			model.addRow(new Object [] {nhanVien.getMaNV(),pass,nhanVien.getHoTen(),vaitro});
		}
		if(list.size()>0) {
			hienthi(0);
		}
	}
	public QuanLyNhanVien() {
		setTitle("Quản lý nhân viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		JLabel lblQunLNhn = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblQunLNhn.setForeground(Color.BLUE);
		lblQunLNhn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQunLNhn.setBounds(10, 0, 257, 34);
		contentPane.add(lblQunLNhn, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		


		JPanel panel = new JPanel();
		tabbedPane.addTab("Danh sách", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblNhanVien = new JTable();
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = tblNhanVien.getSelectedRow();
				index=select;
				hienthi(select);
			}
		});
		scrollPane.setViewportView(tblNhanVien);
		model.addColumn("USERNAME");
		model.addColumn("PASSWORD");
		model.addColumn("HỌ TÊN");
		model.addColumn("VAI TRÒ");
		tblNhanVien.setModel(model);

		JPanel capnhat = new JPanel();
		tabbedPane.addTab("Cập nhật", null, capnhat, null);
		capnhat.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		capnhat.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setBounds(10, 11, 161, 14);
		panel_1.add(lblNewLabel);

		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setBounds(10, 36, 452, 20);
		panel_1.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setBounds(10, 67, 161, 14);
		panel_1.add(lblPassword);

		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(10, 92, 452, 20);
		panel_1.add(txtPass);

		JLabel lblNewLabel_1_1 = new JLabel("Họ tên");
		lblNewLabel_1_1.setBounds(10, 123, 161, 14);
		panel_1.add(lblNewLabel_1_1);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(10, 148, 452, 20);
		panel_1.add(txtHoTen);

		JLabel lblNewLabel_1_1_1 = new JLabel("Vai trò");
		lblNewLabel_1_1_1.setBounds(10, 179, 42, 14);
		panel_1.add(lblNewLabel_1_1_1);
		buttonGroup.add(rdbtnTrgPhg);

		rdbtnTrgPhg.setBounds(20, 200, 113, 23);
		panel_1.add(rdbtnTrgPhg);
		buttonGroup.add(rdbtnNhanVien);

		rdbtnNhanVien.setBounds(135, 200, 111, 23);
		panel_1.add(rdbtnNhanVien);

		ButtonGroup gr = new ButtonGroup();
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vadidate();
				if(sb.length()>0) {
					JOptionPane.showMessageDialog(null, sb.toString());
					return;
				}
				NhanVienDao nvd = new NhanVienDao();
				NhanVien nv = new NhanVien();
				nv.setMaNV(txtUser.getText());
				nv.setMatKhau(txtPass.getText());
				nv.setHoTen(txtHoTen.getText());
				if(rdbtnNhanVien.isSelected()) {
					nv.setVaiTro(false);
				}else {
					nv.setVaiTro(true);
				}
				nvd.insert(nv);
				fillTable();
				JOptionPane.showMessageDialog(null, "thêm mới thành công");
			}
		});


		btnThem.setEnabled(false);
		btnThem.setBounds(262, 248, 89, 23);
		panel_1.add(btnThem);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sb.setLength(0);
				if(txtPass.getText().isBlank()) {
					sb.append("bạn chưa nhập mất khẩu \n");
				}
				if(txtHoTen.getText().isBlank()) {
					sb.append("bạn chưa nhập họ tên \n");
				}
				if(sb.length()>0) {
					JOptionPane.showMessageDialog(null, sb.toString());
					return;
				}
				NhanVienDao nvd = new NhanVienDao();
				NhanVien nv = new NhanVien();
				nv.setMaNV(txtUser.getText());
				nv.setMatKhau(txtPass.getText());
				nv.setHoTen(txtHoTen.getText());
				if(rdbtnNhanVien.isSelected()) {
					nv.setVaiTro(false);
				}else {
					nv.setVaiTro(true);
				}
				nvd.update(nv);
				fillTable();
				JOptionPane.showMessageDialog(null, "sửa thành công");
			}
		});

		btnSua.setBounds(373, 248, 89, 23);
		panel_1.add(btnSua);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVienDao nv = new NhanVienDao();
				nv.delete(txtUser.getText());
				fillTable();
				JOptionPane.showMessageDialog(null, "Delete thành công");
			}
		});
		btnXoa.setBounds(262, 200, 89, 23);
		panel_1.add(btnXoa);
		btnMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUser.setText("");
				txtPass.setText("");
				txtHoTen.setText("");
				buttonGroup.clearSelection();
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnThem.setEnabled(true);
				txtUser.setEditable(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnFirst.setEnabled(false);
				btnLast.setEnabled(false);
				btnPre.setEnabled(false);
				btnNext.setEnabled(false);
			}
		});
		btnMoi.setBounds(373, 200, 89, 23);
		panel_1.add(btnMoi);

		
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.size()>0) {
					index = 0;
					hienthi(0);
				}
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnFirst.setBounds(10, 250, 47, 23);
		panel_1.add(btnFirst);

		
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index>0) {
					index--;
					hienthi(index);
				}
			}
		});
		btnPre.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnPre.setBounds(67, 249, 50, 23);
		panel_1.add(btnPre);

		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index<(list.size()-1)) {
					index++;
					hienthi(index);
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNext.setBounds(127, 250, 50, 23);
		panel_1.add(btnNext);

		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.size()>0) {
					index = list.size()-1;
					hienthi(index);
				}
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnLast.setBounds(187, 250, 47, 23);
		panel_1.add(btnLast);
		fillTable();
	}
	public void vadidate() {
		sb.setLength(0);
		if(txtUser.getText().isBlank()) {
			sb.append("bạn chưa nhập username \n");
		}else {
			for (NhanVien nhanVien : list) {
				if(txtUser.getText().equalsIgnoreCase(nhanVien.getMaNV())) {
					sb.append("username đã tồn tại \n");
				}
			}
		}
		if(txtPass.getText().isBlank()) {
			sb.append("bạn chưa nhập mất khẩu \n");
		}
		if(txtHoTen.getText().isBlank()) {
			sb.append("bạn chưa nhập họ tên \n");
		}
		if(rdbtnNhanVien.isSelected()==false && rdbtnTrgPhg.isSelected()==false) {
			sb.append("bạn chưa chọn chức vụ");
		}
	}
	public void hienthi(int i) {
		loadList();
		txtUser.setText(list.get(i).getMaNV());
		txtPass.setText(list.get(i).getMatKhau());
		txtHoTen.setText(list.get(i).getHoTen());
		if(list.get(i).isVaiTro()==true) {
			rdbtnTrgPhg.setSelected(true);
		}else {
			rdbtnNhanVien.setSelected(true);
		}
		txtUser.setEditable(false);
		btnThem.setEnabled(false);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnFirst.setEnabled(true);
		btnLast.setEnabled(true);
		btnPre.setEnabled(true);
		btnNext.setEnabled(true);
		tblNhanVien.setRowSelectionInterval(i, i);
	}
}
