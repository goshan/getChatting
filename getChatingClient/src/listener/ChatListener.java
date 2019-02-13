package listener;

import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.*;

import file.UserInfo;

import visibleface.*;

public class ChatListener implements MouseListener, KeyListener{
	ChatFrame cf;
	
	public ChatListener(ChatFrame cf){
		this.cf = cf;
	}

	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == cf.send){
			String beSended=cf.masage.getText();
			if (beSended.equals("")){
				beSended = "SYSTEM: can not send empty msg!\n";
				cf.allMasage.append(beSended+"\n");
			}
			else {
				beSended=cf.ui.nickName+" ˵:\n"+beSended;
				cf.cout.println(beSended);
				cf.cout.flush();
			}
			cf.masage.setText("");
			cf.masage.requestFocus();
			cf.tt.reset();
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n'){
			String beSended=cf.masage.getText();
			if (beSended.equals("")){
				beSended = "SYSTEM: can not send empty msg!\n";
				cf.allMasage.append(beSended+"\n");
			}
			else {
				beSended=cf.ui.nickName+" ˵:\n"+beSended;
				cf.cout.println(beSended);
				cf.cout.flush();
			}
			cf.masage.setText("");
			cf.masage.requestFocus();
			cf.tt.reset();
		}
	}
}