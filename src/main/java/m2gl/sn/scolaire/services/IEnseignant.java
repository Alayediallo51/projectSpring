package m2gl.sn.scolaire.services;

import m2gl.sn.scolaire.models.Enseignant;

import org.springframework.data.repository.CrudRepository;

public interface IEnseignant extends CrudRepository<Enseignant, Integer>{
	public Enseignant findByNomComplet(String nomComplet);
}
