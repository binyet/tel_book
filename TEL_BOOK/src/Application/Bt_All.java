package Application;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Bt_All extends JFrame{
	public Bt_All() {
	}

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tel_book?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "lubin";
	
	public static void Bt_all() {
		// TODO Auto-generated method stub
		
		
		try{

			Class.forName("com.mysql.jdbc.Driver");// 数据库连接
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			
			JFrame reg = new JFrame("查看所有联系人");
			reg.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg.setLocation(w, h);
			JPanel contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(10, 50, 50, 50));
			contentPanel.setLayout(new BorderLayout(0,0));
			
			reg.setContentPane(contentPanel);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10,10,350,240);
			contentPanel.add(scrollPane);
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font(null,0,15));
			scrollPane.setViewportView(textArea);
			reg.setBounds(100, 100, 400, 200);
			
			
			reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			reg.setVisible(true);
			
			ResultSet rs = stmt.executeQuery("select * from "+LoginTel.account);
			while(rs.next()){
				textArea.setText(textArea.getText()+rs.getString("姓名")+"\t"+
								rs.getString("手机号")+"\t"+rs.getString("部门")+"\n");
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
