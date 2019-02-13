package visibleface;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import file.*;



public class ConfigureFrame extends JDialog implements MouseListener, KeyListener{
	public Container fatherFrame;
	public JLabel nickName;
	public JLabel IP;
	public JTextField inputNickName;
	public JTextField inputIP;
	public JButton OK;
	public JButton cancel;
	public JPanel input;
	public JPanel control;
	
	public ConfigureFrame(Container fatherFrame){
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java界面风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
		} catch (Exception e){
			e.printStackTrace();
		}
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("user.inf"))));
			this.fatherFrame=fatherFrame;
			this.setLayout(new BorderLayout());
			nickName=new JLabel("nickName");
			IP=new JLabel("Server IP");
			inputNickName=new JTextField();
			inputNickName.addKeyListener(this);
			inputIP=new JTextField();
			inputNickName.addKeyListener(this);
			inputIP.setText(in.readLine());
			input=new JPanel(new GridLayout(3,3));
			input.add(nickName);
			input.add(inputNickName);
			input.add(IP);
			input.add(inputIP);
			OK=new JButton("OK");
			OK.addMouseListener(this);
			cancel=new JButton("Cancel");
			cancel.addMouseListener(this);
			control=new JPanel();
			control.add(OK);
			control.add(cancel);
			this.add(input,"Center");
			this.add(control,"South");
			this.setSize(300,150);
			this.setVisible(true);
			in.close();
		} catch (FileNotFoundException e) {
			this.inputNickName.setText("user file not found");
			this.inputIP.setText("user file not found");
		} catch (IOException e) {
			this.inputNickName.setText("IO Error");
			this.inputIP.setText("IO Error");
		}
	}
	
	public void mouseClicked(MouseEvent e){
		Object source=e.getSource();
		if (source==OK && e.getButton()==e.BUTTON1){
			try{
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("user.inf"))));
				UserInfo.nickName=inputNickName.getText();
				UserInfo.IP=inputIP.getText();
				out.write(UserInfo.IP);
				out.flush();
				this.setVisible(false);
				out.close();
			}
			catch (FileNotFoundException ee) {} 
			catch (IOException ee) {}
		}
		else if (source==cancel && e.getButton()==e.BUTTON1){
			inputNickName.setText("");
			inputIP.setText("");
			this.setVisible(false);
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n'){
			try{
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("user.inf"))));
				UserInfo.nickName=inputNickName.getText();
				UserInfo.IP=inputIP.getText();
				out.write(UserInfo.IP);
				out.flush();
				this.setVisible(false);
				out.close();
			}
			catch (FileNotFoundException ee) {} 
			catch (IOException ee) {}
		}
		
	}
}