package todo;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ZPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ZPanel() {
		super();
		Main.PANELS.add(this);
		setBackground(Confs.getBackgroundColor());
		setLayout(new BorderLayout());
	}
}
