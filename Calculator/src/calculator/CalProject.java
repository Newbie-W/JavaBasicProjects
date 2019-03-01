package calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class CalFrame extends JFrame implements ActionListener{
	String operation[] = {"+","-","*","/"};
	String firstnum = "0";
	String optem = "";
	int point = 0;
	double a=0,b=0,result=0;
	JButton equalbtn = new JButton("=");
	JButton pointbtn = new JButton(".");
	JButton renewbtn = new JButton("C");
	JButton numbtn[] = new JButton[10];
	JButton opbtn[] = new JButton[4];
	JTextField resultScreen = new JTextField("0",10);
	JTextArea resultScreen1 = new JTextArea(2,10);
	
	JPanel nump = new JPanel();
	JPanel opep = new JPanel();
	JPanel btnp = new JPanel();
	
	CalFrame(){
		setBounds(200,200,350,270);
		setTitle("计算器");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		btnp.setLayout(new FlowLayout());
		nump.setLayout(new GridLayout(4,3));
		opep.setLayout(new GridLayout(4,1));
		
		add("North",resultScreen);//
		add("Center",btnp);//numpSouth
		add("South",equalbtn);
		//add("East",opep);
		
		btnp.add(nump);
		btnp.add(opep);
		
		/*
		resultScreen.setText("0");
		resultScreen.setAlignmentX(resultScreen.RIGHT_ALIGNMENT);
		*/
		resultScreen.setHorizontalAlignment(resultScreen.RIGHT);
		resultScreen.setEditable(false);
		equalbtn.setPreferredSize(new Dimension(240,40));
		for (int i=1; i<numbtn.length+1; i++) {
			int i1 = i % 10;
			//System.out.println("i="+i1);
			numbtn[i1] = new JButton(i1+"");
			nump.add(numbtn[i1]);
			numbtn[i1].setPreferredSize(new Dimension(80,35));
			numbtn[i1].addActionListener(this);
		}
		nump.add(pointbtn);
		nump.add(renewbtn);
		for (int i=0; i<operation.length; i++) {
			opbtn[i] = new JButton(operation[i]);
			opep.add(opbtn[i]);
			opbtn[i].setPreferredSize(new Dimension(80,35));
			opbtn[i].addActionListener(this);
		}
		equalbtn.addActionListener(this);
		renewbtn.addActionListener(this);
		pointbtn.addActionListener(this);
		validate();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==pointbtn && point==0 && !resultScreen.getText().contains(".")){
			point = 1;
			resultScreen.setText(resultScreen.getText()+".");
			//a=a+0.1*num;
		}
		for (int i=0; i<numbtn.length; i++) {
			if (e.getSource() == numbtn[i]){
				//System.out.println(resultScreen.getText());
				if (resultScreen.getText().equals("0") || resultScreen.getText().equals("0.0"))
					resultScreen.setText(numbtn[i].getText());
				else 
					resultScreen.setText(resultScreen.getText()+numbtn[i].getText());
			}
		}
		for (int i=0; i<operation.length; i++) {
			if (e.getSource() == opbtn[i]){
				
				firstnum = resultScreen.getText();
				optem = opbtn[i].getText();
				//a = Double.parseDouble(firstnum);
				resultScreen.setText(null);
				if (firstnum.contains(".")) point = 0;//4.2+3.2
			}
		}
		if (e.getSource() == equalbtn){
			result = compute();
			resultScreen.setText(""+result);
		}
		else if (e.getSource() == renewbtn) {
			resultScreen.setText("0");
			point = 0;
		}
	}
	double compute(){
		a = Double.parseDouble(firstnum);
		b = Double.parseDouble(resultScreen.getText());
		System.out.println("a="+a);
		if (optem=="+")
			result = a+b;
		else if (optem=="-")
			result = a-b;
		else if (optem=="*")
			result = a*b;
		else if (optem=="/")
			result = a/b;
		if (point == 1) point = 0;
		resultScreen.setText(""+result);
		return result;
	}
}
public class CalProject {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new CalFrame();
	}

}
