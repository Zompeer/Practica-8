package todo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class ZFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ZPanel panel;
	protected ArrayList<JComponent> components = new ArrayList<JComponent>();
	public ZFrame(int onClose){
		super();
		setLayout(new BorderLayout());
		setBounds(0, 0, Confs.getScreenWidth(), Confs.getScreenHeight());
		setUndecorated(true);
		setDefaultCloseOperation(onClose);
		panel = new ZPanel();
		panel.setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.WHITE);
			topPanel.setLayout(new BorderLayout());
			JPanel toprPanel = new JPanel();
				toprPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
				ZButton exit = new ZButton(new ImageIcon("resources/images/x.png"));
				exit.transform(ZButton.BOX_BUTTON);
				components.add(exit);
				ZButton minimize = new ZButton(new ImageIcon("resources/images/minimize.png"));
				minimize.transform(ZButton.BOX_BUTTON);
				components.add(minimize);
				toprPanel.add(minimize);
				toprPanel.add(exit);
			topPanel.add(toprPanel, BorderLayout.EAST);
			JPanel line = new JPanel();
			Main.WIDGETS.add(line);
			line.setBackground(Confs.getWidgetColor());
			line.setPreferredSize(new Dimension(0,1));
			components.add(line);
			topPanel.add(line, BorderLayout.SOUTH);
		add(topPanel, BorderLayout.NORTH);
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	public ZPanel getPanel() {
		return panel;
	}
	public JComponent[] getCompos() {
		JComponent[] compos = new JComponent[components.size()];
		for(int i=0;i<compos.length;i++) {
			compos[i] = components.get(i);
		}
		return compos;
	}
}
