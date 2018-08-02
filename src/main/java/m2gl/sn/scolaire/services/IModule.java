package m2gl.sn.scolaire.services;

import java.util.Optional;

import m2gl.sn.scolaire.models.Enseignant;
import m2gl.sn.scolaire.models.Module;

import org.springframework.data.repository.CrudRepository;

public interface IModule extends CrudRepository<Module, Integer>{

	public Module findByNomModule(String nomModule);	
	public Optional<Module> findByEnseignant(Optional<Enseignant> ens);
}
