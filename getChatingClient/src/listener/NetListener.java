package listener;

import java.awt.Container;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.text.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import thread.*;
import thread.TimerThread;
import visibleface.*;


import file.*;

public class NetListener implements MouseListener{
	ChatFrame cf;
	
	public NetListener(ChatFrame cf){
		this.cf = cf;
	}

	
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == cf.connect){
			try{
				cf.msg = new Socket(UserInfo.IP,8000);
				cf.min=new BufferedReader(new InputStreamReader(cf.msg.getInputStream()));
				cf.mout=new PrintWriter(cf.msg.getOutputStream());
				cf.mout.println("connect@"+cf.ui.nickName);
				cf.mout.flush();
				String res = cf.min.readLine();
				if (res.equals("connectOk")){
					ChatThread chatThread=new ChatThread(cf);
					chatThread.start();
					cf.allMasage.setText("connect success!\n");
					cf.connect.setEnabled(false);
					cf.disconnect.setEnabled(true);
					cf.setAlwaysOnTop(true);
					cf.tt = new TimerThread (cf, 60000);
					cf.tt.start();
				}
				else {
					cf.allMasage.append("connect failed!\n");
					JOptionPane.showMessageDialog(cf.getContentPane(), "错误：无法连接到服务器","错误",JOptionPane.ERROR_MESSAGE);
				}
			}catch(IOException ie){
				cf.allMasage.append("connect failed!\n");
				JOptionPane.showMessageDialog(cf.getContentPane(), "错误：无法连接到服务器","错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (source == cf.disconnect){
			try{
				cf.mout.println("disconnect@"+cf.ui.nickName);
				cf.mout.flush();
				String anwser=cf.min.readLine();
				if (anwser.equals("disconnectOk")){
					cf.allMasage.setText("disconnect to server\n");
					cf.send.setEnabled(false);
					cf.masage.setEditable(false);
					cf.connect.setEnabled(true);
					cf.disconnect.setEnabled(false);
					cf.cout = null;
					cf.cin = null;
					cf.chat.close();
					cf.mout = null;
					cf.min = null;
					cf.msg.close();
					cf.tt.stop();
					cf.setAlwaysOnTop(false);
					
				}
				else {
					cf.allMasage.append("disconnect failed!\n");
					JOptionPane.showMessageDialog(cf.getContentPane(), "错误：无法断开与服务器的连接","错误",JOptionPane.ERROR_MESSAGE);
				}
			}catch(IOException ie){
				cf.allMasage.append("disconnect failed!\n");
				JOptionPane.showMessageDialog(cf.getContentPane(), "错误：无法断开与服务器的连接","错误",JOptionPane.ERROR_MESSAGE);
			}
			cf.allMasage.append("disconnect success!\n");
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}