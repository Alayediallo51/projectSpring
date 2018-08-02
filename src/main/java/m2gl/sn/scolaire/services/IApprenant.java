/**
 * 
 */
package m2gl.sn.scolaire.services;

import m2gl.sn.scolaire.models.Apprenant;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Akhi
 *
 */
public interface IApprenant extends CrudRepository<Apprenant, Integer>{
	public Apprenant findByMatricule(String mat);

}
