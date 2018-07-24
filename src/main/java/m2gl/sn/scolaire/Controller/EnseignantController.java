package m2gl.sn.scolaire.Controller;

import m2gl.sn.scolaire.models.Enseignant;
import m2gl.sn.scolaire.services.IEnseignant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/enseignant")
public class EnseignantController {
	@Autowired
	private IEnseignant iEnseignant;
	@RequestMapping(value="/add")
	public String AjoutEnsignant(Model model){
		Enseignant en = new Enseignant();
		model.addAttribute("enseignant",en);	
		return "enseignant";
	}
	
	@RequestMapping(value="/liste")
	public String ListeEnseignants(Model model){
		Iterable<Enseignant> enseignants = iEnseignant.findAll();
		model.addAttribute("lesenseignants", enseignants);
		Enseignant en = new Enseignant();
		model.addAttribute("enseignant",en);
		return "listeenseignants";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String AjoutEnsignantPost(@ModelAttribute("enseignant") Enseignant ens, Model model){
		if((iEnseignant.findByNomComplet(ens.getNomComplet())!= null)
				){
			model.addAttribute("message","Impossible d'enregistrer cet enseignant car il existe déja !!!");
			return "enseignant";
		}
		iEnseignant.save(ens);
		Iterable<Enseignant> enseignant = iEnseignant.findAll();
		model.addAttribute("lesapprenants", enseignant);
		
		return "redirect:liste";
	}

}
