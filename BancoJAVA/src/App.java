package huchasegura;

import java.awt.*;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CuentaFrame();
			}
		});
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new CuentaFrame();
//			}
//		});
	}
}
