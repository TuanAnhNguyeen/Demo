package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Dao.NhanVienDao;
import EntityClass.NhanVien;

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {
	JTextField txtUsername;
	JTextField txtPassword;
	static ChangePassword frame = new ChangePassword();
	String change = "Change password";
	JButton btnLogin = new JButton("Xác nhận");
	StringBuilder error = new StringBuilder();
	boolean check = false;
	public static String vaiTro;
	List<NhanVien> list = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setTitle("Đổi mật khẩu");
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
	Action loginAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	private JButton btnCancel;
	private JTextField txtComfirm;

	public ChangePassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 310);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername = new JTextField("");
		txtUsername.setForeground(Color.black);
		txtUsername.setBackground(Color.white);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(Color.WHITE));
		txtUsername.setBounds(242, 52, 185, 27);

		contentPane.add(txtUsername);

		txtPassword = new JTextField();
		txtPassword.setForeground(Color.black);
		txtPassword.setBackground(Color.white);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPassword.setColumns(10);
		txtPassword.setBorder(new LineBorder(Color.WHITE));
		txtPassword.setBounds(242, 92, 185, 27);
		contentPane.add(txtPassword);

		JLabel lblLogin = new JLabel("ĐỔI MẬT KHẨU");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblLogin.setBounds(145, 11, 257, 40);
		contentPane.add(lblLogin);

		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.clear();
				StringBuilder sb = new StringBuilder();
				NhanVienDao nhanvien = new NhanVienDao();
				list = nhanvien.selectAll();

				if (txtUsername.getText().isBlank()) {
					sb.append("Bạn Chưa Nhập User\n");
				}
				if (txtPassword.getText().isBlank()) {
					sb.append("Bạn Chưa Nhập Pass\n");
				}
				NhanVien nv = checkUser(txtUsername.getText(), txtPassword.getText());
				if (sb.length() == 0) {
					if (nv == null) {
						sb.append("Tài Khoản không đúng hoặc kiểm tra lại mật khẩu");
					}
				}
				if(txtComfirm.getText().isBlank()) {
					sb.append("bạn chưa nhập mật khẩu mới\n");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(null, sb.toString());
					return;
				}
				NhanVienDao nhanViendao = new NhanVienDao();
				NhanVien nhanVien = new NhanVien();
				for (NhanVien nhanVien2 : list) {
					if(nhanVien2.getMaNV().equalsIgnoreCase(txtUsername.getText())) {
						nhanVien.setHoTen(nhanVien2.getHoTen());
						nhanVien.setVaiTro(nhanVien2.isVaiTro());
						nhanVien.setMatKhau(txtComfirm.getText());
						nhanVien.setMaNV(txtUsername.getText());
						break;
					}
				}
				nhanViendao.update(nhanVien);
				JOptionPane.showMessageDialog(null, "thay đổi mật khẩu thành công");
			}
		});
		btnLogin.setBorder(new LineBorder(Color.DARK_GRAY));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(242, 179, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblUser = new JLabel("New label");
		lblUser.setIcon(new ImageIcon("C:\\udpm\\Image\\user1.png"));
		lblUser.setBounds(13, 11, 122, 191);
		contentPane.add(lblUser);


		txtUsername.addActionListener(loginAction);
		txtPassword.addActionListener(loginAction);

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBounds(141, 176, 95, 29);
				btnLogin.setBorder(new LineBorder(new Color(64, 64, 64), 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBounds(144, 179, 89, 23);
				btnLogin.setBorder(new LineBorder(new Color(64, 64, 64), 1));
			}
		});

		btnLogin.setContentAreaFilled(false);

		btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword.this.dispose();
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancel.setBounds(240, 176, 95, 29);
				btnCancel.setBorder(new LineBorder(new Color(64, 64, 64), 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancel.setBounds(243, 179, 89, 23);
				btnCancel.setBorder(new LineBorder(new Color(64, 64, 64), 1));
			}
		});

		btnCancel.setForeground(Color.BLACK);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorder(new LineBorder(Color.DARK_GRAY));
		btnCancel.setBackground(Color.BLACK);
		btnCancel.setBounds(338, 179, 89, 23);
		contentPane.add(btnCancel);

		txtComfirm = new JTextField("");
		txtComfirm.setForeground(Color.BLACK);
		txtComfirm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtComfirm.setColumns(10);
		txtComfirm.setBorder(new LineBorder(Color.WHITE));
		txtComfirm.setBackground(Color.WHITE);
		txtComfirm.setBounds(242, 139, 185, 27);
		contentPane.add(txtComfirm);
		
		JLabel lblNewLabel = new JLabel("Tai Khoản");
		lblNewLabel.setBounds(145, 56, 85, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1.setBounds(145, 96, 85, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật Khẩu Mới");
		lblNewLabel_2.setBounds(145, 143, 85, 16);
		contentPane.add(lblNewLabel_2);


	}

	protected NhanVien checkUser(String a, String b) {
		for (NhanVien nv : list) {
			if (a.equalsIgnoreCase(nv.getMaNV())
					&& b.equalsIgnoreCase(nv.getMatKhau())) {
				return nv;
			}
		}
		return null;
	}
}