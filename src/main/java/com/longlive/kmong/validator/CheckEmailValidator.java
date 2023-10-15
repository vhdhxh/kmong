package com.longlive.kmong.validator;

import com.longlive.kmong.DAO.User;

import com.longlive.kmong.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

    @Component
    @RequiredArgsConstructor
    public class CheckEmailValidator extends AbstractValidator<UserDTO> {

        private final User user;

        @Override
        protected void doValidate(UserDTO dto, Errors errors) {
            if(user.CheckEmailValidation(dto.getUser_email())) {
                errors.rejectValue("user_email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }

