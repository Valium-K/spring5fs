package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// ChangePwdCommand 객체 검증 클래스
public class ChangePwdCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePwdCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // currentPassword 필드 검증
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
        // newpassword 필드 검증
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
    }
}
