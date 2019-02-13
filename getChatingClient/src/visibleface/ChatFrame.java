package visibleface;
import javax.swing.*;
import javax.xml.crypto.Data;

import thread.TimerThread;


import file.UserInfo;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.text.*;
import java.sql.*;

import listener.*;

public class ChatFrame extends JFrame{
	public JButton connect;
	public JButton disconnect;
	public JPanel netPane;
	public JScrollPane allMasageScrollPane;
	public JTextArea allMasage;
	public JButton send;
	public JTextField masage;
	public JPanel masageAndSend;
	public JPanel button;
	public JButton eat;
	public JButton toilet;
	public JButton leave;
	public JButton nothand;
	public JButton drink;
	public JButton water;
	
	public Socket msg;
	public PrintWriter mout;
	public BufferedReader min;
	public Socket chat;
	public PrintWriter cout;
	public BufferedReader cin;
	public ChatListener cl;
	public NetListener nl;
	public WinListener wl;
	public CallListener cal;
	public UserInfo ui;
	
	public ConfigureFrame conf;
	public WarningWindow ww;
	
	public TimerThread tt;
	
	public ChatFrame(){
		super("getChating");
		Socket client=null;
		connect = new JButton("connect");
		connect.setEnabled(true);
		disconnect = new JButton ("disconnect");
		disconnect.setEnabled(false);
		netPane = new JPanel();
		netPane.add(connect);
		netPane.add(disconnect);
		allMasage=new JTextArea();
		allMasage.setEditable(false);
		allMasageScrollPane=new JScrollPane(allMasage);
		allMasageScrollPane.setAutoscrolls(true);
		send=new JButton("Send");
		send.setEnabled(false);
		masage=new JTextField();
		masage.addKeyListener(cl);
		masage.setEditable(false);
		button = new JPanel();
		eat = new JButton("eat");
		toilet = new JButton("toilet");
		leave = new JButton("leave");
		nothand = new JButton("nothand");
		drink = new JButton("drink");
		water = new JButton("water");
		button.add(eat);
		button.add(drink);
		button.add(water);
		button.add(toilet);
		button.add(leave);
		button.add(nothand);
		masageAndSend=new JPanel(new BorderLayout());
		masageAndSend.add(masage,"Center");
		masageAndSend.add(send,"East");
		masageAndSend.add(button, "North");
		
		cl = new ChatListener (this);
		send.addMouseListener(cl);
		masage.addKeyListener(cl);
		nl = new NetListener (this);
		connect.addMouseListener(nl);
		disconnect.addMouseListener(nl);
		wl = new WinListener(this);
		this.addWindowListener(wl);
		cal = new CallListener(this);
		eat.addMouseListener(cal);
		toilet.addMouseListener(cal);
		nothand.addMouseListener(cal);
		leave.addMouseListener(cal);
		drink.addMouseListener(cal);
		water.addMouseListener(cal);
		
		setLayout(new BorderLayout());
		add(netPane, "North");
		add(allMasageScrollPane,"Center");
		add(masageAndSend,"South");
		this.setVisible(true);
		this.setSize(350, 400);
		
		ui = new UserInfo();
		conf = new ConfigureFrame(this.getContentPane());
		ww = new WarningWindow(this, 300000);
	}
}