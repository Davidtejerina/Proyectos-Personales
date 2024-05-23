package main;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class RuletaFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextArea cantidadIntroducida;
	private JLabel cantidadLabel;
	private JLabel confirmacionLabel;
	private JLabel resultado;
	private JLabel totalLabel;
	private JLabel totalResultado;
	
	private JButton verde;
	private JButton rojo;
	private JButton negro;
	private JButton go;
	private JLabel flecha;
	
	private JPanel panelRuleta;
	
	private JPanel uno;
	private JPanel dos;
	private JPanel tres;
	private JPanel cuatro;
	private JPanel cinco;
	private JPanel seis;
	private JPanel siete;
	private JPanel ocho;
	private JPanel nueve;
	private JPanel diez;
	private JPanel once;
	
	private Timer timer1;
	private Timer timer2;
	
	public RuletaFrame() {
		super("Ruleta de la suerte");
		estilo();
		componentes();
	}
	
	private void estilo() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 170, 822, 493);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void componentes() {
		confirmacionLabel = new JLabel("Confirmación:");
		confirmacionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmacionLabel.setBounds(411, 407, 147, 22);
		confirmacionLabel.setVisible(false);
		contentPane.add(confirmacionLabel);
		
		go = new JButton("GO");
		go.setBounds(691, 409, 89, 23);
		go.addActionListener(this);
		contentPane.add(go);
		
		panelRuleta = new JPanel();
		panelRuleta.setBounds(54, 75, 697, 261);
		panelRuleta.setLayout(null);
		
		uno = new JPanel();
		uno.setBounds(0, 103, 65, 58);
		uno.setBackground(new Color(240, 240, 240));
		panelRuleta.add(uno);
		
		dos = new JPanel();
		dos.setBounds(64, 103, 65, 58);
		dos.setBackground(new Color(240, 240, 240));
		panelRuleta.add(dos);
		
		tres = new JPanel();
		tres.setBounds(126, 103, 65, 58);
		tres.setBackground(new Color(240, 240, 240));
		panelRuleta.add(tres);
		
		cuatro = new JPanel();
		cuatro.setBounds(189, 103, 65, 58);
		cuatro.setBackground(new Color(240, 240, 240));
		panelRuleta.add(cuatro);
		
		cinco = new JPanel();
		cinco.setBounds(253, 103, 65, 58);
		cinco.setBackground(new Color(240, 240, 240));
		panelRuleta.add(cinco);
		
		seis = new JPanel();
		seis.setBounds(316, 103, 65, 58);
		seis.setBackground(new Color(240, 240, 240));
		panelRuleta.add(seis);
		
		siete = new JPanel();
		siete.setBounds(379, 103, 65, 58);
		siete.setBackground(new Color(240, 240, 240));
		panelRuleta.add(siete);
		
		ocho = new JPanel();
		ocho.setBounds(443, 103, 65, 58);
		ocho.setBackground(new Color(240, 240, 240));
		panelRuleta.add(ocho);
		
		nueve = new JPanel();
		nueve.setBounds(506, 103, 65, 58);
		nueve.setBackground(new Color(240, 240, 240));
		panelRuleta.add(nueve);
		
		diez = new JPanel();
		diez.setBounds(569, 103, 65, 58);
		diez.setBackground(new Color(240, 240, 240));
		panelRuleta.add(diez);
		
		once = new JPanel();
		once.setBounds(632, 103, 65, 58);
		once.setBackground(new Color(240, 240, 240));
		panelRuleta.add(once);
		
		contentPane.add(panelRuleta);
		
		flecha = new JLabel("🔰 ");
		flecha.setBounds(339, 76, 30, 28);
		flecha.setFont(flecha.getFont().deriveFont(20f));
		flecha.setVisible(false);
		panelRuleta.add(flecha);
		
		cantidadLabel = new JLabel("Introduce cantidad: ");
		cantidadLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cantidadLabel.setBounds(40, -6, 234, 28);
		panelRuleta.add(cantidadLabel);
		
		cantidadIntroducida = new JTextArea();
		cantidadIntroducida.setBounds(219, 0, 200, 22);
		panelRuleta.add(cantidadIntroducida);
		
		verde = new JButton("V");
		verde.setFont(new Font("Tahoma", Font.PLAIN, 15));
		verde.setBounds(459, 0, 52, 23);
		verde.addActionListener(this);
		panelRuleta.add(verde);
		
		rojo = new JButton("R");
		rojo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rojo.setBounds(522, 0, 52, 23);
		rojo.addActionListener(this);
		panelRuleta.add(rojo);
		
		negro = new JButton("N");
		negro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		negro.setBounds(583, 0, 52, 23);
		negro.addActionListener(this);
		panelRuleta.add(negro);
		
		resultado = new JLabel("");
		resultado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		resultado.setBounds(515, 407, 147, 22);
		contentPane.add(resultado);
		
		totalLabel = new JLabel("Ganancias: ");
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalLabel.setBounds(54, 407, 96, 20);
		totalLabel.setVisible(false);
		contentPane.add(totalLabel);
		
		totalResultado = new JLabel("");
		totalResultado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalResultado.setBounds(139, 407, 104, 22);
		totalResultado.setVisible(false);
		contentPane.add(totalResultado);
	}
	
	private void reseteo() {
		uno.setBackground(new Color(240, 240, 240));
    	dos.setBackground(new Color(240, 240, 240));
    	tres.setBackground(new Color(240, 240, 240));
    	cuatro.setBackground(new Color(240, 240, 240));
    	cinco.setBackground(new Color(240, 240, 240));
    	seis.setBackground(new Color(240, 240, 240));
    	siete.setBackground(new Color(240, 240, 240));
    	ocho.setBackground(new Color(240, 240, 240));
    	nueve.setBackground(new Color(240, 240, 240));
    	diez.setBackground(new Color(240, 240, 240));
    	once.setBackground(new Color(240, 240, 240));
	}
	
	private void cascada(int tiempoRestante, int tiempoTotal) {
		if(tiempoRestante == (tiempoTotal/1000)-1) {
        	once.setBackground(Color.RED);
        }
        if(tiempoRestante == (tiempoTotal/1000)-2) {
        	diez.setBackground(Color.BLACK);
        }
        if(tiempoRestante == (tiempoTotal/1000)-3) {
        	nueve.setBackground(Color.RED);
        }
        if(tiempoRestante == (tiempoTotal/1000)-4) {
        	ocho.setBackground(Color.BLACK);
        }
        if(tiempoRestante == (tiempoTotal/1000)-5) {
        	siete.setBackground(Color.RED);
        }
        if(tiempoRestante == (tiempoTotal/1000)-6) {
        	seis.setBackground(Color.BLACK);
        }
        if(tiempoRestante == (tiempoTotal/1000)-7) {
        	cinco.setBackground(Color.RED);
        }
        if(tiempoRestante == (tiempoTotal/1000)-8) {
        	cuatro.setBackground(Color.BLACK);
        }
        if(tiempoRestante == (tiempoTotal/1000)-9) {
        	tres.setBackground(Color.RED);
        }
        if(tiempoRestante == (tiempoTotal/1000)-10) {
        	dos.setBackground(Color.BLACK);
        }
        if(tiempoRestante == (tiempoTotal/1000)-11) {
        	uno.setBackground(Color.RED);
        }
	}
	
	private void invisible() {
		flecha.setVisible(false);
		cantidadLabel.setVisible(false);
		cantidadIntroducida.setVisible(false);
		totalLabel.setVisible(false);
		totalResultado.setVisible(false);
		verde.setVisible(false);
		rojo.setVisible(false);
		negro.setVisible(false);
		go.setVisible(false);
	}

	private void visible() {
		cantidadLabel.setVisible(true);
		cantidadIntroducida.setVisible(true);
		verde.setVisible(true);
		rojo.setVisible(true);
		negro.setVisible(true);
		go.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == go) {
			
			if(cantidadIntroducida.getText().equals("")||cantidadIntroducida.getText().equals("0")||cantidadIntroducida.getText().contains("-")) {
				JOptionPane.showMessageDialog(null, "Por favor, Introduce cantidad válida", null, JOptionPane.OK_OPTION);
			}
			else if(resultado.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, elige un color", null, JOptionPane.OK_OPTION);
			}
			else {
				invisible();
				reseteo();
				int numeroVerde = (int) (Math.random()*20);
				int tiempoTotal = 30000;
				
				timer1 = new Timer(tiempoTotal, this);
				timer2 = new Timer(200, this);
				
				timer2.addActionListener(new ActionListener() {
		            int tiempoRestante = timer1.getDelay() / 1000;
		            
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	try {
	            			int total = Integer.parseInt(cantidadIntroducida.getText());
			                tiempoRestante--;
			                
			                cascada(tiempoRestante, tiempoTotal);
			                
			                if(tiempoRestante <= (tiempoTotal/1000)-12 && tiempoRestante >=1) {
			                	flecha.setVisible(true);
			                	
			                	if(tiempoRestante == numeroVerde-10) {
				                	if(uno.getBackground().equals(Color.BLACK)) uno.setBackground(Color.GREEN);
				                	else if (uno.getBackground().equals(Color.RED)) uno.setBackground(Color.GREEN);
			                	}else{
				                	if(uno.getBackground().equals(Color.BLACK)) uno.setBackground(Color.RED);
				                	else if (uno.getBackground().equals(Color.RED)) uno.setBackground(Color.BLACK);
				                	else if (uno.getBackground().equals(Color.GREEN)) uno.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-9) {
				                	if(dos.getBackground().equals(Color.BLACK)) dos.setBackground(Color.GREEN);
				                	else if (dos.getBackground().equals(Color.RED)) dos.setBackground(Color.GREEN);
			                	}else {
				                	if(dos.getBackground().equals(Color.BLACK)) dos.setBackground(Color.RED);
				                	else if (dos.getBackground().equals(Color.RED)) dos.setBackground(Color.BLACK);
				                	else if (dos.getBackground().equals(Color.GREEN)) dos.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-8) {
				                	if(tres.getBackground().equals(Color.BLACK)) tres.setBackground(Color.GREEN);
				                	else if (tres.getBackground().equals(Color.RED)) tres.setBackground(Color.GREEN);
			                	}else {
				                	if(tres.getBackground().equals(Color.BLACK)) tres.setBackground(Color.RED);
				                	else if (tres.getBackground().equals(Color.RED)) tres.setBackground(Color.BLACK);
				                	else if (tres.getBackground().equals(Color.GREEN)) tres.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-7) {
				                	if(cuatro.getBackground().equals(Color.BLACK)) cuatro.setBackground(Color.GREEN);
				                	else if (cuatro.getBackground().equals(Color.RED)) cuatro.setBackground(Color.GREEN);
			                	}else {
				                	if(cuatro.getBackground().equals(Color.BLACK)) cuatro.setBackground(Color.RED);
				                	else if (cuatro.getBackground().equals(Color.RED)) cuatro.setBackground(Color.BLACK);
				                	else if (cuatro.getBackground().equals(Color.GREEN)) cuatro.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-6) {
				                	if(cinco.getBackground().equals(Color.BLACK)) cinco.setBackground(Color.GREEN);
				                	else if (cinco.getBackground().equals(Color.RED)) cinco.setBackground(Color.GREEN);
			                	}else {
				                	if(cinco.getBackground().equals(Color.BLACK)) cinco.setBackground(Color.RED);
				                	else if (cinco.getBackground().equals(Color.RED)) cinco.setBackground(Color.BLACK);
				                	else if (cinco.getBackground().equals(Color.GREEN)) cinco.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-5) {
				                	if(seis.getBackground().equals(Color.BLACK)) seis.setBackground(Color.GREEN);
				                	else if (seis.getBackground().equals(Color.RED)) seis.setBackground(Color.GREEN);
			                	}else {
				                	if(seis.getBackground().equals(Color.BLACK)) seis.setBackground(Color.RED);
				                	else if (seis.getBackground().equals(Color.RED)) seis.setBackground(Color.BLACK);
				                	else if (seis.getBackground().equals(Color.GREEN)) seis.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-4) {
				                	if(siete.getBackground().equals(Color.BLACK)) siete.setBackground(Color.GREEN);
				                	else if (siete.getBackground().equals(Color.RED)) siete.setBackground(Color.GREEN);
			                	}else {
				                	if(siete.getBackground().equals(Color.BLACK)) siete.setBackground(Color.RED);
				                	else if (siete.getBackground().equals(Color.RED)) siete.setBackground(Color.BLACK);
				                	else if (siete.getBackground().equals(Color.GREEN)) siete.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-3) {
			                		if(ocho.getBackground().equals(Color.BLACK)) ocho.setBackground(Color.GREEN);
			                		else if (ocho.getBackground().equals(Color.RED)) ocho.setBackground(Color.GREEN);
			                	}else {
			                		if(ocho.getBackground().equals(Color.BLACK)) ocho.setBackground(Color.RED);
			                		else if (ocho.getBackground().equals(Color.RED)) ocho.setBackground(Color.BLACK);
			                		else if (ocho.getBackground().equals(Color.GREEN)) ocho.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-2) {
				                	if(nueve.getBackground().equals(Color.BLACK)) nueve.setBackground(Color.GREEN);
				                	else if (nueve.getBackground().equals(Color.RED)) nueve.setBackground(Color.GREEN);
			                	}else {
				                	if(nueve.getBackground().equals(Color.BLACK)) nueve.setBackground(Color.RED);
				                	else if (nueve.getBackground().equals(Color.RED)) nueve.setBackground(Color.BLACK);
				                	else if (nueve.getBackground().equals(Color.GREEN)) nueve.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde-1) {
				                	if(diez.getBackground().equals(Color.BLACK)) diez.setBackground(Color.GREEN);
				                	else if (diez.getBackground().equals(Color.RED)) diez.setBackground(Color.GREEN);
			                	}else {
				                	if(diez.getBackground().equals(Color.BLACK)) diez.setBackground(Color.RED);
				                	else if (diez.getBackground().equals(Color.RED)) diez.setBackground(Color.BLACK);
				                	else if (diez.getBackground().equals(Color.GREEN)) diez.setBackground(Color.BLACK);
			                	}
			                	
			                	if(tiempoRestante == numeroVerde) {
				                	if(once.getBackground().equals(Color.BLACK)) once.setBackground(Color.GREEN);
				                	else if (once.getBackground().equals(Color.RED)) once.setBackground(Color.GREEN);
			                	}else {
				                	if(once.getBackground().equals(Color.BLACK)) once.setBackground(Color.RED);
				                	else if (once.getBackground().equals(Color.RED)) once.setBackground(Color.BLACK);
				                	else if (once.getBackground().equals(Color.GREEN)) once.setBackground(Color.BLACK);
			                	}
			                } 
			                if(tiempoRestante == 0) {
			                	cantidadIntroducida.setText("");
			                	resultado.setText("");
			                	confirmacionLabel.setVisible(false);
			                	visible();
			                	timer2.stop();
			                	if(seis.getBackground().equals(Color.BLACK) && resultado.getForeground().equals(Color.BLACK)) total=total*2;
			                	if(seis.getBackground().equals(Color.RED) && resultado.getForeground().equals(Color.RED)) total=total*2;
			                	if(seis.getBackground().equals(Color.GREEN) && resultado.getForeground().equals(Color.GREEN)) total=total*14;
			                	
			                	if(seis.getBackground().equals(Color.BLACK) && !resultado.getForeground().equals(Color.BLACK)) total=0;
			                	if(seis.getBackground().equals(Color.BLACK) && !resultado.getForeground().equals(Color.BLACK)) total=0;
			                	if(seis.getBackground().equals(Color.BLACK) && !resultado.getForeground().equals(Color.BLACK)) total=0;
			                	
			                	if(total==0) {
			                		totalResultado.setForeground(Color.RED);
			                		totalResultado.setText(Integer.toString(total) + " €");
			                	}
			                	else {
			                		totalResultado.setForeground(Color.GREEN);
			                		totalResultado.setText(Integer.toString(total) + " €");
			                	}
			                	totalLabel.setVisible(true);
			                	totalResultado.setVisible(true);
			                }
			            }catch(java.lang.NumberFormatException e1) {
			    			JOptionPane.showMessageDialog(null, "Por favor, Introduce cantidad válida", null, JOptionPane.OK_OPTION);
			    			System.exit(0);
			    		}
		            }
		        });
				timer2.restart();
			}
		}
		if(e.getSource() == verde) {
			if(cantidadIntroducida.getText().equals("")||cantidadIntroducida.getText().equals("0")||cantidadIntroducida.getText().contains("-")) {
				JOptionPane.showMessageDialog(null, "Por favor, Introduce cantidad válida", null, JOptionPane.OK_OPTION);
			}
			else {
				String texto = cantidadIntroducida.getText();
				confirmacionLabel.setVisible(true);
				resultado.setForeground(Color.GREEN);
				resultado.setText(texto + "  € [verde]");
			}
		}
		
		if(e.getSource() == rojo) {
			if(cantidadIntroducida.getText().equals("")||cantidadIntroducida.getText().equals("0")||cantidadIntroducida.getText().contains("-")) {
				JOptionPane.showMessageDialog(null, "Por favor, Introduce cantidad válida", null, JOptionPane.OK_OPTION);
			}
			else {
				String texto = cantidadIntroducida.getText();
				confirmacionLabel.setVisible(true);
				resultado.setForeground(Color.RED);
				resultado.setText(texto + " €  [rojo]");
			}
		}
		
		if(e.getSource() == negro) {
			if(cantidadIntroducida.getText().equals("")||cantidadIntroducida.getText().equals("0")||cantidadIntroducida.getText().contains("-")) {
				JOptionPane.showMessageDialog(null, "Por favor, Introduce cantidad válida", null, JOptionPane.OK_OPTION);
			}
			else {
				String texto = cantidadIntroducida.getText();
				confirmacionLabel.setVisible(true);
				resultado.setForeground(Color.BLACK);
				resultado.setText(texto + " €  [negro]");
			}
		}
	}	
}


