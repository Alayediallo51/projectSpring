package m2gl.sn.scolaire.Controller;


import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.services.IApprenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/apprenant")
public class ApprenantController {
	@Autowired
	private IApprenant iApprenant;
	@RequestMapping(value="/liste")
	public String ListeApprenant(Model model){
		Apprenant app= new Apprenant();
		model.addAttribute("apprenant",app);
		Iterable<Apprenant> apprenants = iApprenant.findAll();
		model.addAttribute("lesapprenants", apprenants);
		return "listeApprenants";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String AjoutApprenantPost(@ModelAttribute("apprenant") Apprenant ap, Model model){
		if((iApprenant.findByNom(ap.getNom())!= null)
				){
			model.addAttribute("message","Impossible d'enregistrer cet apprenant car il existe déja !!!");
			return "apprenant";
		}
		iApprenant.save(ap);
		Iterable<Apprenant> apprenants = iApprenant.findAll();
		model.addAttribute("lesapprenants", apprenants);
		
		return "redirect:liste";
	}
}
