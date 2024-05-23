package huchasegura;

import java.awt.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class DDL implements Constantes {
	private Connection con;

	public DDL(Connection con) {
		this.con = con;
	}

	public void crearTablas() throws SQLException {
		try (Statement st = con.createStatement()) {
			st.executeUpdate(creaTabla);
		}
	}
	
	public void listaUsuario(JTextArea texto) {
		int id = 1;
		texto.append("Listado de usuarios registrados: \n\n");
		try(BufferedReader br = new BufferedReader(new FileReader(rutaUsuarios))){
			String linea;
			br.readLine();
			linea = br.readLine();
			while(linea!=null) {
				if(!linea.equals("")) {
					texto.append(id + ")    " + linea + "\n");
					id++;
				}
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void listaUsuariosInterfaz(JTextArea texto, JLabel cantidadTotal) {
		texto.setText("");
		texto.setForeground(Color.black);
		String clave = JOptionPane.showInputDialog(null, mensajeClave+"   ("+claveAdmin+")", null, JOptionPane.OK_CANCEL_OPTION);
		try {
			clave = clave.toLowerCase();
			if (clave.equals(claveAdmin)) {
				listaUsuario(texto);
				cantidadTotal.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "clave incorrecta", null, JOptionPane.WARNING_MESSAGE);
				return;
			}
		} catch(NullPointerException e) {
		}
	}
}