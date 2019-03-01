import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;


public class StartFrame extends JFrame implements ActionListener,DocumentListener {
	JTextArea txt = new JTextArea(3,4);
	
	JPopupMenu popupm = new JPopupMenu();
	JMenuItem revokepm =new JMenuItem("撤销(U)", 'U');
	JMenuItem resumepm =new JMenuItem("恢复(Y)", 'Y');
	JMenuItem cutpm =new JMenuItem("剪切(T)", 'T');
	JMenuItem copypm =new JMenuItem("复制(C)", 'C');
	JMenuItem pastepm =new JMenuItem("粘贴(P)", 'P');
	JMenuItem deletepm =new JMenuItem("删除(D)", 'D');
	JMenuItem selectAllpm =new JMenuItem("全选(A)", 'A');
	
	JMenuBar bar = new JMenuBar();
	JMenu file = new JMenu("文件(F)");
	JMenu edit = new JMenu("编辑(E)");
	JMenu format = new JMenu("格式(O)");
	JMenu view = new JMenu("查看(V)");
	//JMenu file = new JMenu("文件");
	JMenuItem newf =new JMenuItem("新建(N)", 'N');
	JMenuItem open =new JMenuItem("打开(O)", 'O');//test,'O'
	JMenuItem save =new JMenuItem("保存(S)", 'S');
	JMenuItem saveas =new JMenuItem("另存为(A)", 'A');
	JMenuItem exit =new JMenuItem("退出(X)", 'X');
	
	JMenuItem revoke =new JMenuItem("撤销(U)", 'U');
	JMenuItem resume =new JMenuItem("恢复(Y)", 'Y');
	JMenuItem cut =new JMenuItem("剪切(T)", 'T');
	JMenuItem copy =new JMenuItem("复制(C)", 'C');
	JMenuItem paste =new JMenuItem("粘贴(P)",'P');
	JMenuItem delete =new JMenuItem("删除(L)", 'L');
	JMenuItem find =new JMenuItem("查找(F)", 'F');
	JMenuItem replace =new JMenuItem("替换(R)", 'R');
	JMenuItem selectAll =new JMenuItem("全选(A)", 'A');
	JMenuItem time =new JMenuItem("时间/日期(D)", 'D');
	
	JCheckBoxMenuItem newline =new JCheckBoxMenuItem("自动换行(W)", true);
	JMenuItem setfont =new JMenuItem("字体(F)");//, 'F'
	
	JMenuItem help =new JMenuItem("帮助(H)", 'H');
	JFrame currentframe = this;
	
	ReplaceDialog repdia = new ReplaceDialog(currentframe, "替换", false, txt);
	FindDialog findia = new FindDialog(currentframe, "查找", false, txt);
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Clipboard ClipBoard = toolkit.getSystemClipboard();
	
	UndoManager undo = new UndoManager();
	//UndoableEditListener undoHandler = new UndoHandler();
	
	Boolean LineWrap = true;
	Boolean HasSaved = true;
	String FileAddr = "";
	
	StartFrame() {
		setBounds(200,200,600,500);
		setTitle("记事本");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		setTxtFrameLook();
		setShortcutKey();
		actionProcessor();
		validate();
	}
	
	public void setTxtFrameLook() {
		popupm.add(revokepm); popupm.add(resumepm);
		popupm.addSeparator();
		popupm.add(cutpm); popupm.add(copypm); popupm.add(pastepm); popupm.add(deletepm);
		popupm.addSeparator();
		popupm.add(selectAllpm);
		
		file.add(newf); file.add(open); file.add(save); file.add(saveas);
		file.addSeparator();
		file.add(exit);
		
		edit.add(revoke);edit.add(resume);
		edit.addSeparator();
		edit.add(cut); edit.add(copy); edit.add(paste); edit.add(delete);
		edit.addSeparator();
		edit.add(find); edit.add(replace);
		edit.addSeparator();
		edit.add(selectAll); edit.add(time);
		
		format.add(newline); format.add(setfont);
		view.add(help);
		bar.add(file); bar.add(edit); bar.add(format); bar.add(view);
		this.setJMenuBar(bar);
		add("Center",new JScrollPane(txt));
	}
	
	public void setShortcutKey() {
		file.setMnemonic('F');
		edit.setMnemonic('E');
		format.setMnemonic('O');
		view.setMnemonic('V');
		
		newf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		//saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		
		revoke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		time.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		newline.setMnemonic('W');
		setfont.setMnemonic('F');
		
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}
	
	public void actionProcessor() {
		(txt.getDocument()).addDocumentListener(this);
		(txt.getDocument()).addUndoableEditListener(undo);
		edit.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e){
				checkIsEditable();
				checkUndoAndRedo();
			}
			public void menuDeselected(MenuEvent e) {
				checkIsEditable();
				checkUndoAndRedo();
			}
			public void menuSelected(MenuEvent e) {
				checkIsEditable();
				checkUndoAndRedo();
			}
		});
		popupm.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				checkIsEditable();
				checkUndoAndRedo();
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				checkIsEditable();
				checkUndoAndRedo();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				checkIsEditable();
				checkUndoAndRedo();
			}
		});
		
		newf.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		exit.addActionListener(this);
		
		revoke.addActionListener(this);
		resume.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		delete.addActionListener(this);
		//find.addActionListener(this);
		//replace.addActionListener(this);
		selectAll.addActionListener(this);
		time.addActionListener(this);
		
		revokepm.addActionListener(this);
		resumepm.addActionListener(this);
		cutpm.addActionListener(this);
		copypm.addActionListener(this);
		pastepm.addActionListener(this);
		deletepm.addActionListener(this);
		selectAllpm.addActionListener(this);
		
		newline.addActionListener(this);
		setfont.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent ee){
						FontDialog fontdia = new FontDialog(currentframe,"字体",true,txt);
						//fontdia.setVisible(true);
					}
				});
		find.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent ee){
						findia.setVisible(true);
					}
				});
		replace.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent ee){
						//FontDialog fontdia = new FontDialog(currentframe,"替换",true,txt);
						repdia.setVisible(true);
					}
				});
		txt.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getButton() == MouseEvent.BUTTON3){
					popupm.show(e.getComponent(),e.getX(),e.getY());
				}
			}
		});
		help.addActionListener(this);
	}
	
	public void open() {
		FileDialog fileDia = new FileDialog(this, "打开", FileDialog.LOAD);
		fileDia.setVisible(true);
		
		String dir = fileDia.getDirectory();
		String name = fileDia.getFile();
		if (!(dir+name).equals(FileAddr) || !name.equals(FileAddr)) HasSaved = false;
		if (dir!=null && name!=null) {
			FileAddr = dir + name;//有bug
			BufferedReader in;
			try {
				in = new BufferedReader( new InputStreamReader(new FileInputStream(FileAddr)) );
				String temp = "";
				String nowline;
				while ((nowline=(String)in.readLine()) != null) {
					temp = temp + nowline + "\n";
				}
				in.close();
				setTitle(name);
				txt.setText(temp);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		HasSaved = true;
	}
	
	public void save() {
		FileDialog fileDia = new FileDialog(this,"保存",FileDialog.SAVE);
		fileDia.setVisible(true);
		String dir = fileDia.getDirectory();
		String name = fileDia.getFile();
		try {
			if (dir!=null && name!= null) {
				FileAddr = dir + name ;		//自己添+ ".txt"
				OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(FileAddr) );
				setTitle(name);
				out.write(txt.getText());//,0 , txt.getText().length()
				out.close();
				HasSaved = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "写入错误", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void saveWithoutShowingDia() {
		try {
			System.out.println("ok "+FileAddr);
			OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(FileAddr) );
			out.write(txt.getText());
			out.close();
			HasSaved = true;
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "写入错误", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void saveWarn(){
		int opnum;
		if (FileAddr.equals("")) {
			if (HasSaved == false) {
				opnum = JOptionPane.showConfirmDialog(this,"是否将更改保存到 无标题","记事本",JOptionPane.YES_NO_OPTION);//_CANCEL
				if (opnum == 0) {
					save();
				}
			}
		}
		else {
			if (HasSaved == false) {
				opnum = JOptionPane.showConfirmDialog(this,"是否将更改保存到"+FileAddr,"记事本",JOptionPane.YES_NO_OPTION);
				if (opnum == 0) {
					saveWithoutShowingDia();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 判断是否需要弹出保存或保存修改。
		1.判断s1=ta.getText()和当前的getText()是否相等（不是最佳，文章长效率低）
		2.JTextArea事件响应，DocumentEvent修改文本区内容
		 * */
		if (e.getSource() == newf) {
			saveWarn();
			txt.setText(null);
			setTitle("记事本");
			HasSaved = true;
			FileAddr = "";
		}
		else if (e.getSource() == open) {
			saveWarn();
			open();
		}
		else if (e.getSource() == exit) {
			saveWarn();
			System.exit(0);
		}
		else if (e.getSource() == save) {
			if (HasSaved == false) {
				save();
			} else {
				saveWithoutShowingDia();
			}
		}
		else if (e.getSource()== saveas) {
			save();
		}
		else if (e.getSource()==revoke || e.getSource()==revokepm) {
			txt.requestFocus();
			if (undo.canUndo()) {
				undo.undo();
			}
		}
		else if (e.getSource() == resume || e.getSource() == resumepm) {
			if (undo.canRedo()) {
				undo.redo();
			}
		}
		else if (e.getSource()==cut || e.getSource()==cutpm) {
			txt.requestFocus();
			String TextContent = txt.getSelectedText();
			StringSelection selection = new StringSelection(TextContent);
			ClipBoard.setContents(selection, null);
			txt.replaceRange("", txt.getSelectionStart(), txt.getSelectionEnd());
		}
		else if (e.getSource()==copy || e.getSource()==copypm) {
			txt.requestFocus();
			String TextContent = txt.getSelectedText();
			StringSelection selection = new StringSelection(TextContent);
			ClipBoard.setContents(selection, null);
		}
		else if (e.getSource()==paste || e.getSource()==pastepm) {
			txt.requestFocus();
			Transferable contents = ClipBoard.getContents(this);
			if (contents == null) return;
			String TextContent = "";
			try {
				TextContent = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			txt.replaceRange(TextContent, txt.getSelectionStart(), txt.getSelectionEnd());
		}
		else if (e.getSource()==delete || e.getSource()==deletepm) {
			txt.requestFocus();
			txt.replaceRange("", txt.getSelectionStart(), txt.getSelectionEnd());
		}
		else if (e.getSource() == selectAll || e.getSource()==selectAllpm) {
			txt.selectAll();
		}
		else if (e.getSource() == time) {
			Calendar NowTime = Calendar.getInstance();
			Date date = NowTime.getTime();
			txt.insert(date.toString(), txt.getCaretPosition());
		}
		else if (e.getSource() == newline) {
			if (newline.getState() == true) {
				//newline.setState(false);
				txt.setLineWrap(true);
			}
			else {
				txt.setLineWrap(false);
				//System.out.println(newline.getState());
			}
		}
		else if (e.getSource() == help) {
			JOptionPane.showMessageDialog(this, "20161856124", "制作", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void checkIsEditable() {
		String SelectedText = txt.getSelectedText();
		if (SelectedText == null) {
			cut.setEnabled(false);
			copy.setEnabled(false);
			delete.setEnabled(false);
			cutpm.setEnabled(false);
			copypm.setEnabled(false);
			deletepm.setEnabled(false);
		}
		else {
			cut.setEnabled(true);
			copy.setEnabled(true);
			delete.setEnabled(true);
			cutpm.setEnabled(true);
			copypm.setEnabled(true);
			deletepm.setEnabled(true);
		}
		Transferable contents = ClipBoard.getContents(this);
		if (contents == null) {
			paste.setEnabled(false);
			pastepm.setEnabled(false);
		}
		else {
			paste.setEnabled(true);
			pastepm.setEnabled(true);
		}
	}

	public void checkUndoAndRedo() {
		if (undo.canUndo()) {
			revoke.setEnabled(true);
			revokepm.setEnabled(true);
		}
		else {
			revoke.setEnabled(false);
			revokepm.setEnabled(false);
		}
		if (undo.canRedo()) {
			resume.setEnabled(true);
			resumepm.setEnabled(true);
		}
		else {
			resume.setEnabled(false);
			resumepm.setEnabled(false);
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		HasSaved = false;
	}

	public void insertUpdate(DocumentEvent e) {
		HasSaved = false;
	}

	public void removeUpdate(DocumentEvent e) {
		HasSaved = false;
	}
}