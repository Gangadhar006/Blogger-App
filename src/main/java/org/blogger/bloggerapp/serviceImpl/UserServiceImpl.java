package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.exception.InvalidCredentialsException;
import org.blogger.bloggerapp.repository.IUsersRepository;
import org.blogger.bloggerapp.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUsersRepository userRepo;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> (UserDetails) userRepo.findByEmail(email)
                .orElseThrow(()->new InvalidCredentialsException("Invalid Credentials"));
    }
}