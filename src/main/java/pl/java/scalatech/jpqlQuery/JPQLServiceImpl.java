package pl.java.scalatech.jpqlQuery;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true)
@Profile("dev")
public class JPQLServiceImpl implements JPQLService {


       @Autowired
       private EntityManager entityManager;

		@Override
		@Transactional
		public Object executeQuery(String jpaql) {
			if (jpaql == null || jpaql.trim() == null) {
                return null;
            }
			if (jpaql.toUpperCase().startsWith("INSERT") || jpaql.toUpperCase().startsWith("UPDATE") || jpaql.toUpperCase().startsWith("DELETE")) {
				return entityManager.createQuery(jpaql).executeUpdate();
			}
			return entityManager.createQuery(jpaql).setMaxResults(40).getResultList();
		}

	}
