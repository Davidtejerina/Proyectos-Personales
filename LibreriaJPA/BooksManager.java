package prueba;

import java.util.*;
import jakarta.persistence.*;

public class BooksManager {
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	
	public static void main(String[] args) {
		begin();
//		upDate();
//		create();
		find();
//		consulta();
//		borra();
		end();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("NombrePersistencia");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void create() {
		Book libro = new Book();
//		libro.setTitle("Java");
//		libro.setAuthor("David");
//		libro.setPrice(39);
		
		entityManager.persist(libro);
	}
	
	private static void upDate() {
		Book libroExistente = new Book();
		libroExistente.setBook_id(1);
		libroExistente.setTitle("Java2");
		libroExistente.setAuthor("David");
		libroExistente.setPrice(44);
		
		entityManager.merge(libroExistente);
	}
	
	private static void find() {
		Integer id = 1;
		Book libro = entityManager.find(Book.class, id);
		
		System.out.println("\nResultado: ");
		System.out.println(libro.getBook_id()+"\t"+libro.getTitle()+"\t"+libro.getAuthor()+"\t"+libro.getPrice());
	}
	
	
	private static void consulta() {
		String consulta = "Select b from Book b where b.price < 50";
		Query query = entityManager.createQuery(consulta);
		
		@SuppressWarnings("unchecked")
		List<Book> listaLibros = query.getResultList();
		
		System.out.println("\nResultado: ");
		for (Book libro : listaLibros) {
			System.out.println(libro.getBook_id()+"\t"+libro.getTitle()+"\t"+libro.getAuthor()+"\t"+libro.getPrice());
		}
	}
	
	private static void borra() {
		Integer id = 1;
		Book libroBorrar = entityManager.getReference(Book.class, id);
		entityManager.remove(libroBorrar);
		
		
		
	}
}









