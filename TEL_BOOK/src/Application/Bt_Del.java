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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Bt_Del {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tel_book?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "lubin";

	public static void Bt_del() {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);//数据库连接
			Statement stmt = conn.createStatement();

			JFrame reg1 = new JFrame("删除联系人");
			reg1.setSize(240, 150);
			reg1.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg1.setLocation(w, h);
			Container con = reg1.getContentPane();
			con.setLayout(null);

			JTextField[] t1 = { new JTextField("姓名：", 8), new JTextField("", 15) };
			t1[0].setBounds(10, 10, 80, 30);t1[0].setFont(new Font(null, 0, 15));
			t1[1].setBounds(95, 10, 120, 30);t1[1].setFont(new Font(null, 0, 15));
			t1[0].setEditable(false);
			con.add(t1[0]);
			con.add(t1[1]);
			JButton bt_del = new JButton("删除");
			bt_del.setBounds(40, 50, 70, 30);bt_del.setFont(new Font(null,0,15));
			con.add(bt_del);

			JButton bt_cancel = new JButton("取消");
			bt_cancel.setBounds(120, 50, 70, 30);bt_cancel.setFont(new Font(null,0,15));
			con.add(bt_cancel);
			
			bt_del.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					try {
						int temp = 0;// 检测是否存在联系人
						ResultSet rs = stmt.executeQuery("select * from "+LoginTel.account);
						while (rs.next()) {
							if (t1[1].getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "请输入要删除的联系人的姓名");
								temp = 1;
								break;
							}
							if (rs.getString("姓名").equals(t1[1].getText().trim())) {
								temp = 1;
								JFrame confirm = new JFrame("确定？");
								confirm.setSize(240, 150);
								int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
								int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
								confirm.setLocation(w, h);
								Container con1 = confirm.getContentPane();
								con1.setLayout(null);
								JButton bt_yes = new JButton("确定");
								bt_yes.setBounds(45, 45, 70, 30);bt_yes.setFont(new Font(null,0,15));
								JButton bt_no = new JButton("取消");
								bt_no.setBounds(120, 45, 70, 30);bt_no.setFont(new Font(null,0,15));
								JLabel lab = new JLabel("确定要删除 "+t1[1].getText()+" 的信息吗？");
								lab.setBounds(10, 10, 200, 30);lab.setFont(new Font(null,0,15));
								con1.add(lab);
								con1.add(bt_yes);
								con1.add(bt_no);
								bt_yes.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										try{
											String sql = " delete from "+LoginTel.account+" where 姓名='"+t1[1].getText().trim() + "'";
											//System.out.println(sql);
											if (stmt.executeUpdate(sql) == 1) {
												JOptionPane.showMessageDialog(null, "删除成功");
												reg1.dispose();
												confirm.dispose();
											}
										}catch(Exception e2){
											e2.printStackTrace();
										}
									}
								});
								bt_no.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										confirm.dispose();
									}
								});
								confirm.setVisible(true);
								confirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								break;
							}
						}
						if (temp == 0) {
							JOptionPane.showMessageDialog(null, "联系人不存在");
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
					reg1.setVisible(false);
				}
			});
			reg1.setVisible(true);
			reg1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
