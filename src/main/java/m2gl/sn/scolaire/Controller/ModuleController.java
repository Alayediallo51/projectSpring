package m2gl.sn.scolaire.Controller;

import java.util.Optional;

import m2gl.sn.scolaire.models.Apprenant;
import m2gl.sn.scolaire.models.Enseignant;
import m2gl.sn.scolaire.models.Module;
import m2gl.sn.scolaire.models.Promo;
import m2gl.sn.scolaire.services.IEnseignant;
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
@RequestMapping(value="/module")
public class ModuleController {
	@Autowired
	private IModule iModule;
//	@Autowired
//	private IPromotion iEnseignant;
	@Autowired
	private IEnseignant iEnseignant;
	@RequestMapping(value="/add")
	public String AjoutModule(Model model){
		Module module = new Module();
		module.setEnseignant(new Enseignant());
		model.addAttribute("module",module);
		Iterable<Enseignant> pro = iEnseignant.findAll();
		model.addAttribute("lesensei", pro);
		return "module";
	}
	
	@RequestMapping(value="/liste")
	public String ListeModules(Model model){
		Iterable<Module> mod = iModule.findAll();
		model.addAttribute("lesModules", mod);
		
		Module module = new Module();
		module.setEnseignant(new Enseignant());
		model.addAttribute("module",module);
		Iterable<Enseignant> pro = iEnseignant.findAll();
		model.addAttribute("lesensei", pro);
		
		return "listeModules";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String AjoutModulePost(@ModelAttribute("module") Module mod, Model model){
		if(mod.getId()==0 &&(iModule.findByNomModule(mod.getNomModule())!= null)
				){
			model.addAttribute("message","ce module existe déja !!!");
			return "module";
		}
		iModule.save(mod);
		Iterable<Module> modu = iModule.findAll();
		model.addAttribute("lesModules", modu);
		
		return "redirect:liste";
	}
	
	@RequestMapping(value="/remove")
	public String removeModule(Model model, String id){
		Optional<Module> ap = iModule.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			iModule.delete(ap.get());
		}
		Iterable<Module> modules = iModule.findAll();
		model.addAttribute("lesModules", modules);
		return "redirect:liste";
	}
	
	@RequestMapping(value="/edit")
	public String editModule(@RequestParam("id") String id,Model model){
		
		Optional<Module> ap = iModule.findById(Integer.parseInt(id));
		if(ap.isPresent()){
			Iterable<Enseignant> pro = iEnseignant.findAll();
			model.addAttribute("lesensei", pro);
			model.addAttribute("module", ap.get());
			return "modifModule";
		}
		else{
			Iterable<Module> modules = iModule.findAll();
			model.addAttribute("lesModules", modules);
			return "listeModules";
		}
	}

}
