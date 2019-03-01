import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
public class ReplaceDialog extends JDialog implements ActionListener {
	JTextArea txt;
	JTextField FindText = new JTextField(15);
	JTextField ReplaceText = new JTextField(15);
	JLabel FindTextLabel = new JLabel("查找内容：");
	JLabel ReplaceTextLabel = new JLabel("替换为：   ");
	JButton FindNextBtn = new JButton("查找下一个");
	JButton ReplaceBtn = new JButton("替换");
	JButton ReplaceAllBtn = new JButton("全部替换");
	JButton CancelBtn = new JButton("取消");
	
	JPanel CenterP = new JPanel();
	JPanel ButtonP = new JPanel();
	JPanel FindContentP = new JPanel();
	JPanel ReplaceContentP = new JPanel();
	JPanel blankP1 = new JPanel();//North
	JPanel blankP2 = new JPanel();//East
	
	int m = -100000;
	Boolean isFirstFind = true;
	String LastFindString="";
	
	ReplaceDialog(JFrame f,String s,boolean b,JTextArea txt0) {
		super(f,s,b);
		setSize(400,220);
		//setResizable(false);
		setLayout(new BorderLayout());
		txt = txt0;
		setRepDiaLook();
		actionProcessor();
	}
	public void setRepDiaLook() {
		/*FindNextBtn.setPreferredSize(new Dimension(80,20));
		ReplaceBtn.setPreferredSize(new Dimension(80,20));
		ReplaceAllBtn.setPreferredSize(new Dimension(80,20));
		CancelBtn.setPreferredSize(new Dimension(80,20));
		FindContentP.setPreferredSize(new Dimension(200,100));
		ReplaceContentP.setPreferredSize(new Dimension(200,100));
		ButtonP.setPreferredSize(new Dimension(100,150));
		*/
		CenterP.setLayout(new GridLayout(4,1));
		FindContentP.add(FindTextLabel);
		FindContentP.add(FindText);
		ReplaceContentP.add(ReplaceTextLabel);
		ReplaceContentP.add(ReplaceText);
		CenterP.add(FindContentP); CenterP.add(ReplaceContentP);
		
		ButtonP.setLayout(new GridLayout(5,1,0,10));
		ButtonP.add(FindNextBtn); ButtonP.add(ReplaceBtn); ButtonP.add(ReplaceAllBtn); ButtonP.add(CancelBtn);
		
		add("North",blankP1);
		add("West",CenterP); add("Center",ButtonP); add("East",blankP2);
	}
	
	public void actionProcessor() {
		FindNextBtn.addActionListener(this);
		ReplaceBtn.addActionListener(this);
		ReplaceAllBtn.addActionListener(this);
		CancelBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String source = txt.getText();
		String find = FindText.getText();
		String dest = ReplaceText.getText();
		if (e.getSource() == FindNextBtn) {
			m = source.indexOf(find,m+1);
			if (m == -1) {
				JOptionPane.showMessageDialog(null,"找不到“"+find+"”");
			}
			else {
				txt.setCaretPosition(m);
				txt.select(m,m+find.length());
				//txt.setSelectedText(m,m+find.length());//查找
				//txt.setSelectedTextColor(Color.blue);

			}
		} 
		else if (e.getSource() == ReplaceBtn) {
			m = source.indexOf(find,m);
			
			if (! LastFindString.equals(find)){
				txt.select(m,m+find.length());
				LastFindString = find;
			}
			else {
				if (m >= 0) {
					//m = ;
					System.out.println(m);
					String s1 = source.substring(0, m);
					String s2 = source.substring(m+find.length());
					source = s1 + dest + s2;
					txt.setText(source);
				}
				//System.out.println(m);
				if (dest.length() == 0) m--;
				m = source.indexOf(find,m+1);
				System.out.println("  "+m);
				if (m == -1) JOptionPane.showMessageDialog(null,"找不到“"+find+"”");
				else txt.select(m,m+find.length());
			}
		}
		else if (e.getSource() == ReplaceAllBtn) {//全部替换
			//特例：
			//java 变 java language
			//一直有java 会死循环
			m = - dest.length();
			while (true) {
				m = source.indexOf(find,m+dest.length());//每次从初始位置（m+1）开始查找
				if (m == -1) break;
				String s1 = source.substring(0, m);//子串
				String s2 = source.substring(m+find.length());//从此处取到最后
				source = s1 + dest + s2;//替换一次
				txt.setText(source);
				//m++;
			}
		}
		else if (e.getSource() == CancelBtn) {
			this.setVisible(false);
		}
	}
}
