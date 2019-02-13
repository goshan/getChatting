package listener;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.JOptionPane;

import visibleface.ChatFrame;

public class WinListener implements WindowListener{
	public ChatFrame cf;
	
	public WinListener(ChatFrame cf){
		this.cf = cf;
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		try{
			if (cf.mout != null){
				cf.mout.println("disconnect@"+cf.ui.nickName);
				cf.mout.flush();
				String anwser=cf.min.readLine();
				if (anwser.equals("disconnectOk")){
					cf.allMasage.setText("disconnect to server\n");
					cf.send.setEnabled(false);
					cf.masage.setEditable(false);
					cf.connect.setEnabled(true);
					cf.disconnect.setEnabled(false);
					cf.chat.close();
					cf.msg.close();
				}
				else {
					cf.allMasage.append("disconnect failed!\n");
				}
			}
		}catch(IOException ie){
			cf.allMasage.append("disconnect failed!\n");
		}
		cf.allMasage.append("disconnect success!\n");
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

}
