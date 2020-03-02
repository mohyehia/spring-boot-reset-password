package com.mohyehia.demo.service.implementation;

import com.mohyehia.demo.dao.RoleDAO;
import com.mohyehia.demo.dao.UserDAO;
import com.mohyehia.demo.entity.Role;
import com.mohyehia.demo.entity.User;
import com.mohyehia.demo.service.framework.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }

    @Override
    @Modifying
    public void updatePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleDAO.findById(1L).orElse(null);
        if(role != null){
            user.setRoles(new HashSet<>(Collections.singletonList(role)));
            return userDAO.save(user);
        }
        return null;
    }
}
