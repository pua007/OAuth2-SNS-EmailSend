package com.housing.back.controller;

import com.housing.back.dto.request.auth.CheckCertificationRequestDto;
import com.housing.back.dto.request.auth.EmailCertificationRequestDto;
import com.housing.back.dto.request.auth.IdCheckRequestDto;
import com.housing.back.dto.request.auth.SignInRequestDto;
import com.housing.back.dto.request.auth.SignUpRequestDto;
import com.housing.back.dto.response.auth.CheckCertificationResponseDto;
import com.housing.back.dto.response.auth.EmailCertificationResponseDto;
import com.housing.back.dto.response.auth.IdcheckResponseDto;
import com.housing.back.dto.response.auth.SignInResponseDto;
import com.housing.back.dto.response.auth.SignUpResponseDto;
import com.housing.back.service.AuthService;
import com.housing.back.service.implement.AuthServiceImplement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    //구현체를 직접 호출하여 함수를 써도 기능상 문제는 없지만
    //코드의 유연성을 위하여 인터페이스로 쓰는것이 바람직함
    //만약 구현체의 변경이 있더라도 컨트롤러는 여전히 같은 인터페이스를써서 교체가 용읭함
    //private  final AuthServiceImplement authServiceImplement;

    @PostMapping("id-check")
    public ResponseEntity<? super IdcheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdcheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {
        System.out.println(requestBody.getId() + requestBody.getEmail());
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
        @RequestBody @Valid CheckCertificationRequestDto requestBody)
    {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("sign-in")
    public ResponseEntity<? super SignInResponseDto> signIN(
        @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super  SignInResponseDto> response = authService.signIN(requestBody);
        return response;
    }
}
