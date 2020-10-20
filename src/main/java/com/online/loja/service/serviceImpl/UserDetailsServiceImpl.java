package com.online.loja.service.serviceImpl;

import com.online.loja.exception.UserAlreadyExistsException;
import com.online.loja.repository.UserRepository;
import com.online.loja.security.confirmationToken.model.ConfirmationToken;
import com.online.loja.security.confirmationToken.service.ConfirmationTokenService;
import com.online.loja.security.emailSender.EmailSenderService;
import com.online.loja.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private  UserRepository userRepository;

    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    private  ConfirmationTokenService confirmationTokenService;

    private  EmailSenderService emailSenderService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setConfirmationTokenService(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @Autowired
    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

   public User signUpUser(User user) {
       Optional<User> userUniqueEmailVerification = userRepository.findByEmail(user.getEmail());
       Optional<User> userUniqueUserNameVerification = userRepository.findBySurname(user.getSurname());

       if (userUniqueEmailVerification.isPresent())
           throw new UserAlreadyExistsException("Email already registrated!");
       else if(userUniqueUserNameVerification.isPresent())
           throw new UserAlreadyExistsException("Username already exists");


       final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        final User createdUser = userRepository.save(user);

        final ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return createdUser;
    }

    public void confirmUser(ConfirmationToken confirmationToken) {

        final User user = confirmationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }

    public void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("noreply@loja.com");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/register/confirm?token="
                        + token);

        emailSenderService.sendEmail(mailMessage);
    }

}
