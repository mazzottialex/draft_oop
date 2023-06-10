package application;
import java.awt.EventQueue;

import view.Start;

public class App {
	public static void main(String[] args) throws Exception {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
