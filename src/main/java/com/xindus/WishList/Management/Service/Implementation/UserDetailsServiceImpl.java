package com.xindus.WishList.Management.Service.Implementation;

import com.xindus.WishList.Management.Dto.Credentials;
import com.xindus.WishList.Management.Entites.User;
import com.xindus.WishList.Management.Repository.UserRepository;
import com.xindus.WishList.Management.Service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public String signup(Credentials credentials) {
        User user =new User();
        user.setEmailId(credentials.getEmailId());
        user.setPassword(credentials.getPassword());
        userRepository.save(user);
        return "Signup Successfully";
    }

    @Override
    public boolean login(String email, String password) {
        User user = userRepository.findByEmailId(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByMail(String mail){
      return  userRepository.findByEmailId(mail);
    }
}
