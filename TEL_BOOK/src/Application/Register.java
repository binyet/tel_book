package Application;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register {
	private static final String URL = "jdbc:mysql://123.207.165.51:3306/TELBOOK?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	public static void register() {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");// 数据库连接
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);//远程数据库链接
			Statement stmt = conn.createStatement();
			
			Connection conn_local = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tel_book?useUnicode=true&characterEncoding=utf-8", 
					"root","lubin");
			Statement stmt_local = conn_local.createStatement();
			
			JFrame reg1 = new JFrame("添加联系人");
			reg1.setSize(320, 210);
			reg1.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg1.setLocation(w, h);
			Container con = reg1.getContentPane();
			con.setLayout(null);

			JTextField[] t1 = { new JTextField("账号：", 8), new JTextField("", 15), new JTextField("密码：", 8),
					new JPasswordField("", 15), new JTextField("确认密码：", 8), new JPasswordField("", 15) };
			t1[0].setBounds(10, 10, 90, 30);t1[0].setFont(new Font(null, 0, 15));
			t1[1].setBounds(105, 10, 120, 30);t1[1].setFont(new Font(null, 0, 15));
			t1[2].setBounds(10, 45, 90, 30);t1[2].setFont(new Font(null, 0, 15));
			t1[3].setBounds(105, 45, 120, 30);t1[3].setFont(new Font(null, 0, 15));
			t1[4].setBounds(10, 80, 90, 30);t1[4].setFont(new Font(null, 0, 15));
			t1[5].setBounds(105, 80, 120, 30);t1[5].setFont(new Font(null, 0, 15));
			for (int i = 0; i < t1.length; i++) {
				con.add(t1[i]);
				if (i % 2 == 0)
					t1[i].setEditable(false);
			}
			JButton bt_yes = new JButton("注册");
			bt_yes.setBounds(40, 120, 70, 30);bt_yes.setFont(new Font(null,0,15));
			con.add(bt_yes);
			JButton bt_cancel = new JButton("取消");
			bt_cancel.setBounds(120, 120, 70, 30);bt_cancel.setFont(new Font(null,0,15));
			con.add(bt_cancel);
			bt_yes.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						ResultSet rs = stmt.executeQuery("select * from test");
						int flag = 0;//判断账户是否存在
						while(rs.next()){
							if(rs.getString("account").equals(t1[1].getText())){
								flag = 1;
								JOptionPane.showMessageDialog(null, "账户已存在");
								break;
							}
						}
						if(flag == 0){
							if(t1[3].getText().equals(t1[5].getText())){
								rs.last();
								String sql = "create table "+t1[1].getText()+
										"(姓名 varchar(11),手机号 varchar(11),部门 varchar(11))";
								stmt.executeUpdate(sql);
								stmt_local.executeUpdate(sql);
//								rs.last();
								sql = "insert into test values(" + "'" + t1[1].getText().trim() + "'," + "'"
										+ t1[3].getText().trim()+"')";
								if(stmt.executeUpdate(sql)==1){
									JOptionPane.showMessageDialog(null, "注册成功");
									reg1.dispose();
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "输入的密码不相同");
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			bt_cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					reg1.dispose();
				}
			});
			
			
			reg1.setVisible(true);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
