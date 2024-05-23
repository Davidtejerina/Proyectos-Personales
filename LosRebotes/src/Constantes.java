package hilos;

public interface Constantes {
	//barra
	static final int BARRA_WIDTH = 90;
    static final int BARRA_HEIGHT = 7;
    
    //pelota
    static final int TAMX = 20;
	static final int TAMY = 20;
	
	//bloque
	static final int TAMBLOQUEX = 70;
	static final int TAMBLOQUEY = 70;
	
	//Gestion base de datos
	static final String driver = "org.h2.Driver";
	static final String BD = "jdbc:h2:./Datos/puntuaciones.rebote";
	static final String user = "";
	static final String pass = "";
	
	static final String sentenciaCreateTable = "CREATE TABLE IF NOT EXISTS PUNTUACIONES(jugador TEXT, score BIGINT, horario TIMESTAMP)";
	static final String consultaInsertPuntuacion = "INSERT INTO PUNTUACIONES VALUES (?, ?, ?)";
	static final String consultaSelectMejorPuntuacion = "SELECT SCORE FROM PUNTUACIONES WHERE JUGADOR='%s' ORDER BY SCORE desc LIMIT 1";
	
	//juegoPelota
	static final String botonSalir = "Salir";
	static final String botonVolver = "Volver a empezar";
	static final String botonDale = "Dale!";
	
}
