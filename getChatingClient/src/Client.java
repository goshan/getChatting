import javax.swing.*;

import listener.*;

import visibleface.*;





public class Client{
	public static void main(String args[]){
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java界面风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
		} catch (Exception e){
			e.printStackTrace();
		} 
		ChatFrame client;
		client=new ChatFrame();
	}
}