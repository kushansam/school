package data;

import java.util.List;


//interface that allows classes to store data and manage access to the data between different classes
public interface DataEntity {

	String getName(); //get name of the Location


	String getDescription(); //get description of locaiton

	
	List<String> getLocations(); // returns a list of location names associated with the data entity.
	
	void setLocations(List<String> locations); // Sets the list of location names associated with the data entity.

}
