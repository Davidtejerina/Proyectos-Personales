package huchasegura;

import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.awt.datatransfer.*;

/*
Pulsar un botón 					JButton 					ActionEvent
Cambio del texto 					JTextComponent 				TextEvent
Pulsar Intro en un campo de texto 	JTextField 					ActionEvent
Selección de un nuevo elemento 		JCombobox 					ItemEvent
																ActionEvent
Selección de elemento(s) 			JList 						ListSelectionEvent
Pulsar una casilla de verificación 	JCheckBox 					ItemEvent
																ActionEvent
Pulsar un botón de radio 			JRadioButton 				ItemEvent
																ActionEvent
Selección de una opción de menú 	JMenuItem 					ActionEvent
Mover la barra de desplazamiento 	JScrollBar 					AdjustmentEvent
Abrir, cerrar, minimizar,
maximizar o cerrar la ventana 		JWindow 					WindowEvent
*/

@SuppressWarnings("serial")
public class CuentaFrame extends JFrame implements ActionListener, Constantes {
	private Connection con;

	private Timer timer;
	private Timer timer2;

	private JPanel panelPrincipal;
	private JPanel panelInferior;

	private JLabel cantidadTotal;

	private JMenuBar menu;

	private JMenu menuPrincipal1;
	private JMenu menuPrincipal2;
	private JMenu menuPrincipal3;
	private JMenu menuVacio;
	private JMenu admin;

	private JMenuItem itemInsertar;
	private JMenuItem itemSacar;
	private JMenuItem itemTransferencia;
	private JMenuItem itemListar;
	private JMenuItem itemBorrar;
	private JMenuItem itemNuevo;
	private JMenuItem itemExistente;
	private JMenuItem usuarios;

	private JTextArea texto;
	
	private JButton ayuda;

	private DML dml;
	private DDL ddl;
	
	public CuentaFrame() {
		con = CST.conexion();
		dml = new DML(con);
		ddl = new DDL(con);
		formato();
		menu();
		panelInferior();
		ruta(CST.getUser());
	}
	
	@SuppressWarnings("static-access")
	public CuentaFrame(String user) {
		CST cst = new CST(user);
		con = cst.conexion();
		dml = new DML(con);
		ddl = new DDL(con);
		formato2(user);
		menu();
		panelInferior();
		ruta(user);
	}

	private void formato() {
		setVisible(true);
		try {
			setTitle("CUENTA DE " + CST.getUser().toUpperCase());
		} catch (NullPointerException e) {
			System.exit(ABORT);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(posicionFrameX, posicionFrameY, tamañoFrameX, tamañoFrameY);
		setResizable(false); // No redimensionable
		getContentPane().setBackground(new Color(233, 225, 225));
	}

	private void formato2(String userNuevo) {
		setVisible(true);
		try {
			setTitle("CUENTA DE " + userNuevo.toUpperCase());
		} catch (NullPointerException e) {
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(posicionFrameX, posicionFrameY, tamañoFrameX, tamañoFrameY);
		setResizable(false); // No redimensionable
		getContentPane().setBackground(new Color(233, 225, 225));
	}
	
	private void menu() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new FlowLayout()); // Puedes ajustar el diseño según tus necesidades
		menu = new JMenuBar();
		menu.setBackground(Color.BLACK);

		menuPrincipal1 = new JMenu("Operaciones");
		menuPrincipal1.setForeground(Color.WHITE);
		menuPrincipal1.addActionListener(this);
		menuPrincipal1.setBorderPainted(false);

		menuPrincipal2 = new JMenu("Cartera");
		menuPrincipal2.setForeground(Color.WHITE);
		menuPrincipal2.addActionListener(this);
		menuPrincipal2.setBorderPainted(false);

		menuPrincipal3 = new JMenu("Cambiar usuario");
		menuPrincipal3.setForeground(Color.WHITE);
		menuPrincipal3.addActionListener(this);
		menuPrincipal3.setBorderPainted(false);
		menuPrincipal3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		menuVacio = new JMenu();
		menuVacio.setEnabled(false);
		menuVacio.setForeground(Color.WHITE);
		menuVacio.addActionListener(this);
		menuVacio.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 180));

		admin = new JMenu("ADMIN");
		admin.setForeground(Color.YELLOW);
		admin.addActionListener(this);
		admin.setBorderPainted(false);
		admin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 14));

		itemInsertar = new JMenuItem("Insertar dinero");
		itemInsertar.setForeground(Color.BLACK);
		itemInsertar.setBackground(Color.RED);
		itemInsertar.addActionListener(this);

		itemSacar = new JMenuItem("Sacar dinero");
		itemSacar.setForeground(Color.BLACK);
		itemSacar.setBackground(Color.RED);
		itemSacar.addActionListener(this);

		itemTransferencia = new JMenuItem("Transferir dinero");
		itemTransferencia.setForeground(Color.BLACK);
		itemTransferencia.setBackground(Color.RED);
		itemTransferencia.addActionListener(this);

		itemListar = new JMenuItem("Ver datos");
		itemListar.setForeground(Color.BLACK);
		itemListar.setBackground(Color.RED);
		itemListar.addActionListener(this);

		itemBorrar = new JMenuItem("Borrar datos");
		itemBorrar.setForeground(Color.BLACK);
		itemBorrar.setBackground(Color.RED);
		itemBorrar.addActionListener(this);

		itemNuevo = new JMenuItem("Nuevo");
		itemNuevo.setForeground(Color.BLACK);
		itemNuevo.setBackground(Color.RED);
		itemNuevo.addActionListener(this);

		itemExistente = new JMenuItem("Existente");
		itemExistente.setForeground(Color.BLACK);
		itemExistente.setBackground(Color.RED);
		itemExistente.addActionListener(this);

		usuarios = new JMenuItem("Usuarios");
		usuarios.setForeground(Color.BLACK);
		usuarios.setBackground(Color.RED);
		usuarios.addActionListener(this);

		menuPrincipal1.add(itemInsertar);
		menuPrincipal1.add(itemSacar);
		menuPrincipal1.add(itemTransferencia);

		menuPrincipal2.add(itemListar);
		menuPrincipal2.add(itemBorrar);

		menuPrincipal3.add(itemNuevo);
		menuPrincipal3.add(itemExistente);

		admin.add(usuarios);

		menu.add(menuPrincipal1);
		menu.add(menuPrincipal2);
		menu.add(menuVacio);
		menu.add(menuPrincipal3);
		menu.add(admin);

		setJMenuBar(menu);

		JPanel panelTexto = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		panelTexto.add(scrollPane, BorderLayout.CENTER); // Agregar el área de texto al panel historial

		texto = new JTextArea();
		texto.setEditable(false);
		texto.setMargin(new Insets(20, 40, 10, 30));
		texto.setFont(new Font("Arial", Font.PLAIN, 13));

		scrollPane.setViewportView(texto);
		getContentPane().add(panelTexto, BorderLayout.CENTER);
	}

	private void panelInferior() {
		panelInferior = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.LINE_AXIS));
        
		cantidadTotal = new JLabel();
		cantidadTotal.setHorizontalAlignment(SwingConstants.LEFT);
		cantidadTotal.setBorder(new EmptyBorder(0, 0, 0, 0));
		cantidadTotal.setFont(new Font("Arial", Font.BOLD, 16));
		cantidadTotal.setForeground(Color.BLACK);
		
		ayuda = new JButton("Copiar link BDH2");
		ayuda.setHorizontalAlignment(SwingConstants.RIGHT);
		ayuda.setMargin(new Insets(0, 0, 0, 0));
		ayuda.setFont(new Font("Arial", Font.PLAIN, 15));
		ayuda.setForeground(Color.BLACK);
		ayuda.addActionListener(this);
		
		getContentPane().add(ayuda, BorderLayout.SOUTH);
		getContentPane().add(cantidadTotal, BorderLayout.SOUTH);
		
		panelInferior.add(cantidadTotal);
		panelInferior.add(Box.createHorizontalGlue());
		panelInferior.add(ayuda);
        
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
	}
	
	private void ruta(String user) {
		File archivo = new File(user + "." + extension);
		String a = archivo.getAbsolutePath();
		String ruta = rutaBaseH2+a;
		
		StringSelection seleccion = new StringSelection(ruta);
        Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        portapapeles.setContents(seleccion, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (con != null) {
				ddl.crearTablas();
				texto.setFont(new Font("Arial", Font.BOLD, 13));
				
				if (e.getSource() == itemInsertar) {
					try {
						timer = new Timer(3000, this);
						timer2 = new Timer(1000, this);
				        dml.insertarSaldoInterfaz(texto, cantidadTotal, timer, timer2, menuPrincipal1, menuPrincipal2, menuPrincipal3, admin, ayuda);
					} catch (ExceptionApp ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
				}

				if (e.getSource() == itemSacar) {
					try {
						timer = new Timer(3000, this);
						timer2 = new Timer(1000, this);
						dml.sacarSaldoInterfaz(texto, cantidadTotal, timer, timer2, menuPrincipal1, menuPrincipal2, menuPrincipal3, admin, ayuda);
					} catch (ExceptionApp ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(e.getSource() == itemTransferencia) {
					try {
						dml.getTotal(cantidadTotal);
						texto.setText("");
						texto.setForeground(Color.black);
						boolean encontrado = false;
						String userAenviar = JOptionPane.showInputDialog(null, mensajeTransferencia, null, JOptionPane.PLAIN_MESSAGE);
						try {
							if(userAenviar.isEmpty())
								throw new ExceptionApp("Usuario vacio");
							userAenviar = userAenviar.toLowerCase();
							
							try(BufferedReader br = new BufferedReader(new FileReader(rutaUsuarios))){
								String linea;
								linea = br.readLine();
								while(linea!=null) {
									if(!linea.equals("")) {
										if(userAenviar.equals(linea.trim())) {
											encontrado = true;
											String c = JOptionPane.showInputDialog(null, mensajeCantidad + tipoMensaje[2], null, JOptionPane.PLAIN_MESSAGE);
											double cantidad = Double.parseDouble(c);
											if (cantidad == 0.) {
												throw new ExceptionApp(errorCampoVacio);
											} else if (cantidad > dml.getTotal2()) {
												throw new ExceptionApp(errorTotal);
											}

											JDateChooser dc = new JDateChooser();
											int option = JOptionPane.showOptionDialog(null, dc, mensajeFecha+"transferencia", JOptionPane.DEFAULT_OPTION,
													JOptionPane.PLAIN_MESSAGE, null, null, null);

											if (option == JOptionPane.OK_OPTION) {
												java.util.Date fechaSeleccionada = dc.getDate();
												if (fechaSeleccionada == null) {
													throw new ExceptionApp(errorCampoVacio);
												} else {
													java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
													dml.sqlSaldo(new Saldo(cantidad, fechaSQL, tipoAccion[3]));
													
													con = DriverManager.getConnection(opcionesURL[0] + userAenviar + "." + extension, "", "");
													dml.sqlSaldoTransferido(con, new Saldo(cantidad, fechaSQL, tipoAccion[2]));
												}
											}
											break;
										} 
									}
									linea = br.readLine();
								}
								if(encontrado==false) {
									throw new ExceptionApp("No existe el usuario");
								}
							} catch (FileNotFoundException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
							}
							timer = new Timer(3000, this);
							timer2 = new Timer(1000, this);
							texto.setText(formatoMensajeComfirmacion + procesandoTransferencia + " (2)");
							menuPrincipal1.setEnabled(false);
							menuPrincipal2.setEnabled(false);
							menuPrincipal3.setEnabled(false);
							admin.setEnabled(false);
							ayuda.setEnabled(false);
					        timer2.addActionListener(new ActionListener() {
					            int tiempoRestante = timer.getDelay() / 1000;
					            
					            @Override
					            public void actionPerformed(ActionEvent e) {
					                tiempoRestante--;
					                texto.setText(formatoMensajeComfirmacion + procesandoTransferencia + " (1)");
					                if (tiempoRestante == 1) {
					                	texto.setForeground(Color.GREEN);
					                    texto.setText(formatoMensajeComfirmacion + confirmacionTransferencia);
					                    try {
											dml.getTotal(cantidadTotal);
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
										}
					                } if(tiempoRestante == 0) {
					                	texto.setForeground(Color.BLACK);
					                	texto.setText("");
					                	menuPrincipal1.setEnabled(true);
										menuPrincipal2.setEnabled(true);
										menuPrincipal3.setEnabled(true);
										admin.setEnabled(true);
										ayuda.setEnabled(true);
					                	try {
											dml.getTotal(cantidadTotal);
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
										}
					                	timer2.stop();
					                }
					            }
					        });
					        timer.restart();
					        timer2.restart();
						} catch(NullPointerException e1) {
						}
					} catch (ExceptionApp e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
				}

				if (e.getSource() == itemListar) {
					dml.listarDatos(texto, cantidadTotal);
				}

				if (e.getSource() == itemBorrar) {
					timer = new Timer(3000, this);
					timer2 = new Timer(1000, this);
					dml.borrarHistorial(texto, cantidadTotal, timer, timer2, menuPrincipal1, menuPrincipal2, menuPrincipal3, admin, ayuda);
				}

				if (e.getSource() == itemNuevo) {
					dml.usuarioExiste(this, texto, cantidadTotal, false);
				}

				if (e.getSource() == itemExistente) {
					dml.usuarioExiste(this, texto, cantidadTotal, true);
				}

				if (e.getSource() == usuarios) {
					ddl.listaUsuariosInterfaz(texto, cantidadTotal);
				}
				
				if(e.getSource() == ayuda) {
					timer = new Timer(5000, this);
					timer2 = new Timer(1000, this);
					dml.abrirEnlace(texto, cantidadTotal, timer, timer2, menuPrincipal1, menuPrincipal2, menuPrincipal3, admin, ayuda);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, errorConexion, null, JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		} catch (ExceptionApp e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}
}
