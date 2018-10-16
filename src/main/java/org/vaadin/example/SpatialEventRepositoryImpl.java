package org.vaadin.example;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public class SpatialEventRepositoryImpl implements SpatialEventExRepository {
	private static final Logger log = LoggerFactory.getLogger(SpatialEventRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;

	/*
	 * @Override
	 * 
	 * @Transactional public int insert(double lat, double lon) {
	 * log.info("== updateEmail ==="); //这里是数据库里面的表名，注意区分大小写 String sql =
	 * "INSERT INTO spatial_event(location,version) VALUES(geometry::STPointFromText('POINT ( 22  42 )', 0),32)"
	 * ; Query query = em.createNativeQuery(sql); // query.setParameter(1,
	 * email); // query.setParameter(2, id); return query.executeUpdate();
	 * 
	 * }
	 */

	public Geometry createCircle(double x, double y, double radius) {
		GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
		shapeFactory.setNumPoints(32);
		shapeFactory.setCentre(new Coordinate(x, y));
		shapeFactory.setSize(radius * 2);
		return shapeFactory.createCircle();
	}

	@Override
	@Transactional
	public int selectTest(double lat, double lon) {
		log.info("== updateEmail ===");
		//这里是数据库里面的表名，注意区分大小写

		//select c from Car c where distance(c.location, :geom) < :dist

//		 Query query = em.createQuery("select p from  home_security.dbo.spatial_event p where 			      within(p.point, :circle) = true", SpatialEvent.class);
//			    query.setParameter("circle", createCircle(0.0, 0.0, 5));
//			    query.getResultList();
//		
		String sql = "select * from  spatial_event " + "  where  location.STDistance('POINT(-121.626 47.8315)')<5";

		Query query = em.createNativeQuery(sql);
//		query.setParameter(1, email);
//		query.setParameter(2, id);
		query.getResultList();
		return 0;

	}

	@Override
	public int selectAll() {
		log.info("== selectAll ===");
		//这里是数据库里面的表名，注意区分大小写

		String sql = "select * from  spatial_event ";

		Query query = em.createNativeQuery(sql);
//		query.setParameter(1, email);
//		query.setParameter(2, id);
		List a = query.getResultList();
		for (Object item : a) {
			Object[] obj = (Object[]) item;
			for(Object o:obj){
			if (o != null) {
				System.out.print(o.toString());
				System.out.print("   ");
			}
			}
			System.out.println();
		}
		return 0;

	}
}
