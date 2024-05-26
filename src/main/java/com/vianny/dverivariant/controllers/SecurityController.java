package com.vianny.dverivariant.controllers;

import com.vianny.dverivariant.dto.request.auth.SignInRequest;
import com.vianny.dverivariant.dto.request.auth.SignUpRequest;
import com.vianny.dverivariant.dto.response.jwt.JwtToken;
import com.vianny.dverivariant.dto.response.message.MainMessage;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.UnauthorizedRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.UnregisteredRequiredException;
import com.vianny.dverivariant.services.MinioService;
import com.vianny.dverivariant.services.account.AccountService;
import com.vianny.dverivariant.utils.JwtCore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private AccountService accountService;
    private JwtCore jwtCore;
    private MinioService minioService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Autowired
    public void setJwtCore(JwtCore jwtCore) {
        this.jwtCore = jwtCore;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/signUp")
    @Transactional
    ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            accountService.signUpAccount(signUpRequest);
            minioService.createBucket("dveri-images");
        }
        catch (BadCredentialsException e) {
            throw new UnauthorizedRequiredException(HttpStatus.UNAUTHORIZED, "Регистрация не удалась");
        }
        catch (UnregisteredRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        MainMessage mainMessage = new MainMessage(HttpStatus.CREATED, "Успешно");
        return new ResponseEntity<>(mainMessage, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication;
        try {
            authentication = accountService.signInAccount(signInRequest);
        }
        catch (BadCredentialsException e) {
            throw new UnauthorizedRequiredException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);

        JwtToken jwtToken = new JwtToken(HttpStatus.OK, jwt);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

}
