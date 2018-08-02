package m2gl.sn.scolaire.Controller;


import java.util.List;
import java.util.Optional;

import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.models.Evaluation;
import m2gl.sn.scolaire.services.IApprenant;
import m2gl.sn.scolaire.services.IEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/apprenant")
public class ApprenantController {
	@Autowired
	private IApprenant iApprenant;
	@Autowired
	private IEvaluation iEvaluation;
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
		if(ap.getId()==0 && (iApprenant.findByMatricule(ap.getMatricule())!= null)
				){
			model.addAttribute("message","Impossible d'enregistrer cet apprenant car il existe déja !!!");
			return "apprenant";
		}
		iApprenant.save(ap);
		Iterable<Apprenant> apprenants = iApprenant.findAll();
		model.addAttribute("lesapprenants", apprenants);
		
		return "redirect:liste";
	}
	@RequestMapping(value="/detail")
	public String DetailApprenant(@RequestParam("id") String id,Model model){
	    Optional<Apprenant> ap = iApprenant.findById(Integer.parseInt(id));
		Iterable<Evaluation> evaluation = iEvaluation.findByApprenant(ap);
		model.addAttribute("lesevaluations", evaluation);
		return "detailApprenant";
	}
	@RequestMapping(value="/remove")
	public String removeApprenant(Model model, String id){
		Optional<Apprenant> ap = iApprenant.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			iApprenant.delete(ap.get());
		}
		Iterable<Apprenant> apprenants = iApprenant.findAll();
		model.addAttribute("lesapprenants", apprenants);
		return "redirect:liste";
	}
	
	@RequestMapping(value="/edit")
	public String editMedecin(@RequestParam("id") String id,Model model){
		
		Optional<Apprenant> ap = iApprenant.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			model.addAttribute("apprenant", ap.get());
			return "modifApprenant";
		}
		else{
			Iterable<Apprenant> apprenants = iApprenant.findAll();
			model.addAttribute("lesapprenants", apprenants);
			return "listeApprenants";
		}
	}
	
}
