package Application;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Bt_Add {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tel_book?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "lubin";
	/**
	 * _NET表示网络端数据库
	 */
	private static final String URL_NET = "jdbc:mysql://123.207.165.51:3306/TELBOOK?useUnicode=true&characterEncoding=utf-8";
	private static final String USER_NET = "root";
	private static final String PASSWORD_NET = "123456";
	

	public static void Bt_add() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");// 数据库连接
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			
//			Class.forName("com.mysql.jdbc.Driver");// 数据库连接
			Connection conn_net = DriverManager.getConnection(URL_NET, USER_NET, PASSWORD_NET);
			Statement stmt_net = conn_net.createStatement();

			JFrame reg1 = new JFrame("添加联系人");
			reg1.setSize(320, 210);
			reg1.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg1.setLocation(w, h);
			Container con = reg1.getContentPane();
			con.setLayout(null);

			JTextField[] t1 = { new JTextField("姓名：", 8), new JTextField("", 15), new JTextField("手机号：", 8),
					new JTextField("", 15), new JTextField("部门：", 8), new JTextField("", 15) };
			t1[0].setBounds(10, 10, 80, 30);t1[0].setFont(new Font(null, 0, 15));
			t1[1].setBounds(95, 10, 120, 30);t1[1].setFont(new Font(null, 0, 15));
			t1[2].setBounds(10, 45, 80, 30);t1[2].setFont(new Font(null, 0, 15));
			t1[3].setBounds(95, 45, 120, 30);t1[3].setFont(new Font(null, 0, 15));
			t1[4].setBounds(10, 80, 80, 30);t1[4].setFont(new Font(null, 0, 15));
			t1[5].setBounds(95, 80, 120, 30);t1[5].setFont(new Font(null, 0, 15));
			for (int i = 0; i < t1.length; i++) {
				con.add(t1[i]);
				if (i % 2 == 0)
					t1[i].setEditable(false);
			}

			JButton bt_add = new JButton("添加");
			bt_add.setBounds(40, 120, 70, 30);bt_add.setFont(new Font(null,0,15));
			con.add(bt_add);
			JButton bt_cancel = new JButton("取消");
			bt_cancel.setBounds(120, 120, 70, 30);bt_cancel.setFont(new Font(null,0,15));
			con.add(bt_cancel);

			bt_add.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						int temp = 0;// 检测是否存在联系人
						ResultSet rs = stmt.executeQuery("select * from "+LoginTel.account);

						while (rs.next()) {
							if (rs.getString("姓名").equals(t1[1].getText())) {
								temp = 1;
								JOptionPane.showMessageDialog(null, "联系人已存在");
								break;
							}
						}
						if (temp == 0) {
							if (t1[1].getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "姓名不能为空");
							} else if (t1[3].getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "手机号不能为空");
							} else if (t1[5].getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "部门不能为空");
							} else {
								String sql = "insert into "+LoginTel.account+" values(" + "'" + t1[1].getText().trim() + "'," + "'"
										+ t1[3].getText().trim() + "'," + "'" + t1[5].getText().trim() + "')";
								//System.out.println(sql);
								if (stmt.executeUpdate(sql) == 1) {
									JOptionPane.showMessageDialog(null, "添加成功");
									reg1.dispose();
								}
							}
						}
						rs.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});

			
			bt_cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					reg1.dispose();
				}
			});
			
			JButton bt_up = new JButton("上传");
			bt_up.setBounds(220, 15, 70, 30);bt_up.setFont(new Font(null,0,15));
			con.add(bt_up);
			bt_up.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try{
						ResultSet rs1 = stmt.executeQuery("select * from "+LoginTel.account);
						
						int flag;
						while(rs1.next()){
							ResultSet rs_net = stmt_net.executeQuery("select * from "+LoginTel.account);
							flag = 0;//判断联系人是否存在
							while(rs_net.next()){
								if(rs1.getString("姓名").equals(rs_net.getString("姓名"))){
									flag = 1;
									break;
								}
							}
							if(flag == 0){
								String sql = "insert into "+LoginTel.account+" values(" + "'" + rs1.getString("姓名") + "'," + "'"
										+ rs1.getString("手机号") + "'," + "'" + rs1.getString("部门") + "')";
								
								if(stmt_net.executeUpdate(sql) == 1){
									JOptionPane.showMessageDialog(null, "联系人 "+rs1.getString("姓名")+" 上传成功");
								}
							}
						}
						rs1.close();
					}catch (Exception e1){
						e1.printStackTrace();
					}
				}
			});
			
			
			
			JButton bt_down = new JButton("下载");
			bt_down.setBounds(220, 55, 70, 30);bt_down.setFont(new Font(null,0,15));
			con.add(bt_down);
			bt_down.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try{
						ResultSet rs_net1 = stmt_net.executeQuery("select * from "+LoginTel.account);
						while(rs_net1.next()){
							ResultSet rs2 = stmt.executeQuery("select * from "+LoginTel.account);
							int flag = 0;//判断联系人是否存在
							while(rs2.next()){
								if(rs_net1.getString("姓名").equals(rs2.getString("姓名"))){
									flag = 1;
									break;
								}
							}
							if(flag == 0){
								String sql = "insert into "+LoginTel.account+" values(" + "'" + rs_net1.getString("姓名") + "'," + "'"
										+ rs_net1.getString("手机号") + "'," + "'" + rs_net1.getString("部门") + "')";
								
								if(stmt.executeUpdate(sql) == 1){
									JOptionPane.showMessageDialog(null, "联系人　"+rs_net1.getString("姓名")+"　下载成功");
								}
							}
						}
					}catch (Exception e1){
						e1.printStackTrace();
					}
				}
			});
			
			
			reg1.setVisible(true);
			reg1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
