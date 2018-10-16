package com.rigatron.rigs4j.BL.DAOs;

import java.util.List;

import com.rigatron.rigs4j.BL.entities.SpringUserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserDAO;
import com.rigatron.rigs4j.BL.entities.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserDAO implements IUserDAO, UserDetailsService {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);

        q.select(c);

        return session.createQuery(q).getResultList();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = getUserByName(username);
            return new SpringUserDetails(user);
        }
        catch(NoResultException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Override
    public User getUserByName(String username) throws NoResultException {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);

        q.select(c).where(cb.equal(c.get("username"), username));

        return session.createQuery(q).getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);

        q.select(c).where(cb.equal(c.get("id"), id));

        return session.createQuery(q).getSingleResult();
    }

    @Override
    public void deleteUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if(user != null){
            session.delete(user);
        }
    }

}