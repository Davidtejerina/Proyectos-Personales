package huchasegura;

import javax.swing.*;
import java.io.*;
import java.sql.*;

public class CST implements Constantes {
	private static Connection con;
	private static String user;

	private CST() {
		int seleccion = JOptionPane.showOptionDialog(null, mensajeDriver, null, JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opcionesBaseDatos, opcionesBaseDatos[0]);
		if (seleccion != JOptionPane.CLOSED_OPTION) {
			if (seleccion == 0) {
				try {
					try {
						constantesConexion();
					} catch (ExceptionApp e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
					Class.forName(opcionesDriver[0]);
					con = DriverManager.getConnection(opcionesURL[0] + getUser() + "." + extension, "", "");
					guardarUsuarios();
					
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (SQLNonTransientConnectionException e) {
					JOptionPane.showMessageDialog(null, mensajeCuentaEnUso, null, JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getClass(), null, JOptionPane.ERROR_MESSAGE);
				}
			} else if (seleccion == 1) {
				JOptionPane.showMessageDialog(null, errorMYSQL, null, JOptionPane.WARNING_MESSAGE);
				
			}
		} 
	}

	public CST(String userNuevo) {
		try {
			Class.forName(opcionesDriver[0]);
			con = DriverManager.getConnection(opcionesURL[0] + userNuevo + "." + extension, "", "");
			guardarUsuarios();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		} catch (SQLNonTransientConnectionException e) {
			JOptionPane.showMessageDialog(null, mensajeCuentaEnUso, null, JOptionPane.ERROR_MESSAGE);
			return;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void constantesConexion() throws SQLException, ExceptionApp {
		user = JOptionPane.showInputDialog(null, "Usuario: ", null, JOptionPane.PLAIN_MESSAGE);
		try {
			user = user.toLowerCase();
			if(user.isEmpty()) {
				throw new ExceptionApp(errorCampoVacio);
			}
		} catch (NullPointerException e) {
			System.exit(0);;
		}
	}
	
	public static void guardarUsuarios() {
	    File[] archivos = new File(rutaProyecto).listFiles();

	    try (PrintWriter pw = new PrintWriter(new FileWriter(rutaUsuarios))) {
	        if (archivos != null) {
	            pw.println("Usuarios guardados:");
	            for (File archivo : archivos) {
	            	String[] nombre = archivo.getName().split("\\.");
	                if (archivo.isFile() && !nombre[0].equals("") && (nombre.length == 4 && nombre[2].equals("mv") && nombre[3].equals("db"))) {
                       pw.println(nombre[0].trim());
	                }
	            }
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public static String getUser() {
		return user;
	}

	public static Connection conexion() {
		if (con == null)
			new CST();
		return con;
	}
}
