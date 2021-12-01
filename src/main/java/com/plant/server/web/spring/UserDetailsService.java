package com.plant.server.web.spring;

import com.plant.server.business.entities.user.User;
import com.plant.server.business.services.exceptions.InstanceNotFoundException;
import com.plant.server.business.services.userservice.UserConstants;
import com.plant.server.business.services.userservice.UserServiceHelper;
import com.plant.server.util.jwt.JwtTokenUtil;
import com.plant.server.util.jwt.TokenCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.plant.server.business.entities.user.UserConstants.USER_TYPE_ADMIN;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    public static final String AUTHORITY_ADMIN = "ADMIN_A";
    public static final String AUTHORITY_USER = "USER_A";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = this.userServiceHelper.findUserAndCheckNullAndState(username);
            return getUserDetailsByUser(user);
        } catch (InstanceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public static UserDetails getUserDetailsByUser(User user) {
        return new UserSpring(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(getUserAuthoritiesArray(user.getType())), user.getId());
    }

    public UserDetails getUserDetails(String username) {
        User user = this.userServiceHelper.findUserAndCheckNullAndState(username);
        return getUserDetailsByUser(user);
    }


    private static String[] getUserAuthoritiesArray(String rol) {
        Collection<String> authoritiesList = getUserAuthoritiesCollection(rol);
        String[] authoritiesArr = new String[authoritiesList.size()];
        return authoritiesList.toArray(authoritiesArr);
    }

    private static Set<String> getUserAuthoritiesCollection(String rol) {
        Set<String> authoritiesList = new HashSet<String>();
        if (UserConstants.ROLE_USER.equals(rol)) {
            authoritiesList.add(UserDetailsService.AUTHORITY_USER);
        }
        if (UserConstants.ROLE_ADMIN.equals(rol)) {
            authoritiesList.add(UserDetailsService.AUTHORITY_ADMIN);
        }
        return authoritiesList;
    }

    private static GrantedAuthority getAuthority(String authority) {
        return new SimpleGrantedAuthority(authority);
    }

    public static String getScopeFromRole(String role) {
        return getUserAuthoritiesCollection(role).stream().map(authority -> authority).collect(Collectors.joining(" "));
    }

    public static boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
        return authorities.contains(UserDetailsService.getAuthority(UserDetailsService.AUTHORITY_ADMIN));
    }

    @Transactional(readOnly = true)
    public TokenCO checkCredentialsAndGetTokens(String username, String password) {
        try {
            User user = this.userServiceHelper.findUserAndCheckNullAndState(username);
            UserDetails userDetails = getUserDetailsByUser(user);

            if (!this.passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("invalid password");
            }
            return this.generateTokens(user, userDetails);
        } catch (InstanceNotFoundException e) {
            throw new InstanceNotFoundException("User " + username + " not found");
        }
    }

    @Transactional(readOnly = true)
    public TokenCO checkCredentialsAndGetTokens(String refreshToken) {
        try {
            if (!this.jwtTokenUtil.isValidRefreshToken(refreshToken)) {
                throw new BadCredentialsException("invalid refresh token");
            }
            String username = this.jwtTokenUtil.getUsernameFromToken(refreshToken);
            User user = this.userServiceHelper.findUserAndCheckNullAndState(username);
            UserDetails userDetails = getUserDetailsByUser(user);
            return this.generateTokens(user, userDetails);
        } catch (InstanceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    private TokenCO generateTokens(User user, UserDetails userDetails) {

        if (user.getType().equals(USER_TYPE_ADMIN)) {
            return this.jwtTokenUtil.generateTokens(user.getEmail(), userDetails.getPassword(), UserConstants.ROLE_ADMIN, UserDetailsService.getScopeFromRole(UserConstants.ROLE_ADMIN));
        } else {
            return this.jwtTokenUtil.generateTokens(user.getEmail(), userDetails.getPassword(), UserConstants.ROLE_USER, UserDetailsService.getScopeFromRole(UserConstants.ROLE_USER));
        }

    }


}
