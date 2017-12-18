package br.com.discover.estudosNathan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	// Automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Categoria obj = repo.findOne(id);
		return obj;
	}

}
