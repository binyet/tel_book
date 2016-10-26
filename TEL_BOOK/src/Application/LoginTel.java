package Application;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LoginTel extends JFrame{
	private static final String URL = "jdbc:mysql://123.207.165.51:3306/TELBOOK?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	public static String account;
	public static String password;
	private static JFrame app;
	private static Container c;

	public static void show_login(){
		

		app = new JFrame("登录");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(300, 180);
		app.setResizable(false);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
		app.setLocation(w, h);
		c = app.getContentPane();
		c.setLayout(null);// 采用布局

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(app);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		
		JTextField[] t1 = {new JTextField("账号：", 8), new JTextField("", 15), new JTextField("密码：", 8),
				new JPasswordField("", 15)};
		
		t1[0].setBounds(10, 10, 80, 30);t1[0].setFont(new Font(null, 0, 15));
		t1[1].setBounds(95, 10, 120, 30);t1[1].setFont(new Font(null, 0, 15));
		t1[2].setBounds(10, 45, 80, 30);t1[2].setFont(new Font(null, 0, 15));
		t1[3].setBounds(95, 45, 120, 30);t1[3].setFont(new Font(null, 0, 15));
		for (int i = 0; i < t1.length; i++) {
			c.add(t1[i]);
			if (i % 2 == 0)
				t1[i].setEditable(false);
		}
		JButton bt_log = new JButton("登录");
		bt_log.setBounds(30, 80, 70, 30);
		c.add(bt_log);
		JButton bt_reg = new JButton("注册");
		bt_reg.setBounds(105, 80, 70, 30);
		c.add(bt_reg);
		bt_log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Class.forName("com.mysql.jdbc.Driver");// 数据库连接
					Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from test");
					
					int flag = 0;//判断账户是否存在
					while (rs.next()) {
						if(rs.getString("account").equals(t1[1].getText())){
							flag = 1;
							if(rs.getString("password").equals(t1[3].getText())){
								flag = 1;
								
								account = t1[1].getText();
								
								app.dispose();
								app_main.main_tel();
							}
							else{
								JOptionPane.showMessageDialog(null, "密码错误");
							}
						}
					}
					
					if(flag == 0){
						JOptionPane.showMessageDialog(null, "账户不存在");
					}
					rs.last();
					
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		bt_reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Register.register();
			}
		});
		
		t1[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Class.forName("com.mysql.jdbc.Driver");// 数据库连接
					Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from test");
					
					int flag = 0;//判断账户是否存在
					while (rs.next()) {
						if(rs.getString("account").equals(t1[1].getText())){
							flag = 1;
							if(rs.getString("password").equals(t1[3].getText())){
								flag = 1;
								account = t1[1].getText();
								app.dispose();
								app_main.main_tel();
							}
							else{
								JOptionPane.showMessageDialog(null, "密码错误");
							}
						}
					}
					
					if(flag == 0){
						JOptionPane.showMessageDialog(null, "账户不存在");
					}
					rs.last();
					
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		app.setVisible(true);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		show_login();
	}

}
