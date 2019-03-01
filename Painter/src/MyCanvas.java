import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class MyCanvas extends JPanel implements MouseListener, MouseMotionListener{//Canvas
	ArrayList<Shape> list = new ArrayList<Shape>();
	int startx = 0, starty = 0;
	int endx = 0, endy = 0;
	int flag = PainterFrame.flag;
	String Text = PainterFrame.Text;
	int DrawOneTimef = 1;
	Color UseColor = PainterFrame.color;
	int[] Polx = new int[3];
	int[] Poly = new int[3];
	boolean fillall = false;
	boolean xorModel = false;
	int PenSize = 5;
	int initx = -1, inity = -1;
	boolean isFirst = true;
	MyCanvas() {
		initCanvas();
		actionProcessor();
		validate();
	}
	
	public void initCanvas() {
		this.setBackground(Color.white);
		setVisible(true);
	}
	
	public void actionProcessor() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paintColor(Graphics g, Color c) {
		if (xorModel) {
			g.setXORMode(c);
		}
		else g.setColor(c);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int leftx, lefty, width, height;
		leftx = Math.min(startx, endx);
		lefty = Math.min(starty,endy);
		width = Math.abs(endx-startx);
		height = Math.abs(endy-starty);
		flag = PainterFrame.flag;
		UseColor = PainterFrame.color;
		System.out.println(""+flag);//
		
		int leftx0, lefty0, width0, height0;
		Iterator<Shape> iterator = list.iterator(); 
		//System.out.println("11");
		while (iterator.hasNext()) {
			Shape p = (Shape)iterator.next();
			((Graphics2D)g).setStroke(new BasicStroke(p.PenSize));
			//g.setColor(p.UseColor);
			paintColor(g, p.UseColor);//, p.xorModel
			leftx0 = Math.min(p.startx, p.endx);
			lefty0 = Math.min(p.starty,p.endy);
			width0 = Math.abs(p.endx-p.startx);
			height0 = Math.abs(p.endy-p.starty);
			if (p.flag == 1) {
				g.drawRect(leftx0, lefty0, width0, height0);
			}
			else if (p.flag == 2) {
				g.drawOval(leftx0, lefty0, width0, height0);
			}
			else if (p.flag == 3) {
				g.drawLine(p.startx, p.starty, p.endx, p.endy);
			}
			else if (p.flag == 4) {
				g.clearRect(p.startx, p.starty, p.endx, p.endy);
			}
			else if (p.flag == 5) {
				((Graphics2D)g).drawLine(p.startx, p.starty, p.endx, p.endy);//p.startx+4, p.starty+4
			}
			else if (p.flag == 6) {
				//System.out.println(startx+" "+starty);
				if (p.Text != null) {
					g.drawString(p.Text, p.startx, p.starty);
				}
			}
			else if (p.flag == 7) {
				Polx[0] = p.startx; Polx[1] = p.endx; Polx[2] = 2*p.startx-p.endx;
				Poly[0] = p.starty; Poly[1] = p.endy; Poly[2] = p.endy;
				g.drawPolygon(Polx, Poly, 3);
			}
			else if (p.flag == 8) {
				g.drawLine(p.startx, p.starty, p.endx, p.endy);
			}
			else if (p.flag == 9) {
				g.fillRect(leftx0, lefty0, width0, height0);
			}
			else if (p.flag == 10) {
				g.fillOval(leftx0, lefty0, width0, height0);
			}
			else if (p.flag == 11) {
				Polx[0] = p.startx; Polx[1] = p.endx; Polx[2] = 2*p.startx-p.endx;
				Poly[0] = p.starty; Poly[1] = p.endy; Poly[2] = p.endy;
				g.fillPolygon(Polx, Poly, 3);
			}
		}/**/
		if (DrawOneTimef == 1)
		{
			if (fillall) {
				if (flag == 1) flag = 9;
				if (flag == 2) flag = 10;
				if (flag == 7) flag = 11;
				//if (flag == ) flag = ;
			}
			paintColor(g, UseColor);//g.setColor(UseColor);
			((Graphics2D)g).setStroke(new BasicStroke(PenSize));
			if (flag == 1) {
				g.drawRect(leftx, lefty, width, height);//startx, starty, width, height
			}
			else if (flag == 2) {
				g.drawOval(leftx, lefty, width, height);
			}
			else if (flag == 3) {
				g.drawLine(startx, starty, endx, endy);
			}
			else if (flag == 4) {
				g.clearRect(startx, starty, endx, endy);
			}
			else if (flag == 5) {
				g.drawLine(startx, starty, endx, endy);//startx+5, starty+5
			}
			else if (flag == 6) {
				Text = JOptionPane.showInputDialog("请输入");
				if (Text != null) {
					g.drawString(Text, startx, starty);
					list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
					Text = null;
				}
			}
			else if (flag == 7) {
				Polx[0] = startx; Polx[1] = endx; Polx[2] = 2*startx-endx;//endx-2*startx
				Poly[0] = starty; Poly[1] = endy; Poly[2] = endy;
				g.drawPolygon(Polx, Poly, 3);
			}
			else if (flag == 8) {
				//g.drawLine(startx, starty, endx, endy);//
				//startx = endx;
				//starty = endy;
			}
			else if (flag == 9) {
				g.fillRect(leftx, lefty, width, height);
			}
			else if (flag == 10) {
				g.fillOval(leftx, lefty, width, height);
			}
			else if (flag == 11) {
				Polx[0] = startx; Polx[1] = endx; Polx[2] = 2*startx-endx;//endx-2*startx
				Poly[0] = starty; Poly[1] = endy; Poly[2] = endy;
				g.fillPolygon(Polx, Poly, 3);
			}
		}
		/**/
	}
	
	public void mouseDragged(MouseEvent e) {
		Graphics graphics = this.getGraphics();//
		if (flag!=8) {
			endx = e.getX();
			endy = e.getY();
		}
		else if (flag==8 && isFirst) {
			endx = e.getX();
			endy = e.getY();
			paintColor(graphics, UseColor);
			//((Graphics2D)graphics).setStroke(new BasicStroke(PenSize));
			graphics.drawLine(startx, starty, endx, endy);
			list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
			isFirst = !isFirst;
			System.out.println("drag "+startx+" "+starty+" "+endx+" "+endy);
			startx = endx;
			starty = endy;
		}
		if (flag == 5){ 
			int leftx0, lefty0, width0, height0;
			UseColor = PainterFrame.color;
			Iterator<Shape> iterator = list.iterator(); 
			while (iterator.hasNext()) {
				Shape p = (Shape)iterator.next();
				paintColor(graphics, p.UseColor);//graphics.setColor(p.UseColor);
				((Graphics2D)graphics).setStroke(new BasicStroke(p.PenSize));
				leftx0 = Math.min(p.startx, p.endx);
				lefty0 = Math.min(p.starty,p.endy);
				width0 = Math.abs(p.endx-p.startx);
				height0 = Math.abs(p.endy-p.starty);
				if (p.flag == 1) {
					graphics.drawRect(leftx0, lefty0, width0, height0);
				}
				else if (p.flag == 2) {
					graphics.drawOval(leftx0, lefty0, width0, height0);
				}
				else if (p.flag == 3) {
					graphics.drawLine(p.startx, p.starty, p.endx, p.endy);
				}
				else if (p.flag == 4) {
					graphics.clearRect(p.startx, p.starty, p.endx, p.endy);
				}
				else if (p.flag == 5) {
					((Graphics2D)graphics).drawLine(p.startx, p.starty, p.endx, p.endy);//p.startx+4, p.starty+4
				}
				else if (p.flag == 6) {
					if (p.Text != null) {
						graphics.drawString(p.Text, p.startx, p.starty);
					}
				}
				else if (p.flag == 7) {
					Polx[0] = p.startx; Polx[1] = p.endx; Polx[2] = 2*p.startx-p.endx;//(p.startx>p.endx?(2*p.startx-p.endx):(p.endx-2*p.startx))
					Poly[0] = p.starty; Poly[1] = p.endy; Poly[2] = p.endy;
					graphics.drawPolygon(Polx, Poly, 3);
				}
				else if (p.flag == 8) {
					//graphics.fillOval(leftx0, lefty0, width0, height0);
				}
				else if (p.flag == 9) {
					graphics.fillRect(leftx0, lefty0, width0, height0);
				}
				else if (p.flag == 10) {
					graphics.fillOval(leftx0, lefty0, width0, height0);
				}
				else if (p.flag == 11) {
					Polx[0] = p.startx; Polx[1] = p.endx; Polx[2] = 2*p.startx-p.endx;//(p.startx>p.endx?(2*p.startx-p.endx):(p.endx-2*p.startx))
					Poly[0] = p.starty; Poly[1] = p.endy; Poly[2] = p.endy;
					graphics.drawPolygon(Polx, Poly, 3);
				}
			}
			((Graphics2D)graphics).drawLine(startx, starty, endx, endy);//
			paintColor(graphics, UseColor);//graphics.setColor(UseColor);
			((Graphics2D)graphics).setStroke(new BasicStroke(PenSize));
			list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
			startx = e.getX();
			starty = e.getY();
		}
		else if (flag != 5) repaint();

	}
	
	public void mousePressed(MouseEvent e) {
		Graphics graphics = this.getGraphics();
		System.out.println(""+flag+isFirst);
		if (flag != 8) {
			startx = e.getX();
			starty = e.getY();
		}
		else if (flag==8 && isFirst) {
			startx = e.getX();
			starty = e.getY();
			initx = startx;
			inity = starty;
			System.out.println("init "+startx+" "+starty+" "+endx+" "+endy);
		}
		else {
			endx = e.getX();
			endy = e.getY();
			
			if (Math.abs(endx-initx)<=(PenSize+2) && Math.abs(endy-inity)<=(PenSize+2)) {
				isFirst = true;
				
				System.out.println("OK "+startx+" "+starty+" "+endx+" "+endy);
				endx = initx;
				endy = inity;
				paintColor(graphics, UseColor);
				((Graphics2D)graphics).setStroke(new BasicStroke(PenSize));
				graphics.drawLine(startx, starty, endx, endy);
				JOptionPane.showMessageDialog(null, "成功画出封闭多边形");
				list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
			}
			else {
				paintColor(graphics, UseColor);
				((Graphics2D)graphics).setStroke(new BasicStroke(PenSize));
				graphics.drawLine(startx, starty, endx, endy);
				list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
				startx = endx;
				starty = endy;
			}
		}
		DrawOneTimef = 1;
		//repaint();
	}

	public void mouseReleased(MouseEvent e) {
		//endx = e.getX();
		//endy = e.getY();
		list.add(new Shape(startx,starty,endx,endy,flag,UseColor,Text,PenSize));
		DrawOneTimef = 0;
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
