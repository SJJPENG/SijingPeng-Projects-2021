package voyage.model;


public class Neighborhoods {
  protected int neighborhoodId;
  protected String name;
  protected NeighborhoodGroups neighborhoodGroup;

  public Neighborhoods(int neighborhoodId, String name, NeighborhoodGroups neighborhoodGroup) {
    this.neighborhoodId = neighborhoodId;
    this.name = name;
    this.neighborhoodGroup = neighborhoodGroup;
  }

  

public int getNeighborhoodId() {
    return neighborhoodId;
  }

  public void setNeighborhoodId(int neighborhoodId) {
    this.neighborhoodId = neighborhoodId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNeighborhoodGroupId() {
    return neighborhoodGroup.getNeighborhoodGroupId();
  }

  public void setNeighborhoodGroupId(NeighborhoodGroups neighborhoodGroupId) {
    this.neighborhoodGroup = neighborhoodGroupId;
  }
  
  @Override
  public String toString() {
    return "Neighborhoods{" +
        "neighborhoodId=" + neighborhoodId +
        ", name='" + name + '\'' +
        ", neighborhoodGroupId=" + neighborhoodGroup +
        '}';
  }
}

