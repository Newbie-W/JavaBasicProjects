import java.awt.Color;
import java.io.Serializable;

public class Shape implements Serializable {
	int startx, starty;
	int endx, endy;
	int flag;
	Color UseColor;
	String Text;
	int PenSize;
	//boolean xorModel;
	Shape(int startx, int starty, int endx, int endy,int flag, Color UseColor, String Text, int PenSize) {//, boolean xorModel
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		this.flag = flag;
		this.UseColor = UseColor;
		this.Text = Text;
		this.PenSize = PenSize;
		//this.xorModel = xorModel;
	}
	/*void setTxt(String t) {
		this.Text = t;
	}*/
}
