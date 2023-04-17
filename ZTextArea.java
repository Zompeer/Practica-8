package todo;

import javax.swing.JTextArea;

public class ZTextArea extends JTextArea{
	public ZTextArea() {
		super();
		setFont(Confs.getFont());
		setLineWrap(true);
	}
}
