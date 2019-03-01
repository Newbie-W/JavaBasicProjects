import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//ע�⹦�ܵķ�������Ҫ��д��һ��class��
//�и��뷨�������ɾ�ķ��ڲ�ͬ�����ｨ���棬Ŀǰ��ʱ������+�鷳���ݲ���
//���ˣ�������·��صĽӿ�û����   �Լ����˵����󣬽����޷��л��⣬���඼������
class Bus{
	String type;  //01:�յ�����02����ͨ����03�����ٹ��� 
	int engine_number; //����ţ�1~10����
	int seating_capacity;//��������
	int route;   //��·�ţ�1~10����
	Bus(){
		System.out.println("��������ģ��");
	}
	void addBus(String str,String en,String num,String ro){
		DBCon con_bus=new DBCon();
		
		String sql="insert into Bus values ("+str +","+en+","+num+","+ro+")";
		System.out.println(""+sql);
		con_bus.getIns(con_bus.DBCo(),sql);
	}
		
	void updateBus(String str,String en,String num,String ro/*���������������������������File file*/) {
		DBCon con_bus=new DBCon();
		String sql="update Bus set �����="+en/*+", ����������� ="+num*/+"where ����="+str;
		con_bus.getCha(con_bus.DBCo(),sql);
	}
	
	void delBus(String str){
		DBCon con_bus=new DBCon();
		String sql="delete from Bus where ���� ="+str;
		con_bus.getDel(con_bus.DBCo(),sql);
	}
	String printBus(String str){
		DBCon con_bus=new DBCon();
		String sql="select*from Bus where ���� ="+str;
		String ename="Bus";
		return con_bus.getSel(con_bus.DBCo(),sql,ename,4);
	}
	public String toString(){
		return ("������Ϣ��"+type+" "+engine_number+" "+seating_capacity+" "+route );
		
	}
	
}
class Route{
	int  num_of_stop;  //0~20���ڵ���
	String start_station;  //��ʼվ
	String end_station;   //�յ�վ
	String run_time;    //����ʱ��**~**
	//List  listRoute;//��·�б�
	
	 Route(){
		 System.out.println("��·����ģ��");
	 }
    void addRoute(String nos, String ss, String es, String rt,String list){
    	DBCon con_ro=new DBCon();
		String sql="insert into Route values ("+nos+","+ss +","+es+","+rt +","+list+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateRoute(String nos, String ss, String es, String rt,String list){
		DBCon con_ro=new DBCon();
		String sql="update Route set ͣ��վ��="+nos+", ��ʼվ ="+ss+", �յ�վ ="+es+", ����ʱ�� ="+rt+"where ��·�б� ="+list;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delRoute(String list){
		DBCon con_ro=new DBCon();
		String sql="delete from Route where ��·�б� ="+list;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printRoute(String list) {
		DBCon con_ro=new DBCon();
		String sql="select*from Route where ��·�б� ="+list;
		System.out.println(""+sql);
		String ename="Route";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,5);
	}
}
class Employee{
	int ID;  //Ա�����
	String name;   //Ա������
	int age;    //Ա������
	String message;   //��ע��Ϣ
	//List  listEmployee; //Ա���б�
	Employee(){
		System.out.println("Ա������ģ��");
	}
	void addEmployee(String id, String name, String age, String message, String list){
    	DBCon con_ro=new DBCon();
		String sql="insert into Employee values ("+id+","+name +","+age+","+message+","+list+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateEmployee(String id, String name, String age, String message,String list){
		DBCon con_ro=new DBCon();
		String sql="update Employee set Ա���б�="+list
				+", Ա������ ="+name+", Ա������ ="+age
				+", ��ע��Ϣ ="+message+"where Ա����� ="+id;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delEmployee(String id){
		DBCon con_ro=new DBCon();
		String sql="delete from Employee where Ա����� ="+id;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printEmployee(String id) {
		DBCon con_ro=new DBCon();
		String sql="select*from Employee where Ա����� ="+id;
		System.out.println(""+sql);
		String ename="Employee";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,5);
	}
}
class Passenger{
	String name;  //Ͷ��������
	char sex;   //Ͷ�����Ա�
	int age;   //Ͷ��������
	String message;    //Ͷ����Ϣ
	Passenger(){
		System.out.println("�˿͹���ģ��");
	}
	void addPassenger(String name, String sex, String age, String m){
    	DBCon con_ro=new DBCon();
		String sql="insert into Passenger values ("+name+","+sex +","+age+","+m+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updatePassenger(String name, String sex, String age, String m){
		DBCon con_ro=new DBCon();
		String sql="update Passenger set Ͷ�����Ա� ="+sex+", Ͷ�������� ="+age+", Ͷ����Ϣ ="+m+"where Ͷ�������� ="+name;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delPassenger(String name){
		DBCon con_ro=new DBCon();
		String sql="delete from Passenger where Ͷ�������� ="+name;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printPassenger(String name) {
		DBCon con_ro=new DBCon();
		String sql="select*from Passenger where Ͷ�������� ="+name;
		System.out.println(""+sql);
		String ename="Passenger";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,4);
	}
	
}

class Manager extends Employee{
	String position;   //ְ��
	String depart;   //������
	String duty;   //ְ��
	
	Manager(){
		System.out.println("������ģ��");
	}
	void addManager(String p, String de, String duty){
    	DBCon con_ro=new DBCon();
		String sql="insert into Manager values ("+p+","+de +","+duty+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateManager(String p, String de, String duty){
		DBCon con_ro=new DBCon();
		String sql="update Manager set ������ ="+de+", ְ�� ="+duty+"where ְ�� ="+p;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delManager(String p){
		DBCon con_ro=new DBCon();
		String sql="delete from Manager where ְ�� ="+p;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printManager(String p) {
		DBCon con_ro=new DBCon();
		String sql="select*from Manager where ְ�� ="+p;
		System.out.println(""+sql);
		String ename="Manager";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,3);
	}
}

interface Local {
	
}

interface LongDistance{
	
}
class DBCon{//Connection �������ݵ�����
	
	public static Connection DBCo()/*�˴���throws SQLException��catch�м�java.lang.ClassNotFoundException���ʹ��*/
	{
		//����Ϊ���ݿ��������
		
		String JDriver="sun.jdbc.odbc.JdbcOdbcDriver";
		String conURL="jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};"
				+"DBQ=E:\\study\\biancheng\\java\\workspace\\12try\\12try\\mod\\11try\\db1.mdb";
		//E:\\����
		try {
			Class.forName(JDriver);
			
			/*ע��ո�,б�ܻ�б�ܾ���*/
			Connection Con = DriverManager.getConnection(conURL);//con��Con����
			return Con; 
		}
		catch(Exception e){
			System.out.println("ForName:"+e.getMessage());
			return null;
		}
		/*try{
			Con=DriverManager.getConnection(conURL);
			Stmt=Con.createStatement();
		}
		catch(SQLException ee){
			System.out.println(ee.getMessage());
		}*/
		
		/*
		 * �����ô��ַ�ʽд����������jdbc���ӣ�
		 try{  
		            Class.forName("com.hxtt.sql.access.AccessDriver");  
		             String url = "jdbc:Access:///E:/BusManager.mdb"; 
		             Connection con = DriverManager.getConnection(url);  
		               
		             return con;  
		               
		         }catch(Exception e){  
		             System.out.println("wrong");  
		             e.getCause();  
		             return null;  
		         }*/
	}
	public static void getIns(Connection Con,String temp)//temp�ǲ������ݵ�sql���
	{
		
		try{
			Statement Stmt=null;//Con=DriverManager.getConnection(conURL);
			Stmt=Con.createStatement();//��System.out.println("hhh");����󣬿���ִ��
			
			Stmt.executeUpdate(temp);
			//�˾�δ��ִ��
			Con.close();
		}catch(SQLException e){  
			System.out.println("hhh\n"+e.getMessage());
		} 
	}
	public static void getCha(Connection Con,String temp){
		try{
			Statement Stmt=null;
			Stmt=Con.createStatement();
			//Con=DriverManager.getConnection(conURL);
			Stmt.executeUpdate(temp);
			Con.close();
		}catch(Exception e){
			e.getMessage();
		}
	}
	public static void getDel(Connection Con,String temp){
		try{
			Statement Stmt=null;
			Stmt=Con.createStatement();
			//Con=DriverManager.getConnection(conURL);
			Stmt.executeUpdate(temp);
			Con.close();
		}catch(Exception e){
			e.getMessage();
		}
	}
	public static String getSel(Connection Con,String temp,String excelname,int n){
		String sql = "select * from "+excelname;  
        try{  
        		Statement Stmt=null;
        		//Con=DriverManager.getConnection(conURL);
        		Stmt=Con.createStatement();
        		ResultSet rs = Stmt.executeQuery(sql);
        		String s=new String();
        		String s1=new String();
        		while(rs.next()){  
        			//System.out.println("hh");
        			for(int i = 1; i <= n; i++){  
        				s=rs.getString(i)+"\t";
        				s1+=s;
        				
        			}
        			s1+="\n";
        			System.out.println(""+s1);
                	//System.out.println("hh");  
        		}  
        		return s1;
        	}catch(Exception e){  
        		System.out.println("\n"+e.getMessage());  
        		return e.getMessage();  
        	}
		/*
		try{
			Statement Stmt=null;
			//Con=DriverManager.getConnection(conURL);
			Stmt=Con.createStatement();
			ResultSet rs=Stmt.executeQuery(temp);
			String s=new String();
			while (rs.next())
			{
				s=rs.getString("����")
						+"\t"+rs.getInt("�����")
						+"\t"+rs.getString("�����������")
						+"\t"+rs.getInt("��·");
				//txt1.append("\n"+s);/*System.out.println("\n"+s);
			}
			//txt1.append("\n");
			Con.close();
			//return "hh";
			return s;
		}catch(Exception e){
			return "Wrong"+e.getMessage();
		}*/
	}
}
class MyFrame extends JFrame implements ActionListener{//������
	JMenuItem busNew=new JMenuItem("��ӹ���");
	JMenuItem busCha=new JMenuItem("���Ĺ���");
	JMenuItem busDel=new JMenuItem("ɾ������");//������������ɾ��
	JMenuItem empNew=new JMenuItem("���Ա��");
	JMenuItem empCha=new JMenuItem("����Ա��");
	JMenuItem empDel=new JMenuItem("ɾ��Ա��");//������������ɾ��
	JMenuItem maNew=new JMenuItem("��Ӿ���");
	JMenuItem maCha=new JMenuItem("���ľ���");
	JMenuItem maDel=new JMenuItem("ɾ������");//������������ɾ��
	JMenuItem roNew=new JMenuItem("���·��");
	JMenuItem roCha=new JMenuItem("����·��");
	JMenuItem roDel=new JMenuItem("ɾ��·��");//������������ɾ��
	JMenuItem roLocNew=new JMenuItem("������ڻ����·��");
	JMenuItem roLocCha=new JMenuItem("�������ڻ����·��");
	JMenuItem roLocDel=new JMenuItem("ɾ�����ڻ����·��");//������������ɾ��
	JMenuItem roLoDNew=new JMenuItem("��ӳ�;����·��");
	JMenuItem roLoDCha=new JMenuItem("���ĳ�;����·��");
	JMenuItem roLoDDel=new JMenuItem("ɾ����;����·��");//������������ɾ��
	JMenuItem pasNew=new JMenuItem("��ӳ˿�");
	JMenuItem pasCha=new JMenuItem("���ӳ˿�");
	JMenuItem pasDel=new JMenuItem("ɾ���˿�");//������������ɾ��
	//����Ϊ�˵���Ķ���
	JLabel welcome=new JLabel("W E L C O M E ! !");
	
	Panel p=new Panel();
	MyFrame(){
		super("��������ϵͳ");
		
		JMenu busm=new JMenu("��������");
		JMenu routem=new JMenu("·�߹���");
		JMenu empm=new JMenu("Ա������");
		JMenu pasm=new JMenu("�˿͹���");
		JMenu meMa=new JMenu("�������");//��Ա����
		JMenu meLoc=new JMenu("���ڻ������·");
		JMenu meLoD=new JMenu("��;����");//����������·����
		busm.add(busNew);busm.add(busCha);busm.add(busDel);//������������Ĳ˵�
		empm.add(empNew);empm.add(empCha);empm.add(empDel);
		empm.addSeparator();empm.add(meMa);
		meMa.add(maNew);meMa.add(maCha);meMa.add(maDel);//����Ա������Ĳ˵�
		routem.add(roNew);routem.add(roCha);routem.add(roDel);
		routem.addSeparator();routem.add(meLoc);
		meLoc.add(roLocNew);meLoc.add(roLocCha);meLoc.add(roLocDel);//�������ڻ����·�߹���Ĳ˵�
		routem.addSeparator();routem.add(meLoD);
		meLoD.add(roLoDNew);meLoD.add(roLoDCha);meLoD.add(roLoDDel);
		pasm.add(pasNew);pasm.add(pasCha);pasm.add(pasDel);//������������Ĳ˵�
		//���Ϲ��췽���е��ǣ����ڲ˵��Ķ���
		JMenuBar bar =new JMenuBar();
		setJMenuBar(bar);
		bar.add(busm);
		bar.add(routem);
		bar.add(empm);
		bar.add(pasm);
		//����Ϊ�����˵����������˵����
		busNew.addActionListener(this);
		busCha.addActionListener(this);
		busDel.addActionListener(this);
		empNew.addActionListener(this);
		empCha.addActionListener(this);
		empDel.addActionListener(this);
		maNew.addActionListener(this);
		maCha.addActionListener(this);
		maDel.addActionListener(this);
		roNew.addActionListener(this);
		roCha.addActionListener(this);
		roDel.addActionListener(this);
		roLocNew.addActionListener(this);
		roLocCha.addActionListener(this);
		roLocDel.addActionListener(this);
		roLoDNew.addActionListener(this);
		roLoDCha.addActionListener(this);
		roLoDDel.addActionListener(this);
		pasNew.addActionListener(this);
		pasCha.addActionListener(this);
		pasDel.addActionListener(this);
		//����Ϊ�˵�������
		//����Ϊ��ʾ������Ϣ
		add(welcome);
		busm.addActionListener(this);
		setSize(850,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//�������
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==busNew){
			this.setVisible(false);//
			//this.dispose();//
			BusFrame busf=new BusFrame();
			//p.add(busf);add(p);
			
		}
		if (e.getSource()==busCha){
			this.setVisible(false);//
			//this.dispose();
			BusFrame busf=new BusFrame();
			//p.add(busf);add(p);
			
		}
		if (e.getSource()==busDel){
			this.setVisible(false);
			//this.dispose();//
			BusFrame busf=new BusFrame();
			//p.add(busf);add(p);
			
		}
		//����Ϊbus�˵������
		if (e.getSource()==empNew){
			this.setVisible(false);
			//this.dispose();//
			EmpFrame empf=new EmpFrame();
			//p.add(empf);add(p);
			
		}
		if (e.getSource()==empCha){
			this.setVisible(false);
			//this.dispose();
			new EmpFrame();
		}
		if (e.getSource()==empDel){
			this.setVisible(false);
			//this.dispose();
			new EmpFrame();
		}
		//����Ϊemp�˵������
		if (e.getSource()==maNew){
			this.setVisible(false);
			new MaFrame();
		}
		if (e.getSource()==maCha){
			this.setVisible(false);
			new MaFrame();
		}
		if (e.getSource()==maDel){
			this.setVisible(false);
			new MaFrame();
		}
		//����Ϊma�˵������
		if (e.getSource()==roNew){
			this.setVisible(false);
			new RoFrame();
		}
		if (e.getSource()==roCha){
			this.setVisible(false);
			new RoFrame();
		}
		if (e.getSource()==roDel){
			this.setVisible(false);
			new RoFrame();
		}
		//����Ϊro�˵������
		if (e.getSource()==roLocNew){
			this.setVisible(false);
			new RolocFrame();
		}
		if (e.getSource()==roLocCha){
			this.setVisible(false);
			new RolocFrame();
		}
		if (e.getSource()==roLocDel){
			this.setVisible(false);
			new RolocFrame();
		}
		//����ΪroLoc�˵������
		if (e.getSource()==roLoDNew){
			this.setVisible(false);
			new RolodFrame();
		}
		if (e.getSource()==roLoDCha){
			this.setVisible(false);
			new RolodFrame();
		}
		if (e.getSource()==roLoDDel){
			this.setVisible(false);
			new RolodFrame();
		}
		//����ΪroLoD�˵������
		if (e.getSource()==pasNew){
			this.setVisible(false);
			new PasFrame();
		}
		if (e.getSource()==pasCha){
			this.setVisible(false);
			new PasFrame();
		}
		if (e.getSource()==pasDel){
			this.setVisible(false);
			new PasFrame();
		}
		//����Ϊpas�˵������
	}
}
class BusFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	//JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("���ͣ�");
	JLabel lbus2=new JLabel("����ţ�");
	JLabel lbus3=new JLabel("�������������");
	JLabel lbus4=new JLabel("��·��");
	//����������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="����\t�����\t�����������\t��·";
	
	Bus b=new Bus();
	BusFrame(){
		super();//
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�//
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		//p1.add(txt04);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		//����Ϊ���ݿ������ʾ����
		txt00.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		//txt0.setText(((JMenuItem)e.getSource())
		/*�˴���һ�����ţ���ס(JMenuItem)e.getSource()*/  //.getText());
		//�����������Ҫ�Ӻ�;���⣬�˾������ڴ�������ʾ�������ѡ����Ϣ
		if (e.getSource()==txt00){txt1.append(txt00.getText()+"\n");}//������
		
		else if (e.getSource()==this.b1){//����
			try {
				BusN();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==this.b2){//����
			try {
				BusC();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==this.b3){//ɾ��
			try {
				BusD();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==b4){//��ѯ
			try {
				BusR();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
		/*else JOptionPane.showMessageDialog(null, "��ʱδ���죬����ing");*/
		
	}
	/*public void WindowClosing(WindowEvent ee)
	{
		this.dispose();
	}*/
	public void BusN()throws SQLException/*ע��Ҫ��throws�˾�*/{
		String s1="'"+txt00.getText().trim()+"'",
				s2="'"+txt01.getText().trim()+"'",
				s3="'"+txt02.getText().trim()+"'",
				s4="'"+txt03.getText().trim()+"'";
		b.addBus(s1,s2,s3,s4);
		
	}
	public void BusC()throws SQLException{
		
		String s1="'"+txt00.getText().trim()+"'",
				s2="'"+txt01.getText().trim()+"'",
				s3="'"+txt02.getText().trim()+"'",
				s4="'"+txt03.getText().trim()+"'";
		b.updateBus(s1,s2,s3,s4);
	}
	/*DELETE Bus.����
FROM Bus
WHERE (((Bus.����)="7"));*/
	public void BusD()throws SQLException{
		String s1="'"+txt00.getText().trim()+"'",
				s2="'"+txt01.getText().trim()+"'",
				s3="'"+txt02.getText().trim()+"'",
				s4="'"+txt03.getText().trim()+"'";
		b.delBus(s1);
	}
	public void BusR()throws SQLException{//������ѯ
		String s1="'"+txt00.getText().trim()+"'";
		//String temp="select*from Bus ";
		txt1.append("\n"+b.printBus(s1));
		}
}
//·�߽���
class RoFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("ͣ��վ�㣺");
	JLabel lbus2=new JLabel("��ʼվ��");
	JLabel lbus3=new JLabel("�յ�վ��");
	JLabel lbus4=new JLabel("����ʱ�䣺");
	JLabel lbus5=new JLabel("��·�б�");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="ͣ��վ��\t��ʼվ\t�յ�վ\t����ʱ��\t��·�б�";
	
	Route b=new Route();
	RoFrame(){
		//super();
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(lbus5);p1.add(txt04);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";

			b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//��ѯ
			String s5="'"+txt04.getText().trim()+"'";
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printRoute(s5));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
//·�߽���
class EmpFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("Ա����ţ�");
	JLabel lbus2=new JLabel("Ա��������");
	JLabel lbus3=new JLabel("Ա�����䣺");
	JLabel lbus4=new JLabel("��ע��Ϣ��");
	JLabel lbus5=new JLabel("Ա���б�");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="Ա�����\tԱ������\tԱ������\t��ע��Ϣ\tԱ���б�";
	
	Employee b=new Employee();
	EmpFrame(){
		super();
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(lbus5);p1.add(txt04);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1=txt00.getText().trim(),//int�ͣ�����Ϊ����ɾֵʱ������''
					s2="'"+txt01.getText().trim()+"'",
					s3=txt02.getText().trim(),
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.addEmployee(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//����
			String s1=txt00.getText().trim(),
					s2="'"+txt01.getText().trim()+"'",
					s3=txt02.getText().trim(),
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateEmployee(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1=txt00.getText().trim();
			b.delEmployee(s1);
		}
		else if (e.getSource()==b4){//��ѯ
			String s1=txt00.getText().trim();
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printEmployee(s1));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
//·�߽���
class PasFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("Ͷ����������");
	JLabel lbus2=new JLabel("Ͷ�����Ա�");
	JLabel lbus3=new JLabel("Ͷ�������䣺");
	JLabel lbus4=new JLabel("Ͷ����Ϣ��");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="Ͷ��������\tͶ�����Ա�\tͶ��������\tͶ����Ϣ";
	
	Passenger b=new Passenger();
	PasFrame(){
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.addPassenger(s1,s2,s3,s4);
		}
		else if (e.getSource()==this.b2){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.updatePassenger(s1,s2,s3,s4);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.delPassenger(s1);
		}
		else if (e.getSource()==b4){//��ѯ
			String s1="'"+txt00.getText().trim()+"'";
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printPassenger(s1));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
//·�߽���
class MaFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("ְ��");
	JLabel lbus2=new JLabel("�����ţ�");
	JLabel lbus3=new JLabel("ְ��");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="ְ��\t������\tְ��";
	
	Manager b=new Manager();
	MaFrame(){
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);

		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.addManager(s1,s2,s3);
		}
		else if (e.getSource()==this.b2){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.updateManager(s1,s2,s3);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.delManager(s1);
		}
		else if (e.getSource()==b4){//��ѯ
			String s1="'"+txt00.getText().trim()+"'";
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printManager(s1));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
//·�߽���
class RolocFrame extends MyFrame implements Local,ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("ͣ��վ�㣺");
	JLabel lbus2=new JLabel("��ʼվ��");
	JLabel lbus3=new JLabel("�յ�վ��");
	JLabel lbus4=new JLabel("����ʱ�䣺");
	JLabel lbus5=new JLabel("��·�б�");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="ͣ��վ��\t��ʼվ\t�յ�վ\t����ʱ��\t��·�б�";
	
	Route b=new Route();
	RolocFrame(){
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(lbus5);p1.add(txt04);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			//b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//��ѯ
			String s5="'"+txt04.getText().trim()+"'";
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printRoute(s5));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
//·�߽���
class RolodFrame extends MyFrame implements ActionListener{//����������
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//ע�ⴴ����ʽ��������new...��
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//��ʾ��ѯ���
	JLabel lbus1=new JLabel("ͣ��վ�㣺");
	JLabel lbus2=new JLabel("��ʼվ��");
	JLabel lbus3=new JLabel("�յ�վ��");
	JLabel lbus4=new JLabel("����ʱ�䣺");
	JLabel lbus5=new JLabel("��·�б�");
	//�������ذ�ť
	JButton b1=new JButton("����");
	JButton b2=new JButton("����");
	JButton b3=new JButton("ɾ��");
	JButton b4=new JButton("��ѯ");
	String s1="ͣ��վ��\t��ʼվ\t�յ�վ\t����ʱ��\t��·�б�";
	
	Route b=new Route();
	RolodFrame(){
		super.welcome.setVisible(false);//��  ��Ϊ����  �� MyFrame��  �е�    ��ӭ��ǩ   Ϊ���ɼ�
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//�˾��ʡ������Ч����ͬ
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(lbus5);p1.add(txt04);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//����Ϊ��ť����
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			//b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//����
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//ɾ��
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//��ѯ
			String s5="'"+txt04.getText().trim()+"'";
			//String temp="select*from Bus ";
			txt1.append("\n"+b.printRoute(s5));
		}
		else if (e.getSource()==busNew||e.getSource()==busCha||e.getSource()==busDel)
		{
			this.setVisible(false);
			new BusFrame();
		}
		else if (e.getSource()==roNew||e.getSource()==roCha||e.getSource()==roDel)
		{
			this.setVisible(false);
			new RoFrame();
		}
		else if (e.getSource()==empNew||e.getSource()==empCha||e.getSource()==empDel)
		{
			this.setVisible(false);
			new EmpFrame();
		}
		else if (e.getSource()==pasNew||e.getSource()==pasCha||e.getSource()==pasDel)
		{
			this.setVisible(false);
			new PasFrame();
		}	
		else if (e.getSource()==maNew||e.getSource()==maCha||e.getSource()==maDel)
		{
			this.setVisible(false);
			new MaFrame();
		}
	}
}
public class Rebus {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}

}
