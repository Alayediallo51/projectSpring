package m2gl.sn.scolaire.Controller;

import java.util.Optional;

import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.models.Enseignant;
import m2gl.sn.scolaire.models.Evaluation;
import m2gl.sn.scolaire.models.Module;
import m2gl.sn.scolaire.services.IEnseignant;
import m2gl.sn.scolaire.services.IEvaluation;
import m2gl.sn.scolaire.services.IModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/enseignant")
public class EnseignantController {
	@Autowired
	private IEnseignant iEnseignant;
	@Autowired
	private IModule iModule;
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
		if((iEnseignant.findByCodeProf(ens.getCodeProf())!= null)
				){
			model.addAttribute("message","Impossible d'enregistrer cet enseignant car il existe déja !!!");
			return "enseignant";
		}
		iEnseignant.save(ens);
		Iterable<Enseignant> enseignant = iEnseignant.findAll();
		model.addAttribute("lesapprenants", enseignant);
		
		return "redirect:liste";
	}
	
	@RequestMapping(value="/edit")
	public String editEnseignant(@RequestParam("id") String id,Model model){
		
		Optional<Enseignant> en = iEnseignant.findById(Integer.parseInt(id));
		if(en.isPresent()){
			model.addAttribute("enseignant", en.get());
			return "modifEnseignant";
		}
		else{
			Iterable<Enseignant> enseignants = iEnseignant.findAll();
			model.addAttribute("lesenseignants", enseignants);
			return "listeenseignants";
		}
	}
	
	@RequestMapping(value="/remove")
	public String removeEnseignant(Model model, String id){
		Optional<Enseignant> en = iEnseignant.findById(Integer.parseInt(id));
		if(en.isPresent()){
			Optional<Module> module = iModule.findByEnseignant(en);
			if(module.isPresent()){
				model.addAttribute("message", "impossible de supprimer cet enseignant");
				Iterable<Enseignant> enseignants = iEnseignant.findAll();
				model.addAttribute("lesenseignants", enseignants);
				Enseignant enn = new Enseignant();
				model.addAttribute("enseignant",enn);
				return "listeenseignants";				
			}else{
				model.addAttribute("messageRe", "Suppression reussi");
				iEnseignant.delete(en.get());
			}
		}
		Iterable<Enseignant> enseignants = iEnseignant.findAll();
		model.addAttribute("lesenseignants", enseignants);
		Enseignant enn = new Enseignant();
		model.addAttribute("enseignant",enn);
		return "listeenseignants";			
	}

}
