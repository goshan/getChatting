package visibleface;

import java.awt.Container;

import javax.swing.*;

import thread.TimerThread;

public class WarningWindow extends JFrame{
	public JProgressBar timer;
	double time;
	
	public TimerThread tt;
	public ChatFrame cf;
	public int type;   //0-eat, 1-toilet, 2-leave, 3-not hand
	public static int EAT = 0;
	public static int TOILET = 1;
	public static int LEAVE = 2;
	public static int NOTHAND = 3;
	public static int EATAGAIN = 4;
	public static int EATFINAL = 5;
	public static int DRINK = 6;
	public static int WATER = 7;
	
	public WarningWindow(ChatFrame cf, double time){
		this.time = time;
		this.timer = new JProgressBar(0, (int)time);
		this.timer.setValue((int)time);
		this.timer.setString((int)(time/60000)+" min");
		this.timer.setStringPainted(true);
		this.add(timer, "Center");
		this.setSize(200, 100);
		this.setVisible(false);
		this.cf = cf;
	}
	
	public void setTime(double time){
		this.time = time;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void printWin(){
		switch(type){
		case 0:
			String []options = {"好的，喵喵", "再等会嘛，喵~"};
			int response = JOptionPane.showOptionDialog(cf.getContentPane(), "喵：臭喵，该吃饭啦！！！", "呼叫", 
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(response==0){
				cf.mout.println("eatOk@"+cf.ui.nickName);
				cf.mout.flush();
			}
			else if(response==1){
				cf.mout.println("eatwait@"+cf.ui.nickName);
				cf.mout.flush();
				this.setVisible(true);
				this.setAlwaysOnTop(true);
				this.tt = new TimerThread(this, time);
				tt.start();
			}
			break;
		case 1:
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：我要上厕所，喵~陪我一起去吧！","提示",JOptionPane.INFORMATION_MESSAGE);
			cf.mout.println("toiletOk@"+cf.ui.nickName);
			cf.mout.flush();
			break;
		case 2:
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：咱们该走了，喵喵~~","提示",JOptionPane.INFORMATION_MESSAGE);
			cf.mout.println("leaveOk@"+cf.ui.nickName);
			cf.mout.flush();
			break;
		case 3:
			cf.mout.println("nothandOk@"+cf.ui.nickName);
			cf.mout.flush();
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：喵！！！不许扣手！！","警告",JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			String []optionsAg = {"好的，喵喵", "再等会嘛，喵~"};
			int responseAg = JOptionPane.showOptionDialog(cf.getContentPane(), "喵：臭喵，时间到了，现在该吃饭了吧~", "呼叫", 
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsAg, optionsAg[0]);
			if(responseAg==0){
				cf.mout.println("eatOk@"+cf.ui.nickName);
				cf.mout.flush();
			}
			else if(responseAg==1){
				cf.mout.println("eatwait@"+cf.ui.nickName);
				cf.mout.flush();
				this.remove(timer);
				this.timer = new JProgressBar(0, (int)time);
				this.timer.setValue((int)time);
				this.timer.setString((int)(time/60000)+" min");
				this.timer.setStringPainted(true);
				this.timer.repaint();
				this.add(timer);
				this.setVisible(true);
				this.setAlwaysOnTop(true);
				this.tt = new TimerThread(this, time);
				tt.start();
			}
			break;
		case 5:
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：喵！！！必须要取吃饭啦！！","警告",JOptionPane.ERROR_MESSAGE);
			cf.mout.println("eatOk@"+cf.ui.nickName);
			cf.mout.flush();
			break;
		case 6:
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：喵~喝点水吧","提示",JOptionPane.INFORMATION_MESSAGE);
			cf.mout.println("drinkOk@"+cf.ui.nickName);
			cf.mout.flush();
			break;
		case 7:
			JOptionPane.showMessageDialog(cf.getContentPane(), "喵：木有水了，呜呜~","提示",JOptionPane.INFORMATION_MESSAGE);
			cf.mout.println("waterOk@"+cf.ui.nickName);
			cf.mout.flush();
			break;
		}
	}
}
