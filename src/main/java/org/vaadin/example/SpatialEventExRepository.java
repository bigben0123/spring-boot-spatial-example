package org.vaadin.example;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SpatialEventExRepository  {
	public int selectTest(double lat, double lon) ;

	int selectAll();

}
