package thread;

import visibleface.ChatFrame;
import visibleface.WarningWindow;

public class TimerThread extends Thread{
	public ChatFrame cf;
	public WarningWindow ww;
	public double time;
	public double start;
	public int type;
	
	public TimerThread(ChatFrame cf, double time){
		this.cf=cf;
		this.time = time;
		this.type = 0;  //0-system, 1-eating
	}
	
	public TimerThread(WarningWindow ww, double time){
		this.ww=ww;
		this.time = time;
		this.type = 1;  //0-system, 1-eating
	}
	
	public void reset(){
		this.start = System.currentTimeMillis();
	}
	
	public void reset(double time){
		this.start = System.currentTimeMillis();
		this.time = time;
	}
	
	public void run(){
		start = System.currentTimeMillis();
		while (true){
			double end = System.currentTimeMillis();
			double last = start+time-end;
			switch(type){
			case 0:
				if (last<=0){
					cf.setAlwaysOnTop(false);
					cf.toBack();
					this.reset();
					last = start+time-end;
				}
				break;
			case 1:
				int min = (int)(last/60000);
				int sec = (int)((last-(min*60000))/1000);
				ww.timer.setValue((int)last);
				ww.timer.setString(min+" min "+sec+" sec");
				if (min <= 0 && sec <= 0){
					this.ww.setAlwaysOnTop(false);
					this.ww.setVisible(false);
					time -= 120000;
					if (time > 0){
						this.ww.setTime(time);
						this.ww.setType(WarningWindow.EATAGAIN);
						this.ww.printWin();
						cf.tt.reset();
						last = -1;
					}
					else {
						this.ww.setType(WarningWindow.EATFINAL);
						this.ww.printWin();
						cf.tt.reset();
						last = -1;
					}
				}
				break;
			}
			if (last<=0)
				break;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
