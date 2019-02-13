package thread;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;




public class ServerForeverThread extends Thread{
	public ServerSocket serverSocket;
	public Socket server;
	public BufferedReader in;
	public PrintWriter out;
	public JTextArea masage;
	public static ServerChatThread[] clientChat=new ServerChatThread[2];
	
	public ServerForeverThread(JTextArea masage){
		this.masage=masage;
	}
	
	public void run(){
		try{
			serverSocket=null;
			serverSocket=new ServerSocket(8000);
		}catch(Exception e){}
		while(true){
			try{
				server=null;
				server=serverSocket.accept();
				DoThread doThing=new DoThread(server, masage);
				doThing.start();
			}catch(Exception e){
				break;
			}
		}
	}
}






class DoThread extends Thread{
	public Socket socket;
	public BufferedReader in;
	public PrintWriter out;
	public JTextArea masage;
	
	public DoThread(Socket socket, JTextArea masage){
		this.socket=socket;
		this.masage=masage;
	}
	
	public void run(){
		try {
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true){
			try{
				if (socket!=null){
					String line=in.readLine();
					if (line.startsWith("connect@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						if (user.equals("goshan")){
							ServerChatThread newUser=new ServerChatThread(masage);
							newUser.start();
							ServerForeverThread.clientChat[0] = newUser;
							out.println("connectOk");
							out.flush();
							masage.append("accept connect: "+user+"\n");
							masage.setSelectionStart(masage.getText().length());
							ServerChatThread salt = ServerForeverThread.clientChat[1];
							if (salt != null){
								salt.out.println("online");
								salt.out.flush();
								salt.out.println("goshan");
								salt.out.flush();
								sleep(100);
								ServerChatThread goshan = ServerForeverThread.clientChat[0];
								goshan.out.println("online");
								goshan.out.flush();
								goshan.out.println("salt");
								goshan.out.flush();
							}
						}
						else if (user.equals("salt")){
							ServerChatThread newUser=new ServerChatThread(masage);
							newUser.start();
							ServerForeverThread.clientChat[1] = newUser;
							out.println("connectOk");
							out.flush();
							masage.append("accept connect: "+user+"\n");
							masage.setSelectionStart(masage.getText().length());
							ServerChatThread goshan = ServerForeverThread.clientChat[0];
							if (goshan != null){
								goshan.out.println("online");
								goshan.out.flush();
								goshan.out.println("salt");
								goshan.out.flush();
								sleep(100);
								ServerChatThread salt = ServerForeverThread.clientChat[1];
								salt.out.println("online");
								salt.out.flush();
								salt.out.println("goshan");
								salt.out.flush();
							}
						}
						else {
							out.println("connectFailed");
							out.flush();
							masage.append("not accept connect: "+user+"\n");
							masage.setSelectionStart(masage.getText().length());
						}
					}
					else if (line.startsWith("disconnect@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						if (user.equals("goshan")){
							ServerChatThread disclient = ServerForeverThread.clientChat[0];
							disclient.server.close();
							disclient.serverSocket.close();
							ServerForeverThread.clientChat[0] = null;
							out.println("disconnectOk");
							out.flush();
							masage.append("client disconnect: "+user+"\n");
							masage.setSelectionStart(masage.getText().length());
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("offline");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread disclient = ServerForeverThread.clientChat[1];
							disclient.server.close();
							disclient.serverSocket.close();
							ServerForeverThread.clientChat[1] = null;
							out.println("disconnectOk");
							out.flush();
							masage.append("client disconnect: "+user+"\n");
							masage.setSelectionStart(masage.getText().length());
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("offline");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("eating@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for eating\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("eating");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("eating");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("eatOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for eating\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("eatOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("eatOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("eatwait@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" refuse for eating\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("eatwait");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("eatwait");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("drink@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for drinking\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("drink");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("drink");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("drinkOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for drinking\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("drinkOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("drinkOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("water@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for watering\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("water");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("water");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("waterOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for watering\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("waterOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("waterOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("toilet@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for toilet\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("toilet");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("toilet");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("toiletOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for toilet\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("toiletOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("toiletOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("nothand@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for not hand\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("nothand");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("nothand");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("nothandOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for not hand\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("nothandOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("nothandOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("leave@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" required for leaving\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("leave");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("leave");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
					else if (line.startsWith("leaveOk@")){
						int index = line.indexOf("@");
						String user = line.substring(index+1);
						masage.append(user+" confirm for leaving\n");
						masage.setSelectionStart(masage.getText().length());
						if (user.equals("goshan")){
							ServerChatThread temp = ServerForeverThread.clientChat[1];
							temp.out.println("leaveOk");
							temp.out.flush();
							temp.out.println("goshan");
							temp.out.flush();
						}
						else if (user.equals("salt")){
							ServerChatThread temp = ServerForeverThread.clientChat[0];
							temp.out.println("leaveOk");
							temp.out.flush();
							temp.out.println("salt");
							temp.out.flush();
						}
					}
				}
				sleep(100);
			}catch(Exception e){}
		}
	}
}