package com.mohyehia.demo.service.implementation;

import com.mohyehia.demo.dao.PasswordResetTokenDAO;
import com.mohyehia.demo.entity.PasswordResetToken;
import com.mohyehia.demo.service.framework.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenDAO passwordResetTokenDAO;

    @Autowired
    public PasswordResetTokenServiceImpl(PasswordResetTokenDAO passwordResetTokenDAO) {
        this.passwordResetTokenDAO = passwordResetTokenDAO;
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenDAO.findByToken(token).orElse(null);
    }

    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenDAO.save(passwordResetToken);
    }
}
