package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class QLDT extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLDT frame = new QLDT();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public QLDT() {
		setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setFocusable(true);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 599, 745, 24);
		panel.setBackground(new Color(169, 169, 169));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hệ thống quản lý đào tạo");
		lblNewLabel.setIcon(new ImageIcon("C:\\udpm\\Image\\trogiup2.png"));
		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setBounds(10, 0, 236, 24);
		panel.add(lblNewLabel);

		JLabel lblClock = new JLabel("New label");
		lblClock.setIcon(new ImageIcon("C:\\udpm\\Image\\clock.png"));
		lblClock.setBounds(640, 0, 95, 24);
		panel.add(lblClock);
		Clock clock = new Clock(lblClock);
		clock.start();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 745, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ thống");
		menuBar.add(mnNewMenu);

		JMenuItem mntmDoiMK = new JMenuItem("Đổi mật khẩu");
		mntmDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword doimatkhau = new ChangePassword();
//				doimatkhau.txtUsername.setText(Login.Manv);
				doimatkhau.txtUsername.setText("NV1");
				doimatkhau.setVisible(true);
				doimatkhau.setLocationRelativeTo(null);
			}
		});
		mntmDoiMK.setIcon(new ImageIcon("C:\\udpm\\Image\\doimk.png"));
		mnNewMenu.add(mntmDoiMK);

		JMenuItem mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		mntmDangXuat.setIcon(new ImageIcon("C:\\udpm\\Image\\dangxuat1.png"));
		mntmDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmDangXuat);

		JMenuItem mntmThoat = new JMenuItem("Thoát");
		mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLDT.this.dispose();
			}
		});
		mntmThoat.setIcon(new ImageIcon("C:\\udpm\\Image\\ketthuc1.png"));
		mntmThoat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mnNewMenu.add(mntmThoat);

		JMenu mnNewMenu_1 = new JMenu("Quản lý");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmChuyenDe = new JMenuItem("Chuyển đề");
		mntmChuyenDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		mntmChuyenDe.setIcon(new ImageIcon("C:\\udpm\\Image\\chuyende1.png"));
		mnNewMenu_1.add(mntmChuyenDe);

		JMenuItem mntmKhoaHoc = new JMenuItem("Khóa học");
		mntmKhoaHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		mntmKhoaHoc.setIcon(new ImageIcon("C:\\udpm\\Image\\khoahoc1.png"));
		mnNewMenu_1.add(mntmKhoaHoc);

		JMenuItem mntmNguoiHoc = new JMenuItem("Người học");
		mntmNguoiHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
		mntmNguoiHoc.setIcon(new ImageIcon("C:\\udpm\\Image\\nguoihoc1.png"));
		mnNewMenu_1.add(mntmNguoiHoc);

		JMenuItem mntmHocVien = new JMenuItem("Học viên");
		mntmHocVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmHocVien.setIcon(new ImageIcon("C:\\udpm\\Image\\hocvien1.png"));
		mnNewMenu_1.add(mntmHocVien);

		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);

		JMenuItem mntmNhanVien = new JMenuItem("Nhân viên");
		mntmNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Login.vaiTro == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền ở chức năng này");
				} else {
				QuanLyNhanVien nhanVien = new QuanLyNhanVien();
				close();
				desktopPane.add(nhanVien);
				nhanVien.setVisible(true);
				}
			}
		});
		mntmNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
		mntmNhanVien.setIcon(new ImageIcon("C:\\udpm\\Image\\nhanvien.png"));
		mnNewMenu_1.add(mntmNhanVien);

		JMenu mnNewMe = new JMenu("Thống kê");
		menuBar.add(mnNewMe);

		JMenuItem mntmBangDiem = new JMenuItem("Bảng điểm");
		mntmBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe thongKe = new ThongKe();
				close();
				desktopPane.add(thongKe);
				thongKe.setVisible(true);
			}
		});
		mntmBangDiem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_MASK));
		mntmBangDiem.setIcon(new ImageIcon("C:\\udpm\\Image\\bangdiem.png"));
		mnNewMe.add(mntmBangDiem);

		JSeparator separator_1 = new JSeparator();
		mnNewMe.add(separator_1);

		JMenuItem mntmSLNguoiHoc = new JMenuItem("Lượng người học");
		mntmSLNguoiHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe thongKe = new ThongKe();
				close();
				desktopPane.add(thongKe);
				thongKe.setVisible(true);
				thongKe.tabbedPane.setSelectedIndex(1);
			}
		});
		mntmSLNguoiHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_MASK));
		mntmSLNguoiHoc.setIcon(new ImageIcon("C:\\udpm\\Image\\sinhvien.png"));
		mnNewMe.add(mntmSLNguoiHoc);

		JMenuItem mntmDiemChuyenDe = new JMenuItem("Điểm chuyên đề");
		mntmDiemChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe thongKe = new ThongKe();
				close();
				desktopPane.add(thongKe);
				thongKe.setVisible(true);
				thongKe.tabbedPane.setSelectedIndex(2);
			}
		});
		mntmDiemChuyenDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_MASK));
		mntmDiemChuyenDe.setIcon(new ImageIcon("C:\\udpm\\Image\\diemchuyende.png"));
		mnNewMe.add(mntmDiemChuyenDe);

		JMenuItem mntmDoanhThu = new JMenuItem("Doanh thu");
		mntmDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Login.vaiTro == true) {
					ThongKe thongKe = new ThongKe();
					close();
					desktopPane.add(thongKe);
					thongKe.setVisible(true);
					thongKe.tabbedPane.setSelectedIndex(3);
				}else {
					JOptionPane.showMessageDialog(null, "bạn không có quyền truy cấp vào chức năng này");
				}
			}
		});
		mntmDoanhThu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.SHIFT_MASK));
		mntmDoanhThu.setIcon(new ImageIcon("C:\\udpm\\Image\\doanhthu.png"));
		mnNewMe.add(mntmDoanhThu);

		JMenu mnNewMenu_3 = new JMenu("Trợ giúp");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Hướng dẫn sử dụng");
		mntmNewMenuItem_12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmNewMenuItem_12.setIcon(new ImageIcon("C:\\udpm\\Image\\hdsd.png"));
		mnNewMenu_3.add(mntmNewMenuItem_12);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu_3.add(separator_2);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Giới thiệu sản phẩm");
		mntmNewMenuItem_13.setIcon(new ImageIcon("C:\\udpm\\Image\\trogiup2.png"));
		mnNewMenu_3.add(mntmNewMenuItem_13);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(0, 22, 745, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 12, 56);
		panel_1.add(toolBar);

		JLabel lblNewLabel_1 = new JLabel("Đăng xuất");
		lblNewLabel_1.setBounds(22, 33, 62, 16);

		JButton btnDangXuat = new JButton("");
		btnDangXuat.setIcon(new ImageIcon("C:\\udpm\\Image\\dangxuat.png"));
		btnDangXuat.setBorder(null);
		btnDangXuat.setBounds(25, 5, 49, 49);
		btnDangXuat.setContentAreaFilled(false);

		panel_1.add(lblNewLabel_1);
		panel_1.add(btnDangXuat);

		JButton btnKetThuc = new JButton("");
		btnKetThuc.setIcon(new ImageIcon("C:\\udpm\\Image\\ketthuc.png"));
		btnKetThuc.setContentAreaFilled(false);
		btnKetThuc.setBorder(null);
		btnKetThuc.setBounds(94, 5, 49, 49);
		panel_1.add(btnKetThuc);

		JButton btnChuyenDe = new JButton("");
		btnChuyenDe.setIcon(new ImageIcon("C:\\udpm\\Image\\chuyende.png"));
		btnChuyenDe.setContentAreaFilled(false);
		btnChuyenDe.setBorder(null);
		btnChuyenDe.setBounds(161, 5, 49, 49);
		panel_1.add(btnChuyenDe);

		JLabel lblNewLabel_2 = new JLabel("Kết thúc");
		lblNewLabel_2.setBounds(94, 33, 49, 16);
		panel_1.add(lblNewLabel_2);

		JButton btnNguoiHoc = new JButton("");
		btnNguoiHoc.setIcon(new ImageIcon("C:\\udpm\\Image\\nguoihoc.png"));
		btnNguoiHoc.setContentAreaFilled(false);
		btnNguoiHoc.setBorder(null);
		btnNguoiHoc.setBounds(241, 5, 49, 49);
		panel_1.add(btnNguoiHoc);

		JButton btnKhoaHoc = new JButton("");
		btnKhoaHoc.setIcon(new ImageIcon("C:\\udpm\\Image\\khoahoc.png"));
		btnKhoaHoc.setContentAreaFilled(false);
		btnKhoaHoc.setBorder(null);
		btnKhoaHoc.setBounds(313, 5, 49, 49);
		panel_1.add(btnKhoaHoc);

		JButton btnHocVien = new JButton("");
		btnHocVien.setIcon(new ImageIcon("C:\\udpm\\Image\\hocvien.png"));
		btnHocVien.setContentAreaFilled(false);
		btnHocVien.setBorder(null);
		btnHocVien.setBounds(385, 5, 49, 49);
		panel_1.add(btnHocVien);

		JButton btnTroGiup = new JButton("");
		btnTroGiup.setIcon(new ImageIcon("C:\\udpm\\Image\\trogiup.png"));
		btnTroGiup.setContentAreaFilled(false);
		btnTroGiup.setBorder(null);
		btnTroGiup.setBounds(457, 5, 49, 49);
		panel_1.add(btnTroGiup);

		JLabel lblNewLabel_1_1 = new JLabel("Chuyên đề");
		lblNewLabel_1_1.setBounds(158, 33, 73, 16);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Người học");
		lblNewLabel_1_2.setBounds(241, 33, 62, 16);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Khóa học");
		lblNewLabel_1_3.setBounds(316, 33, 62, 16);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Học viên");
		lblNewLabel_1_4.setBounds(389, 33, 62, 16);
		panel_1.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Trợ giúp");
		lblNewLabel_1_5.setBounds(459, 33, 62, 16);
		panel_1.add(lblNewLabel_1_5);

		desktopPane.setBounds(10, 91, 719, 507);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new CardLayout(0, 0));

		btnChuyenDe.addActionListener(openChuyenDe);
		btnDangXuat.addActionListener(null);
		btnHocVien.addActionListener(openHocVien);
		btnKetThuc.addActionListener(null);
		btnKhoaHoc.addActionListener(openKhoaHoc);
		btnNguoiHoc.addActionListener(openNguoiHoc);

		mntmKhoaHoc.addActionListener(openKhoaHoc);
		mntmChuyenDe.addActionListener(openChuyenDe);
		mntmHocVien.addActionListener(openHocVien);
		mntmNguoiHoc.addActionListener(openNguoiHoc);
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		btnKetThuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLDT.this.dispose();
			}
		});
		quanlihocvien();
	}

	ActionListener openKhoaHoc = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuanLyKhoaHoc khoaHoc = new QuanLyKhoaHoc();
			close();
			desktopPane.add(khoaHoc);
			khoaHoc.setVisible(true);
		}
	};

	public void close() {
		try {
			desktopPane.getAllFrames()[0].setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	ActionListener openChuyenDe = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuanLyChuyenDe chuyenDe = new QuanLyChuyenDe();
			close();
			desktopPane.add(chuyenDe);
			chuyenDe.setVisible(true);
		}
	};

	ActionListener openHocVien = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			quanlihocvien();
		}
	};
    public void quanlihocvien() {
    	QuanLyHocVien hocVien = new QuanLyHocVien();
		close();
		desktopPane.add(hocVien);
		hocVien.setVisible(true);
    };
	ActionListener openNguoiHoc = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuanLyNguoiHoc nguoiHoc = new QuanLyNguoiHoc();
			close();
			desktopPane.add(nguoiHoc);
			nguoiHoc.setVisible(true);
		}
	};

	public void dangXuat() {
		Login login = new Login();
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		QLDT.this.dispose();
	}
}
