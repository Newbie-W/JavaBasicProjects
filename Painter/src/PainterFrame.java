import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PainterFrame extends JFrame implements ActionListener{
	Icon RectIco = new ImageIcon("image/rect.jpg");
	Icon CirIco = new ImageIcon("image/oval.jpg");
	Icon LineIco = new ImageIcon("image/line.jpg");
	Icon ClearIco = new ImageIcon("image/clear.jpg");
	Icon FreeIco = new ImageIcon("image/free.jpg");
	Icon WordIco = new ImageIcon("image/word.jpg");
	Icon TriIco = new ImageIcon("image/tri.jpg");
	Icon PolIco = new ImageIcon("image/multi.jpg");//Polygon
	Icon MoreColorIco = new ImageIcon("image/MoreColor.jpg");
	Icon FillIco = new ImageIcon("image/fill.jpg");
	//Image Img = new ImageIcon("image/.jpg").getImage();
	
	JMenuBar bar = new JMenuBar();
	JMenu FileM = new JMenu("文件");
	JMenu MainPageM = new JMenu("主页");
	JMenu CheckM = new JMenu("查看");
	JMenu ShapeM = new JMenu("形状");
	JMenu ColorM = new JMenu("颜色");
	JMenu FillM = new JMenu("填充图形");
	JMenuItem SaveMI = new JMenuItem("保存");
	JMenuItem SaveasDatMI = new JMenuItem("保存为dat文件");
	JMenuItem OpenMI = new JMenuItem("打开");
	JMenuItem RecMI = new JMenuItem("矩形");
	JMenuItem CirMI = new JMenuItem("圆");
	JMenuItem LineMI = new JMenuItem("直线");
	JMenuItem ClearMI = new JMenuItem("清除");
	JMenuItem FreeDrawMI = new JMenuItem("自由作画");
	JMenuItem WordMI = new JMenuItem("文字");
	JMenuItem TriMI = new JMenuItem("三角形");
	JMenuItem PolMI = new JMenuItem("多边形");
	JMenuItem FillRecMI = new JMenuItem("矩形");
	JMenuItem FillCirMI = new JMenuItem("圆");
	JMenuItem FillTriMI = new JMenuItem("等腰三角形");
	JMenuItem MoreColorMI = new JMenuItem("更多颜色");
	
	JCheckBoxMenuItem FillAllMI = new JCheckBoxMenuItem("全部变为填充",false);
	JCheckBoxMenuItem ModelMI = new JCheckBoxMenuItem("异或模式",false);
	//JMenuItem MI = new ImageMenu(Img, "更多颜色");
	
	JScrollBar PenSizeBar = new JScrollBar(JScrollBar.HORIZONTAL, 5,0,1,30);
	JLabel PenL = new JLabel("画笔大小");
	JLabel PenSizeL = new JLabel("5");
	JPanel PenSizeP = new JPanel();
	JToolBar tbar = new JToolBar();
	//JButton 
	JButton RectBtn = new JButton(RectIco);
	JButton CirBtn = new JButton(CirIco);
	JButton LineBtn = new JButton(LineIco);
	JButton ClearBtn = new JButton(ClearIco);
	JButton FreeBtn = new JButton(FreeIco);
	JButton WordBtn = new JButton(WordIco);
	JButton TriBtn = new JButton(TriIco);
	JButton MoreColorBtn = new JButton(MoreColorIco);
	
	MyCanvas draw = new MyCanvas();
	static int flag = 5;
	static Color color = Color.black;
	static String Text;
	PainterFrame(){
		initPainter();
		setPainterLook();
		actionProcessor();
		validate();
	}
	
	public void initPainter() {
		setTitle("画图");
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getGraphics().setPaintMode();
	}
	
	public void setPainterLook() {
		
		RecMI.setIcon(RectIco);
		CirMI.setIcon(CirIco);
		LineMI.setIcon(LineIco);
		ClearMI.setIcon(ClearIco);
		FreeDrawMI.setIcon(FreeIco);
		WordMI.setIcon(WordIco);
		TriMI.setIcon(TriIco);
		PolMI.setIcon(PolIco);
		MoreColorMI.setIcon(MoreColorIco);
		FillM.setIcon(FillIco);
		FillRecMI.setIcon(RectIco);
		FillCirMI.setIcon(CirIco);
		FillTriMI.setIcon(TriIco);
		
		setLayout(new BorderLayout());
		bar.add(FileM); bar.add(MainPageM); bar.add(CheckM);
		FileM.add(SaveMI); FileM.add(SaveasDatMI); FileM.add(OpenMI);
		MainPageM.add(ShapeM); MainPageM.add(ColorM);
		ShapeM.add(RecMI); ShapeM.add(CirMI); ShapeM.add(LineMI); ShapeM.add(ClearMI); ShapeM.add(FreeDrawMI);
		ShapeM.add(WordMI); ShapeM.add(TriMI); ShapeM.add(PolMI); ShapeM.add(FillM);
		FillM.add(FillRecMI); FillM.add(FillCirMI); FillM.add(FillTriMI);//FillM.add();
		ColorM.add(MoreColorMI);
		CheckM.add(FillAllMI); CheckM.add(ModelMI);
		add(bar);
		this.setJMenuBar(bar);
		
		tbar.add(RectBtn); tbar.add(CirBtn); tbar.add(LineBtn); tbar.add(ClearBtn);
		tbar.add(FreeBtn); tbar.add(WordBtn); tbar.add(TriBtn); tbar.add(MoreColorBtn);
		
		PenSizeP.add(PenL); PenSizeP.add(PenSizeBar); PenSizeP.add(PenSizeL);
		
		add("North", tbar);
		add("Center", draw);
		add("South", PenSizeP);
		//ColorM.add();
	}
	public void actionProcessor() {
		MainPageM.addActionListener(this);
		CheckM.addActionListener(this);
		ShapeM.addActionListener(this);
		ColorM.addActionListener(this);
		SaveMI.addActionListener(this);
		SaveasDatMI.addActionListener(this);
		OpenMI.addActionListener(this);
		RecMI.addActionListener(this);
		CirMI.addActionListener(this);
		LineMI.addActionListener(this);
		ClearMI.addActionListener(this);
		FreeDrawMI.addActionListener(this);
		WordMI.addActionListener(this);
		TriMI.addActionListener(this);
		PolMI.addActionListener(this);
		FillRecMI.addActionListener(this);
		FillCirMI.addActionListener(this);
		FillTriMI.addActionListener(this);
		MoreColorMI.addActionListener(this);
		FillAllMI.addActionListener(this);
		ModelMI.addActionListener(this);
		
		RectBtn.addActionListener(this);
		CirBtn.addActionListener(this);
		LineBtn.addActionListener(this);
		ClearBtn.addActionListener(this);
		FreeBtn.addActionListener(this);
		WordBtn.addActionListener(this);
		TriBtn.addActionListener(this);
		MoreColorBtn.addActionListener(this);
		
		PenSizeBar.addAdjustmentListener(new AdjustmentListener() {
			
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				// TODO Auto-generated method stub
				PenSizeL.setText("" + PenSizeBar.getValue());
				draw.PenSize = PenSizeBar.getValue();
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SaveMI) {
			savef();
		}
		else if (e.getSource() == SaveasDatMI) {
			saveAsDat();
		}
		else if (e.getSource() == OpenMI) {
			openf();
			draw.repaint();
		}
		else if (e.getSource() == RecMI || e.getSource() == RectBtn) {
			flag = 1;
			draw.repaint();
		}
		else if (e.getSource() == CirMI || e.getSource() == CirBtn) {
			flag = 2;
			draw.repaint();
		}
		else if (e.getSource() == LineMI || e.getSource() == LineBtn) {
			flag = 3;
			draw.repaint();
		}
		else if (e.getSource() == ClearMI || e.getSource() == ClearBtn) {
			flag = 4;
			draw.repaint();
		}
		else if (e.getSource() == FreeDrawMI || e.getSource() == FreeBtn) {
			flag = 5;
			draw.repaint();
		}
		else if (e.getSource() == WordMI || e.getSource() == WordBtn) {
			flag = 6;
			//Text = JOptionPane.showInputDialog("请输入");
			draw.repaint();
		}
		else if (e.getSource() == TriMI || e.getSource() == TriBtn) {
			flag = 7;
			draw.repaint();
		}
		else if (e.getSource() == PolMI) {
			flag = 8;
			draw.repaint();
		}
		else if (e.getSource() == FillRecMI) {
			flag = 9;
			//System.out.println("1"+flag);
			draw.repaint();
		}
		else if (e.getSource() == FillCirMI) {
			flag = 10;
			draw.repaint();
		}
		else if (e.getSource() == FillTriMI) {
			flag = 11;
			draw.repaint();
		}
		else if (e.getSource() == MoreColorMI || e.getSource() == MoreColorBtn) {
			color = JColorChooser.showDialog(this,"Color",color);
			System.out.println(""+color);
		}
		else if (e.getSource() == FillAllMI) {
			draw.fillall = !draw.fillall;
			//draw.repaint();
		}
		else if (e.getSource() == ModelMI) {
			draw.xorModel = !draw.xorModel;
			//draw.repaint();
		}
	}
	public void savef() {
		/*Dimension imageSize = this.draw.getSize();
		BufferedImage bi = new BufferedImage(imageSize.width,imageSize.height,BufferedImage.TYPE_INT_RGB);
		String picType = "png";
		
		*/JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();
		if (file == null) {
			JOptionPane.showMessageDialog(null, "没有文件");
		}
		else {
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(draw.list);
				JOptionPane.showMessageDialog(null,"OK");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public void saveAsDat() {
		File file = new File("D:\\test\\test1.dat");//D:\\java\\Demo.dat
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			for (int i=0; i<draw.list.size(); i++) {
				Shape p = (Shape)draw.list.get(i);
				out.writeObject(p);
			}
			Shape p = null;
			out.writeObject(p);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void openf() {
		File file = new File("D:\\test\\test1.dat");//Demo.dat
		ObjectInputStream in;
		try {
			
			in = new ObjectInputStream(new FileInputStream(file));
				
			Shape p = null;
			while((p = (Shape)in.readObject()) != null ) {
				draw.list.add(p);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
