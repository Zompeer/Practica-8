package todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class Confs {
	protected static Dimension screenSize = Toolkit. getDefaultToolkit(). getScreenSize();
	protected static int screenWidth = screenSize.width;
	protected static int screenHeight = screenSize.height;
	protected static Font font = new Font("Consolas", Font.PLAIN, 20);
	protected static Color[] color = new Color[] {Color.decode("#EE9C15"), Color.decode("#D88E15"), Color.decode("#EFC071"), Color.WHITE};
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
	public static int getWidth() {
		return screenWidth;
	}
	public static int getHeight() {
		return screenHeight-32;
	}
	public static Color getBackgroundColor() {
		return color[0];
	}
	public static Color getWidgetColor() {
		return color[1];
	}
	public static Color getForegroundColor() {
		return color[3];
	}
	public static Color getDisabledColor() {
		return color[2];
	}
	public static Font getFont() {
		return font;
	}
	public static void setColor(int color) {
		switch(color) {
		case 0://ROJO
			Confs.color = new Color[] {Color.decode("#EE1515"), Color.decode("#D81515"), Color.decode("#EF7171"), Color.WHITE};
			break;
		case 1://NARANJA
			Confs.color = new Color[] {Color.decode("#EE9C15"), Color.decode("#D88E15"), Color.decode("#EFC071"), Color.WHITE};
			break;
		case 2://AMARILLO
			Confs.color = new Color[] {Color.decode("#E4EE15"), Color.decode("#CFD815"), Color.decode("#E7EF71"), Color.WHITE};
			break;
		case 3://VERDE
			Confs.color = new Color[] {Color.decode("#33EE15"), Color.decode("#30D815"), Color.decode("#82EF71"), Color.WHITE};
			break;
		case 4://AZUL
			Confs.color = new Color[] {Color.decode("#15EEDE"), Color.decode("#15D8C9"), Color.decode("#71EFE5"), Color.WHITE};
			break;
		case 5://AZUL MARINO
			Confs.color = new Color[] {Color.decode("#1564EE"), Color.decode("#155CD8"), Color.decode("#71A3EF"), Color.WHITE};
			break;
		case 6://MORADO
			Confs.color = new Color[] {Color.decode("#9215EE"), Color.decode("#8215D8"), Color.decode("#B871EF"), Color.WHITE};
			break;
		case 7://ROSA
			Confs.color = new Color[] {Color.decode("#EE15E1"), Color.decode("#D815CF"), Color.decode("#EF71EB"), Color.WHITE};
			break;
		}
		Main.PANELS.forEach((value) -> {
			value.setBackground(getBackgroundColor());
		});
		Main.WIDGETS.forEach((value) -> {
			if(value.isEnabled()) {
				if(!(value instanceof JLabel)) {
					value.setBackground(getWidgetColor());
				}
				value.setForeground(getForegroundColor());
			}else {
				value.setBackground(getDisabledColor());
				value.setForeground(getDisabledColor());
			}
		});
	}
}
