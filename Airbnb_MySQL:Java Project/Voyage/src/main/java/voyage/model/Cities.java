
package voyage.model;

public class Cities {
	  protected int cityId;
	  protected String name;
	  protected String state;
	  protected String country;

	  public Cities(int cityId, String name, String state, String country) {
	    this.cityId = cityId;
	    this.name = name;
	    this.state = state;
	    this.country = country;
	  }

	  public int getCityId() {
	    return cityId;
	  }

	  public void setCityId(int cityId) {
	    this.cityId = cityId;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getState() {
	    return state;
	  }

	  public void setState(String state) {
	    this.state = state;
	  }

	  public String getCountry() {
	    return country;
	  }

	  public void setCountry(String country) {
	    this.country = country;
	  }

	  @Override
	  public String toString() {
	    return "cities{" +
	        "cityId=" + cityId +
	        ", name='" + name + '\'' +
	        ", state='" + state + '\'' +
	        ", country='" + country + '\'' +
	        '}';
	  }
}



