package hilos;


public class Bloque {
	private double posicionX;
	private double posicionY;
	
	public java.awt.geom.Rectangle2D getShape() {
		return new java.awt.geom.Rectangle2D.Double(getPosicionX(), getPosicionY(), Constantes.TAMBLOQUEX, Constantes.TAMBLOQUEY);
	}
	
	public void posicionInicialBloque(java.awt.geom.Rectangle2D limites) {
	    posicionY = limites.getHeight()/3 - Constantes.TAMBLOQUEY/2;
	    posicionX = limites.getWidth()/2 - Constantes.TAMBLOQUEX/2;
	}

	public double getPosicionX() {
		return posicionX;
	}

	public double getPosicionY() {
		return posicionY;
	}
	

	public void moverBloque(String posicion) {
		switch(posicion) {
		case "izquierda": posicionX += 15; break;
        case "derecha": posicionX -= 15; break;
        case "arriba": posicionY += 15; break;
        case "abajo": posicionY -= 15; break;
		}
	}
}
