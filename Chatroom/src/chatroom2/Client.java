package chatroom2;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;

import javax.swing.border.TitledBorder;
import javax.swing.tree.*;
import javax.swing.*;

import java.io.*;//DataInputStream和DataOutputStream
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
class Cstart extends JFrame implements ActionListener{//欢迎界面
	JPanel p1=new JPanel();//north
	JPanel p2=new JPanel();//center
	JLabel welcome=new JLabel("<html><body>Welcome!<br>请选择您的登录身份 </body></html>");//" Welcome!\n请选择您的登录身份"
	JRadioButton mab=new JRadioButton("管理员");//managerbutton
	JRadioButton urb=new JRadioButton("用户");//userbutton
	ButtonGroup rbg1=new ButtonGroup();//radiobutton
	String tab;
	Cstart(){
		p1.setLayout(new GridLayout(1,2));
		setSize(300,200);
		setVisible(true);
		setTitle("聊天室客户端");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		welcome.setFont(new Font("",1,15));//设置字体大小，1是加粗
		welcome.setForeground(Color.BLUE);//设置字体颜色
		p1.add(mab);p1.add(urb);
		add(p1,BorderLayout.NORTH);
		p2.setSize(100,100);
		p2.add(welcome);
		add(p2,BorderLayout.CENTER);
		mab.addActionListener(this);
		urb.addActionListener(this);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==mab){
			//tab="管理员";//System.out.println("111"+tab);
			//this.removeAll();//p2.remove(mab);//p2.remove(urb);//validate();
			this.setVisible(false);
			Login c1=new Login();c1.tab="管理员";//System.out.println("111"+tab);
		}
		else if (e.getSource()==urb){
			this.setVisible(false);
			Login c1=new Login();c1.tab="用户";
		}
	}
}
class Login extends Cstart implements ActionListener{//登录界面
	//JPanel p1=new JPanel();//north//JPanel p2=new JPanel();//center
	JPanel p3=new JPanel();//south
	JLabel username=new JLabel("用户名：");
	JLabel password=new JLabel("密码：");
	JButton login=new JButton("登录");
	JButton register=new JButton("注册");
	JTextField uname=new JTextField(7);
	JPasswordField pword=new JPasswordField(7);
	InetAddress address=null;//InetAddress.getLocalHost();//本机机名+ip地址
	String temp;int tem;
	
	
	//JRadioButton mab=new JRadioButton("管理员");//managerbutton
	//JRadioButton urb=new JRadioButton("用户");//userbutton
	//ButtonGroup rbg1=new ButtonGroup();//radiobutton
	Login(){
		//super.welcome.setVisible(false);
		//p2.remove(super.mab);//p2.remove(super.urb);
		//super.mab.setVisible(false);//super.urb.setVisible(false);//
		p2.remove(welcome);
		setSize(300,200);
		setVisible(true);
		setTitle("聊天室客户端");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//以下是布局安排
		p1.setLayout(new GridLayout(1,2));
		p2.setLayout(new GridLayout(2,1,3,3));
		p3.setLayout(new GridLayout(1,2));
		setLayout(new BorderLayout());
		
		p2.setSize(100,100);
		p1.add(mab);p1.add(urb);
		p2.add(username);p2.add(uname);
		p2.add(password);p2.add(pword);
		p3.add(login);p3.add(register);
		
		add(p1,BorderLayout.NORTH);
		rbg1.add(mab);
		rbg1.add(urb);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		mab.addActionListener(this);
		urb.addActionListener(this);
		login.addActionListener(this);
		register.addActionListener(this);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==mab){
			tab="管理员";
		}
		else if (e.getSource()==urb){
			tab="用户";
		}
		else if (e.getSource()==login){
			//System.out.println("111"+super.tab);
			DBconnect d1=new DBconnect();
			 String str = new String(pword.getPassword());
			 temp=uname.getText();//temp=new String(getPassword());t_apass.
			try {
				if (tab=="用户"){
					if (d1.search(super.tab, temp, str)==1){
						JOptionPane.showMessageDialog(null,"登录成功");
						this.setVisible(false);
						Cchat chatclient=new Cchat();
						tem=d1.searchid(temp);//if查找不到该用户id（=null）
						if (tem==0){
							String idname=chatclient.uname();
							new UserF(idname);//随机定义id
							d1.changeid(idname,temp);//修改用户id
							User user=new User(temp,idname);
						}
						else {
							chatclient.namef(d1.getname(),d1.getid());
							new UserF(d1.getid());
						}
						chatclient.ClFrame();//
						//new C1();
						d1.close();
					}
					else JOptionPane.showMessageDialog(null,"登录失败，用户名或密码错误");
				}
				else {
					if (d1.search(super.tab, temp, str)==1){
						JOptionPane.showMessageDialog(null,"登录成功");
						this.setVisible(false);//
						new ManageF(d1);
						d1.close();
					}
					else JOptionPane.showMessageDialog(null,"登录失败，用户名或密码错误");
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource()==register){
			DBconnect d1=new DBconnect();
			 String str = new String(pword.getPassword());
			 temp=uname.getText();
			 System.out.println("111"+super.tab+"\n"+temp);
			 try {
				d1.insertuser(tab,temp,str);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}


class DBconnect{
	//String s1;
	String r1,r2,temp;
	String JDriver="sun.jdbc.odbc.JdbcOdbcDriver";
	String conURL="jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};"
			+"DBQ=E:\\study in school\\biancheng\\java\\workspace\\java\\chatroom2\\db1.mdb";
	String name1=null,id1=null;
	//
	//+"DBQ=E:\\桌面\\chatroom2\\db1.mdb";
	//String conURL="jdbc:odbc:db";
	Connection Con=null;
	Statement Stmt=null;
	ResultSet rs=null;
	DBconnect(){
		try{
			Class.forName(JDriver);
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("ForName:"+e.getMessage());
		}
		try{
			Con=DriverManager.getConnection(conURL);
			Stmt=Con.createStatement();
			
		}catch(SQLException ee){
			System.out.println(ee.getMessage());
		}
	}
	public void changeid(String id,String name) throws SQLException {
		r1="'"+id+"'";r2="'"+name+"'";
		temp="update 用户登录信息 set id="+r1+"where 用户名="+r2;
		Stmt.executeUpdate(temp);
	}
	public int searchid(String name) throws SQLException{//查找id
		r1="'"+name+"'";
		temp="select * from 用户登录信息  where 用户名 = "+r1+"and id is not null";
		rs=Stmt.executeQuery(temp);
		int i=0;
		try{
			while (rs.next()){
				if (rs!=null){
					i=1;
					name1=rs.getString("用户名");
					id1=rs.getString("id");
				}
			}
		}catch(Exception e){
			System.out.println("search:"+e.getMessage());
		}
		rs.close();//
		//Stmt.close();
		//Con.close();
		return i;
	}
	public String displayuser(){
		temp="select * from 用户";
		String result=null;
		//while ()
		return result;
	}
	public String getname(){
		return name1;
	}
	public String getid(){
		return id1;
	}
	public void insertuser(String table,String name,String paw) throws SQLException{//注册的查找及向数据库添加信息。
		r1="'"+name+"'";
		r2="'"+paw+"'";
		temp="select * from "+table+" where 用户名 = "+r1;
		rs=Stmt.executeQuery(temp);
		int i=0;
		try{
			while (rs.next()){
				if (rs!=null){
					JOptionPane.showMessageDialog(null,"注册失败，用户名已注册");
					return ;
				}
			}
		}catch(Exception e){
			System.out.println("search:"+e.getMessage());
		}
		rs.close();
		temp="insert into "+table+" values( "+r1+","+r2+")";
		Stmt.executeUpdate(temp);
		if (table=="用户"){
			temp="insert into 用户登录信息  values ("+r1+",null,null)";//id和ip暂时为null
			Stmt.executeUpdate(temp);
		}
		JOptionPane.showMessageDialog(null,"注册成功");	//rs.close();
		Stmt.close();
		Con.close();
	}
	public int search(String table,String name,String paw) throws SQLException{
		r1="'"+name+"'";
		r2="'"+paw+"'";
		temp="select * from "+table+" where 用户名 = "+r1+" and 密码 = "+r2;
		rs=Stmt.executeQuery(temp);
		int i=0;
		try{
			while (rs.next()){//System.out.print(rs.getString(1));
				if (rs!=null){//
					i=1;//System.out.println("rs");
				}//
			}
		}catch(Exception e){
			System.out.println("search:"+e.getMessage());
		}
		rs.close();//
		//Stmt.close();
		//Con.close();
		return i;
	}
	public void close() throws SQLException{
		Stmt.close();
		Con.close();//
	}
}
class ManageF extends JFrame implements ActionListener{
	JTextArea textArea=new JTextArea(7,24);
	JTextField name=new JTextField();
	JTextField id=new JTextField();
	JTextField pwd=new JTextField();
	JLabel uname=new JLabel("昵称：");
	JLabel uid=new JLabel("id：");
	JLabel upwd=new JLabel("密码：");
	JScrollPane sp=null;
	JPanel panel=new JPanel();
	JButton display=new JButton("显示");
	JButton del=new JButton("删除");
	JButton change=new JButton("更改");
	//JButton adduser=new JButton("增加用户信息");
	DBconnect d;
	ManageF(DBconnect d1){
		setTitle("管理员客户端");
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		d=d1;
		textArea.setVisible(true);
		setLayout(new BorderLayout());
		sp=new JScrollPane(textArea);
		panel.setLayout(new GridLayout(1,9));
		panel.add(uname);panel.add(name);panel.add(uid);panel.add(id);panel.add(upwd);panel.add(pwd);
		panel.add(display);panel.add(del);panel.add(change);
		panel.setBorder(new TitledBorder("管理操作区"));
		sp.setBorder(new TitledBorder("结果显示区"));
		add(panel,new BorderLayout().NORTH);
		add(sp,new BorderLayout().CENTER);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==display){
			textArea.append(d.displayuser());
		}else if (e.getSource()==del){//
			
		}
	}
}
class UserF extends JFrame{//用户界面
	String uname=null;
	JPanel p0=new JPanel();JPanel p1=new JPanel();JPanel p2=new JPanel();
	JPanel p3=new JPanel();JPanel p4=new JPanel();
	JPanel p5=new JPanel();JPanel p6=new JPanel();//好友和群聊界面
	JLabel label1=new JLabel(new ImageIcon("E:\\study in school\\大二下\\计算机网络课程设计\\作业\\尝试\\user1.png"));
	JLabel namelabel=new JLabel(uname);
	JLabel signlabel=new JLabel("我是个性签名");
	JTabbedPane tabbedPane = new JTabbedPane();//选项卡布局
	FlowLayout flowLayout = new FlowLayout();
	JButton b1=new JButton("设置");JButton b2=new JButton("查找");JButton b3=new JButton("退出");
	UserF(String uname){
		this.uname=uname;
		initFriendF();
	}
	public void initFriendF(){
		setTitle(uname+"用户端");
		setBounds(100,100,250,580);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		label1.setText("New Label");
		label1.setPreferredSize(new Dimension(74,74));//设置大小
		b2.setHorizontalTextPosition(SwingConstants.LEFT);
        b2.setHorizontalAlignment(SwingConstants.LEFT);
		flowLayout.setAlignment(FlowLayout.LEFT);//左对齐
		p0.setLayout(new BorderLayout());
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(flowLayout);
		Container c=this.getContentPane();//初始化一个容器
		c.add(p1,BorderLayout.NORTH); //在容器上添加控件
		p1.add(label1,BorderLayout.WEST);
		p1.add(namelabel,BorderLayout.CENTER);
		p1.add(signlabel,BorderLayout.SOUTH);
		p0.add(p1,BorderLayout.CENTER);
		p2.add(p4,BorderLayout.EAST);
		getContentPane().add(p2,BorderLayout.SOUTH);
		p2.add(p3);
		p3.add(b1);
		p3.add(b2);
		p4.add(b3);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("好友", null, p5, "好友列表");
		tabbedPane.addTab("群聊", null, p6,"群聊列表");
		validate();
	}
	
	/*JButton b1=null;//显示好友头像
	JPanel Jp1=new JPanel();
	JLabel username=null;
	String uname;//int pic;
	JLabel sign=new JLabel();//个签
	JLabel j1=new JLabel("好友列表");
	JButton addf=new JButton("添加好友");
	FriendP(String uname){
		this.uname=uname;
		initFriendP();
		
	}
	public void initFriendP(){
		setLayout(null);int i=0;
		sign.setBounds(new Rectangle(51,30,131,20));
		sign.setFont(new Font("Dialog",Font.PLAIN,12));
		sign.setText("我是个性签名"+(++i));
		sign.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				setBackground(new Color(192,224,248));
				sign.setToolTipText(sign.getText());
			}
			public void mouseExited(MouseEvent e){
				setBackground(null);  
			}
		});
		addf.setBounds(20,20,100,30);
		j1.setBounds(20,120,70,50);
		add(addf);add(j1);
	}*/
}

class ChatP extends Panel{
	JLabel j1=new JLabel("群聊列表");
	JButton addf=new JButton("添加群聊");
	JButton startf=new JButton("启动主群聊");
	ChatP(){
		setLayout(null);
		addf.setBounds(20,20,100,30);
		j1.setBounds(20,120,70,50);
		add(addf);add(startf);add(j1);
	}
}


class User{  
    private String name;  
    private String ip;  
  
    public User(String name, String ip) {  
        this.name = name;  
        this.ip = ip;  
    }  
    public User(){
    	
    }
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getIp() {  
        return ip;  
    }  
  
    public void setIp(String ip) {  
        this.ip = ip;  
    }  
}

class Cchat extends JFrame{  
	
    private JFrame frame;  
    private JList userList;  
    private JTextArea textArea;  
    private JTextField textField;  
    private JTextField txt_port;  
    private JTextField txt_hostIp;  
    private JTextField txt_name;  
    private JButton btn_start;  
    private JButton btn_stop;  
    private JButton btn_send;  
    private JPanel northPanel;  
    private JPanel southPanel;  
    private JScrollPane rightScroll;  
    private JScrollPane leftScroll;  
    private JSplitPane centerSplit;  
  
    private DefaultListModel listModel;  
    private boolean isConnected = false;  
  
    private Socket socket;  
    private PrintWriter writer;  
    private BufferedReader reader;  
    private MessageThread messageThread;// 负责接收消息的线程  
    private Map<String, User> onLineUsers = new HashMap<String, User>();// 所有在线用户  
    private String name="";
    private String id="";
    // 执行发送  
    public void send() {  
        if (!isConnected) {  
            JOptionPane.showMessageDialog(frame, "还没有连接服务器，无法发送消息！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        String message = textField.getText().trim();  
        if (message == null || message.equals("")) {  
            JOptionPane.showMessageDialog(frame, "消息不能为空！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        if (message.startsWith("@")&&message.indexOf(':')>-1){
        	String owner=message.substring(1,message.indexOf(':'));
        	String contest=message.substring(message.indexOf(':')+1);
        	message=message.substring(1,message.length());
        	System.out.println("client"+owner+":"+contest);
        	sendMessage(frame.getTitle() + "@" +owner+ "@" + message);//  
        }
        else sendMessage(frame.getTitle() + "@" + "ALL" + "@" + message);  
        textField.setText(null);  
    }  

	public void namef(String name, String id) {
		this.id=id;
		this.name=name;
		this.name=this.name+"("+id+")";
		User user=new User();
		user.setName(name);
	}

	public String uname() {
		String[] str1 = { "0","1","2","3","4","5","6","7","8","9" };
    	Random ran=new Random();
    	for (int i=0;i<6;i++){
    		int n=ran.nextInt(str1.length);
    		if (n<str1.length&&!(i==0&&n==0)){
    			String str=str1[n];
    			id=id+str;
    		}
    		else {
    			i--;continue;//重新随机
    		}
    		this.setTitle(id);
    	}
    	return id;
	}

	// 构造方法  
    public void ClFrame() {  
        textArea = new JTextArea();  
        textArea.setEditable(false);  
        textArea.setForeground(Color.blue);  
        textField = new JTextField();  
        txt_port = new JTextField("8080");  
        txt_hostIp = new JTextField("127.0.0.1");  
        txt_name = new JTextField(name);  
        btn_start = new JButton("连接");  
        btn_stop = new JButton("断开");  
        btn_send = new JButton("发送");  
        listModel = new DefaultListModel();  
        userList = new JList(listModel);  
  
        northPanel = new JPanel();  
        northPanel.setLayout(new GridLayout(1, 7));  
        northPanel.add(new JLabel("端口"));  
        northPanel.add(txt_port);  
        northPanel.add(new JLabel("服务器IP"));  
        northPanel.add(txt_hostIp);  
        northPanel.add(new JLabel("姓名"));  
        northPanel.add(txt_name);  
        northPanel.add(btn_start);  
        northPanel.add(btn_stop);  
        northPanel.setBorder(new TitledBorder("连接信息"));  
  
        rightScroll = new JScrollPane(textArea);  
        rightScroll.setBorder(new TitledBorder("消息显示区"));  
        leftScroll = new JScrollPane(userList);  
        leftScroll.setBorder(new TitledBorder("在线用户"));  
        southPanel = new JPanel(new BorderLayout());  
        southPanel.add(textField, "Center");  
        southPanel.add(btn_send, "East");  
        southPanel.setBorder(new TitledBorder("写消息"));  
  
        centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll,  
                rightScroll);  
        centerSplit.setDividerLocation(100);  
  
        frame = new JFrame(name+"客户机");    
        frame.setLayout(new BorderLayout());  
        frame.add(northPanel, "North");  
        frame.add(centerSplit, "Center");  
        frame.add(southPanel, "South");  
        frame.setSize(600, 400);  
        int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;  
        int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;  
        frame.setLocation((screen_width - frame.getWidth()) / 2,  
                (screen_height - frame.getHeight()) / 2);  
        frame.setVisible(true);  
  
        // 写消息的文本框中按回车键时事件  
        textField.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent arg0) {  
                send();  
            }  
        });  
  
        // 单击发送按钮时事件  
        btn_send.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                send();  
            }  
        });  
  
        // 单击连接按钮时事件  
        btn_start.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                int port;  
                if (isConnected) {  
                    JOptionPane.showMessageDialog(frame, "已处于连接上状态，不要重复连接!",  
                            "错误", JOptionPane.ERROR_MESSAGE);  
                    return;  
                }  
                try {  
                    try {  
                        port = Integer.parseInt(txt_port.getText().trim());  
                    } catch (NumberFormatException e2) {  
                        throw new Exception("端口号不符合要求!端口为整数!");  
                    }  
                    String hostIp = txt_hostIp.getText().trim();  
                    String name1 = txt_name.getText().trim();  
                    if (name1.equals("") || hostIp.equals("")) {  
                        throw new Exception("姓名、服务器IP不能为空!");  
                    }  
                    boolean flag = connectServer(port, hostIp, name);  
                    if (flag == false) {  
                        throw new Exception("与服务器连接失败!");  
                    }  
                    frame.setTitle(name1);  
                    JOptionPane.showMessageDialog(frame, "成功连接!");  
                } catch (Exception exc) {  
                    JOptionPane.showMessageDialog(frame, exc.getMessage(),  
                            "错误", JOptionPane.ERROR_MESSAGE);  
                }  
            }  
        });  
  
        // 单击断开按钮时事件  
        btn_stop.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (!isConnected) {  
                    JOptionPane.showMessageDialog(frame, "已处于断开状态，不要重复断开!",  
                            "错误", JOptionPane.ERROR_MESSAGE);  
                    return;  
                }  
                try {  
                    boolean flag = closeConnection();// 断开连接  
                    if (flag == false) {  
                        throw new Exception("断开连接发生异常！");  
                    }  
                    JOptionPane.showMessageDialog(frame, "成功断开!");  
                } catch (Exception exc) {  
                    JOptionPane.showMessageDialog(frame, exc.getMessage(),  
                            "错误", JOptionPane.ERROR_MESSAGE);  
                }  
            }  
        });  
  
        // 关闭窗口时事件  
        frame.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                if (isConnected) {  
                    closeConnection();// 关闭连接  
                }  
                System.exit(0);// 退出程序  
            }  
        });  
    }  
    public boolean connectServer(int port, String hostIp, String name) {  
        // 连接服务器  
        try {  
            socket = new Socket(hostIp, port);// 根据端口号和服务器ip建立连接  
            writer = new PrintWriter(socket.getOutputStream());  
            reader = new BufferedReader(new InputStreamReader(socket  
                    .getInputStream()));  
            // 发送客户端用户基本信息(用户名和ip地址)  
            sendMessage(name + "@" + socket.getLocalAddress().toString());  
            // 开启接收消息的线程  
            messageThread = new MessageThread(reader, textArea);  
            messageThread.start();  
            isConnected = true;// 已经连接上了  
            return true;  
        } catch (Exception e) {  
            textArea.append("与端口号为：" + port + "    IP地址为：" + hostIp  
                    + "   的服务器连接失败!" + "\r\n");  
            isConnected = false;// 未连接上  
            return false;  
        }  
    }  
  
    /**  
     * 发送消息  
     *   
     * @param message  
     */  
    public void sendMessage(String message) {  
        writer.println(message);  
        writer.flush();  
    }  
  
    /**  
     * 客户端主动关闭连接  
     */  
    @SuppressWarnings("deprecation")  
    public synchronized boolean closeConnection() {  
        try {  
            sendMessage("CLOSE");// 发送断开连接命令给服务器  
            messageThread.stop();// 停止接受消息线程  
            // 释放资源  
            if (reader != null) {  
                reader.close();  
            }  
            if (writer != null) {  
                writer.close();  
            }  
            if (socket != null) {  
                socket.close();  
            }  
            isConnected = false;  
            return true;  
        } catch (IOException e1) {  
            e1.printStackTrace();  
            isConnected = true;  
            return false;  
        }  
    }  
  
    // 不断接收消息的线程  
    class MessageThread extends Thread {  
        private BufferedReader reader;  
        private JTextArea textArea;  
  
        // 接收消息线程的构造方法  
        public MessageThread(BufferedReader reader, JTextArea textArea) {  
            this.reader = reader;  
            this.textArea = textArea;  
        }  
  
        // 被动的关闭连接  
        public synchronized void closeCon() throws Exception {  
            // 清空用户列表  
            listModel.removeAllElements();  
            // 被动的关闭连接释放资源  
            if (reader != null) {  
                reader.close();  
            }  
            if (writer != null) {  
                writer.close();  
            }  
            if (socket != null) {  
                socket.close();  
            }  
            isConnected = false;// 修改状态为断开  
        }  
  
        public void run() {  
            String message = "";  
            while (true) {  
                try {  
                    message = reader.readLine();  
                    StringTokenizer stringTokenizer = new StringTokenizer(  
                            message, "/@");  
                    String command = stringTokenizer.nextToken();// 命令  
                    if (command.equals("CLOSE"))// 服务器已关闭命令  
                    {  
                        textArea.append("服务器已关闭!\r\n");  
                        closeCon();// 被动的关闭连接  
                        return;// 结束线程  
                    } else if (command.equals("ADD")) {// 有用户上线更新在线列表  
                        String username = "";  
                        String userIp = "";  
                        if ((username = stringTokenizer.nextToken()) != null  
                                && (userIp = stringTokenizer.nextToken()) != null) {  
                            User user = new User(username, userIp);  
                            onLineUsers.put(username, user);  
                            listModel.addElement(username);  
                        }  
                    } else if (command.equals("DELETE")) {// 有用户下线更新在线列表  
                        String username = stringTokenizer.nextToken();  
                        User user = (User) onLineUsers.get(username);  
                        onLineUsers.remove(user);  
                        listModel.removeElement(username);  
                    } else if (command.equals("USERLIST")) {// 加载在线用户列表  
                        int size = Integer  
                                .parseInt(stringTokenizer.nextToken());  
                        String username = null;  
                        String userIp = null;  
                        for (int i = 0; i < size; i++) {  
                            username = stringTokenizer.nextToken();  
                            userIp = stringTokenizer.nextToken();  
                            User user = new User(username, userIp);  
                            onLineUsers.put(username, user);  
                            listModel.addElement(username);  
                        }  
                    } else if (command.equals("MAX")) {// 人数已达上限  
                        textArea.append(stringTokenizer.nextToken()  
                                + stringTokenizer.nextToken() + "\r\n");  
                        closeCon();// 被动的关闭连接  
                        JOptionPane.showMessageDialog(frame, "服务器缓冲区已满！", "错误",  
                                JOptionPane.ERROR_MESSAGE);  
                        return;// 结束线程  
                    } else {// 普通消息  
                        textArea.append(message + "\r\n");  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
} 

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Cstart();
	}

}
