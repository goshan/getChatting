package listener;

import visibleface.ChatFrame;
import java.awt.event.*;

public class CallListener implements MouseListener{
	ChatFrame cf;
	
	public CallListener(ChatFrame cf){
		this.cf = cf;
	}

	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		cf.setAlwaysOnTop(true);
		cf.tt.reset();
		if (source == cf.eat){
			cf.mout.println("eating@"+cf.ui.nickName);
			cf.mout.flush();
		}
		else if (source == cf.drink){
			cf.mout.println("drink@"+cf.ui.nickName);
			cf.mout.flush();
		}else if (source == cf.water){
			cf.mout.println("water@"+cf.ui.nickName);
			cf.mout.flush();
		}
		else if (source == cf.toilet){
			cf.mout.println("toilet@"+cf.ui.nickName);
			cf.mout.flush();
		}
		else if (source == cf.nothand){
			cf.mout.println("nothand@"+cf.ui.nickName);
			cf.mout.flush();
		}
		else if (source == cf.leave){
			cf.mout.println("leave@"+cf.ui.nickName);
			cf.mout.flush();
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
