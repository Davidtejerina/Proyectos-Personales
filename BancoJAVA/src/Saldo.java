package huchasegura;

import java.sql.Date;
import java.util.*;

public class Saldo {
	private int idAccion;
	private double cantidad;
	private Date fecha;
	private String accion;

	public Saldo(double cantidad, Date fecha, String accion) {
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.accion = accion;
	}

	public int getIdAccion() {
		return idAccion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getAccion() {
		return accion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, fecha, idAccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Saldo other = (Saldo) obj;
		return Double.doubleToLongBits(cantidad) == Double.doubleToLongBits(other.cantidad)
				&& Objects.equals(fecha, other.fecha) && idAccion == other.idAccion;
	}

	@Override
	public String toString() {
		return idAccion + "\t" + cantidad + "\t" + fecha + "\t" + accion + "\n";
	}

}
