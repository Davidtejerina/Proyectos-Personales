package huchasegura;

@SuppressWarnings("serial")
public class ExceptionApp extends Exception {
	public ExceptionApp() {
		super();
	}
	
	public ExceptionApp(String texto) {
		super(texto);
	}
}
