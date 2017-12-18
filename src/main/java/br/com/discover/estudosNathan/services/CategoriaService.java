package br.com.discover.estudosNathan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.repositories.CategoriaRepository;
import br.com.discover.estudosNathan.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	// Automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id
					+ " , Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

}
