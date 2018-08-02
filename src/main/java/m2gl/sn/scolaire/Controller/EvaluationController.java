package m2gl.sn.scolaire.Controller;

import java.util.Optional;

import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.models.Enseignant;
import m2gl.sn.scolaire.models.Evaluation;
import m2gl.sn.scolaire.models.Module;
import m2gl.sn.scolaire.models.Promo;
import m2gl.sn.scolaire.services.IApprenant;
import m2gl.sn.scolaire.services.IEnseignant;
import m2gl.sn.scolaire.services.IEvaluation;
import m2gl.sn.scolaire.services.IModule;
import m2gl.sn.scolaire.services.IPromotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EvaluationController {
	@Autowired
	private IApprenant iApprenant;
	@Autowired
	private IModule iModule;
	@Autowired
	private IPromotion iPromo;
	
	@Autowired
	private IEvaluation iEvaluation;
	
	@RequestMapping(value="/evaluation/liste")
	public String ListeEvaluation(Model model){
				
		Evaluation evaluation = new Evaluation();
		evaluation.setApprenant(new Apprenant());
		evaluation.setModule(new Module());
		evaluation.setPromo(new Promo());
		
		Iterable<Apprenant> apprenant = iApprenant.findAll();
		model.addAttribute("apprenants", apprenant);
		
		Iterable<Module> mod = iModule.findAll();
		model.addAttribute("lesModules", mod);
		
		Iterable<Promo> promos = iPromo.findAll();
		model.addAttribute("promos", promos);
		
		model.addAttribute("DonneEvaluation", evaluation);
		
		Iterable<Evaluation> eval = iEvaluation.findAll();
		model.addAttribute("lesevaluations", eval);
		
		
		return "listeevaluations";
	}
	
	@RequestMapping(value="/evaluation/add",method=RequestMethod.POST)
	public String AjoutEvaluationPost(@ModelAttribute("DonneEvaluation") Evaluation eval, Model model){
//		if((iModule.findByNomModule(mod.getNomModule())!= null)
//				){
//			model.addAttribute("message","ce module existe déja !!!");
//			return "module";
//		}
		if(eval.getNote()>=0 && eval.getNote()<12){
			eval.setMention("Passable");
		}else
			if(eval.getNote()>=12 && eval.getNote()<14){
				eval.setMention("Assez Bien");
			}else
				if(eval.getNote()>=14 && eval.getNote()<16){
					eval.setMention("Bien");
				}else
					if(eval.getNote()>=16 && eval.getNote()<18){
						eval.setMention("Très Bien");
					}else
						if(eval.getNote()>=18 && eval.getNote()<=20){
							eval.setMention("Excelent");
						}		
		iEvaluation.save(eval);
		Iterable<Evaluation> evaluations = iEvaluation.findAll();
		model.addAttribute("lesEvaluations",evaluations);
		
		return "redirect:/evaluation/liste";
	}

	@RequestMapping(value="/remove")
	public String removeEvaluation(Model model, String id){
		Optional<Evaluation> ap = iEvaluation.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			iEvaluation.delete(ap.get());
		}
		Iterable<Evaluation> evaluations = iEvaluation.findAll();
		model.addAttribute("lesevaluations", evaluations);
		return "redirect:liste";
	}
	
	@RequestMapping(value="/edit")
	public String editEvaluation(@RequestParam("id") String id,Model model){
		Optional<Evaluation> ap = iEvaluation.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			model.addAttribute("DonneEvaluation", ap.get());
			return "modifEvaluation";
		}
		else{
			Iterable<Evaluation> evaluations = iEvaluation.findAll();
			model.addAttribute("lesEvaluations", evaluations);
			return "listeApprenants";
		}
	}
}
