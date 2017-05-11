package com.nanan.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class MainWindow {
    
	private JFrame frame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnCenter;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnBack;
	private JButton btnVolumUp;
	private JButton btnVolumDowm;
	private JTextPane textBox;
	private JButton btnEdit;
	private JButton btnSend;
	
	private List<String> devices = new LinkedList<>();
	private JComboBox deviceList;
	private JButton btnRefresh;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initLookAndFeel();
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static void initLookAndFeel() throws Exception {
		if (System.getProperty("os.name").contains("Windows")) {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Windows".equals(info.getName())) {
	            	UIManager.setLookAndFeel(info.getClassName());
	            }
	        }
		}
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Android key event monitor");
		frame.setResizable(false);
		frame.setBounds(100, 100, 462, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if (!textBox.isEnabled()) return;
				sendText(textBox.getText().replace(" ", "%s"));
				textBox.setEnabled(false);
			}
		});
		btnSend.setFocusable(false);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textBox.setEnabled(true);
				textBox.requestFocus();
				textBox.setText("");
			}
		});
		btnEdit.setFocusable(false);
		
		deviceList = new JComboBox();
		deviceList.setFocusable(false);
		
		btnRefresh = new JButton("Refresh Devices");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshDeviceList();
			}
		});
		btnRefresh.setFocusable(false);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(deviceList, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(btnRefresh)
							.addContainerGap(24, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(312)
							.addComponent(btnSend)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(deviceList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnSend))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		
		textBox = new JTextPane();
		textBox.setText("Send text to the focused view on the target device;");
		textBox.setToolTipText("");
		textBox.setEnabled(false);
		scrollPane.setViewportView(textBox);
		
		btnLeft = new JButton("\u25C0 ");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_DPAD_LEFT);
			}
		});
		btnLeft.setFocusable(false);
		btnLeft.setPreferredSize(new Dimension(70, 30));
		btnLeft.setMaximumSize(new Dimension(70, 30));
		btnLeft.setMinimumSize(new Dimension(70, 30));
		
		btnCenter = new JButton("Center");
		btnCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_DPAD_CENTER);
			}
		});
		btnCenter.setFocusable(false);
		btnCenter.setPreferredSize(new Dimension(70, 30));
		btnCenter.setMinimumSize(new Dimension(70, 30));
		btnCenter.setMaximumSize(new Dimension(70, 30));
		
		btnRight = new JButton("\u25B6");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_DPAD_RIGHT);
			}
		});
		btnRight.setFocusable(false);
		btnRight.setPreferredSize(new Dimension(70, 30));
		btnRight.setMinimumSize(new Dimension(70, 30));
		btnRight.setMaximumSize(new Dimension(70, 30));
		
		btnUp = new JButton("\u25B2");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendKey(AndroidKeyEvent.KEYCODE_DPAD_UP);
			}
		});
		btnUp.setFocusable(false);
		btnUp.setPreferredSize(new Dimension(70, 30));
		btnUp.setMinimumSize(new Dimension(70, 30));
		btnUp.setMaximumSize(new Dimension(70, 30));
		
		btnDown = new JButton("\u25BC");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_DPAD_DOWN);
			}
		});
		btnDown.setFocusable(false);
		btnDown.setPreferredSize(new Dimension(70, 30));
		btnDown.setMinimumSize(new Dimension(70, 30));
		btnDown.setMaximumSize(new Dimension(70, 30));
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_HOME);
			}
		});
		btnHome.setFocusable(false);
		btnHome.setPreferredSize(new Dimension(70, 30));
		btnHome.setMinimumSize(new Dimension(70, 30));
		btnHome.setMaximumSize(new Dimension(70, 30));
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_MENU);
			}
		});
		btnMenu.setFocusable(false);
		btnMenu.setPreferredSize(new Dimension(70, 30));
		btnMenu.setMinimumSize(new Dimension(70, 30));
		btnMenu.setMaximumSize(new Dimension(70, 30));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_BACK);
			}
		});
		btnBack.setFocusable(false);
		btnBack.setPreferredSize(new Dimension(70, 30));
		btnBack.setMinimumSize(new Dimension(70, 30));
		btnBack.setMaximumSize(new Dimension(70, 30));
		
		btnVolumUp = new JButton("Volum+");
		btnVolumUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_VOLUME_UP);
			}
		});
		btnVolumUp.setFocusable(false);
		btnVolumUp.setPreferredSize(new Dimension(70, 30));
		btnVolumUp.setMinimumSize(new Dimension(70, 30));
		btnVolumUp.setMaximumSize(new Dimension(70, 30));
		
		btnVolumDowm = new JButton("Volum-");
		btnVolumDowm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_VOLUME_DOWN);
			}
		});
		btnVolumDowm.setFocusable(false);
		btnVolumDowm.setPreferredSize(new Dimension(70, 30));
		btnVolumDowm.setMinimumSize(new Dimension(70, 30));
		btnVolumDowm.setMaximumSize(new Dimension(70, 30));
		
		btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendKey(AndroidKeyEvent.KEYCODE_SETTINGS);
			}
		});
		btnSettings.setFont(new Font("宋体", Font.PLAIN, 12));
		btnSettings.setMargin(new Insets(2, 2, 2, 2));
		btnSettings.setActionCommand("Settings");
		btnSettings.setPreferredSize(new Dimension(70, 30));
		btnSettings.setMinimumSize(new Dimension(70, 30));
		btnSettings.setMaximumSize(new Dimension(70, 30));
		btnSettings.setFocusable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCenter, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSettings, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVolumUp, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVolumDowm, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnVolumDowm, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnCenter, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnVolumUp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSettings, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
				int androidCode = AndroidKeyEvent.keyMaping(arg0.getKeyCode());
				if (androidCode != -1)
					sendKey(AndroidKeyEvent.keyMaping(arg0.getKeyCode()));
			}
		});
		refreshDeviceList();
	}
	
	private void refreshDeviceList() {
		try {
			Process process = Runtime.getRuntime().exec("adb devices");
			new Thread() {
		        public void run() {
		            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		            String line = null; 
		            devices.clear();
		            try {
		                while ((line = input.readLine()) != null) {
		                    if (!line.endsWith("device")) continue;
		                    System.out.println(line);
		                    devices.add(line.substring(0, line.indexOf((char)9)));
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            deviceList.removeAllItems();
		            for (String deviceName : devices) {
		            	deviceList.addItem(deviceName);
		            }
		        }
		    }.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendKey(int keyCode) {
		try {
			Runtime.getRuntime().exec("adb -s " + deviceList.getSelectedItem() + " shell input keyevent " + keyCode);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private final static String[] replacement = {
			"\\", "\\\\", "%", "\\%", "\"", "\\\"", "(", "\\(",
			"\\)", "\\\\)", "\\&", "\\\\&", "\\<", "\\\\<", "\\>", "\\\\>",
			"\\;", "\\\\;", "\\*", "\\\\*", "\\|", "\\\\|", "\\~", "\\\\~",
			"\\¬", "\\\\¬", "\\`", "\\\\`"}; 
	private JButton btnSettings;
	private void sendText(String text) {
		for (int i = 0; i < replacement.length; i += 2) {
			text.replace(replacement[i], replacement[i + 1]);
		}
		try {
			Runtime.getRuntime().exec("adb -s " + deviceList.getSelectedItem() + " shell input text " + text.replace(" ", "%s"));
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
