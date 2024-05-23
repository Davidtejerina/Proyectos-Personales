package hilos;

public class PelotaHilos implements Runnable{
	private Pelota pelota;
	private Barra barra;
	private Bloque bloque;
	private java.awt.Component pantalla;
	private boolean detenerEjecucion;

	public PelotaHilos(Pelota unaPelota, Barra unaBarra, Bloque unBloque, java.awt.Component lamina) {
		pelota=unaPelota;
		bloque=unBloque;
		barra=unaBarra;
		pantalla=lamina;
		detenerEjecucion=false;
	}
	
	public void detenerHilos() { 
		detenerEjecucion = true; 
	}
	
	public void run() {
		boolean isFin=false;
		pelota.posicionInicialPelota(pantalla.getBounds());
		barra.posicionInicialBarra(pantalla.getBounds());
		bloque.posicionInicialBloque(pantalla.getBounds());
		
		while(!detenerEjecucion&&!isFin) {
			pelota.moverPelota(pantalla.getBounds());
			pantalla.repaint();
			
			if(pelota.isFin()) isFin=true;
			try{Thread.sleep(pelota.getVelocidad());} catch(InterruptedException e){e.printStackTrace();}
		}
	}
}



