package ies.joatzel.erosketa;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErosketaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ErosketaApplication.class, args);
		System.out.println("*******************************************************************************************************************************************************************************************");
		System.out.println("*                                                                                                                                                      			                          *");
		System.out.println("*                                                                                                                                                      			       			          *");
		System.out.println("*                                                                                                                                                                           		      *");
		System.out.println("*                                                                                   ¡BIENVENIDO!                                                                            		      *");
		System.out.println("*                                                                                    A EROSKETA                                                                            		          *");
		System.out.println("*                                                                                                                                                                           		      *");
		System.out.println("*                                                                                                                                                      			            		      *");
		System.out.println("*                                                                                                                                                                          			      *");
		System.out.println("*******************************************************************************************************************************************************************************************");

		System.out.println("Ábra Postman EN http://localhost:8080/api/categories para ver las categorias!!!");
		System.out.println("Ábra Postman EN http://localhost:8080/api/products para ver los productos!!!");
	}
}
