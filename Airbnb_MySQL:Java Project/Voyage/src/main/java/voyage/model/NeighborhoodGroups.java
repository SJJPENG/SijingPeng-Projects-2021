package voyage.model;

public class NeighborhoodGroups {
	 protected int neighborhoodGroupId;
	 protected String name;
	 protected Cities city;
	 
	 public NeighborhoodGroups(int neighborhoodGroupId, String name, Cities city) {
		    this.neighborhoodGroupId = neighborhoodGroupId;
		    this.name = name;
		    this.city = city;
		  }
	 
	 public int getNeighborhoodGroupId() {
		    return neighborhoodGroupId;
		  }

	 public void setNeighborhoodGroupId(int neighborhoodGroupId) {
		    this.neighborhoodGroupId = neighborhoodGroupId;
		  }
		  
	 public String getName() {
		    return name;
		  }
	 
	 public void setName(String name) {
		    this.name = name;
		  }
	  
	  public Cities getCity() {
		    return city;
		  }

	  public void setCity(Cities city) {
		    this.city = city;
		  }
		  
	  @Override
	  public String toString() {
	    return "NeighborhoodGroups{" +
	        "neighborhoodGroupId=" + neighborhoodGroupId +
	        ", name='" + name + '\'' +
	        ", cityId=" + city +
	        '}';
	  }

}
