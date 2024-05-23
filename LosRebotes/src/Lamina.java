package hilos;

@SuppressWarnings("serial")
public class Lamina extends javax.swing.JPanel {
	private java.util.List<Pelota> pelotas = new java.util.concurrent.CopyOnWriteArrayList<>();
	private Barra barra;
	private Bloque bloque;
    private boolean juegoEnCurso = false;
	
    public Lamina() {
        setFocusable(true);
        setBackground(java.awt.Color.black);
    }
    
	public void add(Pelota pelota) { 
		pelotas.add(pelota); 
	}
	
	public void add(Barra barra) { 
		this.barra=barra; 
		juegoEnCurso=true;
		repaint();
	}
	
	public void add(Bloque bloque) { 
		this.bloque=bloque; 
		repaint();
	}
	
	public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        
        					// pelota
        for (Pelota pelota : pelotas) {
        	g2.setColor(java.awt.Color.CYAN);
            g2.fill(pelota.getShape());
        }
        
        if(juegoEnCurso) {   //	barra
	        g2.setColor(java.awt.Color.GREEN);		
	        g2.fill(barra.getShape());
	        				
	        				//	bloque
	        g2.setColor(java.awt.Color.RED);
	        g2.fill(bloque.getShape());
        }
        
        if(isFin()) {
        	g2.setColor(java.awt.Color.BLACK);
	        g2.fill(bloque.getShape());
        }
        
        repaint();
    }
	
	public void vaciarLamina() {
		juegoEnCurso=false;
		pelotas.clear();
        repaint();
    }
	
	public void moverBloque(String movimiento) {
		if(!isFin()) bloque.moverBloque(movimiento);
    }
	
	public void moverBarraIzquierda() {
		if(!isFin()) barra.moverBarraIzquierda();
    }

    public void moverBarraDerecha() {
    	if(!isFin()) barra.moverBarraDerecha();
    }

	public double getBarra_posicion_X() { return barra.getBarra_posicion_X(); }

	public double getBarra_posicion_Y() { return barra.getBarra_posicion_Y(); }
	
	public boolean isFin() {
		try {
			return pelotas.get(0).isFin();
		} catch(java.lang.ArrayIndexOutOfBoundsException e) {}
		return false;
	}
}
