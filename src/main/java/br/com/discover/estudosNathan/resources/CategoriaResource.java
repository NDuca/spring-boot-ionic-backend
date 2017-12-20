package br.com.discover.estudosNathan.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	//ResponseEntity = tipo especial que encapsula, armazenando varias informacoes de uma resposta Http tipo Rest
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	// Para o obj Categoria ser construido a partir dos dados Json, usa-se a anotacao @RequestBody, fazendo o Json converter para obj Java automaticamente;
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		// Fornecer a URI para resposta na requisicao para criar uma nova categoria passando como argumento;
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
