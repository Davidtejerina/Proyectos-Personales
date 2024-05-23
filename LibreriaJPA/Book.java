package prueba;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea autoincrement y no se repitan los id
	private Integer book_id;
	
	@Column(name = "title", length = 500, nullable = false)
	private String title;
	
	@Column(name = "author", length = 500, nullable = false)
	private String author;
	
	@Column(name = "price", nullable = true)
	private float price;

	public Book(String title, String author, float price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Book() {
		
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
