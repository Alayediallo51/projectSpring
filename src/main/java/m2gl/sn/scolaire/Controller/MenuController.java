package m2gl.sn.scolaire.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	@RequestMapping(value="/home")
	public String Menu(){
		return "menu";
	}

}
