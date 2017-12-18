package br.com.discover.estudosNathan;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoSpringEstudosDiscoverApplication implements CommandLineRunner{
// CommandLineRunner permite implementar um metodo auxiliar
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringEstudosDiscoverApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
	}
}
