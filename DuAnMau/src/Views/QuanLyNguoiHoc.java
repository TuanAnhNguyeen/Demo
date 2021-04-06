package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import Dao.NguoiHocDao;
import EntityClass.NguoiHoc;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyNguoiHoc extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	DefaultTableModel model = new DefaultTableModel();
	private JTextField txtSeach;
	private JTable tblHocVien;
	List<NguoiHoc> list = new ArrayList();
	ButtonGroup gr = new ButtonGroup();
	JRadioButton rdoNu = new JRadioButton("Nữ");
	JRadioButton rdoNam = new JRadioButton("Nam");
	JDateChooser dateChooser = new JDateChooser();
	JTextArea txtChuThich = new JTextArea();
	StringBuilder sb = new StringBuilder();
	JButton btnAdd = new JButton("Thêm");
	JButton btnDelete = new JButton("Xóa");
	JButton btnUpdate = new JButton("Sửa");
	JButton btnNext = new JButton(">>");
	JButton btnLast = new JButton(">|");
	JButton btnFirst = new JButton("|<");
	JButton btnBlack = new JButton("<<");
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNguoiHoc frame = new QuanLyNguoiHoc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadList() {
		list.clear();
		NguoiHocDao nh = new NguoiHocDao();
		list = nh.selectAll();
	}

	public void fillTable() {
		model.setRowCount(0);
		loadList();
		for (NguoiHoc nh : list) {
			String gioitinh;
			if (nh.getGioiTinh() == true) {
				gioitinh = "Nam";
			} else {
				gioitinh = "Nữ";
			}
			model.addRow(new Object[] { nh.getMaNH(), nh.getHoTen(), gioitinh, nh.getNgaySinh(), nh.getSoDT(),
					nh.getEmail(), nh.getMaNV(), nh.getNgayDK() });
		}
		if (list.size() == 0) {
			return;
		}
		hienthi(0);
	}

	public QuanLyNguoiHoc() {
		setTitle("Quản Lý Người Học");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQunLChuyn = new JLabel("QUẢN LÝ CHUYÊN ĐỀ");
		lblQunLChuyn.setForeground(Color.BLUE);
		lblQunLChuyn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQunLChuyn.setBounds(10, 0, 257, 34);
		contentPane.add(lblQunLChuyn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 29, 603, 458);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Danh sách", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("TÌM KIẾM");
		lblNewLabel.setBounds(10, 11, 116, 14);
		panel.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 25, 578, 42);
		panel.add(panel_3);
		panel_3.setLayout(null);

		txtSeach = new JTextField();
		txtSeach.setBounds(10, 11, 459, 23);
		panel_3.add(txtSeach);
		txtSeach.setColumns(10);

		JButton btnSeach = new JButton("Tìm");
		btnSeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadList();
				if (txtSeach.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã người học");
					return;
				}
				for (NguoiHoc nguoiHoc : list) {
					if (txtSeach.getText().equalsIgnoreCase(nguoiHoc.getMaNH())) {
						hienthi(list.indexOf(nguoiHoc));
						txtSeach.setText("");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "người học không tồn  tại");
			}
		});
		btnSeach.setBounds(479, 10, 89, 23);
		panel_3.add(btnSeach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 578, 325);
		panel.add(scrollPane);

		tblHocVien = new JTable();
		tblHocVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = tblHocVien.getSelectedRow();
				index = select;
				hienthi(select);
			}
		});
		scrollPane.setViewportView(tblHocVien);
		model.addColumn("MÃ NH");
		model.addColumn("HỌ VÀ TÊN");
		model.addColumn("GIỚI TÍNH");
		model.addColumn("NGÀY SINH");
		model.addColumn("ĐIỆN THOẠI");
		model.addColumn("EMAIL");
		model.addColumn("MÃ NV");
		model.addColumn("NGÀY ĐK");
		tblHocVien.setModel(model);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Cập nhật", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 598, 426);
		panel_1.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("Mã Người Học");
		lblNewLabel_2.setBounds(10, 11, 227, 14);
		panel_2.add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(10, 36, 578, 26);
		panel_2.add(txtId);

		JLabel lblNewLabel_1_2 = new JLabel("Họ và tên");
		lblNewLabel_1_2.setBounds(10, 73, 227, 14);
		panel_2.add(lblNewLabel_1_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(10, 98, 578, 26);
		panel_2.add(txtName);

		JLabel lblNewLabel_1_1_2 = new JLabel("Giới tính");
		lblNewLabel_1_1_2.setBounds(10, 135, 227, 14);
		panel_2.add(lblNewLabel_1_1_2);

		rdoNam.setBounds(10, 156, 62, 23);
		panel_2.add(rdoNam);

		rdoNu.setBounds(74, 156, 69, 23);
		panel_2.add(rdoNu);

		gr.add(rdoNu);
		gr.add(rdoNam);

		JLabel lblNewLabel_1_1_1_4 = new JLabel("Điện thoại");
		lblNewLabel_1_1_1_4.setBounds(10, 194, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_4);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(10, 219, 282, 26);
		panel_2.add(txtSoDienThoai);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(306, 219, 282, 26);
		panel_2.add(txtEmail);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ngày sinh");
		lblNewLabel_1_1_1_1_1.setBounds(306, 135, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Địa chỉ email");
		lblNewLabel_1_1_1_2_1.setBounds(306, 194, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_2_1);

		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("Ghi chú");
		lblNewLabel_1_1_1_3_1.setBounds(10, 256, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_3_1);

		JScrollPane txtGhiChu = new JScrollPane();
		txtGhiChu.setBounds(10, 281, 578, 51);
		panel_2.add(txtGhiChu);

		txtGhiChu.setViewportView(txtChuThich);

		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd();
			}
		});
		btnAdd.setBounds(10, 355, 73, 23);
		panel_2.add(btnAdd);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdate();
			}
		});
		btnUpdate.setBounds(93, 355, 73, 23);
		panel_2.add(btnUpdate);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXoa();
			}
		});
		btnDelete.setBounds(176, 355, 73, 23);
		panel_2.add(btnDelete);

		JButton btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMoi();
			}
		});
		btnNew.setBounds(262, 355, 73, 23);
		panel_2.add(btnNew);

		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.size() > 0) {
					index = list.size() - 1;
					hienthi(index);
				}
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnLast.setBounds(541, 356, 47, 23);
		panel_2.add(btnLast);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < (list.size() - 1)) {
					index++;
					hienthi(index);
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNext.setBounds(486, 356, 47, 23);
		panel_2.add(btnNext);

		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					hienthi(index);
				}
			}
		});
		btnBlack.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnBlack.setBounds(429, 356, 47, 23);
		panel_2.add(btnBlack);

		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.size() > 0) {
					index = 0;
					hienthi(0);
				}
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnFirst.setBounds(372, 356, 47, 23);
		panel_2.add(btnFirst);

		dateChooser.setBounds(306, 157, 282, 22);
		panel_2.add(dateChooser);
		fillTable();
	}

	protected void btnAdd() {
		vadidate();
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		NguoiHocDao ngd = new NguoiHocDao();
		NguoiHoc ng = new NguoiHoc();
		ng.setMaNH(txtId.getText());
		ng.setHoTen(txtName.getText());
		if (rdoNam.isSelected()) {
			ng.setGioiTinh(true);
		} else {
			ng.setGioiTinh(false);
		}
		SimpleDateFormat sbl = new SimpleDateFormat("yyyy-MM-dd");
		String date = sbl.format(dateChooser.getDate());
		Date time = null;
		try {
			time = sbl.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ng.setNgaySinh(time);
		ng.setSoDT(txtSoDienThoai.getText());
		ng.setEmail(txtEmail.getText());
		ng.setMaNV(Login.Manv);
		ng.setGhiChu(txtChuThich.getText());
		Date now = new Date();
		ng.setNgayDK(now);
		ngd.insert(ng);
		JOptionPane.showMessageDialog(null, "thêm mới thành công");
		fillTable();
		
	}

	protected void btnUpdate() {
		vadidateSua();
		if(sb.length()>0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		NguoiHocDao ngd = new NguoiHocDao();
		NguoiHoc ng = new NguoiHoc();
		ng.setMaNH(txtId.getText());
		ng.setHoTen(txtName.getText());
		if(rdoNam.isSelected()) {
			ng.setGioiTinh(true);
		}else {
			ng.setGioiTinh(false);
		}
		SimpleDateFormat sbl = new SimpleDateFormat("yyyy-MM-dd");
		String date = sbl.format(dateChooser.getDate());
		Date time = null;
		try {
			time = sbl.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ng.setNgaySinh(time);
		ng.setSoDT(txtSoDienThoai.getText());
		ng.setEmail(txtEmail.getText());
		ng.setMaNV(Login.Manv);
		ng.setGhiChu(txtChuThich.getText());
		Date now = new Date();
		ng.setNgayDK(now);
		ngd.update(ng);
		JOptionPane.showMessageDialog(null, "sửa thành công");
		fillTable();	
	}	
	protected void btnXoa() {
		NguoiHocDao ngd = new NguoiHocDao();
		String ma = txtId.getText();
		ngd.delete(ma);
		JOptionPane.showMessageDialog(null, "Xóa Thành Công");
		fillTable();
	}

	protected void btnMoi() {
		txtId.setText("");
		txtName.setText("");
		txtChuThich.getText();
		txtEmail.setText("");
		txtSoDienThoai.setText("");
		txtChuThich.setText("");
		gr.clearSelection();
		dateChooser.setCalendar(null);
		btnAdd.setEnabled(true);
		txtId.setEditable(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnFirst.setEnabled(false);
		btnLast.setEnabled(false);
		btnBlack.setEnabled(false);
		btnNext.setEnabled(false);

	}

	public void hienthi(int i) {
		txtId.setText(list.get(i).getMaNH());
		txtName.setText(list.get(i).getHoTen());
		if (list.get(i).getGioiTinh() == true) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
		dateChooser.setDate(list.get(i).getNgaySinh());
		txtSoDienThoai.setText(list.get(i).getSoDT());
		txtEmail.setText(list.get(i).getEmail());
		txtChuThich.setText(list.get(i).getGhiChu());
		btnAdd.setEnabled(false);
		txtId.setEditable(false);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
		tblHocVien.setRowSelectionInterval(i, i);
		btnFirst.setEnabled(true);
		btnLast.setEnabled(true);
		btnBlack.setEnabled(true);
		btnNext.setEnabled(true);
	}
	public void vadidateSua() {
		sb.setLength(0);
		if (txtName.getText().isBlank()) {
			sb.append("bạn chưa nhập tên người hoc \n");
		}
		if (rdoNam.isSelected() == false && rdoNu.isSelected() == false) {
			sb.append("bạn chưa chọn giới tính\n");
		}
		if (dateChooser.getCalendar() == null) {
			sb.append("bạn chưa chọn ngày sinh \n");
		}
		String sdt = "0\\d{9}";
		if (txtSoDienThoai.getText().isBlank()) {
			sb.append("bạn chưa nhập số điện thoại \n");
		} else if (!txtSoDienThoai.getText().matches(sdt)) {
			sb.append("số điện thoại không đúng \n");
		} 
		String email = "\\w+@\\w+\\.\\w+";
		if (txtEmail.getText().isBlank()) {
			sb.append("bạn chưa nhập Email\n");
		} else if (!txtEmail.getText().matches(email)) {
			sb.append("email không đúng định dạng\n");
		} 
		if (txtChuThich.getText().isBlank()) {
			sb.append("bạn chưa nhập ghi chú");
		}
	}
	public void vadidate() {
		sb.setLength(0);
		if (txtId.getText().isBlank()) {
			sb.append("bạn chưa nhập mã người học \n");
		} else {
			for (NguoiHoc ng : list) {
				if (txtId.getText().equalsIgnoreCase(ng.getMaNH())) {
					sb.append("mã người học đã tồn tại \n");
				}
			}
		}
		if (txtName.getText().isBlank()) {
			sb.append("bạn chưa nhập tên người hoc \n");
		}
		if (rdoNam.isSelected() == false && rdoNu.isSelected() == false) {
			sb.append("bạn chưa chọn giới tính\n");
		}
		if (dateChooser.getCalendar() == null) {
			sb.append("bạn chưa chọn ngày sinh \n");
		}
		String sdt = "0\\d{9}";
		if (txtSoDienThoai.getText().isBlank()) {
			sb.append("bạn chưa nhập số điện thoại \n");
		} else if (!txtSoDienThoai.getText().matches(sdt)) {
			sb.append("số điện thoại không đúng \n");
		} else {
			for (NguoiHoc x : list) {
				if (txtSoDienThoai.getText().equalsIgnoreCase(x.getSoDT())) {
					sb.append("số điện thoại Đã Tồn Tại\n");
				}
			}
		}
		String email = "\\w+@\\w+\\.\\w+";
		if (txtEmail.getText().isBlank()) {
			sb.append("bạn chưa nhập Email\n");
		} else if (!txtEmail.getText().matches(email)) {
			sb.append("email không đúng định dạng\n");
		} else {
			for (NguoiHoc x : list) {
				if (txtEmail.getText().equalsIgnoreCase(x.getEmail())) {
					sb.append("Email Sinh Viên Đã Tồn Tại\n");
				}
			}
		}
		if (txtChuThich.getText().isBlank()) {
			sb.append("bạn chưa nhập ghi chú");
		}
	}
}
