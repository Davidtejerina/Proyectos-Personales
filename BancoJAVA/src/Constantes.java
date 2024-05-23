package huchasegura;

public interface Constantes {
	String rutaBaseH2 = "jdbc:h2:";
	String claveAdmin = "admin";
	String[] tipoAccion = { "insertado", "retirado", "transferencia recibida", "transferencia enviada"};

	// CONSTANTES DE ARCHIVOS/DRIVERS
	String[] opcionesBaseDatos = { "H2", "MYSQL" };
	String[] opcionesDriver = { "org.h2.Driver", "com.mysql.cj.jdbc.Driver" };
	String[] opcionesURL = { "jdbc:h2:./", "jdbc:mysql://127.0.0.1/" };
	String rutaUsuarios = "./listaUsuarios";
	String rutaProyecto = "./";
	String extension = "cuenta";

	// CONSTANTES DEL JFRAME
	int tamañoFrameX = 520;
	int posicionFrameX = 510;
	int tamañoFrameY = 500;
	int posicionFrameY = 190;

	String procesandoRetirada = "Procesando retiro";
	String procesandoInsercion = "Procesando insertado";
	String procesandoTransferencia = "Procesando transferencia";
	String procesandoBorrado = "*** Procesando borrado de datos";
	String confirmacionRetirada = "Dinero retirado con exito";
	String confirmacionInsercion = "Dinero insertado con exito";
	String confirmacionTransferencia = "Dinero transferido con exito";
	String confirmacionBorrado = "*** Datos Borrados";
	String formatoMensajeComfirmacion = "\n\n\n\n\n\n\n\n\t               ";
	String formatoMensajeComfirmacion2 = "\n\n\n\n\n\n\n\n\t            ";

	// CONSTANTES DE INPUTS
	String mensajeDriver = "¿Qué base de datos desea usar?";
	String mensajeConexionCorrecta = "Conexión correcta: ";
	String mensajeFecha = "Seleccione la fecha del día de ";
	String mensajeCantidad = "Introduce la cantidad a ";
	String mensajeTransferencia = "Introduce un usuario al que transferir dinero";
	String[] tipoMensaje = { "insertar: ", "retirar: ", "transferir: "};
	String errorMYSQL = "Todavia no está operativo";
	String mensajeCuentaEnUso = "Esta cuenta ya está en uso";
	String errorCampoVacio = "Campo vacio";
	String errorTotal = "No hay suficientes fondos";
	String mensajeBorrarHistorial = "¿Desea borrar el historial?";
	String errorUsuarioExistente = "El usuario ya existe";
	String errorUsuarioNoExistente = "El usuario no existe, debe crearlo";
	String errorConexion = "La conexión a la base de datos no se ha establecido correctamente";
	String mensajeClave = "Introduce la clave del admin: ";

	// CONSTANTES DE DDL
	String creaTabla = "CREATE TABLE IF NOT EXISTS cuenta(Nº_ACCIÓN INT PRIMARY KEY AUTO_INCREMENT, CANTIDAD DOUBLE, FECHA DATE, ACCIÓN TEXT);";

	// CONSTANTES DE DML
	String insertarSaldo = "INSERT INTO cuenta (CANTIDAD, FECHA, ACCIÓN) VALUES (?, ?, ?);";
	String consultaDatos = "SELECT * FROM CUENTA;";
	String borrarHistorial = "DELETE FROM CUENTA;";

}
