package todo;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ZButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int NORMAL_BUTTON = 0;
	public static final int BOX_BUTTON = 1;
	public ZButton() {
		super();
		configureButton();
		Main.WIDGETS.add(this);
	}
	public ZButton(String text) {
		super("<html>"+text+"</html>");
		configureButton();
		Main.WIDGETS.add(this);
	}
	public ZButton(ImageIcon image) {
		super(image);
		configureButton();
		Main.WIDGETS.add(this);
	}
	public void setText(String text) {
		super.setText("<html>"+text+"</html>");
	}
	public String getText() {
		String text = super.getText();
		text = text.replace("<html>", "");
		text = text.replace("</html>", "");
		return text;
	}
	private void configureButton() {
		setBackground(Confs.getWidgetColor());
		setFocusPainted(false);
		setFont(Confs.getFont());
		setForeground(Confs.getForegroundColor());
	}
	public void transform(int buttonType) {
		switch(buttonType) {
		case ZButton.NORMAL_BUTTON:
			setPreferredSize(new Dimension());
			break;
		case ZButton.BOX_BUTTON:
			setPreferredSize(new Dimension(48, 32));
			break;
		}
	}
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if(enabled) {
			setBackground(Confs.getWidgetColor());
			setForeground(Confs.getForegroundColor());
		}else {
			setBackground(Confs.getDisabledColor());
			setForeground(Confs.getDisabledColor());
		}
	}
}
