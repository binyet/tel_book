package Application;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Find_out extends JFrame {

	public static void find_out(String s) {
		try {
			JFrame reg = new JFrame("查找结果");
			reg.setResizable(false);
			int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
			int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 180) / 2;
			reg.setLocation(w, h);
			JPanel contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(10, 50, 50, 50));
			contentPanel.setLayout(new BorderLayout(0, 0));

			reg.setContentPane(contentPanel);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 350, 240);
			contentPanel.add(scrollPane);
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font(null, 0, 15));
			scrollPane.setViewportView(textArea);
			reg.setBounds(100, 100, 400, 200);
			textArea.setText(s);
			

			
			reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			reg.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
