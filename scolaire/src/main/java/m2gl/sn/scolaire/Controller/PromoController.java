package m2gl.sn.scolaire.Controller;

import m2gl.sn.scolaire.models.Promo;
import m2gl.sn.scolaire.services.IPromotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/promo")
public class PromoController {
	@Autowired
	private IPromotion iPromo;
	@RequestMapping(value="/add")
	public String AjoutPromo(Model model){
		Promo promo = new Promo();
		model.addAttribute("promos",promo);
		return "promo";
	}
	
	@RequestMapping(value="/liste")
	public String ListePromo(Model model){
		Iterable<Promo> pro = iPromo.findAll();
		model.addAttribute("lesPromo", pro);
		return "listePromo";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String AjoutPromoPost(@ModelAttribute("promos") Promo promo, Model model){
		if((iPromo.findByPromo(promo.getPromo())!= null)
				){
			model.addAttribute("message","cette promotion existe déja !!!");
			return "promo";
		}
		iPromo.save(promo);
		Iterable<Promo> pro = iPromo.findAll();
		model.addAttribute("lesPromo", pro);
		
		return "redirect:liste";
	}

}
