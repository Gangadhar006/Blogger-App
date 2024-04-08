package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.payload.request.SigninRequest;
import org.blogger.bloggerapp.payload.request.SignupRequest;
import org.blogger.bloggerapp.payload.response.SigninResponse;
import org.blogger.bloggerapp.payload.response.SignupResponse;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.service.IAuthenticationService;
import org.blogger.bloggerapp.service.IJwtService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.blogger.bloggerapp.constants.AppConstants.INVALID_CREDENTIALS_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final IUsersRepository userRepo;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SigninResponse signin(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        String jwt;
        if (authentication.isAuthenticated())
            jwt = jwtService.generateToken(signinRequest.getEmail());
        else
            throw new UsernameNotFoundException(INVALID_CREDENTIALS_MESSAGE);
        return SigninResponse.builder()
                .token(jwt)
                .build();
    }

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        String password = signupRequest.getPassword();
        Users user = mapper.map(signupRequest, Users.class);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepo.save(user);
        return mapper.map(user, SignupResponse.class);
    }
}