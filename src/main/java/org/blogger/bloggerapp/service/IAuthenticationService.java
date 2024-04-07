package org.blogger.bloggerapp.service;

import org.blogger.bloggerapp.payload.request.SigninRequest;
import org.blogger.bloggerapp.payload.request.SignupRequest;
import org.blogger.bloggerapp.payload.response.SigninResponse;
import org.blogger.bloggerapp.payload.response.SignupResponse;

public interface IAuthenticationService {
    SigninResponse signin(SigninRequest request);

    SignupResponse signup(SignupRequest signupRequest);
}
