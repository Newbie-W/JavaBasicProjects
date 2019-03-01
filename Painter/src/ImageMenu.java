import java.awt.*;
import javax.swing.*;


public class ImageMenu extends JMenuItem {
	private Image img = null;
	ImageMenu(Image img, String title) {
		super(title);
		this.img = img;
	}
	
	Insets getInset() {
		Insets insets = (Insets) super.getInsets().clone();
		insets.left += img.getWidth(null);
		return insets;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (img != null) {
			Insets insets = getInsets();
			g.drawImage(img, 20, 0, null);
		}
	}
}
