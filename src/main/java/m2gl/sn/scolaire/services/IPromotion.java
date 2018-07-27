package m2gl.sn.scolaire.services;

import java.util.Date;

import m2gl.sn.scolaire.models.Promo;

import org.springframework.data.repository.CrudRepository;

public interface IPromotion extends CrudRepository<Promo, Integer>{
	public Promo findByDateDebut(Date dateDebut);

}
