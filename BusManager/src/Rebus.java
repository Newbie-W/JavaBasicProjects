import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//注意功能的分区，不要都写在一个class里
//有个想法，想把增删改放在不同的类里建界面，目前因时间问题+麻烦，暂不做
//除了；两个线路相关的接口没做好   以及各菜单项点后，界面无法切换外，其余都做好了
class Bus{
	String type;  //01:空调车；02：普通车；03：快速公交 
	int engine_number; //引擎号：1~10的数
	int seating_capacity;//多少座车
	int route;   //线路号：1~10的数
	Bus(){
		System.out.println("汽车管理模块");
	}
	void addBus(String str,String en,String num,String ro){
		DBCon con_bus=new DBCon();
		
		String sql="insert into Bus values ("+str +","+en+","+num+","+ro+")";
		System.out.println(""+sql);
		con_bus.getIns(con_bus.DBCo(),sql);
	}
		
	void updateBus(String str,String en,String num,String ro/*这个括号里曾经案例给的内容是File file*/) {
		DBCon con_bus=new DBCon();
		String sql="update Bus set 引擎号="+en/*+", 最大容纳人数 ="+num*/+"where 类型="+str;
		con_bus.getCha(con_bus.DBCo(),sql);
	}
	
	void delBus(String str){
		DBCon con_bus=new DBCon();
		String sql="delete from Bus where 类型 ="+str;
		con_bus.getDel(con_bus.DBCo(),sql);
	}
	String printBus(String str){
		DBCon con_bus=new DBCon();
		String sql="select*from Bus where 类型 ="+str;
		String ename="Bus";
		return con_bus.getSel(con_bus.DBCo(),sql,ename,4);
	}
	public String toString(){
		return ("公交信息："+type+" "+engine_number+" "+seating_capacity+" "+route );
		
	}
	
}
class Route{
	int  num_of_stop;  //0~20以内的数
	String start_station;  //起始站
	String end_station;   //终点站
	String run_time;    //运行时间**~**
	//List  listRoute;//线路列表
	
	 Route(){
		 System.out.println("线路管理模块");
	 }
    void addRoute(String nos, String ss, String es, String rt,String list){
    	DBCon con_ro=new DBCon();
		String sql="insert into Route values ("+nos+","+ss +","+es+","+rt +","+list+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateRoute(String nos, String ss, String es, String rt,String list){
		DBCon con_ro=new DBCon();
		String sql="update Route set 停靠站数="+nos+", 起始站 ="+ss+", 终点站 ="+es+", 运行时间 ="+rt+"where 线路列表 ="+list;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delRoute(String list){
		DBCon con_ro=new DBCon();
		String sql="delete from Route where 线路列表 ="+list;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printRoute(String list) {
		DBCon con_ro=new DBCon();
		String sql="select*from Route where 线路列表 ="+list;
		System.out.println(""+sql);
		String ename="Route";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,5);
	}
}
class Employee{
	int ID;  //员工编号
	String name;   //员工姓名
	int age;    //员工年龄
	String message;   //备注信息
	//List  listEmployee; //员工列表
	Employee(){
		System.out.println("员工管理模块");
	}
	void addEmployee(String id, String name, String age, String message, String list){
    	DBCon con_ro=new DBCon();
		String sql="insert into Employee values ("+id+","+name +","+age+","+message+","+list+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateEmployee(String id, String name, String age, String message,String list){
		DBCon con_ro=new DBCon();
		String sql="update Employee set 员工列表="+list
				+", 员工姓名 ="+name+", 员工年龄 ="+age
				+", 备注信息 ="+message+"where 员工编号 ="+id;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delEmployee(String id){
		DBCon con_ro=new DBCon();
		String sql="delete from Employee where 员工编号 ="+id;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printEmployee(String id) {
		DBCon con_ro=new DBCon();
		String sql="select*from Employee where 员工编号 ="+id;
		System.out.println(""+sql);
		String ename="Employee";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,5);
	}
}
class Passenger{
	String name;  //投诉人姓名
	char sex;   //投诉人性别
	int age;   //投诉人年龄
	String message;    //投诉信息
	Passenger(){
		System.out.println("乘客管理模块");
	}
	void addPassenger(String name, String sex, String age, String m){
    	DBCon con_ro=new DBCon();
		String sql="insert into Passenger values ("+name+","+sex +","+age+","+m+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updatePassenger(String name, String sex, String age, String m){
		DBCon con_ro=new DBCon();
		String sql="update Passenger set 投诉人性别 ="+sex+", 投诉人年龄 ="+age+", 投诉信息 ="+m+"where 投诉人姓名 ="+name;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delPassenger(String name){
		DBCon con_ro=new DBCon();
		String sql="delete from Passenger where 投诉人姓名 ="+name;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printPassenger(String name) {
		DBCon con_ro=new DBCon();
		String sql="select*from Passenger where 投诉人姓名 ="+name;
		System.out.println(""+sql);
		String ename="Passenger";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,4);
	}
	
}

class Manager extends Employee{
	String position;   //职务
	String depart;   //管理部门
	String duty;   //职责
	
	Manager(){
		System.out.println("管理者模块");
	}
	void addManager(String p, String de, String duty){
    	DBCon con_ro=new DBCon();
		String sql="insert into Manager values ("+p+","+de +","+duty+")";
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void updateManager(String p, String de, String duty){
		DBCon con_ro=new DBCon();
		String sql="update Manager set 管理部门 ="+de+", 职责 ="+duty+"where 职务 ="+p;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	
	void delManager(String p){
		DBCon con_ro=new DBCon();
		String sql="delete from Manager where 职务 ="+p;
		System.out.println(""+sql);
		con_ro.getIns(con_ro.DBCo(),sql);
	}
	public String printManager(String p) {
		DBCon con_ro=new DBCon();
		String sql="select*from Manager where 职务 ="+p;
		System.out.println(""+sql);
		String ename="Manager";
		return con_ro.getSel(con_ro.DBCo(),sql,ename,3);
	}
}

interface Local {
	
}

interface LongDistance{
	
}
class DBCon{//Connection 建立数据的连接
	
	public static Connection DBCo()/*此处加throws SQLException和catch中加java.lang.ClassNotFoundException配合使用*/
	{
		//以下为数据库连接相关
		
		String JDriver="sun.jdbc.odbc.JdbcOdbcDriver";
		String conURL="jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};"
				+"DBQ=E:\\study\\biancheng\\java\\workspace\\12try\\12try\\mod\\11try\\db1.mdb";
		//E:\\桌面
		try {
			Class.forName(JDriver);
			
			/*注意空格,斜杠或反斜杠均可*/
			Connection Con = DriverManager.getConnection(conURL);//con变Con则不行
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
		 * 或者用此种方式写（此种是用jdbc连接）
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
	public static void getIns(Connection Con,String temp)//temp是插入数据的sql语句
	{
		
		try{
			Statement Stmt=null;//Con=DriverManager.getConnection(conURL);
			Stmt=Con.createStatement();//把System.out.println("hhh");加其后，可以执行
			
			Stmt.executeUpdate(temp);
			//此句未被执行
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
				s=rs.getString("类型")
						+"\t"+rs.getInt("引擎号")
						+"\t"+rs.getString("最大容纳人数")
						+"\t"+rs.getInt("线路");
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
class MyFrame extends JFrame implements ActionListener{//主界面
	JMenuItem busNew=new JMenuItem("添加公交");
	JMenuItem busCha=new JMenuItem("更改公交");
	JMenuItem busDel=new JMenuItem("删除公交");//以上三条是增删改
	JMenuItem empNew=new JMenuItem("添加员工");
	JMenuItem empCha=new JMenuItem("更改员工");
	JMenuItem empDel=new JMenuItem("删除员工");//以上三条是增删改
	JMenuItem maNew=new JMenuItem("添加经理");
	JMenuItem maCha=new JMenuItem("更改经理");
	JMenuItem maDel=new JMenuItem("删除经理");//以上三条是增删改
	JMenuItem roNew=new JMenuItem("添加路线");
	JMenuItem roCha=new JMenuItem("更改路线");
	JMenuItem roDel=new JMenuItem("删除路线");//以上三条是增删改
	JMenuItem roLocNew=new JMenuItem("添加市内或近郊路线");
	JMenuItem roLocCha=new JMenuItem("更改市内或近郊路线");
	JMenuItem roLocDel=new JMenuItem("删除市内或近郊路线");//以上三条是增删改
	JMenuItem roLoDNew=new JMenuItem("添加长途客运路线");
	JMenuItem roLoDCha=new JMenuItem("更改长途客运路线");
	JMenuItem roLoDDel=new JMenuItem("删除长途客运路线");//以上三条是增删改
	JMenuItem pasNew=new JMenuItem("添加乘客");
	JMenuItem pasCha=new JMenuItem("更加乘客");
	JMenuItem pasDel=new JMenuItem("删除乘客");//以上三条是增删改
	//以上为菜单项的定义
	JLabel welcome=new JLabel("W E L C O M E ! !");
	
	Panel p=new Panel();
	MyFrame(){
		super("公交管理系统");
		
		JMenu busm=new JMenu("汽车管理");
		JMenu routem=new JMenu("路线管理");
		JMenu empm=new JMenu("员工管理");
		JMenu pasm=new JMenu("乘客管理");
		JMenu meMa=new JMenu("经理管理");//在员工中
		JMenu meLoc=new JMenu("市内或近郊线路");
		JMenu meLoD=new JMenu("长途客运");//以上两条在路线中
		busm.add(busNew);busm.add(busCha);busm.add(busDel);//加完汽车管理的菜单
		empm.add(empNew);empm.add(empCha);empm.add(empDel);
		empm.addSeparator();empm.add(meMa);
		meMa.add(maNew);meMa.add(maCha);meMa.add(maDel);//加完员工管理的菜单
		routem.add(roNew);routem.add(roCha);routem.add(roDel);
		routem.addSeparator();routem.add(meLoc);
		meLoc.add(roLocNew);meLoc.add(roLocCha);meLoc.add(roLocDel);//加完市内或近郊路线管理的菜单
		routem.addSeparator();routem.add(meLoD);
		meLoD.add(roLoDNew);meLoD.add(roLoDCha);meLoD.add(roLoDDel);
		pasm.add(pasNew);pasm.add(pasCha);pasm.add(pasDel);//加完汽车管理的菜单
		//以上构造方法中的是，关于菜单的定义
		JMenuBar bar =new JMenuBar();
		setJMenuBar(bar);
		bar.add(busm);
		bar.add(routem);
		bar.add(empm);
		bar.add(pasm);
		//以上为建立菜单栏，并将菜单添加
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
		//以上为菜单栏设置
		//以下为显示测试信息
		add(welcome);
		busm.addActionListener(this);
		setSize(850,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//窗体设计
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
		//以上为bus菜单项相关
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
		//以上为emp菜单项相关
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
		//以上为ma菜单项相关
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
		//以上为ro菜单项相关
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
		//以上为roLoc菜单项相关
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
		//以上为roLoD菜单项相关
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
		//以上为pas菜单项相关
	}
}
class BusFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	//JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("类型：");
	JLabel lbus2=new JLabel("引擎号：");
	JLabel lbus3=new JLabel("最大容纳人数：");
	JLabel lbus4=new JLabel("线路：");
	//公交管理相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="类型\t引擎号\t最大容纳人数\t线路";
	
	Bus b=new Bus();
	BusFrame(){
		super();//
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见//
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
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
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		//以下为数据库相关显示内容
		txt00.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		//txt0.setText(((JMenuItem)e.getSource())
		/*此处多一个括号，括住(JMenuItem)e.getSource()*/  //.getText());
		//上面这句括号要加好;另外，此句用于在窗体中显示所点击的选项信息
		if (e.getSource()==txt00){txt1.append(txt00.getText()+"\n");}//测试用
		
		else if (e.getSource()==this.b1){//增加
			try {
				BusN();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==this.b2){//更新
			try {
				BusC();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==this.b3){//删除
			try {
				BusD();
			}
			catch(SQLException ee){
				System.out.println(ee.getMessage());
			}
		}
		else if (e.getSource()==b4){//查询
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
		/*else JOptionPane.showMessageDialog(null, "暂时未创造，完善ing");*/
		
	}
	/*public void WindowClosing(WindowEvent ee)
	{
		this.dispose();
	}*/
	public void BusN()throws SQLException/*注意要加throws此句*/{
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
	/*DELETE Bus.类型
FROM Bus
WHERE (((Bus.类型)="7"));*/
	public void BusD()throws SQLException{
		String s1="'"+txt00.getText().trim()+"'",
				s2="'"+txt01.getText().trim()+"'",
				s3="'"+txt02.getText().trim()+"'",
				s4="'"+txt03.getText().trim()+"'";
		b.delBus(s1);
	}
	public void BusR()throws SQLException{//公交查询
		String s1="'"+txt00.getText().trim()+"'";
		//String temp="select*from Bus ";
		txt1.append("\n"+b.printBus(s1));
		}
}
//路线界面
class RoFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("停靠站点：");
	JLabel lbus2=new JLabel("起始站：");
	JLabel lbus3=new JLabel("终点站：");
	JLabel lbus4=new JLabel("运行时间：");
	JLabel lbus5=new JLabel("线路列表：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="停靠站点\t起始站\t终点站\t运行时间\t线路列表";
	
	Route b=new Route();
	RoFrame(){
		//super();
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
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
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";

			b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//更新
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//删除
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//查询
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
//路线界面
class EmpFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("员工编号：");
	JLabel lbus2=new JLabel("员工姓名：");
	JLabel lbus3=new JLabel("员工年龄：");
	JLabel lbus4=new JLabel("备注信息：");
	JLabel lbus5=new JLabel("员工列表：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="员工编号\t员工姓名\t员工年龄\t备注信息\t员工列表";
	
	Employee b=new Employee();
	EmpFrame(){
		super();
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
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
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1=txt00.getText().trim(),//int型，在作为主键删值时，不用''
					s2="'"+txt01.getText().trim()+"'",
					s3=txt02.getText().trim(),
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.addEmployee(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//更新
			String s1=txt00.getText().trim(),
					s2="'"+txt01.getText().trim()+"'",
					s3=txt02.getText().trim(),
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateEmployee(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//删除
			String s1=txt00.getText().trim();
			b.delEmployee(s1);
		}
		else if (e.getSource()==b4){//查询
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
//路线界面
class PasFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("投诉人姓名：");
	JLabel lbus2=new JLabel("投诉人性别：");
	JLabel lbus3=new JLabel("投诉人年龄：");
	JLabel lbus4=new JLabel("投诉信息：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="投诉人姓名\t投诉人性别\t投诉人年龄\t投诉信息";
	
	Passenger b=new Passenger();
	PasFrame(){
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);
		p1.add(lbus4);p1.add(txt03);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.addPassenger(s1,s2,s3,s4);
		}
		else if (e.getSource()==this.b2){//更新
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.updatePassenger(s1,s2,s3,s4);
		}
		else if (e.getSource()==this.b3){//删除
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'";
			b.delPassenger(s1);
		}
		else if (e.getSource()==b4){//查询
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
//路线界面
class MaFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("职务：");
	JLabel lbus2=new JLabel("管理部门：");
	JLabel lbus3=new JLabel("职责：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="职务\t管理部门\t职责";
	
	Manager b=new Manager();
	MaFrame(){
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		//super.dispose();
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
		p1.add(lbus1);p1.add(txt00);
		p1.add(lbus2);p1.add(txt01);
		p1.add(lbus3);p1.add(txt02);

		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(txt1);
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.addManager(s1,s2,s3);
		}
		else if (e.getSource()==this.b2){//更新
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.updateManager(s1,s2,s3);
		}
		else if (e.getSource()==this.b3){//删除
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'";
			b.delManager(s1);
		}
		else if (e.getSource()==b4){//查询
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
//路线界面
class RolocFrame extends MyFrame implements Local,ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("停靠站点：");
	JLabel lbus2=new JLabel("起始站：");
	JLabel lbus3=new JLabel("终点站：");
	JLabel lbus4=new JLabel("运行时间：");
	JLabel lbus5=new JLabel("线路列表：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="停靠站点\t起始站\t终点站\t运行时间\t线路列表";
	
	Route b=new Route();
	RolocFrame(){
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
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
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			//b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//更新
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//删除
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//查询
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
//路线界面
class RolodFrame extends MyFrame implements ActionListener{//窗体界面相关
	
	Panel p1=new Panel();
	Panel p2=new Panel();
	JTextField txt00=new JTextField(10);//注意创建格式（后面有new...）
	JTextField txt01=new JTextField(10);
	JTextField txt02=new JTextField(10);
	JTextField txt03=new JTextField(10);
	JTextField txt04=new JTextField(10);
	JTextArea txt1=new JTextArea(10,50);//显示查询结果
	JLabel lbus1=new JLabel("停靠站点：");
	JLabel lbus2=new JLabel("起始站：");
	JLabel lbus3=new JLabel("终点站：");
	JLabel lbus4=new JLabel("运行时间：");
	JLabel lbus5=new JLabel("线路列表：");
	//管理的相关按钮
	JButton b1=new JButton("增加");
	JButton b2=new JButton("更改");
	JButton b3=new JButton("删除");
	JButton b4=new JButton("查询");
	String s1="停靠站点\t起始站\t终点站\t运行时间\t线路列表";
	
	Route b=new Route();
	RolodFrame(){
		super.welcome.setVisible(false);//设  作为父类  的 MyFrame类  中的    欢迎标签   为不可见
		txt1.setText(s1);
		setLayout(new BorderLayout());
		add("North",p1);
		add("Center",p2);
		p1.setLayout(new GridLayout(1,2));//此句可省可留，效果不同
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
		//以下为按钮操作
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==this.b1){//增加
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			//b.addRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b2){//更新
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.updateRoute(s1,s2,s3,s4,s5);
		}
		else if (e.getSource()==this.b3){//删除
			String s1="'"+txt00.getText().trim()+"'",
					s2="'"+txt01.getText().trim()+"'",
					s3="'"+txt02.getText().trim()+"'",
					s4="'"+txt03.getText().trim()+"'",
					s5="'"+txt04.getText().trim()+"'";
			b.delRoute(s5);
		}
		else if (e.getSource()==b4){//查询
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
