package controller;

import config.RegisterRequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

import javax.validation.Valid;

@Controller
public class RegisterController {

	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}

	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}

	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}

	@PostMapping("/register/step3")
	// 커맨드 객체(registerRequest)뒤에 Errors 객체가 위치해야한다.
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {
		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) {
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		} catch (WrongIdPasswordException ex) {
			// 위 예외는 발생하지 않지만
			// reject 메소드는 글로벌 에를 위함임을 보이기위해 썼다.
			errors.reject("notMatchingIdPassword");
			return "register/step2";
		}
	}

	// 컨트롤러 범위 Validator 설정
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator());
	}

}
