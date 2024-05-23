package hilos;

public class Barra {
	private double posicionBarra_y;
	private double posicionBarra_x;
	private Lamina lamina;
	
    public Barra(Lamina lamina) {
    	this.lamina=lamina;
    }
    
    public java.awt.geom.Rectangle2D getShape() {
		return new java.awt.geom.Rectangle2D.Double(posicionBarra_x, posicionBarra_y, Constantes.BARRA_WIDTH, Constantes.BARRA_HEIGHT);
	}
    
    public void posicionInicialBarra(java.awt.geom.Rectangle2D limites) {
    	posicionBarra_x = (limites.getWidth() - Constantes.BARRA_WIDTH) / 2;
    	posicionBarra_y = limites.getHeight()- Constantes.BARRA_HEIGHT;
    }
    
    public double getBarra_posicion_X() { 
    	return posicionBarra_x; 
    }

	public double getBarra_posicion_Y() { 
		return posicionBarra_y; 
	}

	public void moverBarraDerecha() {
		posicionBarra_x += 15;
		if(posicionBarra_x + 15 >= lamina.getWidth() - Constantes.BARRA_WIDTH) posicionBarra_x = lamina.getWidth() - Constantes.BARRA_WIDTH;  
	}
	
	public void moverBarraIzquierda() {
		posicionBarra_x -= 15;
        if(posicionBarra_x - 15 <= 0)posicionBarra_x = 0;
	}
}
