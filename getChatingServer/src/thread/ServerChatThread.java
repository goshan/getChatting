package thread;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import thread.*;




public class ServerChatThread extends Thread{
	public ServerSocket serverSocket;
	public Socket server;
	public BufferedReader in;
	public PrintWriter out;
	public JTextArea masage;
	
	public ServerChatThread(JTextArea masage){
		this.masage=masage;
	}
	
	public void run(){
		try{
			serverSocket=null;
			serverSocket=new ServerSocket(8002);
		}catch(Exception e){}
		server=null;
		try{
			server=serverSocket.accept();
			serverSocket.close();
			in=new BufferedReader(new InputStreamReader(server.getInputStream()));
			out=new PrintWriter(server.getOutputStream());
		}catch(Exception e) {}
		while(true){
			try{
				try{
					String line1=in.readLine();
					String line2=in.readLine();
					if (line1!=null && line2!=null){
						Date date=new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String currentTime= formatter.format(date);
						String words=currentTime+" "+line1+"\n"+line2;
						this.masage.append(words+"\n");
						masage.setSelectionStart(masage.getText().length());
						for(int i=0; i<ServerForeverThread.clientChat.length; i++){
							ServerChatThread temp = ServerForeverThread.clientChat[i];
							if (temp == null)
								continue;
							PrintWriter output=temp.out;
							output.println(words);
							output.flush();
						}
					}
					sleep(100);
				}catch (IOException e){
					break;
				}
			}catch(Exception e){
				masage.append("Error at chating!\n");
				masage.setSelectionStart(masage.getText().length());
			}
		}
	}
}