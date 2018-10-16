
package org.vaadin.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mstahv
 */
public interface SpatialEventRepository
		extends JpaQueryDslPredicateRepository<SpatialEvent, Long>,SpatialEventExRepository {
	
//	@Transactional
//	@Modifying
//	@Query("select * from SpatialEvent")	
//	public List<SpatialEvent> find();
}
