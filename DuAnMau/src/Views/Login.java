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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Dao.NhanVienDao;
import EntityClass.NhanVien;

import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassworld;
	static Login frame = new Login();
	JLabel lblChange = new JLabel();
	String change = "Change password";
	JButton btnLogin = new JButton("Đăng nhập");
	List<NhanVien> list = new ArrayList();
	boolean check = false;
	public static boolean vaiTro;
	static String Manv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setTitle("Login");
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

	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 376, 262);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername = new JTextField("");
		txtUsername.setBounds(145, 52, 178, 27);
		txtUsername.setForeground(Color.black);
		txtUsername.setBackground(Color.white);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(Color.WHITE));

		contentPane.add(txtUsername);

		txtPassworld = new JPasswordField();
		txtPassworld.setBounds(145, 90, 178, 27);
		txtPassworld.setForeground(Color.black);
		txtPassworld.setBackground(Color.white);
		txtPassworld.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPassworld.setColumns(10);
		txtPassworld.setBorder(new LineBorder(Color.WHITE));
		contentPane.add(txtPassworld);

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
		lblLogin.setBounds(145, 11, 257, 40);
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(lblLogin);
		btnLogin.setBounds(144, 179, 89, 23);

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
				if (txtPassworld.getText().isBlank()) {
					sb.append("Bạn Chưa Nhập Pass");
				}
				NhanVien nv = checkUser(txtUsername.getText(), txtPassworld.getText());
				if(sb.length()==0) {
					if(nv==null) {
						sb.append("Tài Khoản không đúng hoặc kiểm tra lại mật khẩu");
					}
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(null, sb.toString());
					return;
				}
				Manv = txtUsername.getText();
				for (NhanVien nhanVien2 : list) {
					if (nhanVien2.getMaNV().equalsIgnoreCase(Manv)) {
						vaiTro = nhanVien2.isVaiTro();
					}
				}
				QLDT add = new QLDT();
				add.setVisible(true);
				add.setLocationRelativeTo(null);
				Login.this.dispose();

			}
		});
		btnLogin.setBorder(new LineBorder(Color.DARK_GRAY));
		btnLogin.setBackground(Color.BLACK);
		contentPane.add(btnLogin);

		JLabel lblUser = new JLabel("New label");
		lblUser.setBounds(13, 11, 122, 191);
		lblUser.setIcon(new ImageIcon("C:\\udpm\\Image\\user1.png"));
		contentPane.add(lblUser);

		lblChange.setBounds(145, 128, 141, 14);

		lblChange.setText(change);
		lblChange.addMouseListener(new MouseAdapter() {
			private ChangePassword ChangePassword;

			@Override
			public void mouseEntered(MouseEvent e) {
				lblChange.setText("<html><p style=\"text-decoration: underline;\">" + change + "</p></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblChange.setText("<html><p style=\"text-decoration: none;\">" + change + "</p></html>");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePassword changePassworld = new ChangePassword();
				changePassworld.setVisible(true);
				changePassworld.setLocationRelativeTo(null);

			}
		});
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblChange.setForeground(Color.BLUE);
		contentPane.add(lblChange);

		txtUsername.addActionListener(loginAction);
		txtPassworld.addActionListener(loginAction);

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

		btnCancel = new JButton("Kết thúc");
		btnCancel.setBounds(234, 179, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
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
		contentPane.add(btnCancel);
	}
	public NhanVien checkUser(String a , String b) {
		for (NhanVien nv : list) {
			if (a.equalsIgnoreCase(nv.getMaNV())
					&& b.equalsIgnoreCase(nv.getMatKhau())) {
				return nv;
			}
		}
		return null;
	}
}
