package com.zerofiltre.snapanonyme.application.Snaper;

import com.zerofiltre.snapanonyme.domain.model.Snaper;
import com.zerofiltre.snapanonyme.domain.repository.Snapers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class GetSnapers implements UserDetailsService {

    private Snapers snapers;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public GetSnapers(Snapers snapers) {
        this.snapers = snapers;
    }


    // hard coding the mockUsers. All passwords must be encoded.
    final List<Snaper> mockUsers = Arrays.asList(
            new Snaper(1, "omar", passwordEncoder.encode("12345"), "USER"),
            new Snaper(2, "admin", passwordEncoder.encode("12345"), "ADMIN")
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO set up user management system and stop using dump users, use snapers to retrieve users
        //User user = (User) entityManager.createQuery("Select s from " + User.class.getSimpleName() + " s where s.userName = " + username).getSingleResult();
        for (Snaper user : mockUsers) {
            if (user.getUserName().equals(username)) {
                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

                // The "Snaper" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
            }

        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");

    }
}
