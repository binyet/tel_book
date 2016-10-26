package Application;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class app_main extends JFrame {

	private static JFrame app;
	public static void main_tel() {

		app = new JFrame("电话查询系统");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(340, 250);
		app.setResizable(false);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
		app.setLocation(w, h);
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(app);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		JMenuBar mbar = new JMenuBar();
		app.setJMenuBar(mbar);
		JMenu[] m = { new JMenu("操作") };
		JMenuItem[] mI = { new JMenuItem("切换用户"), new JMenuItem("退出")

		};
		for (int i = 0; i < m.length; i++) {
			mbar.add(m[i]);
			for (int j = 0; j < mI.length; j++) {
				m[i].add(mI[j]);

			}
		}

		Container c = app.getContentPane();
		c.setLayout(null);// 采用布局

		JButton bt_add = new JButton("添加联系人");//
		bt_add.setBounds(50, 10, 120, 50);
		bt_add.setFont(new Font(null, 0, 15));
		c.add(bt_add);

		JButton bt_del = new JButton("删除联系人");
		bt_del.setBounds(180, 10, 120, 50);
		bt_del.setFont(new Font(null, 0, 15));
		c.add(bt_del);

		JButton bt_change = new JButton("修改联系人");
		bt_change.setBounds(50, 70, 120, 50);
		bt_change.setFont(new Font(null, 0, 15));
		c.add(bt_change);

		JButton bt_find = new JButton("查找联系人");
		bt_find.setBounds(180, 70, 120, 50);
		bt_find.setFont(new Font(null, 0, 15));
		c.add(bt_find);

		JButton bt_all = new JButton("查看所有");
		bt_all.setBounds(50, 130, 120, 50);
		bt_all.setFont(new Font(null, 0, 15));
		c.add(bt_all);

		JButton bt_close = new JButton("关闭");
		bt_close.setBounds(180, 130, 120, 50);
		bt_close.setFont(new Font(null, 0, 15));
		c.add(bt_close);

		bt_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Bt_Add.Bt_add();
			}
		});

		bt_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Bt_Del.Bt_del();
			}
		});

		bt_change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Bt_Chage.Bt_chage();
			}
		});

		bt_find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Bt_Find.Bt_find();
			}
		});

		bt_all.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Bt_All.Bt_all();
			}
		});

		bt_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				app.dispose();
			}
		});
		
		mI[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				app.dispose();
				LoginTel.show_login();
			}
		});

		mI[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				app.dispose();
			}
		});
		app.setVisible(true);
	}

}
