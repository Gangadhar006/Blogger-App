package org.blogger.bloggerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.payload.request.SigninRequest;
import org.blogger.bloggerapp.payload.request.SignupRequest;
import org.blogger.bloggerapp.payload.response.SigninResponse;
import org.blogger.bloggerapp.payload.response.SignupResponse;
import org.blogger.bloggerapp.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(AUTHENTICATION_BASE_URL)
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping(AUTHENTICATION_SIGNIN_URL)
    public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok().body(authenticationService.signin(signinRequest));
    }

    @PostMapping(AUTHENTICATION_SIGNUP_URL)
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok().body(authenticationService.signup(signupRequest));
    }
}