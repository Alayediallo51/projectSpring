package m2gl.sn.scolaire.services;

import java.util.Optional;

import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.models.Evaluation;
import m2gl.sn.scolaire.models.Module;
import m2gl.sn.scolaire.models.Promo;

import org.springframework.data.repository.CrudRepository;

public interface IEvaluation extends CrudRepository<Evaluation, Integer>{
	public Iterable<Evaluation> findByApprenant(Optional<Apprenant> ap);
	public Iterable<Evaluation> findByModule(Optional<Module> mod);
	public Iterable<Evaluation> findByPromo(Optional<Promo> pro);



}
