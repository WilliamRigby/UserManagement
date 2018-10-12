package com.rigatron.rigs4j.BL.DAOs;

import com.rigatron.rigs4j.BL.entities.SpringUserDetails;
import com.rigatron.rigs4j.BL.entities.User;
import com.rigatron.rigs4j.BL.entities.UserRole;
import com.rigatron.rigs4j.BL.entities.enums.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDetailsServiceDAO implements UserDetailsService {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);

        q.select(c).where(cb.equal(c.get("username"), username));

        User user = session.createQuery(q).getSingleResult();

        return new SpringUserDetails(user);
    }
}
