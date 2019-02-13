import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

import thread.*;


import java.awt.*;
import java.awt.event.*;

public class Server implements MouseListener{
	public JFrame sf;
	public JScrollPane jsp;
	public JTextArea masage;
	public JPanel bp;
	public JButton save;
	public ServerForeverThread server;
	public Server(){
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
		sf=new JFrame("Server");
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		masage=new JTextArea();
		jsp = new JScrollPane(masage);
		save = new JButton("save");
		save.addMouseListener(this);
		bp = new JPanel();
		bp.add(save);
		sf.getContentPane().add(jsp,"Center");
		sf.getContentPane().add(bp, "South");
		sf.setSize(300,200);
		sf.setVisible(true);
		sf.setDefaultCloseOperation(sf.EXIT_ON_CLOSE);
		server=new ServerForeverThread(masage);
		server.start();
	}
	
	public void mouseClicked(MouseEvent e) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("save.inf"))));
			out.write(this.masage.getText());
			out.flush();
			out.close();
			this.masage.append("save info OK\n");
		} catch (Exception e1) {}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public static void main(String args[]){
		Server newServer=new Server();
	}
}