package Application;

import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Bt_Find {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tel_book?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "lubin";
	private static int temp = 3;// 记录查找的内容,0是根据姓名，1是根据手机号,２是根据部门,3是指没有选择修改内容
	
	public static void Bt_find() {
		// TODO Auto-generated method stub
		
		try{
			Class.forName("com.mysql.jdbc.Driver");// 数据库连接
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			
			JFrame reg1 = new JFrame("查找联系人");
			reg1.setSize(450, 250);
			reg1.setVisible(true);
			reg1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			reg1.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg1.setLocation(w, h);
			Container con = reg1.getContentPane();
			con.setLayout(null);
			JLabel lab_list = new JLabel("希望根据什么查找？");
			lab_list.setFont(new Font(null, 0, 15));
			lab_list.setBounds(280, 10, 160, 30);
			con.add(lab_list);
			
			JButton bt = new JButton("确定");
			bt.setBounds(45, 150, 80, 40);bt.setFont(new Font(null, 0, 13));
			con.add(bt);
			
			JButton bt_cancel = new JButton("取消");
			bt_cancel.setBounds(130, 150, 80, 40);bt_cancel.setFont(new Font(null, 0, 13));
			con.add(bt_cancel);
			JTextField[] t1 = { new JTextField("姓名：", 8), new JTextField("", 15),
					new JTextField("手机号：", 8), new JTextField("", 15), 
					new JTextField("部门：", 8),	new JTextField("", 15) };
			
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
			String[] str = { "姓名", "手机号", "部门" };
			JList list = new JList(str);
			list.setBounds(280, 40, 160, 80);
			list.setFont(new Font(null, 0, 15));
			con.add(list);
			
			list.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					// TODO Auto-generated method stub
					if (arg0.getValueIsAdjusting() == false) {
						if (list.getSelectedValue().equals("姓名")) {
							temp = 0;
							for (int i = 0; i < t1.length; i++) {
								if (i % 2 == 0)
									t1[i].setEditable(false);
								else
									t1[i].setEditable(true);
							} // 目的是为了初始化设置,防止前一个选择对其有影响
							t1[3].setEditable(false);t1[3].setText("");
							t1[5].setEditable(false);t1[5].setText("");
						} else if (list.getSelectedValue().equals("手机号")) {
							temp = 1;
							for (int i = 0; i < t1.length; i++) {
								if (i % 2 == 0)
									t1[i].setEditable(false);
								else
									t1[i].setEditable(true);
							} // 目的是为了初始化设置,防止前一个选择对其有影响
							t1[1].setEditable(false);t1[1].setText("");
							t1[5].setEditable(false);t1[5].setText("");
						} else {
							temp = 2;
							for (int i = 0; i < t1.length; i++) {
								if (i % 2 == 0)
									t1[i].setEditable(false);
								else
									t1[i].setEditable(true);
							} // 目的是为了初始化设置,防止前一个选择对其有影响
							t1[1].setEditable(false);t1[1].setText("");
							t1[3].setEditable(false);t1[3].setText("");
						}
					}
				}
			});
			
			bt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try{
						ResultSet rs = stmt.executeQuery("select * from "+LoginTel.account);
						String str = new String();
						int is_in = 0;//判断要查找的人是否在通讯录中
						while(rs.next()){
							if(temp == 3){
								JOptionPane.showMessageDialog(null, "请选择要查询的信息");
								break;
							}
							else if(temp == 0){
								if (rs.getString("姓名").equals(t1[1].getText())) {
									is_in = 1;
									str = str+rs.getString("姓名")+"\t"+
											rs.getString("手机号")+"\t"+rs.getString("部门")+"\n";
//									Find_out.find_out(str);
//									str = null;//对str初始化
									break;
								}
							}
							else if(temp == 1){
								if (rs.getString("手机号").equals(t1[3].getText())){
									is_in = 1;
									str = str+rs.getString("姓名")+"\t"+
											rs.getString("手机号")+"\t"+rs.getString("部门")+"\n";
//									Find_out.find_out(str);
//									str = null;
									break;
								}
							}
							else{
								if(rs.getString("部门").equals(t1[5].getText().trim())){
									is_in = 1;
									str = str+rs.getString("姓名")+"\t"+
											rs.getString("手机号")+"\t"+rs.getString("部门")+"\n";
//									Find_out.find_out(str);
								}
							}
							
						}
						Find_out.find_out(str);
						rs.close();
					}catch(Exception e1){
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
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
