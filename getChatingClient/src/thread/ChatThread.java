package thread;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import visibleface.ChatFrame;
import visibleface.WarningWindow;

import file.UserInfo;

public class ChatThread extends Thread{
	public ChatFrame chat;
	
	public ChatThread(ChatFrame chat){
		this.chat=chat;
	}
	
	public void run(){
		try{
			chat.chat=new Socket(UserInfo.IP,8002);
			chat.cout=new PrintWriter(chat.chat.getOutputStream());
			chat.cin=new BufferedReader(new InputStreamReader(chat.chat.getInputStream()));
			chat.allMasage.append("connect to the server\nyour nickname is "+UserInfo.nickName+"\n");
			chat.send.setEnabled(true);
			chat.masage.setEditable(true);
		}catch(Exception e){chat.allMasage.setText("fail to connect to server\n");}        //连接失败
		while(true){
			try{
				String line1=chat.cin.readLine();
				String line2=chat.cin.readLine();
				chat.setAlwaysOnTop(true);
				chat.tt.reset();
				if (line1!=null && line2!=null){
					if (line1.equals("online")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" is online\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("offline")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" is offline\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("eating")){
						chat.ww.setType(WarningWindow.EAT);
						chat.ww.printWin();
					}
					else if (line1.equals("eatOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for eating\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("eatwait")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" refuse for eating\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("drink")){
						chat.ww.setType(WarningWindow.DRINK);
						chat.ww.printWin();
					}
					else if (line1.equals("drinkOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for drinking\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("water")){
						chat.ww.setType(WarningWindow.WATER);
						chat.ww.printWin();
					}
					else if (line1.equals("waterOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for watering\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("toilet")){
						chat.ww.setType(WarningWindow.TOILET);
						chat.ww.printWin();
					}
					else if (line1.equals("toiletOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for toilet\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("nothand")){
						chat.ww.setType(WarningWindow.NOTHAND);
						chat.ww.printWin();
					}
					else if (line1.equals("nothandOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for not hand\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else if (line1.equals("leave")){
						chat.ww.setType(WarningWindow.LEAVE);
						chat.ww.printWin();
					}
					else if (line1.equals("leaveOk")){
						chat.allMasage.append(
								"\n-------------------\n"
								+line2+" confirm for leaving\n"+
								"CopyRight: goshan\n" +
								"-------------------\n\n");
					}
					else {
						if (line2.length() > 19)
							line2 = line2.substring(0, 19)+"\n"+line2.substring(19);
						String words=line1+"\n"+line2+"\n";
						chat.allMasage.append(words);
					}
					chat.allMasage.setSelectionStart(chat.allMasage.getText().length());
				}
				sleep(100);
			}catch(Exception e){
				try {
					chat.allMasage.append("lose the connection to server\n");             //失去连接
					chat.cin = null;
					chat.cout = null;
					chat.chat.close();
					chat.min = null;
					chat.mout = null;
					chat.msg.close();
					chat.disconnect.setEnabled(false);
					chat.connect.setEnabled(true);
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}