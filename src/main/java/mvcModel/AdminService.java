package mvcModel;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;
import entities.Admin;

/**
 * Session Bean implementation class AdminService
 */
@Stateless
@LocalBean
public class AdminService {

    @PersistenceContext(unitName = "projectfinal")
    private EntityManager em;

    public AdminService() {
        // Auto-generated constructor stub
    }

    public void addAdmin(Admin admin) {
        List<Admin> existingAdmins = em.createQuery("SELECT a FROM Admin a WHERE a.user = :user", Admin.class)
                                       .setParameter("user", admin.getUser())
                                       .getResultList();
        
        if (existingAdmins.isEmpty()) {
            em.persist(admin);
            System.out.println("Admin added to database");
        } else {
         
            System.out.println("Admin with username " + admin.getUser() + " already exists.");
        }
    }


    public List<Admin> listAdmins() {
        TypedQuery<Admin> query = em.createNamedQuery("Admin.findAll", Admin.class);
        return query.getResultList();
    }

    public Admin updateAdmin(int adminId, Admin updatedAdmin) {
        Admin admin = em.find(Admin.class, adminId);
        if (admin != null) {
            admin.setUser(updatedAdmin.getUser());
            admin.setPassword(updatedAdmin.getPassword());
            return em.merge(admin);
        }
        return null;
    }

    public void deleteAdmin(int adminId) {
        Admin admin = em.find(Admin.class, adminId);
        if (admin != null) {
            em.remove(admin);
            System.out.println("Admin deleted");
        } else {
            System.out.println("Admin not found");
        }
    }

    public Admin findAdminById(int adminId) {
        Admin admin = em.find(Admin.class, adminId);
        if (admin != null) {
            System.out.println("Admin found");
        } else {
            System.out.println("Admin not found");
        }
        return admin;
    }

    public boolean validateLogin(String username, String password) {
        try {
            Admin admin = em.createQuery("SELECT a FROM Admin a WHERE a.user = :user AND a.password = :password", Admin.class)
                            .setParameter("user", username)
                            .setParameter("password", password)
                            .getSingleResult();
            return admin != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    /**
     * Register a new admin.
     */
    public void registerAdmin(String username, String password) {
        Admin newAdmin = new Admin();
        newAdmin.setUser(username);
        newAdmin.setPassword(password);
        em.persist(newAdmin);
    }
}
