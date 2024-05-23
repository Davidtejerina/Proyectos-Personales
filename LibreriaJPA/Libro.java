package prueba;

import jakarta.persistence.*;

@Entity
@Table(name = "Libro")
public class Libro {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea autoincrement y no se repitan los id
	private Integer id;

	@Column(name = "titulo", length = 500, nullable = false)
	private String titulo;

	@Column(name = "autor", length = 500, nullable = false)
	private String autor;

	@Column(name = "precio", nullable = true)
	private float precio;

	
	public Libro(Integer id, String titulo, String autor, float precio) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}
	
	public Libro(String titulo, String autor, float precio) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	public Libro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
