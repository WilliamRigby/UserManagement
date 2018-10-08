package com.rigatron.rigs4j.BL.DAOs;

import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserRoleDAO;
import com.rigatron.rigs4j.BL.entities.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserRoleDAO implements IUserRoleDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUserRole(UserRole role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Override
    public void updateUserRole(UserRole role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> getAllUserRoles() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from UserRole").list();
    }

    @Override
    public UserRole getUserRoleById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(UserRole.class, id);
    }

    @Override
    public void deleteUserRoleById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        UserRole role = session.load(UserRole.class, id);
        if(role != null){
            session.delete(role);
        }
    }

}