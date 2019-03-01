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

import java.io.*;//DataInputStream��DataOutputStream
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
class Cstart extends JFrame implements ActionListener{//��ӭ����
	JPanel p1=new JPanel();//north
	JPanel p2=new JPanel();//center
	JLabel welcome=new JLabel("<html><body>Welcome!<br>��ѡ�����ĵ�¼��� </body></html>");//" Welcome!\n��ѡ�����ĵ�¼���"
	JRadioButton mab=new JRadioButton("����Ա");//managerbutton
	JRadioButton urb=new JRadioButton("�û�");//userbutton
	ButtonGroup rbg1=new ButtonGroup();//radiobutton
	String tab;
	Cstart(){
		p1.setLayout(new GridLayout(1,2));
		setSize(300,200);
		setVisible(true);
		setTitle("�����ҿͻ���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		welcome.setFont(new Font("",1,15));//���������С��1�ǼӴ�
		welcome.setForeground(Color.BLUE);//����������ɫ
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
			//tab="����Ա";//System.out.println("111"+tab);
			//this.removeAll();//p2.remove(mab);//p2.remove(urb);//validate();
			this.setVisible(false);
			Login c1=new Login();c1.tab="����Ա";//System.out.println("111"+tab);
		}
		else if (e.getSource()==urb){
			this.setVisible(false);
			Login c1=new Login();c1.tab="�û�";
		}
	}
}
class Login extends Cstart implements ActionListener{//��¼����
	//JPanel p1=new JPanel();//north//JPanel p2=new JPanel();//center
	JPanel p3=new JPanel();//south
	JLabel username=new JLabel("�û�����");
	JLabel password=new JLabel("���룺");
	JButton login=new JButton("��¼");
	JButton register=new JButton("ע��");
	JTextField uname=new JTextField(7);
	JPasswordField pword=new JPasswordField(7);
	InetAddress address=null;//InetAddress.getLocalHost();//��������+ip��ַ
	String temp;int tem;
	
	
	//JRadioButton mab=new JRadioButton("����Ա");//managerbutton
	//JRadioButton urb=new JRadioButton("�û�");//userbutton
	//ButtonGroup rbg1=new ButtonGroup();//radiobutton
	Login(){
		//super.welcome.setVisible(false);
		//p2.remove(super.mab);//p2.remove(super.urb);
		//super.mab.setVisible(false);//super.urb.setVisible(false);//
		p2.remove(welcome);
		setSize(300,200);
		setVisible(true);
		setTitle("�����ҿͻ���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//�����ǲ��ְ���
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
			tab="����Ա";
		}
		else if (e.getSource()==urb){
			tab="�û�";
		}
		else if (e.getSource()==login){
			//System.out.println("111"+super.tab);
			DBconnect d1=new DBconnect();
			 String str = new String(pword.getPassword());
			 temp=uname.getText();//temp=new String(getPassword());t_apass.
			try {
				if (tab=="�û�"){
					if (d1.search(super.tab, temp, str)==1){
						JOptionPane.showMessageDialog(null,"��¼�ɹ�");
						this.setVisible(false);
						Cchat chatclient=new Cchat();
						tem=d1.searchid(temp);//if���Ҳ������û�id��=null��
						if (tem==0){
							String idname=chatclient.uname();
							new UserF(idname);//�������id
							d1.changeid(idname,temp);//�޸��û�id
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
					else JOptionPane.showMessageDialog(null,"��¼ʧ�ܣ��û������������");
				}
				else {
					if (d1.search(super.tab, temp, str)==1){
						JOptionPane.showMessageDialog(null,"��¼�ɹ�");
						this.setVisible(false);//
						new ManageF(d1);
						d1.close();
					}
					else JOptionPane.showMessageDialog(null,"��¼ʧ�ܣ��û������������");
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
	//+"DBQ=E:\\����\\chatroom2\\db1.mdb";
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
		temp="update �û���¼��Ϣ set id="+r1+"where �û���="+r2;
		Stmt.executeUpdate(temp);
	}
	public int searchid(String name) throws SQLException{//����id
		r1="'"+name+"'";
		temp="select * from �û���¼��Ϣ  where �û��� = "+r1+"and id is not null";
		rs=Stmt.executeQuery(temp);
		int i=0;
		try{
			while (rs.next()){
				if (rs!=null){
					i=1;
					name1=rs.getString("�û���");
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
		temp="select * from �û�";
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
	public void insertuser(String table,String name,String paw) throws SQLException{//ע��Ĳ��Ҽ������ݿ������Ϣ��
		r1="'"+name+"'";
		r2="'"+paw+"'";
		temp="select * from "+table+" where �û��� = "+r1;
		rs=Stmt.executeQuery(temp);
		int i=0;
		try{
			while (rs.next()){
				if (rs!=null){
					JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ��û�����ע��");
					return ;
				}
			}
		}catch(Exception e){
			System.out.println("search:"+e.getMessage());
		}
		rs.close();
		temp="insert into "+table+" values( "+r1+","+r2+")";
		Stmt.executeUpdate(temp);
		if (table=="�û�"){
			temp="insert into �û���¼��Ϣ  values ("+r1+",null,null)";//id��ip��ʱΪnull
			Stmt.executeUpdate(temp);
		}
		JOptionPane.showMessageDialog(null,"ע��ɹ�");	//rs.close();
		Stmt.close();
		Con.close();
	}
	public int search(String table,String name,String paw) throws SQLException{
		r1="'"+name+"'";
		r2="'"+paw+"'";
		temp="select * from "+table+" where �û��� = "+r1+" and ���� = "+r2;
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
	JLabel uname=new JLabel("�ǳƣ�");
	JLabel uid=new JLabel("id��");
	JLabel upwd=new JLabel("���룺");
	JScrollPane sp=null;
	JPanel panel=new JPanel();
	JButton display=new JButton("��ʾ");
	JButton del=new JButton("ɾ��");
	JButton change=new JButton("����");
	//JButton adduser=new JButton("�����û���Ϣ");
	DBconnect d;
	ManageF(DBconnect d1){
		setTitle("����Ա�ͻ���");
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
		panel.setBorder(new TitledBorder("���������"));
		sp.setBorder(new TitledBorder("�����ʾ��"));
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
class UserF extends JFrame{//�û�����
	String uname=null;
	JPanel p0=new JPanel();JPanel p1=new JPanel();JPanel p2=new JPanel();
	JPanel p3=new JPanel();JPanel p4=new JPanel();
	JPanel p5=new JPanel();JPanel p6=new JPanel();//���Ѻ�Ⱥ�Ľ���
	JLabel label1=new JLabel(new ImageIcon("E:\\study in school\\�����\\���������γ����\\��ҵ\\����\\user1.png"));
	JLabel namelabel=new JLabel(uname);
	JLabel signlabel=new JLabel("���Ǹ���ǩ��");
	JTabbedPane tabbedPane = new JTabbedPane();//ѡ�����
	FlowLayout flowLayout = new FlowLayout();
	JButton b1=new JButton("����");JButton b2=new JButton("����");JButton b3=new JButton("�˳�");
	UserF(String uname){
		this.uname=uname;
		initFriendF();
	}
	public void initFriendF(){
		setTitle(uname+"�û���");
		setBounds(100,100,250,580);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		label1.setText("New Label");
		label1.setPreferredSize(new Dimension(74,74));//���ô�С
		b2.setHorizontalTextPosition(SwingConstants.LEFT);
        b2.setHorizontalAlignment(SwingConstants.LEFT);
		flowLayout.setAlignment(FlowLayout.LEFT);//�����
		p0.setLayout(new BorderLayout());
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(flowLayout);
		Container c=this.getContentPane();//��ʼ��һ������
		c.add(p1,BorderLayout.NORTH); //����������ӿؼ�
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
		tabbedPane.addTab("����", null, p5, "�����б�");
		tabbedPane.addTab("Ⱥ��", null, p6,"Ⱥ���б�");
		validate();
	}
	
	/*JButton b1=null;//��ʾ����ͷ��
	JPanel Jp1=new JPanel();
	JLabel username=null;
	String uname;//int pic;
	JLabel sign=new JLabel();//��ǩ
	JLabel j1=new JLabel("�����б�");
	JButton addf=new JButton("��Ӻ���");
	FriendP(String uname){
		this.uname=uname;
		initFriendP();
		
	}
	public void initFriendP(){
		setLayout(null);int i=0;
		sign.setBounds(new Rectangle(51,30,131,20));
		sign.setFont(new Font("Dialog",Font.PLAIN,12));
		sign.setText("���Ǹ���ǩ��"+(++i));
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
	JLabel j1=new JLabel("Ⱥ���б�");
	JButton addf=new JButton("���Ⱥ��");
	JButton startf=new JButton("������Ⱥ��");
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
    private MessageThread messageThread;// ���������Ϣ���߳�  
    private Map<String, User> onLineUsers = new HashMap<String, User>();// ���������û�  
    private String name="";
    private String id="";
    // ִ�з���  
    public void send() {  
        if (!isConnected) {  
            JOptionPane.showMessageDialog(frame, "��û�����ӷ��������޷�������Ϣ��", "����",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        String message = textField.getText().trim();  
        if (message == null || message.equals("")) {  
            JOptionPane.showMessageDialog(frame, "��Ϣ����Ϊ�գ�", "����",  
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
    			i--;continue;//�������
    		}
    		this.setTitle(id);
    	}
    	return id;
	}

	// ���췽��  
    public void ClFrame() {  
        textArea = new JTextArea();  
        textArea.setEditable(false);  
        textArea.setForeground(Color.blue);  
        textField = new JTextField();  
        txt_port = new JTextField("8080");  
        txt_hostIp = new JTextField("127.0.0.1");  
        txt_name = new JTextField(name);  
        btn_start = new JButton("����");  
        btn_stop = new JButton("�Ͽ�");  
        btn_send = new JButton("����");  
        listModel = new DefaultListModel();  
        userList = new JList(listModel);  
  
        northPanel = new JPanel();  
        northPanel.setLayout(new GridLayout(1, 7));  
        northPanel.add(new JLabel("�˿�"));  
        northPanel.add(txt_port);  
        northPanel.add(new JLabel("������IP"));  
        northPanel.add(txt_hostIp);  
        northPanel.add(new JLabel("����"));  
        northPanel.add(txt_name);  
        northPanel.add(btn_start);  
        northPanel.add(btn_stop);  
        northPanel.setBorder(new TitledBorder("������Ϣ"));  
  
        rightScroll = new JScrollPane(textArea);  
        rightScroll.setBorder(new TitledBorder("��Ϣ��ʾ��"));  
        leftScroll = new JScrollPane(userList);  
        leftScroll.setBorder(new TitledBorder("�����û�"));  
        southPanel = new JPanel(new BorderLayout());  
        southPanel.add(textField, "Center");  
        southPanel.add(btn_send, "East");  
        southPanel.setBorder(new TitledBorder("д��Ϣ"));  
  
        centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll,  
                rightScroll);  
        centerSplit.setDividerLocation(100);  
  
        frame = new JFrame(name+"�ͻ���");    
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
  
        // д��Ϣ���ı����а��س���ʱ�¼�  
        textField.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent arg0) {  
                send();  
            }  
        });  
  
        // �������Ͱ�ťʱ�¼�  
        btn_send.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                send();  
            }  
        });  
  
        // �������Ӱ�ťʱ�¼�  
        btn_start.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                int port;  
                if (isConnected) {  
                    JOptionPane.showMessageDialog(frame, "�Ѵ���������״̬����Ҫ�ظ�����!",  
                            "����", JOptionPane.ERROR_MESSAGE);  
                    return;  
                }  
                try {  
                    try {  
                        port = Integer.parseInt(txt_port.getText().trim());  
                    } catch (NumberFormatException e2) {  
                        throw new Exception("�˿ںŲ�����Ҫ��!�˿�Ϊ����!");  
                    }  
                    String hostIp = txt_hostIp.getText().trim();  
                    String name1 = txt_name.getText().trim();  
                    if (name1.equals("") || hostIp.equals("")) {  
                        throw new Exception("������������IP����Ϊ��!");  
                    }  
                    boolean flag = connectServer(port, hostIp, name);  
                    if (flag == false) {  
                        throw new Exception("�����������ʧ��!");  
                    }  
                    frame.setTitle(name1);  
                    JOptionPane.showMessageDialog(frame, "�ɹ�����!");  
                } catch (Exception exc) {  
                    JOptionPane.showMessageDialog(frame, exc.getMessage(),  
                            "����", JOptionPane.ERROR_MESSAGE);  
                }  
            }  
        });  
  
        // �����Ͽ���ťʱ�¼�  
        btn_stop.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (!isConnected) {  
                    JOptionPane.showMessageDialog(frame, "�Ѵ��ڶϿ�״̬����Ҫ�ظ��Ͽ�!",  
                            "����", JOptionPane.ERROR_MESSAGE);  
                    return;  
                }  
                try {  
                    boolean flag = closeConnection();// �Ͽ�����  
                    if (flag == false) {  
                        throw new Exception("�Ͽ����ӷ����쳣��");  
                    }  
                    JOptionPane.showMessageDialog(frame, "�ɹ��Ͽ�!");  
                } catch (Exception exc) {  
                    JOptionPane.showMessageDialog(frame, exc.getMessage(),  
                            "����", JOptionPane.ERROR_MESSAGE);  
                }  
            }  
        });  
  
        // �رմ���ʱ�¼�  
        frame.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                if (isConnected) {  
                    closeConnection();// �ر�����  
                }  
                System.exit(0);// �˳�����  
            }  
        });  
    }  
    public boolean connectServer(int port, String hostIp, String name) {  
        // ���ӷ�����  
        try {  
            socket = new Socket(hostIp, port);// ���ݶ˿ںźͷ�����ip��������  
            writer = new PrintWriter(socket.getOutputStream());  
            reader = new BufferedReader(new InputStreamReader(socket  
                    .getInputStream()));  
            // ���Ϳͻ����û�������Ϣ(�û�����ip��ַ)  
            sendMessage(name + "@" + socket.getLocalAddress().toString());  
            // ����������Ϣ���߳�  
            messageThread = new MessageThread(reader, textArea);  
            messageThread.start();  
            isConnected = true;// �Ѿ���������  
            return true;  
        } catch (Exception e) {  
            textArea.append("��˿ں�Ϊ��" + port + "    IP��ַΪ��" + hostIp  
                    + "   �ķ���������ʧ��!" + "\r\n");  
            isConnected = false;// δ������  
            return false;  
        }  
    }  
  
    /**  
     * ������Ϣ  
     *   
     * @param message  
     */  
    public void sendMessage(String message) {  
        writer.println(message);  
        writer.flush();  
    }  
  
    /**  
     * �ͻ��������ر�����  
     */  
    @SuppressWarnings("deprecation")  
    public synchronized boolean closeConnection() {  
        try {  
            sendMessage("CLOSE");// ���ͶϿ����������������  
            messageThread.stop();// ֹͣ������Ϣ�߳�  
            // �ͷ���Դ  
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
  
    // ���Ͻ�����Ϣ���߳�  
    class MessageThread extends Thread {  
        private BufferedReader reader;  
        private JTextArea textArea;  
  
        // ������Ϣ�̵߳Ĺ��췽��  
        public MessageThread(BufferedReader reader, JTextArea textArea) {  
            this.reader = reader;  
            this.textArea = textArea;  
        }  
  
        // �����Ĺر�����  
        public synchronized void closeCon() throws Exception {  
            // ����û��б�  
            listModel.removeAllElements();  
            // �����Ĺر������ͷ���Դ  
            if (reader != null) {  
                reader.close();  
            }  
            if (writer != null) {  
                writer.close();  
            }  
            if (socket != null) {  
                socket.close();  
            }  
            isConnected = false;// �޸�״̬Ϊ�Ͽ�  
        }  
  
        public void run() {  
            String message = "";  
            while (true) {  
                try {  
                    message = reader.readLine();  
                    StringTokenizer stringTokenizer = new StringTokenizer(  
                            message, "/@");  
                    String command = stringTokenizer.nextToken();// ����  
                    if (command.equals("CLOSE"))// �������ѹر�����  
                    {  
                        textArea.append("�������ѹر�!\r\n");  
                        closeCon();// �����Ĺر�����  
                        return;// �����߳�  
                    } else if (command.equals("ADD")) {// ���û����߸��������б�  
                        String username = "";  
                        String userIp = "";  
                        if ((username = stringTokenizer.nextToken()) != null  
                                && (userIp = stringTokenizer.nextToken()) != null) {  
                            User user = new User(username, userIp);  
                            onLineUsers.put(username, user);  
                            listModel.addElement(username);  
                        }  
                    } else if (command.equals("DELETE")) {// ���û����߸��������б�  
                        String username = stringTokenizer.nextToken();  
                        User user = (User) onLineUsers.get(username);  
                        onLineUsers.remove(user);  
                        listModel.removeElement(username);  
                    } else if (command.equals("USERLIST")) {// ���������û��б�  
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
                    } else if (command.equals("MAX")) {// �����Ѵ�����  
                        textArea.append(stringTokenizer.nextToken()  
                                + stringTokenizer.nextToken() + "\r\n");  
                        closeCon();// �����Ĺر�����  
                        JOptionPane.showMessageDialog(frame, "������������������", "����",  
                                JOptionPane.ERROR_MESSAGE);  
                        return;// �����߳�  
                    } else {// ��ͨ��Ϣ  
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
