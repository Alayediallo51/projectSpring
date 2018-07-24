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
	public Apprenant findByNom(String nom);
	public Apprenant findByPrenom(String prenom);

}
