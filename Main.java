package todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Main {
	public static ArrayList<JPanel> PANELS = new ArrayList<JPanel>();
	public static ArrayList<JComponent> WIDGETS = new ArrayList<JComponent>();
	public static ZFrame MAIN;
	public static Thread th1=null, th2=null;
	public static void main(String[] args) {
		
		createMainFrame();
		createFields();
	}
	public static void createMainFrame() {
		MAIN = new ZFrame(JFrame.EXIT_ON_CLOSE);
		MAIN.setVisible(true);
		MAIN.revalidate();
		MAIN.repaint();
	}
	public static void createFields() {
		boolean banderas[] = new boolean[]{false,false,false};
		String errores[] = new String[] {"El archivo se esta editando", "El archivo esta abierto", "El archivo esta abierto"};
		//EDITANDO|LEYENDO1|LEYENDO2
		ZPanel lPanel = new ZPanel();
		ZPanel panel = new ZPanel(), panel2 = new ZPanel(), panel3 = new ZPanel();
		ZPanel lbPanel = new ZPanel(), mPanel = new ZPanel(), rPanel = new ZPanel();
		JScrollPane spanel = new JScrollPane(panel), smpanel = new JScrollPane(panel2), srpanel = new JScrollPane(panel3);
		spanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		smpanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		srpanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		lPanel.setPreferredSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		lPanel .setMaximumSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		mPanel.setPreferredSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		mPanel .setMaximumSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		rPanel.setPreferredSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		rPanel .setMaximumSize(new Dimension(Confs.getScreenWidth()/3, Confs.getHeight()));
		ZTextArea area = new ZTextArea(), area2 = new ZTextArea(), area3 = new ZTextArea();
		area2.setEnabled(false);
		area3.setEnabled(false);
		ZButton but0 = new ZButton("Editar");
		ZButton but1 = new ZButton("Guardar");
		ZButton but2 = new ZButton("Leer");
		ZButton but3 = new ZButton("Leer");
		but2.setPreferredSize(new Dimension(Confs.getWidth()/3, 64));
		but3.setPreferredSize(new Dimension(Confs.getWidth()/3, 64));
		but0.setPreferredSize(new Dimension(Confs.getWidth()/3/2,64));
		but1.setPreferredSize(new Dimension(Confs.getWidth()/3/2, 64));
		but1.setEnabled(false);
		
		but0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					if(banderas[i]) {
						ZPopup.msg(errores[i]);
						return;
					}
				}
				banderas[0] = true;
				but1.setEnabled(true);
				area.setEnabled(true);
			}
		});
		
		but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				but1.setEnabled(false);
				area.setEnabled(false);
				banderas[0] = false;
				String text=area.getText();
				try {
		            BufferedWriter writer = new BufferedWriter(new FileWriter("resources/files/archivo.txt"));
		            writer.write(text);
		            writer.close();
		            System.out.println("Se ha escrito en el archivo exitosamente.");
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		but2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					if(banderas[i] && i!=2) {
						ZPopup.msg(errores[i]);
						return;
					}
				}
				Thread thread1 = new Thread() {
					public void run() {
						banderas[1] = true;
						area2.setText("");
						try {
				            FileReader reader = new FileReader("resources/files/archivo.txt");
				            int car;
				            while ((car = reader.read()) != -1 && !isInterrupted()) {
				            	area2.append(""+(char)car);
				            	try {
									Thread.sleep(20);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									th1=null;
									interrupt();
								}
				            }
				            reader.close();
				            th1=null;
							interrupt();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						banderas[1] = false;
					}
				};
				if(th1==null) {
					th1 = thread1;
					thread1.start();
				}
			}
		});
		but3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					if(banderas[i] && i!=1) {
						ZPopup.msg(errores[i]);
						return;
					}
				}
				Thread thread2 = new Thread() {
					public void run() {
						area3.setText("");
						banderas[2] = true;
						try {
				            FileReader reader = new FileReader("resources/files/archivo.txt");
				            int car;
				            while ((car = reader.read()) != -1 && !isInterrupted()) {
				            	area3.append(""+(char)car);
				            	try {
									Thread.sleep(20);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									th2 = null;
									interrupt();
								}
				            }
				            reader.close();
				            th2 = null;
							interrupt();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						banderas[2] = false;
					}
				};
				if(th2==null) {
					th2 = thread2;
					thread2.start();
				}
				
			}
		});
		lPanel.add(spanel, BorderLayout.CENTER);
		lPanel.add(lbPanel, BorderLayout.SOUTH);
		lbPanel.add(but0,BorderLayout.WEST);
		lbPanel.add(but1, BorderLayout.EAST);
		mPanel.add(but2, BorderLayout.NORTH);
		mPanel.add(smpanel, BorderLayout.CENTER);
		rPanel.add(but3, BorderLayout.NORTH);
		rPanel.add(srpanel, BorderLayout.CENTER);
		panel.add(area, BorderLayout.CENTER);
		panel2.add(area2,BorderLayout.CENTER);
		panel3.add(area3);
		cargaArchivo(area);
		area.setEnabled(false);
		MAIN.getPanel().add(lPanel, BorderLayout.WEST);
		MAIN.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				if(th1!=null)
					th1.interrupt();
				if(th2!=null)
					th2.interrupt();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		MAIN.getPanel().add(mPanel, BorderLayout.CENTER);
		MAIN.getPanel().add(rPanel, BorderLayout.EAST);
		MAIN.revalidate();
	}
	public static void cargaArchivo(JTextArea donde) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("resources/files/archivo.txt"));
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append("\n"); // Agregar un salto de línea para cada línea leída
	        }
	        reader.close();
	        donde.setText(stringBuilder.toString());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
