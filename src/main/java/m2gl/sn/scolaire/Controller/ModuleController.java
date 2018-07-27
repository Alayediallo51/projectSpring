package m2gl.sn.scolaire.Controller;

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
		if((iModule.findByNomModule(mod.getNomModule())!= null)
				){
			model.addAttribute("message","ce module existe déja !!!");
			return "module";
		}
		iModule.save(mod);
		Iterable<Module> modu = iModule.findAll();
		model.addAttribute("lesModules", modu);
		
		return "redirect:liste";
	}

}
