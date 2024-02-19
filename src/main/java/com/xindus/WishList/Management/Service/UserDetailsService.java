package com.xindus.WishList.Management.Service;

import com.xindus.WishList.Management.Dto.Credentials;
import com.xindus.WishList.Management.Entites.User;
import jakarta.servlet.http.HttpSession;

public interface UserDetailsService {
    public String signup(Credentials credentials);
    public boolean login(String email, String password);

    User findUserByMail(String mail);
}
