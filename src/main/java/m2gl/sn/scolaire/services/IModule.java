package m2gl.sn.scolaire.services;

import m2gl.sn.scolaire.models.Module;

import org.springframework.data.repository.CrudRepository;

public interface IModule extends CrudRepository<Module, Integer>{

	public Module findByNomModule(String nomModule);	
}
