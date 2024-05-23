package prueba;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import jakarta.persistence.*;

public class Libreria {
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public Libreria() {
		begin();
		mensaje();
		menu();
		end();
	}

	
	
	// MENSAJE INICIO
	private static void mensaje() {
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│             DAW 1          CRUD           ORM (Object-Relational Mapping) y JPA (Java Persistence API)                       │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	
	// MENU 
	private static void menu() {
		try (Scanner tc = new Scanner(System.in)) {
			int opcion;
			
			System.out.println("╔═══════════════════════════════════════╗");
			System.out.println("║MENÚ:\t\t\t\t\t║");
			System.out.println("║1. Mostrar libros\t\t\t║");
			System.out.println("║2. Buscar un libro [id]\t\t║");
			System.out.println("║3. Buscar un libro [consulta]\t\t║");
			System.out.println("║4. Crea un libro\t\t\t║");
			System.out.println("║5. Crea ~automaticamente~ 20 libros\t║");
			System.out.println("║6. Actualiza un libro\t\t\t║");
			System.out.println("║7. Borrar libros\t\t\t║");
			System.out.println("║8. Borrar un libro\t\t\t║");
			System.out.println("║9. Salir\t\t\t\t║");
			System.out.println("╚═══════════════════════════════════════╝");

			System.out.print("Opcion: ");
			try {
				opcion = tc.nextInt();
			
				System.out.println();
				
				while(opcion!=9) {
					switch (opcion) {
						case 1:
							entityManager.getTransaction().begin();
							mostrarTodos();
							entityManager.getTransaction().commit();
							break;
						case 2:
							tc.nextLine();
							entityManager.getTransaction().begin();
							System.out.print("Dime el [id]: ");
							Integer id = tc.nextInt();
							mostrarUno(id);
							entityManager.getTransaction().commit();
							break;
						case 3:
							entityManager.getTransaction().begin();
							tc.nextLine();
							System.out.println("\n──────────────────────────────────────────────────────── NUEVA CONSULTA ────────────────────────────────────────────────────────");
							System.out.print("Introduce el filtro de la consulta [titulo/autor/precio]:  ");
							String filtro = tc.nextLine();
							filtro = filtro.toLowerCase();
							
							consulta(filtro);
							entityManager.getTransaction().commit();
							break;
						case 4: entityManager.getTransaction().begin(); createOne(); entityManager.getTransaction().commit();break;
						case 5: entityManager.getTransaction().begin(); createMany(); entityManager.getTransaction().commit();break;
						case 6: entityManager.getTransaction().begin(); upDate(); entityManager.getTransaction().commit();break;
						case 7: entityManager.getTransaction().begin(); borraTodos(); entityManager.getTransaction().commit();break;
						case 8:
							tc.nextLine();
							entityManager.getTransaction().begin();
							System.out.print("Dime el [id]: ");
							Integer idBorrar = tc.nextInt();
							
							borraUno(idBorrar);
							entityManager.getTransaction().commit();
							break;
						default: System.out.println("Por favor, eliga una opción válida del menú"); break;
					}
				
					System.out.println("╔═══════════════════════════════════════╗");
					System.out.println("║MENÚ:\t\t\t\t\t║");
					System.out.println("║1. Mostrar libros\t\t\t║");
					System.out.println("║2. Buscar un libro [id]\t\t║");
					System.out.println("║3. Buscar un libro [consulta]\t\t║");
					System.out.println("║4. Crea un libro\t\t\t║");
					System.out.println("║5. Crea ~automaticamente~ 20 libros\t║");
					System.out.println("║6. Actualiza un libro\t\t\t║");
					System.out.println("║7. Borrar libros\t\t\t║");
					System.out.println("║8. Borrar un libro\t\t\t║");
					System.out.println("║9. Salir\t\t\t\t║");
					System.out.println("╚═══════════════════════════════════════╝");
					System.out.print("Opcion: ");
					opcion = tc.nextInt();
					
					System.out.println();
				}
				System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
				System.out.println("│                                                       FIN DEL PROGRAMA                                                       │");
				System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
			} catch(InputMismatchException e) {
				System.out.println("\n Opcion invalida\n");
				menu();
			}
		}
	}
	
	
	
	// MUESTRA TODOS LOS LIBROS
	private static void mostrarTodos() {
		Query query = entityManager.createQuery("SELECT l FROM Libro l");					// HQL
//		Query query = entityManager.createNativeQuery("SELECT * FROM Libro", Libro.class);	// SQL
	
		@SuppressWarnings("unchecked")
		List<Libro> listaLibros = query.getResultList();
	
		if(!listaLibros.isEmpty()) {
			System.out.println("\n┌──────────────────────── LIBROS ────────────────────────");
			System.out.println("│ Resultados: ");
			for (Libro libro : listaLibros) {
				System.out.println("│ " + libro.getId() + ", " + libro.getTitulo() + ", " + libro.getAutor() + ", " + libro.getPrecio()+"€");
			}
			System.out.println("└────────────────────────────────────────────────────────\n");
		} else {
			System.out.println("\n┌────────────────────────┐");
			System.out.println("│ Base de datos vacia ***│");
			System.out.println("└────────────────────────┘\n");
		}
	}
	
	
	
	// BUSCAR LIBRO POR ID
	private static void mostrarUno(Integer id) {
		Libro libro = entityManager.find(Libro.class, id);
		
		if(libro != null) {
			System.out.println("\n┌──────────────────────────────────────────────────────────────────────────────");
			System.out.println("│ Resultado: ");
			System.out.println("│ " + libro.getId() + ", " + libro.getTitulo() + ", " + libro.getAutor() + ", " + libro.getPrecio()+"€");
			System.out.println("└──────────────────────────────────────────────────────────────────────────────\n");
		} else
			System.out.println("\n NO SE ENCUENTRA EL LIBRO CON ID: "+id+"\n");
	}

	
	
	// CONSULTAS
	private static void consulta(String opcion) {
		@SuppressWarnings("resource")
		Scanner tc = new Scanner(System.in);	// ES IMPORTANTE NO CERRAR LOS SCANNER YA QUE COMPARTEN LA MISMA FUENTE QUE EL DEL MENÚ Y SI CERRAMOS
												// ESTE, SE CERRARÍA EL DEL MENÚ TAMBIÉN, POR TANTO SOLO SE CIERRA CON TRY EL DEL MENÚ.
		String consulta = "";
		
			if(opcion.contains("ulo")) {
				System.out.print("Dime un titulo: ");
				String t = tc.nextLine();
				consulta = String.format("SELECT l FROM Libro l WHERE l.titulo = '%s'", t);
			}
			else if(opcion.contains("aut")) {
				System.out.print("Dime un autor: ");
				String n = tc.nextLine();
				consulta = String.format("SELECT l FROM Libro l WHERE l.autor = '%s'", n);
			}
			else if(opcion.contains("pre")) {
				System.out.print("Dime un precio: ");
				try {
					float p = tc.nextFloat();
					tc.nextLine();
					System.out.print("Quieres que sea mayor o menor a " + p + "? ");
					String tipo = tc.nextLine();
					tipo = tipo.toLowerCase();
					do {
						if (tipo.contains("mayor")) {
							consulta = String.format("SELECT l FROM Libro l WHERE l.precio >= %s", p);
						} else if(tipo.contains("menor")) {
							consulta = String.format("SELECT l FROM Libro l WHERE l.precio <= %s", p);
						} else {
							System.out.println("Opción inválida");
							tc.nextLine();
							System.out.print("Quieres que sea mayor o menor a " + p + "? ");
							tipo = tc.nextLine();
							tipo = tipo.toLowerCase();
						}
					} while(!tipo.contains("mayor") && !tipo.contains("menor"));
				}catch (InputMismatchException e) {
					System.out.println("\n ERROR: El precio no puede ser un caracter \n");
					return;
				}
			} else 
		        System.out.println("Opción inválida");
			
		if(opcion.contains("ulo") || opcion.contains("aut") || opcion.contains("pre")) {
			Query query = entityManager.createQuery(consulta);

			@SuppressWarnings("unchecked")
			List<Libro> listaLibros = query.getResultList();

			if(!listaLibros.isEmpty()) {
				System.out.println("\n────────────────────────────────────────────");
				System.out.println("Resultados: ");
				for (Libro libro : listaLibros) {
					System.out.println(libro.getId() + ", " + libro.getTitulo() + ", " + libro.getAutor() + ", " + libro.getPrecio()+"€");
				}
				System.out.println("────────────────────────────────────────────\n");
			} else
				System.out.println("\n NO SE HA ENCONTRADO EL LIBRO\n");
		}
	}

	
	
	// CREA UN LIBRO MANUALMENTE
	private static void createOne() {
		@SuppressWarnings("resource")
		Scanner tc = new Scanner(System.in);	// ES IMPORTANTE NO CERRAR LOS SCANNER YA QUE COMPARTEN LA MISMA FUENTE QUE EL DEL MENÚ Y SI CERRAMOS
												// ESTE, SE CERRARÍA EL DEL MENÚ TAMBIÉN, POR TANTO SOLO SE CIERRA CON TRY EL DEL MENÚ.
		System.out.print("Titulo: ");
		String t = tc.nextLine();
		System.out.print("Autor: ");
		String a = tc.nextLine();
		System.out.print("Precio: ");
		try {
			float p = tc.nextFloat();
			entityManager.persist(new Libro(t, a, p));
			
			System.out.println("\n┌──────────────────┐");
			System.out.println("│ LIBRO CREADO *** │");
			System.out.println("└──────────────────┘\n");
			
		}catch (InputMismatchException e) {
			System.out.println("\n ERROR: El precio no puede ser un caracter \n");
		}
	}

	
	
	// CREA 20 LIBROS AUTOMATICAMENTE
	private static void createMany() {
		Query query = entityManager.createQuery("SELECT MAX(id) FROM Libro");
		Integer rs = (Integer) query.getSingleResult();
		
		if (rs != null) {
		    Integer ultimoID = rs;
		    for (int id = ultimoID+1; id <= ultimoID+20; id++) {
	            BigDecimal precioRedondeado = BigDecimal.valueOf(Math.random() * 30 + 5).setScale(2, RoundingMode.HALF_UP);
	            float precio = precioRedondeado.floatValue();
				Libro libro = new Libro("Titulo " + id, "Autor " + id, precio);
				entityManager.persist(libro);
			}
		} else {
			for (int id = 1; id <= 20; id++) {
				BigDecimal precioRedondeado = BigDecimal.valueOf(Math.random() * 30 + 5).setScale(2, RoundingMode.HALF_UP);
	            float precio = precioRedondeado.floatValue();
				Libro libro = new Libro("Titulo " + id, "Autor " + id, precio);
				entityManager.persist(libro);
			}
		}
		
		System.out.println("┌────────────────────┐");
		System.out.println("│ LIBROS CREADOS *** │");
		System.out.println("└────────────────────┘\n");
	}

	
	
	// ACTUALIZA UN LIBRO SEGUN SU ID
	private static void upDate() {
		@SuppressWarnings("resource")
		Scanner tc = new Scanner(System.in);	// ES IMPORTANTE NO CERRAR LOS SCANNER YA QUE COMPARTEN LA MISMA FUENTE QUE EL DEL MENÚ Y SI CERRAMOS
												// ESTE, SE CERRARÍA EL DEL MENÚ TAMBIÉN, POR TANTO SOLO SE CIERRA CON TRY EL DEL MENÚ.
		
	    System.out.println("Qué libro desea actualizar? [id]: ");
	    Integer id = tc.nextInt();
	    
	    try {
	    	Query query = entityManager.createQuery("SELECT l FROM Libro l WHERE id = " + id);
		    Libro libroExistente = (Libro) query.getSingleResult();
		    
		    tc.nextLine();
		    System.out.print("Titulo: ");
		    String t = tc.nextLine();
		    System.out.print("Autor: ");
		    String a = tc.nextLine();
		    System.out.print("Precio: ");
		    try{
		    	float p = tc.nextFloat();

			    if(libroExistente != null) {
			    	libroExistente.setId(id);
				    libroExistente.setTitulo(t);
				    libroExistente.setAutor(a);
				    libroExistente.setPrecio(p);

				    entityManager.merge(libroExistente);
				    
				    System.out.println("\n┌───────────────────────┐");
					System.out.println("│ LIBRO ACTUALIZADO *** │");
					System.out.println("└───────────────────────┘\n");
			    } else
			    	System.out.println("\n NO SE ENCUENTRA EL LIBRO CON ID: "+id+"\n");
			    
			}catch (InputMismatchException e) {
				System.out.println("ERROR: El precio no puede ser un caracter");
			}
	    } catch(NoResultException e) {
	    	System.out.println("\n NO SE ENCUENTRA EL LIBRO CON ID: "+id+"\n");
	    }
	}

	
	
	// BORRA TODOS LOS LIBROS
	private static void borraTodos() {
		Query query = entityManager.createQuery("SELECT l FROM Libro l");
		
		@SuppressWarnings("unchecked")
		List<Libro> listaLibros = query.getResultList();
	
		int librosBorrado = 0;
		if(!listaLibros.isEmpty()) {
			for (Libro libro : listaLibros) {
				entityManager.remove(libro);
				librosBorrado++;
			}
			if(librosBorrado>0) {
				System.out.println("Libros borrados: " + librosBorrado);
				System.out.println("┌─────────────────────┐");
				System.out.println("│ LIBROS BORRADOS *** │");
				System.out.println("└─────────────────────┘\n");
			}
		} else {
			System.out.println("\n  ERROR: BASE DE DATOS VACIA \n");
		}
	}

	
	
	// BORRA UN LIBRO
	private static void borraUno(Integer id) {
		Libro libroBorrar = entityManager.find(Libro.class, id);
		if(libroBorrar!=null) {
			entityManager.remove(libroBorrar);
			System.out.println("\n┌───────────────────┐");
			System.out.println("│ LIBRO BORRADO *** │");
			System.out.println("└───────────────────┘\n");
		}
		else
			System.out.println("\n NO SE ENCUENTRA EL LIBRO CON ID: "+id+"\n");
	}

	
	
	// ABRE CONEXION
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("NombrePersistencia");
		entityManager = factory.createEntityManager();
		
		System.out.println("===================================================================================================================="
				+ "============================================================================");
	}

	
	
	// CIERRA CONEXION
	private static void end() {
		entityManager.close();
		factory.close();
	}
}