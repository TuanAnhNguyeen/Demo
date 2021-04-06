package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Dao.ChuyenDeDao;
import EntityClass.ChuyenDe;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuanLyChuyenDe extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	List<ChuyenDe> list = new ArrayList();
	StringBuilder sb = new StringBuilder();
	JButton btnADD = new JButton("Thêm");
	JButton btnXoa = new JButton("Xóa");
	JButton btnMo = new JButton("Mới");
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtClock;
	private JTextField txtMoney;
	JTextArea txtGhiChu = new JTextArea();
	JButton btnSua = new JButton("Sửa");
	JLabel lblLogo = new JLabel("");
	JButton btnBlack = new JButton("<<");
	JButton btnNext = new JButton(">>");
	JButton btnLast = new JButton(">|");
	JButton btnFirst = new JButton("|<");
	String pathAnh = "";
	boolean check = true;
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyChuyenDe frame = new QuanLyChuyenDe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadList() {
		list.clear();
		ChuyenDeDao cd = new ChuyenDeDao();
		list = cd.selectAll();
	}

	public void fillTable() {
		model.setRowCount(0);
		loadList();
		for (ChuyenDe x : list) {
			String hocphi =(""+x.getHocPhi()).replace(".0", "");
			model.addRow(new Object[] { x.getMaCD(), x.getTenCD(), hocphi, x.getThoiLuong(), x.getHinh() });
		}
		btnADD.setEnabled(false);
		if (list.size() > 0) {
			hienThi(0);
		}
	}

	public QuanLyChuyenDe() {
		setTitle("Quản lý chuyên đề");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQunLChuyn = new JLabel("QUẢN LÝ CHUYÊN ĐỀ");
		lblQunLChuyn.setForeground(Color.BLUE);
		lblQunLChuyn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQunLChuyn.setBounds(10, 11, 257, 34);
		contentPane.add(lblQunLChuyn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 39, 602, 482);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("DANH SÁCH", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				hienThi(select);
				index = select;
			}
		});
		scrollPane.setViewportView(table);
		model.addColumn("MÃ CD");
		model.addColumn("TÊN CD");
		model.addColumn("HỌC PHÍ");
		model.addColumn("THỜI LƯỢNG");
		model.addColumn("HÌNH");
		table.setModel(model);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Cập nhật", null, scrollPane_1);

		JPanel panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hình logo");
		lblNewLabel.setBounds(10, 11, 115, 14);
		panel.add(lblNewLabel);
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "Image File (*.png)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".png");
						}
					}
				});
				if (file.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
					return;
				}
				File choose = file.getSelectedFile();
				pathAnh = choose.getPath();
				lblLogo.setIcon(icon(pathAnh));
				check = false;
			}
		});

		lblLogo.setIcon(new ImageIcon("C:\\udpm\\Image\\user.jpg"));
		lblLogo.setBackground(Color.DARK_GRAY);
		lblLogo.setBounds(15, 36, 153, 191);
		panel.add(lblLogo);

		JLabel lblMChuyn = new JLabel("Mã chuyên đề");
		lblMChuyn.setBounds(181, 11, 115, 14);
		panel.add(lblMChuyn);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(180, 30, 405, 26);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tên chuyên đề");
		lblNewLabel_2_1.setBounds(181, 67, 115, 14);
		panel.add(lblNewLabel_2_1);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(180, 87, 405, 26);
		panel.add(txtName);

		JLabel lblNewLabel_2_1_1 = new JLabel("Thời lượng (giờ)");
		lblNewLabel_2_1_1.setBounds(181, 124, 115, 14);
		panel.add(lblNewLabel_2_1_1);

		txtClock = new JTextField();
		txtClock.setColumns(10);
		txtClock.setBounds(180, 143, 405, 26);
		panel.add(txtClock);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Học phí");
		lblNewLabel_2_1_1_1.setBounds(181, 180, 115, 14);
		panel.add(lblNewLabel_2_1_1_1);

		txtMoney = new JTextField();
		txtMoney.setColumns(10);
		txtMoney.setBounds(180, 201, 405, 26);
		panel.add(txtMoney);

		JLabel lblMTiChuyn = new JLabel("Mô tải chuyên đề");
		lblMTiChuyn.setBounds(10, 238, 115, 14);
		panel.add(lblMTiChuyn);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 263, 575, 90);
		panel.add(scrollPane_2);

		scrollPane_2.setViewportView(txtGhiChu);

		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnADD();
			}
		});
		btnADD.setEnabled(false);
		btnADD.setBounds(10, 366, 73, 23);
		panel.add(btnADD);

		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSua();
			}
		});
		btnSua.setBounds(93, 366, 73, 23);
		panel.add(btnSua);

		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXoa();
			}
		});
		btnXoa.setBounds(176, 366, 73, 23);
		panel.add(btnXoa);

		btnMo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMo();
			}
		});
		btnMo.setBounds(259, 366, 73, 23);
		panel.add(btnMo);

		
		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					hienThi(index);
				}
			}
		});
		btnBlack.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnBlack.setBounds(426, 367, 47, 23);
		panel.add(btnBlack);

		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < (list.size() - 1)) {
					index++;
					hienThi(index);
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNext.setBounds(483, 366, 47, 23);
		panel.add(btnNext);

		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.size() > 0) {
					index = list.size() - 1;
					hienThi(index);
				}
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnLast.setBounds(538, 366, 47, 23);
		panel.add(btnLast);

		
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.size() > 0) {
					index = 0;
					hienThi(0);
				}
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnFirst.setBounds(369, 366, 47, 23);
		panel.add(btnFirst);
		fillTable();
	}

	protected void btnADD() {
		vadidate();
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		ChuyenDeDao cd = new ChuyenDeDao();
		ChuyenDe x = new ChuyenDe();
		x.setMaCD(txtId.getText());
		x.setTenCD(txtName.getText());
		x.setThoiLuong(Integer.parseInt(txtClock.getText()));
		x.setHocPhi(Double.parseDouble(txtMoney.getText()));
		x.setMoTa(txtGhiChu.getText());
		x.setHinh(pathAnh);
		cd.insert(x);
		JOptionPane.showMessageDialog(null, "Thêm Mới Thành Công");
		fillTable();
	}

	protected void btnSua() {
		sb.setLength(0);
		if (txtName.getText().isBlank()) {
			sb.append("bạn chưa nhập tên chuyên đề \n");
		}
		if (txtClock.getText().isBlank()) {
			sb.append("bạn chưa nhập thời lượng chuyên đề \n");
		} else {
			try {
				int clock = Integer.parseInt(txtClock.getText());
				if (clock <= 0) {
					sb.append("bạn phải nhập thời lượng lớn hơn 0 \n");
				}
			} catch (Exception e1) {
				sb.append("bạn nhập sai thời lượng \n");
			}
		}
		if (txtMoney.getText().isBlank()) {
			sb.append("bạn chưa nhập Học Phí chuyên đề \n");
		} else {
			try {
				double money = Double.parseDouble(txtMoney.getText());
				if (money <= 0) {
					sb.append("bạn phải nhập số tiền lớn hơn 0\n");
				}
			} catch (Exception e1) {
				sb.append("bạn nhập sai Học phí \n");
			}
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(null, sb.toString());
			return;
		}
		ChuyenDeDao cd = new ChuyenDeDao();
		ChuyenDe x = new ChuyenDe();
		x.setMaCD(txtId.getText());
		x.setTenCD(txtName.getText());
		x.setThoiLuong(Integer.parseInt(txtClock.getText()));
		x.setHocPhi(Double.parseDouble(txtMoney.getText()));
		x.setMoTa(txtGhiChu.getText());
		if (check == true) {
			for (ChuyenDe a : list) {
				if (txtId.getText().equalsIgnoreCase(a.getMaCD())) {
					pathAnh = a.getHinh();
					break;
				}
			}
		}
		x.setHinh(pathAnh);
		JOptionPane.showMessageDialog(null, "Sửa Thành Công");
		cd.update(x);
		fillTable();
		
	}

	protected void btnXoa() {
		if (txtId.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã chuyên đề");
			return;
		}
		ChuyenDeDao cd = new ChuyenDeDao();
		cd.delete(txtId.getText());
		JOptionPane.showMessageDialog(null, "Xóa thành công");
		fillTable();
	}
	

	protected void btnMo() {
		txtId.setText("");
		txtClock.setText("");
		txtGhiChu.setText("");
		txtMoney.setText("");
		txtName.setText("");
		lblLogo.setIcon(icon("C:\\udpm\\Image\\user.jpg"));
		btnADD.setEnabled(true);
		txtId.setEditable(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnFirst.setEnabled(false);
		btnLast.setEnabled(false);
		btnBlack.setEnabled(false);
		btnNext.setEnabled(false);

	}

	public void hienThi(int i) {
		loadList();
		txtId.setText(list.get(i).getMaCD());
		txtName.setText(list.get(i).getTenCD());
		txtClock.setText(String.valueOf(list.get(i).getThoiLuong()));
		txtMoney.setText(String.valueOf(list.get(i).getHocPhi()));
		txtGhiChu.setText(list.get(i).getMoTa());
		String a = list.get(i).getHinh();
		lblLogo.setIcon(icon(a));
		btnADD.setEnabled(false);
		check = true;
		txtId.setEditable(false);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		table.setRowSelectionInterval(i, i);
		btnFirst.setEnabled(true);
		btnLast.setEnabled(true);
		btnBlack.setEnabled(true);
		btnNext.setEnabled(true);
	}

	public ImageIcon icon(String a) {
		ImageIcon imgicon = new ImageIcon((new ImageIcon(a).getImage().getScaledInstance(lblLogo.getWidth(),
				lblLogo.getHeight(), Image.SCALE_SMOOTH)));
		return imgicon;
	}

	public void vadidate() {
		sb.setLength(0);
		if (txtId.getText().isBlank()) {
			sb.append("bạn chưa nhập ma chuyên đê \n");
		} else {
			loadList();
			for (ChuyenDe chuyenDe : list) {
				if (txtId.getText().equalsIgnoreCase(chuyenDe.getMaCD())) {
					sb.append("Mã chuyên đề đã tồn tại \n");
				}
			}
		}
		if (txtName.getText().isBlank()) {
			sb.append("bạn chưa nhập tên chuyên đề \n");
		}
		if (txtClock.getText().isBlank()) {
			sb.append("bạn chưa nhập thời lượng chuyên đề \n");
		} else {
			try {
				int clock = Integer.parseInt(txtClock.getText());
				if (clock <= 0) {
					sb.append("bạn phải nhập thời lượng lớn hơn 0 \n");
				}
			} catch (Exception e) {
				sb.append("bạn nhập sai thời lượng \n");
			}
		}
		if (txtMoney.getText().isBlank()) {
			sb.append("bạn chưa nhập Học Phí chuyên đề \n");
		} else {
			try {
				double money = Double.parseDouble(txtMoney.getText());
				if (money <= 0) {
					sb.append("bạn phải nhập số tiền lớn hơn 0\n");
				}
			} catch (Exception e) {
				sb.append("bạn nhập sai Học phí \n");
			}
		}
	}
}
