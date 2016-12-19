package qu.senior.repositories;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import qu.common.InvalidLoginException;
import qu.common.PasswordSecurity;
import qu.senior.entities.Scan;
import qu.senior.entities.User;


@Stateless
public class UserRepository   {

    @PersistenceContext
    EntityManager em;


	
    public User login(String email, String password)
            throws InvalidLoginException {
//        if (!email.toLowerCase().endsWith("@qu.edu.qa")) {
//            email += "@qu.edu.qa";
//        }
        
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email");
        query.setParameter("email", email.toLowerCase());
      
        User user = null;
        try {
        	user = (User) query.getSingleResult();
                if(new PasswordSecurity().isPasswordLegit(password, user.getPassword()))
                    return user;
        } catch(Exception ex) {}
        
        //After login set the current semester, the current sections, isStudent and the semesters
        return null;
    }
    
        public User inserUser(User user)
            throws InvalidLoginException {
   
            user.setPassword(new PasswordSecurity().encryptPassword(user.getPassword()));
            em.persist(user);
            em.flush();
            return user;
    }
        
//    public Scan inserScan(Scan scan)
//            throws InvalidLoginException {
//   
//          
//            em.persist(scan);
//            em.flush();
//            return scan;
//    }

    public User getUser(int id){
    	return em.getReference(User.class, id);
    }
    
    
    public User addUser(User user){
        user.setPassword(new PasswordSecurity().encryptPassword(user.getPassword()));
        em.persist(user);
        em.flush();
        return user;
    }
 
    public void deleteUser(int userId) {
        User user = getUser(userId);
        if (user != null) {
            em.remove(user);
        } else {
            throw new RuntimeException(String.format("User %d not found", userId));
        }
    }
     
        public List<User> getUsers() {
      return em.createQuery("Select s from User s").getResultList();
    }
    
//    public Scan getScan(int id){
//    	return em.getReference(Scan.class, id);
//    }
//    
    
    public List getHistoryList(int userId){
            String sqlQuery = "Select s.id , s.ip,  s.scandate " +
			"From Scan s " +
			"Where s.userId= ?userId " +
			"ORDER BY s.scandate DESC";
		System.out.println(sqlQuery);
//		Query query = em.createNativeQuery(sqlQuery,
//				qu.senior.entities.History.class);
                		Query query = em.createNativeQuery(sqlQuery);

		query.setParameter("userId", userId);
           
    	return query.getResultList();
    }
   
    }

