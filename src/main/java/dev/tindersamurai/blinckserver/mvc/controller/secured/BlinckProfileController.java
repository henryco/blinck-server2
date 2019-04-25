package dev.tindersamurai.blinckserver.mvc.controller.secured;

import dev.tindersamurai.blinckserver.mvc.controller.BlinckController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author Henry on 28/08/17.
 */
public interface BlinckProfileController extends BlinckController {


	default @RequestMapping(
			method = GET,
			value = "/profile"
	) @ResponseBody
	String profile() {
		return "wellcome in your profile page";
	}


	default @RequestMapping(
			method = GET,
			value = "/permissions"
	) @ResponseBody
	String[] permissions(Authentication authentication) {

		return authentication
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
		.toArray(String[]::new);
	}


}