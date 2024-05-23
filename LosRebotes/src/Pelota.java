package hilos;

public class Pelota {
	private double posicion_x;
	private double posicion_y;
	private double dx;
	private double dy = -1;
	
	private Lamina lamina;
	private Bloque bloque;
	
	private boolean fin = false;
	private long velocidad = 3;
	private int marcadorVelocidad = 0; 
	
	
	public Pelota(Lamina lamina) {
	    this.lamina = lamina;
	    double numero=0;
	    do{ numero = Math.random()*2.0-1; }while(numero>-0.4&&numero<0.4);
	    dx = numero;
	}
	
	public void configBloque(Bloque bloque) {
		this.bloque = bloque;
	}
	
	public java.awt.geom.Ellipse2D getShape() {
		return new java.awt.geom.Ellipse2D.Double(posicion_x, posicion_y, Constantes.TAMX, Constantes.TAMY);
	}
	
	public void posicionInicialPelota(java.awt.geom.Rectangle2D limites) {
		posicion_x = limites.getCenterX() - (Constantes.TAMX / 2);  
		posicion_y = limites.getMaxY() - Constantes.TAMY;
	}
	
	public void moverPelota(java.awt.geom.Rectangle2D limites) {
		posicion_x+=dx;
		posicion_y+=dy;

	    if(posicion_x < limites.getMinX() || posicion_x+Constantes.TAMX >= limites.getMaxX()) dx=-dx;
	    if(posicion_y < limites.getMinY()) dy = -dy;
	    
	    if (posicion_x + Constantes.TAMX >= bloque.getPosicionX() &&
	    	    posicion_x <= bloque.getPosicionX() + Constantes.TAMBLOQUEX &&
	    	    posicion_y + Constantes.TAMY >= bloque.getPosicionY() &&
	    	    posicion_y <= bloque.getPosicionY() + Constantes.TAMBLOQUEY) {

	    	    double centroPelotaX = posicion_x + Constantes.TAMX / 2;
	    	    double centroBloqueX = bloque.getPosicionX() + Constantes.TAMBLOQUEX / 2;

	    	    double centroPelotaY = posicion_y + Constantes.TAMY / 2;
	    	    double centroBloqueY = bloque.getPosicionY() + Constantes.TAMBLOQUEY / 2;

	    	    double diferenciaX = centroPelotaX - centroBloqueX;
	    	    double diferenciaY = centroPelotaY - centroBloqueY;

	    	    if (Math.abs(diferenciaX) > Math.abs(diferenciaY)) {
	    	        if (diferenciaX > 0 && bloque.getPosicionX() + Constantes.TAMBLOQUEX < limites.getMaxX()) lamina.moverBloque("derecha");
	    	        else if (bloque.getPosicionX() > limites.getMinX()) lamina.moverBloque("izquierda");
	    	        dx = -dx; 
	    	    } else {
	    	        if (diferenciaY > 0 && bloque.getPosicionY() + Constantes.TAMBLOQUEY < limites.getMaxY()) lamina.moverBloque("abajo");
	    	        else if (bloque.getPosicionY() > limites.getMinY()) lamina.moverBloque("arriba");
	    	        dy = -dy;
	    	    }
    	}
	    
	    if(posicion_y+Constantes.TAMY >= limites.getMaxY()) {
	        if(posicion_y+Constantes.TAMY >= lamina.getBarra_posicion_Y() 
	        		&& posicion_x+Constantes.TAMX >= lamina.getBarra_posicion_X() 
	        			&& posicion_x <= lamina.getBarra_posicion_X()+90) {
	            
	        	//rebota segun donde choque con la barra
	            dx = ((posicion_x+Constantes.TAMX/2)-(lamina.getBarra_posicion_X()+Constantes.BARRA_WIDTH/2)) / 50;  
	        	dy=-dy;
	        	marcadorVelocidad++;
	        	if(marcadorVelocidad==6) velocidad=(long) (velocidad-0.01);
	        	if(marcadorVelocidad==12) velocidad=(long) (velocidad-0.01);
	        	if(marcadorVelocidad==18) velocidad=(long) (velocidad-0.01);
	        }
	        else fin=true;
	    }
	}

	public long getVelocidad() {
		return velocidad;
	}
	
	public boolean isFin() {
        return fin;
    }
}