import javax.swing.*;

import listener.*;

import visibleface.*;





public class Client{
	public static void main(String args[]){
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus���
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//��ǰϵͳ���
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif���
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//��ƽ̨��Java������
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows���
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java���
		} catch (Exception e){
			e.printStackTrace();
		} 
		ChatFrame client;
		client=new ChatFrame();
	}
}