import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class FindDialog extends JDialog implements ActionListener {
	JTextArea txt;
	JTextField FindText = new JTextField(15);
	JLabel FindTextLabel = new JLabel("查找内容：");
	JButton FindNextBtn = new JButton("查找下一个");
	JButton CancelBtn = new JButton("取消");
	ButtonGroup DireBtnG = new ButtonGroup();
	JRadioButton UpBtn = new JRadioButton("向上");
	JRadioButton DownBtn = new JRadioButton("向下");
	
	JPanel CenterP = new JPanel();
	JPanel ButtonP = new JPanel();
	JPanel FindContentP = new JPanel();
	JPanel DirectionP = new JPanel();
	JPanel blankP1 = new JPanel();//North
	JPanel blankP2 = new JPanel();//East
	
	int m = -100000;
	Boolean isFirstFind = true;
	String LastFindString="";
	
	FindDialog(JFrame f,String s,boolean b,JTextArea txt0) {
		super(f,s,b);
		setSize(400,180);
		//setResizable(false);
		setLayout(new BorderLayout());
		txt = txt0;
		setFindDiaLook();
		actionProcessor();
	}
	public void setFindDiaLook() {
		CenterP.setLayout(new BorderLayout());//new GridLayout(3,1)
		FindContentP.add(FindTextLabel);
		FindContentP.add(FindText);
		CenterP.add("North",FindContentP); CenterP.add("South",DirectionP);
		DirectionP.setBorder(new TitledBorder("方向"));
		//DirectionP.setBounds(200, 200, 300, 200);
		
		ButtonP.setLayout(new GridLayout(4,1,0,10));
		ButtonP.add(FindNextBtn); ButtonP.add(CancelBtn);
		
		DownBtn.setSelected(true);
		DireBtnG.add(UpBtn); DireBtnG.add(DownBtn);
		DirectionP.add(UpBtn);DirectionP.add(DownBtn);
		
		add("North",blankP1);
		add("West",CenterP); add("Center",ButtonP); add("East",blankP2);
	}
	
	public void actionProcessor() {
		FindNextBtn.addActionListener(this);
		CancelBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String source = txt.getText();
		String find = FindText.getText();
		if (e.getSource() == FindNextBtn) {
			if (DownBtn.isSelected())
			{
				m = source.indexOf(find,m+1);
				if (m == -1) {
					//m = -10000;
					JOptionPane.showMessageDialog(null,"找不到“"+find+"”");
				}
				else {
					//txt.setCaretPosition(m);
					txt.select(m,m+find.length());
					//txt.setSelectedText(m,m+find.length());//查找
					//txt.setSelectedTextColor(Color.blue);
	
				}
			}
			else if (UpBtn.isSelected())
			{
				if (m <= -1) m = source.length()+1;
				m = source.lastIndexOf(find,m-1);
				if (m == -1) {
					JOptionPane.showMessageDialog(null,"找不到“"+find+"”");
				}
				else {
					txt.setCaretPosition(m);
					txt.select(m,m+find.length());
				}
				/*if(txt.getSelectedText()==null)  
                    m=source.lastIndexOf(find,txt.getCaretPosition()-1);  
                else  
                    m=source.lastIndexOf(find, txt.getCaretPosition()-find.length()-1);      
                if(m>-1)  
                {   //String strData=strA.subString(k,strB.getText().length()+1);  
                    txt.setCaretPosition(m);  
                    txt.select(m,m+find.length());  
                }  
                else  
                {   JOptionPane.showMessageDialog(null,"找不到您查找的内容！","查找",JOptionPane.INFORMATION_MESSAGE);  
                } */ 
			}
		} 
		
		else if (e.getSource() == CancelBtn) {
			this.setVisible(false);
		}
	}
}
