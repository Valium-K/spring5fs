package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// 로그인 form에 입력된 값이 올바른지 확인하기 위한 클래스
// Validator에 대한 설명은 chap12 참고.
public class LoginCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return LoginCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
    }
}
