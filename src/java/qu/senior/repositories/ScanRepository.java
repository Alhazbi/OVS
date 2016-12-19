/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ScanRepository {

    @PersistenceContext
    EntityManager em;

    public Scan inserScan(Scan scan)
            throws InvalidLoginException {

        em.persist(scan);
        em.flush();
        return scan;
    }
    

    
        public Scan getScan(int id){
    	return em.getReference(Scan.class, id);
    }
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
