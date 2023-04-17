package todo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ZTextField extends ZPanel {
	public static final int STRING_TYPE = 0;
	public static final int NUMERIC_TYPE = 1;
	public static final int DECIMAL_TYPE = 2;
	private final JTextField textfield;
	private final ZLabel label;
	private final int fieldType;
	public ZTextField(int type) {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		fieldType = type;
		textfield = new JTextField();
		textfield.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
				new EmptyBorder(4, 4, 4, 4)));
		textfield.setFont(Confs.getFont());
		label = new ZLabel();
		add(label);
		add(textfield);
		switch(type) {
		case ZTextField.NUMERIC_TYPE:
			textfield.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
						textfield.setEditable(true);
						textfield.setBackground(Color.white);
					}else {
						textfield.setEditable(false);
						textfield.setBackground(Color.white);
					}
				}
			});
			break;
		case ZTextField.DECIMAL_TYPE:
			textfield.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					String text = textfield.getText();
					if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_BACK_SPACE
							|| (ke.getKeyChar() == '.' && !Functions.hasPoint(text))) {
						textfield.setEditable(true);
						textfield.setBackground(Color.white);
					}else {
						textfield.setEditable(false);
						textfield.setBackground(Color.white);
					}
				}
			});
		}
	}
	public void setPlaceHolder(String holder) {
		label.setText(holder);
	}
	public JTextField getTextField() {
		return textfield;
	}
	public int getInt() {
		return Functions.parseInt(textfield.getText());
	}
	public float getFloat() {
		return Functions.parseFloat(textfield.getText());
	}
	public int getFieldType() {
		return fieldType;
	}
}
