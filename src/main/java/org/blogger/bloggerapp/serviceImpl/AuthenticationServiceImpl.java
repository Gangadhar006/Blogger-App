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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final IUsersRepository userRepo;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SigninResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        Users user = userRepo.findByEmail(signinRequest.getEmail())
                .orElseThrow(IllegalArgumentException::new);
        String jwt = jwtService.generateToken(user);
        return SigninResponse.builder()
                .token(jwt)
                .userId(user.getId())
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