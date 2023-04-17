package todo;

import javax.swing.JLabel;

public class ZLabel extends JLabel{
	public ZLabel() {
		super();
		setFont(Confs.getFont());
		Main.WIDGETS.add(this);
	}
	public ZLabel(String text) {
		super(text);
		setFont(Confs.getFont());
		Main.WIDGETS.add(this);
	}
}
