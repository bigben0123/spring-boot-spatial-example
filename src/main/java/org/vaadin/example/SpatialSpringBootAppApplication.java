package org.vaadin.example;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

@SpringBootApplication
public class SpatialSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpatialSpringBootAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(SpatialEventRepository repo, SimpleEventRepository repo1) {
		return (String... strings) -> {
			//0, simple event table test
			SimpleEvent simple = new SimpleEvent();
			simple.setTitle("Example event");
			repo1.save(simple);
			
			GeometryFactory factory = new GeometryFactory();

			//1, insert test.
			SpatialEvent theEvent = new SpatialEvent();
			theEvent.setTitle("Example event");
			theEvent.setDate(new Date());
			theEvent.setLocation(factory.createPoint(new Coordinate(26, 62)));
			theEvent.getLocation().setSRID(4326);
			repo.save(theEvent);

			SpatialEvent eventWithPath = new SpatialEvent();
			Coordinate[] coords = new Coordinate[] { new Coordinate(22, 60), new Coordinate(23, 61), new Coordinate(22, 63) };
			eventWithPath.setRoute(factory.createLineString(coords));
			eventWithPath.getRoute().setSRID(4326);
			eventWithPath.setLocation(factory.createPoint(new Coordinate(22, 60)));
			eventWithPath.getLocation().setSRID(4326);
			eventWithPath.setDate(new Date());
			eventWithPath.setTitle("MTB cup 1/10");
			repo.save(eventWithPath);

			//2, query
			testPredicateQuery(repo);

			repo.selectAll();

			repo.selectTest(0, 0);

		};
	}

	private Predicate createPredicate() {
		boolean onlyInViewport = false;
		String titleContains = "e";
		QSpatialEvent qspatialEvent = QSpatialEvent.spatialEvent;
		BooleanExpression intersection = null;
		BooleanExpression predicate = null;
		if (onlyInViewport) {
//	            Polygon polygon = toPolygon(map.getBounds());
//	            intersection = qspatialEvent.location.intersects(polygon);
		}
		BooleanExpression title = null;
		if (StringUtils.isNotBlank(titleContains)) {
			title = qspatialEvent.title.containsIgnoreCase(titleContains);
		}
		if (intersection != null && title != null) {
			predicate = title.and(intersection);
		} else if (intersection != null) {
			predicate = intersection;
		} else if (title != null) {
			predicate = title;
		}
		return predicate;
	}

	private void testPredicateQuery(SpatialEventRepository repo) {
		Predicate predicate = createPredicate();
		List<SpatialEvent> events = null;
		if (predicate != null) {
			events = repo.findAll(predicate);
		} else {
			events = repo.findAll();
		}
		for (final SpatialEvent spatialEvent : events) {
			System.out.println(spatialEvent);
		}

	}
}
