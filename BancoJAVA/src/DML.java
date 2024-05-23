package huchasegura;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class DML implements Constantes {
	private Connection con;
	private double total;

	public DML(Connection con) {
		this.con = con;
	}

	public void insertarSaldoInterfaz(JTextArea texto, JLabel cantidadTotal, Timer timer, Timer timer2, JMenu menuPrincipal1, JMenu menuPrincipal2, JMenu menuPrincipal3, JMenu admin, JButton ayuda) throws SQLException, ExceptionApp {
		getTotal(cantidadTotal);
		texto.setText("");
		String c = JOptionPane.showInputDialog(null, mensajeCantidad + tipoMensaje[0], null, JOptionPane.PLAIN_MESSAGE);
		try {
			if (c.isEmpty()) {
				throw new ExceptionApp(errorCampoVacio);
			}
			double cantidad = Double.parseDouble(c);
			if (cantidad == 0.) {
				throw new ExceptionApp(errorCampoVacio);
			}
	
			JDateChooser dc = new JDateChooser();
			int option = JOptionPane.showOptionDialog(null, dc, mensajeFecha+"insercción", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
	
			if (option == JOptionPane.OK_OPTION) {
				java.util.Date fechaSeleccionada = dc.getDate();
				if (fechaSeleccionada == null) {
					throw new ExceptionApp(errorCampoVacio);
				} else {
					java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
					sqlSaldo(new Saldo(cantidad, fechaSQL, tipoAccion[0]));
					
					menuPrincipal1.setEnabled(false);
					menuPrincipal2.setEnabled(false);
					menuPrincipal3.setEnabled(false);
					admin.setEnabled(false);
					ayuda.setEnabled(false);
					
					texto.setText(formatoMensajeComfirmacion + procesandoInsercion + " (2)");
					timer2.addActionListener(new ActionListener() {
			            int tiempoRestante = timer.getDelay() / 1000;
			            
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                tiempoRestante--;
			                texto.setText(formatoMensajeComfirmacion + procesandoInsercion + " (1)");
			                if (tiempoRestante == 1) {
			                	texto.setForeground(Color.GREEN);
			                    texto.setText(formatoMensajeComfirmacion + confirmacionInsercion);
			                    try {
									getTotal(cantidadTotal);
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
								
			                	timer2.stop();
			                }
			            }
			        });
			        timer.restart();
			        timer2.restart();
				}
			}
		} catch(NullPointerException e) {
		}
	}
	
	public void sacarSaldoInterfaz(JTextArea texto, JLabel cantidadTotal, Timer timer, Timer timer2, JMenu menuPrincipal1, JMenu menuPrincipal2, JMenu menuPrincipal3, JMenu admin, JButton ayuda) throws SQLException, ExceptionApp {
		getTotal(cantidadTotal);
		texto.setText("");
		String c = JOptionPane.showInputDialog(null, mensajeCantidad + tipoMensaje[1], null, JOptionPane.PLAIN_MESSAGE);
		try {
			if (c.isEmpty()) {
				throw new ExceptionApp(errorCampoVacio);
			}
			double cantidad = Double.parseDouble(c);
			if (cantidad > getTotal2()) {
				throw new ExceptionApp(errorTotal);
			}

			JDateChooser dc = new JDateChooser();
			int option = JOptionPane.showOptionDialog(null, dc, mensajeFecha+"retirada", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);

			if (option == JOptionPane.OK_OPTION) {
				java.util.Date fechaSeleccionada = dc.getDate();
				if (fechaSeleccionada == null) {
					throw new ExceptionApp(errorCampoVacio);
				} else {
					java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
					sqlSaldo(new Saldo(cantidad, fechaSQL, tipoAccion[1]));
					
					menuPrincipal1.setEnabled(false);
					menuPrincipal2.setEnabled(false);
					menuPrincipal3.setEnabled(false);
					admin.setEnabled(false);
					ayuda.setEnabled(false);
					
					texto.setText(formatoMensajeComfirmacion + procesandoRetirada + " (2)");
					timer2.addActionListener(new ActionListener() {
			            int tiempoRestante = timer.getDelay() / 1000;
			            
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                tiempoRestante--;
			                texto.setText(formatoMensajeComfirmacion + procesandoRetirada + " (1)");
			                if (tiempoRestante == 1) {
			                	texto.setForeground(Color.GREEN);
			                    texto.setText(formatoMensajeComfirmacion + confirmacionRetirada);
			                    try {
									getTotal(cantidadTotal);
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
								
			                	timer2.stop();
			                }
			            }
			        });
			        timer.restart();
			        timer2.restart();
				}
			}
		} catch(NullPointerException e) {
		}
	}
	
	public void sqlSaldo(Saldo s) throws SQLException {
		try (PreparedStatement pst = con.prepareStatement(String.format(insertarSaldo))) {
			pst.setDouble(1, s.getCantidad());
			pst.setDate(2, s.getFecha());
			pst.setString(3, s.getAccion());
			pst.executeUpdate();
		}
	}
	
	public void sqlSaldoTransferido(Connection con, Saldo s) throws SQLException {
		DDL ddl = new DDL(con);
		ddl.crearTablas();
		try (PreparedStatement pst = con.prepareStatement(String.format(insertarSaldo))) {
			pst.setDouble(1, s.getCantidad());
			pst.setDate(2, s.getFecha());
			pst.setString(3, s.getAccion());
			pst.executeUpdate();
		}
	}

	public void listarDatos(JTextArea texto, JLabel ct) throws SQLException {
		texto.setText("");
		ct.setText("");
		getTotal(ct);
		texto.setForeground(Color.black);
		try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				ResultSet rs = st.executeQuery(consultaDatos)) {

			if (rs.next()) {
				texto.append("Nº ACCIÓN\tCANTIDAD\tFECHA\tTIPO DE ACCIÓN\n");
				texto.append("--------------------------------------------------------------------------------------------------------\n");
			} else {
				texto.append("\t\tTABLA VACÍA");
				ct.setText("");
			}
			rs.afterLast();
			while (rs.previous()) {
				texto.append(String.format("%s\t%s €\t%s\t%s\n", rs.getInt(1), rs.getDouble(2), rs.getDate(3),
						rs.getString(4)));
			}
		}
	}
	
	public void borrarHistorial(JTextArea texto, JLabel ct, Timer timer, Timer timer2, JMenu menuPrincipal1, JMenu menuPrincipal2, JMenu menuPrincipal3, JMenu admin, JButton ayuda) throws SQLException {
		texto.setForeground(Color.black);
		String[] opciones = { "Si", "No" };
		int respuesta = JOptionPane.showOptionDialog(null, mensajeBorrarHistorial, null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[1]);		
		if (respuesta == JOptionPane.YES_OPTION) {
			try (Statement st = con.createStatement()) {
				st.executeUpdate(borrarHistorial);
				reiniciarIdAcciones();
				
				menuPrincipal1.setEnabled(false);
				menuPrincipal2.setEnabled(false);
				menuPrincipal3.setEnabled(false);
				admin.setEnabled(false);
				ayuda.setEnabled(false);
				
				texto.setText(formatoMensajeComfirmacion + procesandoBorrado + " (2)");
		        timer2.addActionListener(new ActionListener() {
		            int tiempoRestante = timer.getDelay() / 1000;
		            
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                tiempoRestante--;
		                texto.setText(formatoMensajeComfirmacion + procesandoBorrado + " (1)");
		                if (tiempoRestante == 1) {
		                	texto.setForeground(Color.GREEN);
		                    texto.setText(formatoMensajeComfirmacion + confirmacionBorrado);
		                } if(tiempoRestante == 0) {
		                	texto.setForeground(Color.BLACK);
		                	texto.setText("");
		                	menuPrincipal1.setEnabled(true);
							menuPrincipal2.setEnabled(true);
							menuPrincipal3.setEnabled(true);
							admin.setEnabled(true);
							ayuda.setEnabled(true);
							
		                	timer2.stop();
		                }
		            }
		        });
		        timer.restart();
		        timer2.restart();
		        ct.setText("");
			}
		} else if (respuesta == JOptionPane.NO_OPTION) {
			getTotal2();
			return;
		}
	}

	public void reiniciarIdAcciones() throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "ALTER TABLE CUENTA ALTER COLUMN Nº_ACCIÓN RESTART WITH 1";
		stmt.executeUpdate(sql);
		total = 0;
	}
	
	public void usuarioExiste(JFrame jf, JTextArea texto, JLabel cantidadTotal, boolean existe) throws ExceptionApp {
		if(existe == true) {
			cantidadTotal.setText("");
			texto.setText("");
			texto.setForeground(Color.black);

			String userExistente = JOptionPane.showInputDialog(null, "Usuario: ", null, JOptionPane.PLAIN_MESSAGE);
			try {
				if(!userExistente.equals("")) {
					if(userExistente!=null) {
						File[] archivos = new File(rutaProyecto).listFiles();
		
						if (archivos != null) {
							boolean encontrado = false; // Variable para verificar si se encontró el usuario existente
							for (File archivo : archivos) {
								if (archivo.isFile()) {
									String[] nombre = archivo.getName().split("\\.");
									if (nombre[0].equals(userExistente)) {
										encontrado = true; // Se encontró el usuario existente
										try {
											con = DriverManager.getConnection(opcionesURL[0] + userExistente + ".cuenta",
													"", "");
											CST.guardarUsuarios();
											jf.dispose();
											new CuentaFrame(userExistente);
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
										}
										break; // Salir del bucle si se encuentra el usuario existente
									}
								}
							}
							if (!encontrado) {
								JOptionPane.showMessageDialog(null, errorUsuarioNoExistente, null, JOptionPane.ERROR_MESSAGE);
							}
						}
					} 
				} else
					throw new ExceptionApp("Usuario vacio");
			} catch(NullPointerException e) {
			}

		} else {
			cantidadTotal.setText("");
			texto.setText("");
			texto.setForeground(Color.black);

			String userNuevo = JOptionPane.showInputDialog(null, "Usuario: ", null, JOptionPane.PLAIN_MESSAGE);
			try {
				if(!userNuevo.isEmpty()) {
					File[] archivos = new File(rutaProyecto).listFiles();

					if (archivos != null) {
						for (File archivo : archivos) {
							if (archivo.isFile()) {
								String[] nombre = archivo.getName().split("\\.");
								if (nombre[0].equals(userNuevo)) {
									JOptionPane.showMessageDialog(null, errorUsuarioExistente, null, JOptionPane.ERROR_MESSAGE);
									return; // Salir del método si el usuario ya existe
								}
							}
						}
					}
					
					try {
						con = DriverManager.getConnection(opcionesURL[0] + userNuevo + ".cuenta", "", "");
						CST.guardarUsuarios();
						jf.dispose();
						new CuentaFrame(userNuevo);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
				} else
					throw new ExceptionApp("Usuario vacio");
			} catch(NullPointerException e) {
			}
		}
	}
	
	public void abrirEnlace(JTextArea texto, JLabel cantidadTotal, Timer timer, Timer timer2, JMenu menuPrincipal1, JMenu menuPrincipal2, JMenu menuPrincipal3, JMenu admin, JButton ayuda) {
		texto.setText("");
		
		menuPrincipal1.setEnabled(false);
		menuPrincipal2.setEnabled(false);
		menuPrincipal3.setEnabled(false);
		admin.setEnabled(false);
		ayuda.setEnabled(false);
		
		texto.setForeground(Color.red);
		texto.setText(formatoMensajeComfirmacion2 + "ENLACE COPIADO, ESPERE");
		
		timer2.addActionListener(new ActionListener() {
            int tiempoRestante = timer.getDelay() / 1000;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if(tiempoRestante == 4) {
                	texto.setForeground(Color.BLACK);
                	texto.setText("");
                	texto.setText(formatoMensajeComfirmacion + "ABRIENDO SERVIDOR.");
                } if(tiempoRestante == 3) {
                	texto.setForeground(Color.BLACK);
                	texto.setText(formatoMensajeComfirmacion + "ABRIENDO SERVIDOR...");
                } if(tiempoRestante == 2) {
                	texto.setForeground(Color.BLACK);
                	texto.setText(formatoMensajeComfirmacion + "ABRIENDO SERVIDOR.");
                } if(tiempoRestante == 1) {
                	texto.setForeground(Color.BLACK);
                	texto.setText(formatoMensajeComfirmacion + "ABRIENDO SERVIDOR..");
                } if(tiempoRestante == 0) {
                	
                	String url = "http://localhost:8082/login.jsp?jsessionid=e44d31d2d2a710460dd5f3e96625626d";
                    try {
                    	Desktop.getDesktop().browse(new URI(url));
                    	
//                		Document doc = Jsoup.connect("http://localhost:8082/login.jsp?jsessionid=e44d31d2d2a710460dd5f3e96625626d").timeout(6000).get();
//                		Elements td = doc.select("td.login input[name=url]");
//                		System.out.println(doc);
//                		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//                        Transferable transferable = clipboard.getContents(null);
//                        String clipboardContent;
//						clipboardContent = (String) transferable.getTransferData(DataFlavor.stringFlavor);
//                		
//						td.attr("value", clipboardContent);
//						System.out.println(td.attr("value"));
                    		
        			} catch (IOException e1) {
        				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        			} catch (URISyntaxException e1) {
        				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        			} 
//                    catch (UnsupportedFlavorException e1) {
//        				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
//        			}
                    
                    menuPrincipal1.setEnabled(true);
					menuPrincipal2.setEnabled(true);
					menuPrincipal3.setEnabled(true);
					admin.setEnabled(true);
					ayuda.setEnabled(true);
					
					texto.setText("");
					timer2.stop();
					
					System.exit(0);
                }
            }
        });
        timer.restart();
        timer2.restart();
	}
	
	public void getTotal(JLabel cantidadTotal) throws SQLException {
		total = 0.0; // Reinicializar total a 0
		try (Statement st = con.createStatement(); 
				ResultSet rs = st.executeQuery(consultaDatos)) {
			while (rs.next()) {
				if (rs.getString(4).equals(tipoAccion[0]))
					total += rs.getDouble(2);
				else if (rs.getString(4).equals(tipoAccion[1]))
					total -= rs.getDouble(2);
				else if (rs.getString(4).matches(tipoAccion[2]))
					total += rs.getDouble(2);
				else if (rs.getString(4).matches(tipoAccion[3]))
					total -= rs.getDouble(2);
			}
		}
		cantidadTotal.setText(total + " €");
	}
	
	public double getTotal2() throws SQLException {
		total = 0.0; // Reinicializar total a 0
		try (Statement st = con.createStatement(); 
				ResultSet rs = st.executeQuery(consultaDatos)) {
			while (rs.next()) {
				if (rs.getString(4).equals(tipoAccion[0]))
					total += rs.getDouble(2);
				else if (rs.getString(4).equals(tipoAccion[1]))
					total -= rs.getDouble(2);
				else if (rs.getString(4).matches(tipoAccion[2]))
					total += rs.getDouble(2);
				else if (rs.getString(4).matches(tipoAccion[3]))
					total -= rs.getDouble(2);
			}
		}
		return total;
	}

	

}