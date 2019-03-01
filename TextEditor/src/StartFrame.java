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
	JMenuItem revokepm =new JMenuItem("����(U)", 'U');
	JMenuItem resumepm =new JMenuItem("�ָ�(Y)", 'Y');
	JMenuItem cutpm =new JMenuItem("����(T)", 'T');
	JMenuItem copypm =new JMenuItem("����(C)", 'C');
	JMenuItem pastepm =new JMenuItem("ճ��(P)", 'P');
	JMenuItem deletepm =new JMenuItem("ɾ��(D)", 'D');
	JMenuItem selectAllpm =new JMenuItem("ȫѡ(A)", 'A');
	
	JMenuBar bar = new JMenuBar();
	JMenu file = new JMenu("�ļ�(F)");
	JMenu edit = new JMenu("�༭(E)");
	JMenu format = new JMenu("��ʽ(O)");
	JMenu view = new JMenu("�鿴(V)");
	//JMenu file = new JMenu("�ļ�");
	JMenuItem newf =new JMenuItem("�½�(N)", 'N');
	JMenuItem open =new JMenuItem("��(O)", 'O');//test,'O'
	JMenuItem save =new JMenuItem("����(S)", 'S');
	JMenuItem saveas =new JMenuItem("���Ϊ(A)", 'A');
	JMenuItem exit =new JMenuItem("�˳�(X)", 'X');
	
	JMenuItem revoke =new JMenuItem("����(U)", 'U');
	JMenuItem resume =new JMenuItem("�ָ�(Y)", 'Y');
	JMenuItem cut =new JMenuItem("����(T)", 'T');
	JMenuItem copy =new JMenuItem("����(C)", 'C');
	JMenuItem paste =new JMenuItem("ճ��(P)",'P');
	JMenuItem delete =new JMenuItem("ɾ��(L)", 'L');
	JMenuItem find =new JMenuItem("����(F)", 'F');
	JMenuItem replace =new JMenuItem("�滻(R)", 'R');
	JMenuItem selectAll =new JMenuItem("ȫѡ(A)", 'A');
	JMenuItem time =new JMenuItem("ʱ��/����(D)", 'D');
	
	JCheckBoxMenuItem newline =new JCheckBoxMenuItem("�Զ�����(W)", true);
	JMenuItem setfont =new JMenuItem("����(F)");//, 'F'
	
	JMenuItem help =new JMenuItem("����(H)", 'H');
	JFrame currentframe = this;
	
	ReplaceDialog repdia = new ReplaceDialog(currentframe, "�滻", false, txt);
	FindDialog findia = new FindDialog(currentframe, "����", false, txt);
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Clipboard ClipBoard = toolkit.getSystemClipboard();
	
	UndoManager undo = new UndoManager();
	//UndoableEditListener undoHandler = new UndoHandler();
	
	Boolean LineWrap = true;
	Boolean HasSaved = true;
	String FileAddr = "";
	
	StartFrame() {
		setBounds(200,200,600,500);
		setTitle("���±�");
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
						FontDialog fontdia = new FontDialog(currentframe,"����",true,txt);
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
						//FontDialog fontdia = new FontDialog(currentframe,"�滻",true,txt);
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
		FileDialog fileDia = new FileDialog(this, "��", FileDialog.LOAD);
		fileDia.setVisible(true);
		
		String dir = fileDia.getDirectory();
		String name = fileDia.getFile();
		if (!(dir+name).equals(FileAddr) || !name.equals(FileAddr)) HasSaved = false;
		if (dir!=null && name!=null) {
			FileAddr = dir + name;//��bug
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
		FileDialog fileDia = new FileDialog(this,"����",FileDialog.SAVE);
		fileDia.setVisible(true);
		String dir = fileDia.getDirectory();
		String name = fileDia.getFile();
		try {
			if (dir!=null && name!= null) {
				FileAddr = dir + name ;		//�Լ���+ ".txt"
				OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(FileAddr) );
				setTitle(name);
				out.write(txt.getText());//,0 , txt.getText().length()
				out.close();
				HasSaved = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "д�����", "����", JOptionPane.WARNING_MESSAGE);
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
			JOptionPane.showMessageDialog(this, "д�����", "����", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void saveWarn(){
		int opnum;
		if (FileAddr.equals("")) {
			if (HasSaved == false) {
				opnum = JOptionPane.showConfirmDialog(this,"�Ƿ񽫸��ı��浽 �ޱ���","���±�",JOptionPane.YES_NO_OPTION);//_CANCEL
				if (opnum == 0) {
					save();
				}
			}
		}
		else {
			if (HasSaved == false) {
				opnum = JOptionPane.showConfirmDialog(this,"�Ƿ񽫸��ı��浽"+FileAddr,"���±�",JOptionPane.YES_NO_OPTION);
				if (opnum == 0) {
					saveWithoutShowingDia();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 �ж��Ƿ���Ҫ��������򱣴��޸ġ�
		1.�ж�s1=ta.getText()�͵�ǰ��getText()�Ƿ���ȣ�������ѣ����³�Ч�ʵͣ�
		2.JTextArea�¼���Ӧ��DocumentEvent�޸��ı�������
		 * */
		if (e.getSource() == newf) {
			saveWarn();
			txt.setText(null);
			setTitle("���±�");
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
			JOptionPane.showMessageDialog(this, "20161856124", "����", JOptionPane.INFORMATION_MESSAGE);
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