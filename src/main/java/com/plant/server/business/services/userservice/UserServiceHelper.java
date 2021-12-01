package com.plant.server.business.services.userservice;

import com.plant.server.business.entities.user.User;
import com.plant.server.business.entities.user.UserRepository;
import com.plant.server.business.services.exceptions.InstanceNotFoundException;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.crypto.CryptUtil;
import com.plant.server.util.date.DateUtil;
import com.plant.server.web.controller.api.ApiURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceHelper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonProperties commonProperties;

    @Value("${code.secret}")
    private String secret;
    @Autowired
    private CryptUtil cryptUtil;

    public User findUserAndCheckNullAndState(String username) {
        try {
            return findUserAndCheckNullAndState(this.userRepository.findByEmail(username));
        } catch (InstanceNotFoundException e) {
            throw new InstanceNotFoundException(username, User.class.getName());
        }
    }

    public User findUserAndCheckNull(String username) {
        try {
            return findUserAndCheckNull(this.userRepository.findByEmail(username));
        } catch (InstanceNotFoundException e) {
            throw new InstanceNotFoundException(username, User.class.getName());
        }
    }

    public User findUserAndCheckNull(Long userId) {
        Optional<User> optUser = this.userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new InstanceNotFoundException(userId, User.class.getName());
        }
        return optUser.get();
    }

    public static User findUserAndCheckNull(User user) {
        if (user == null) {
            throw new InstanceNotFoundException(null, User.class.getName());
        }
        return user;
    }

    public static User findUserAndCheckNullAndState(User user) {
        if (user == null) {
            throw new InstanceNotFoundException(null, User.class.getName());
        }
        return user;
    }

    public String getChangePasswordUrl(String userEmail) {
        return generateChangePasswordUrl(userEmail);
    }

    private String generateChangePasswordUrl(String userEmail) {

        long now = DateUtil.getNow().getTimeInMillis();
        String hash = now + userEmail;

        String hashCode = CryptUtil.generateHash(this.commonProperties.getPrivateKeyHash(), hash);

        return this.commonProperties.getClientBaseUrl() + ApiURL.CHANGE_PASSWORD + ApiURL.SLASH + userEmail + ApiURL.SLASH + now + ApiURL.SLASH + hashCode;
    }

}