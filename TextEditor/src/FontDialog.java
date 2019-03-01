import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
public class FontDialog extends JDialog implements ItemListener,ListSelectionListener, ActionListener {
	String fontname = "宋体";
	int stylename = Font.PLAIN;
	int size = 13;
	
	JLabel fontLabel = new JLabel("字体：");
	JLabel styleLabel = new JLabel("字形：");
	JLabel sizeLabel = new JLabel("大小：");
	JTextField fontjf;
	JTextField stylejf;
	JTextField sizejf;
	JTextField examplejf = new JTextField("示例 1 ABC abc");
	JButton sure = new JButton("确定");
	JButton cancel = new JButton("取消");
	JPanel optionP = new JPanel();
	JPanel choiceP1 = new JPanel();//fontname
	JPanel choiceP2 = new JPanel();//style
	JPanel choiceP3 = new JPanel();//fontsize
	JPanel centerP = new JPanel();
	JPanel displayP = new JPanel();
	JPanel operationP = new JPanel();

	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	String[] fnameString = ge.getAvailableFontFamilyNames();//String fnameString[] = {"宋体","楷体","隶书"};
	String fstyleString[] = {"常规","倾斜","粗体","粗偏斜体"};//cuti,xieti
	String fsizeString[] = {"8","10","12","14","16","18","20","22","24","26","28","36","48"};
	JList fnamelist = new JList(fnameString);
	JList fstylelist = new JList(fstyleString);
	JList fsizelist = new JList(fsizeString);
	
	JComboBox list = new JComboBox();//script
	JScrollPane fnamejsp = new JScrollPane(fnamelist);
	JScrollPane fstylejsp = new JScrollPane(fstylelist);
	JScrollPane fsizejsp = new JScrollPane(fsizelist);
	
	JTextArea txt;
	FontDialog(JFrame f,String s,boolean b,JTextArea txt0) {
		super(f,s,b);
		setSize(450,550);
		setResizable(false);
		setLayout(new BorderLayout());
		
		initFontDia(txt0);
		setFontDiaLook();
		actionProcessor();
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		validate();
	}
	
	public void initFontDia(JTextArea txt0) {
		txt = txt0;
		
		/*fontname = getFont().getName();
		stylename = Font.PLAIN;
		size = 13;*/
		fontjf = new JTextField(fontname);
		stylejf = new JTextField();
		sizejf = new JTextField(""+size);
		
		/*fontjf.setEditable(false);
		stylejf.setEditable(false);
		sizejf.setEditable(false);*/
	}
	
	public void setFontDiaLook() {
		
		list.addItem("西欧语言");  list.addItem("中文 GB2312");
		
		choiceP1.setLayout(null);
		//choiceP1.setBounds(10,10,300,300);
		choiceP1.setPreferredSize(new Dimension(210,200));
		fontLabel.setBounds(0,10,100,20);
		fontjf.setBounds(0,30,210,20);
		fnamejsp.setBounds(0,50,210,145);//210,145
		choiceP1.add(fontLabel);choiceP1.add(fontjf);
		choiceP1.add(fnamejsp);
		//fnamelist.setVisibleRowCount(7);
		
		choiceP2.setLayout(null);
		choiceP2.setPreferredSize(new Dimension(120,200));
		styleLabel.setBounds(10,10,100,20);
		stylejf.setBounds(10,30,100,20);
		fstylejsp.setBounds(10,50,100,145);//210,145
		choiceP2.add(styleLabel);choiceP2.add(stylejf);
		choiceP2.add(fstylejsp);
		
		choiceP3.setLayout(null);
		choiceP3.setPreferredSize(new Dimension(80,200));
		sizeLabel.setBounds(0,10,70,20);
		sizejf.setBounds(0,30,70,20);
		fsizejsp.setBounds(0,50,70,145);/**/
		choiceP3.add(sizeLabel);choiceP3.add(sizejf);
		choiceP3.add(fsizejsp);
		
		//optionP.setLayout(new GridLayout(1,3));
		optionP.add(choiceP1); optionP.add(choiceP2); optionP.add(choiceP3);
		add("North",optionP);
		
		operationP.add(sure);  operationP.add(cancel);
		add("South",operationP);
		operationP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//displayP.pack();
		centerP.setPreferredSize(new Dimension(450,300));
		centerP.setLayout(null);//new GridLayout(2,1)
		displayP.setLayout(new BorderLayout());
		displayP.setBorder(new TitledBorder("示例"));
		displayP.setBounds(220,0,200,100);
		displayP.add("Center",examplejf);
		examplejf.setEditable(false);
		//System.out.println("x:"+centerP.getBounds().height+"\t y="+this.getSize().width);
		list.setBounds(220,120,200,20);
		centerP.add(displayP);centerP.add(list);
		add("Center",centerP);
	}
	
	public void actionProcessor() {
		fnamelist.addListSelectionListener(this);
		fstylelist.addListSelectionListener(this);
		fsizelist.addListSelectionListener(this);
		list.addItemListener(this);
		sure.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == fnamelist){
			//fontname = fnameString[fnamelist.getSelectedIndex()];
			fontname = (String) fnamelist.getSelectedValue();
			fontjf.setText(fontname);
			System.out.println(""+fnamelist.getSelectedIndex());
		}
		else if (e.getSource() == fstylelist){
			String styleS = (String) fstylelist.getSelectedValue();
			stylejf.setText(styleS);
			if (styleS.equals("常规")) {
				stylename = Font.PLAIN;
			}
			else if (styleS.equals("粗体")) {
				stylename = Font.BOLD;
			}
			else if (styleS.equals("倾斜")) {
				stylename = Font.ITALIC;
			}
			else if (styleS.equals("粗偏斜体")) {
				stylename = Font.ITALIC + Font.BOLD;
			}/**/
		}
		else if (e.getSource() == fsizelist) {
			String sizeS = (String) fsizelist.getSelectedValue();
			sizejf.setText(sizeS);
			size = Integer.parseInt(sizeS);
		}
		changeExample(examplejf);
		System.out.println(""+fontname+"\t"+stylename+"\t"+size);
	}
	
	public void changeExample(JTextField jtf) {
		Font f = new Font(fontname,stylename,size);
		jtf.setFont(f);
	}
	
	public void itemStateChanged(ItemEvent e) {
		
	}/**/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sure) {
			Font f = new Font(fontname,stylename,size);
			txt.setFont(f);
			this.dispose();
			System.out.println(""+fontname+"\t"+stylename+"\t"+size);
		}
		else if (e.getSource() == cancel) {
			this.dispose();
			//System.out.println("class:"+this.getTitle());
		}
	}
}
