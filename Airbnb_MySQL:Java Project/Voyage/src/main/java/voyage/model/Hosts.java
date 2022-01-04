package voyage.model;


public class Hosts {
	protected long hostId;
	protected String hostURL;
	protected String name;
	protected String location;
	protected String about;
	protected String thumbnailURL;
	protected String pictureURL;


	public Hosts(long hostId, String hostURL, String name, String location,
				 String about, String thumbnailURL, String pictureURL) {
		this.hostId = hostId;
		this.hostURL = hostURL;
		this.name = name;
		this.location = location;
		this.about = about;
		this.thumbnailURL = thumbnailURL;
		this.pictureURL = pictureURL;
	}

	public long getHostId() {
		return hostId;
	}

	public void setHostId(long hostId) {
		this.hostId = hostId;
	}

	public String getHostURL() {
		return hostURL;
	}

	public void setHostURL(String hostURL) {
		this.hostURL = hostURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}

	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Override
	public String toString() {
		return "Hosts [hostId=" + hostId + ", hostURL=" + hostURL + ", name=" + name + ", location=" + location
				+ ", about=" + about + ", thumbnailURL=" + thumbnailURL + ", pictureURL=" + pictureURL + "]";
	}
	

}
