package hilos;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.util.*;


@SuppressWarnings("serial")
public class JuegoPelota extends JFrame {
	private Lamina lamina;
	private JLabel mejorPuntuacion;
	private JLabel nuevaPuntuacion;
	private JLabel actualPuntuacion;
	private JTextPane usuarioPanel;
	private boolean isSeleccionado=false;
	
	private Timer timerIndicadorPuntuacion;
	private Timer timerColorIndicadorUsuarioIntroducido;
	private long tiempoInicial;
	private boolean juegoEnCurso=false;
	
	private JButton botonDale;
	private JButton botonSalir;
	private JButton botonVolver;
	
	private ArrayList<PelotaHilos> hilosPelotas = new ArrayList<>();

	public JuegoPelota() {
		super("Rebotes - David Tejerina");
		this.setBounds(550, 150, 400, 600);
		this.setResizable(false);
		lamina = new Lamina();
		
		
		
		//Pulsa Dale!
		JLabel texto=new JLabel("Pulsa Dale!");
		texto.setBounds(0, 0, 386, 513);
        texto.setHorizontalAlignment(JLabel.CENTER); 
        texto.setVerticalAlignment(JLabel.CENTER);
        texto.setForeground(new Color(255, 255, 255));
        texto.setFont(new Font("Showcard Gothic", Font.BOLD, 36));
        texto.setVisible(true);
        
        
        
        //datos usuario
        usuarioPanel=new JTextPane();
        usuarioPanel.setText("Usuario");
        
        LineBorder bordeRojo = new LineBorder(Color.RED, 2);
        usuarioPanel.setBorder(bordeRojo);
        StyledDocument doc = usuarioPanel.getStyledDocument();
        SimpleAttributeSet centerAlignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlignment, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), centerAlignment, false);

        usuarioPanel.setBounds(45, 345, 298, 40);
        usuarioPanel.setForeground(new Color(255, 255, 255));
        usuarioPanel.setOpaque(false);
        usuarioPanel.setFont(new Font("Agency FB", Font.BOLD, 30));
        usuarioPanel.setVisible(true);
        usuarioPanel.setEditable(false);
        usuarioPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				isSeleccionado = true;
            	usuarioPanel.setText(null);
            	usuarioPanel.setEditable(true);
			}
		});
        
        
        
        
		//creacción de la mejor puntuacion del usuario
		mejorPuntuacion = new JLabel();
		mejorPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		mejorPuntuacion.setBounds(0, 131, 386, 99);
		mejorPuntuacion.setVerticalAlignment(JLabel.CENTER);
		mejorPuntuacion.setForeground(new Color(255, 255, 255));
		mejorPuntuacion.setFont(new Font("Agency FB", Font.PLAIN, 36));
		mejorPuntuacion.setVisible(true);
		lamina.setLayout(null);
		
		
		
		//creacción de la puntuacion nueva del usuario
		nuevaPuntuacion = new JLabel();
		nuevaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		nuevaPuntuacion.setBounds(0, 254, 386, 99);
		nuevaPuntuacion.setVerticalAlignment(JLabel.CENTER);
		nuevaPuntuacion.setForeground(new Color(255, 255, 255));
		nuevaPuntuacion.setFont(new Font("Agency FB", Font.PLAIN, 36));
		nuevaPuntuacion.setVisible(true);
		lamina.setLayout(null);
		
		
		
		//creacción de la puntuacion actual (en el momento)
		actualPuntuacion = new JLabel("0",SwingConstants.CENTER);
		actualPuntuacion.setHorizontalAlignment(SwingConstants.RIGHT);
		actualPuntuacion.setBounds(283, 10, 93, 32);
		actualPuntuacion.setVerticalAlignment(JLabel.CENTER);
		actualPuntuacion.setForeground(new Color(255, 255, 255));
		actualPuntuacion.setFont(new Font("Agency FB", Font.PLAIN, 20));
		actualPuntuacion.setVisible(false);
		lamina.setLayout(null);
		
		
		// Agregar el contenedor central al centro de la lámina
		lamina.add(texto);
		lamina.add(mejorPuntuacion);
		lamina.add(nuevaPuntuacion);
		lamina.add(actualPuntuacion);
		lamina.add(usuarioPanel);
		getContentPane().add(lamina, BorderLayout.CENTER);
		
		
		//panel botones y el método para poner los 3 botones
		JPanel laminaBotones = new JPanel();
		laminaBotones.setBackground(Color.red);
		
		ponerBotonPantalla(laminaBotones, Constantes.botonSalir, new ActionListener() {
			public void actionPerformed(ActionEvent evento) { 
				juegoEnCurso=false;
				System.exit(0); 
			}
		});
		
		ponerBotonPantalla(laminaBotones, Constantes.botonVolver, new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				juegoEnCurso=false;
				texto.setVisible(true);
				usuarioPanel.setText("Usuario");
				resetearJuego();
			}
		});
		
		ponerBotonPantalla(laminaBotones, Constantes.botonDale, new ActionListener() {
		    public void actionPerformed(ActionEvent evento) {
		    	if(!usuarioPanel.getText().equals("Usuario")&&!usuarioPanel.getText().isBlank()) {
		    		usuarioPanel.setVisible(false);
			    	juegoEnCurso=true;
			        texto.setVisible(false);
	
			        // Crear el temporizador con un ActionListener separado
			        timerIndicadorPuntuacion = new Timer(1000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	actualPuntuacion.setText(String.valueOf((System.currentTimeMillis() - tiempoInicial) / 1000));
			                if (lamina.isFin()) {
			                	timerIndicadorPuntuacion.stop(); 
			                	actualPuntuacion.setVisible(false);
			                	if(juegoEnCurso) {
			                		String puntuacion = String.valueOf((System.currentTimeMillis() - tiempoInicial) / 1000 -1);
			                		nuevaPuntuacion.setText("NUEVO: ".concat(puntuacion).concat(" puntos"));
				                	GestionBaseDeDatos gestion = new GestionBaseDeDatos();
				                	gestion.registrarPuntuacion(puntuacion);
				                	mejorPuntuacion.setText("RECORD: "+gestion.mejorPuntuacion()+" puntos");
				                	actualPuntuacion.setText("0");
			                	}
			                }
			            }
			        });
			        
			        comenzarJuego();
			        
		    	}else {
		    		//crear el temporizador con un actionListener sobre el la variable Timer
			        timerColorIndicadorUsuarioIntroducido = new Timer(100, new ActionListener() {
			        	private int tiempo = 6;
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	tiempo--;
			            	usuarioPanel.setForeground(Color.RED);
			            	switch (tiempo) {
				            	case 6:case 3:case 1:usuarioPanel.setForeground(Color.RED); break;
				                case 5:case 2:case 0:usuarioPanel.setForeground(Color.WHITE); break;
			            	}
							if(tiempo<=0) {
								usuarioPanel.setForeground(Color.WHITE);
								timerColorIndicadorUsuarioIntroducido.stop();
							}
			            }
			        });
			        timerColorIndicadorUsuarioIntroducido.restart();
		    		if(isSeleccionado) usuarioPanel.setText("Usuario");
		    	}
		    }
		});

		getContentPane().add(laminaBotones, BorderLayout.SOUTH);

		setTeclado();
	}

	private void setTeclado() {
		// Agregar KeyListener a la lámina para manejar eventos del teclado
        lamina.addKeyListener(new java.awt.event.KeyListener() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                int key = e.getKeyCode();
                if (key == java.awt.event.KeyEvent.VK_LEFT) lamina.moverBarraIzquierda();
                if (key == java.awt.event.KeyEvent.VK_RIGHT) lamina.moverBarraDerecha();
//              if(key == java.awt.event.KeyEvent.VK_SPACE) lamina.moverBloque();
            }

            public void keyReleased(java.awt.event.KeyEvent e) {
            }

            public void keyTyped(java.awt.event.KeyEvent e) {
            }
        });

        // Asegurar que la lámina obtenga el enfoque para recibir eventos del teclado
        lamina.setFocusable(true); 
	}
	
	private void ponerBotonPantalla(Container contenedorBotones, String tituloDelBoton, ActionListener accion) {
		switch(tituloDelBoton) {
		case Constantes.botonDale: botonDale=new JButton(tituloDelBoton); contenedorBotones.add(botonDale); botonDale.addActionListener(accion); break;
		case Constantes.botonSalir: botonSalir=new JButton(tituloDelBoton); contenedorBotones.add(botonSalir); botonSalir.addActionListener(accion); break;
		case Constantes.botonVolver: botonVolver=new JButton(tituloDelBoton); contenedorBotones.add(botonVolver); botonVolver.addActionListener(accion); break;
		}
		
	}

	private void comenzarJuego() {
		juegoEnCurso=true;
		botonDale.setEnabled(false);
		actualPuntuacion.setVisible(true);
		nuevaPuntuacion.setText(null);
		actualPuntuacion.setText("0");
		
        Barra barra = new Barra(lamina);
        Bloque bloque = new Bloque();
        Pelota pelota = new Pelota(lamina);
        pelota.configBloque(bloque);
        
        lamina.add(barra);
        lamina.add(pelota);
        lamina.add(bloque);
        
		Runnable r = new PelotaHilos(pelota, barra, bloque, lamina);
		hilosPelotas.add((PelotaHilos) r); 
		Thread t = new Thread(r);
		t.start();
		
        tiempoInicial = System.currentTimeMillis(); 
        timerIndicadorPuntuacion.start();
	}
	
	private void resetearJuego() {
		juegoEnCurso=false;
		botonDale.setEnabled(true);
		usuarioPanel.setVisible(true);
		mejorPuntuacion.setText(null);
		nuevaPuntuacion.setText(null);
		actualPuntuacion.setVisible(false);
		timerIndicadorPuntuacion.stop();
		
        for(PelotaHilos hilo : hilosPelotas) hilo.detenerHilos();
        hilosPelotas.clear();  
        
        lamina.vaciarLamina();
	}

	private class GestionBaseDeDatos {
		private static Connection con;
		
		private GestionBaseDeDatos() {
			try {
				if(con==null) {
					Class.forName(Constantes.driver);
					con=DriverManager.getConnection(Constantes.BD, Constantes.user, Constantes.pass);
				}
				try(Statement st = con.createStatement()){ st.executeUpdate(Constantes.sentenciaCreateTable);
//				try(Statement st = con.createStatement()){ st.executeUpdate("DROP TABLE PUNTUACIONES");
				}catch (SQLException e) { e.printStackTrace(); }
			} catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
		}
		
		private void registrarPuntuacion(String puntuacion) {
			try(PreparedStatement ps = con.prepareStatement(Constantes.consultaInsertPuntuacion)){
				ps.setString(1, usuarioPanel.getText().trim());
				ps.setInt(2, Integer.parseInt(puntuacion));
				ps.setTimestamp(3, Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.executeUpdate();
			}catch (SQLException e) { e.printStackTrace(); }
		}
		
		private long mejorPuntuacion() {
			try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(String.format(Constantes.consultaSelectMejorPuntuacion, usuarioPanel.getText().trim()))){
				if(rs.next()) return rs.getLong("SCORE");
			}catch (SQLException e) { e.printStackTrace(); }
			return 0;
		}
	}
}
