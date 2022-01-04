package voyage.model;

public class Listings {
	protected int listingId;
	protected String listingURL;
	protected String name;
	protected String description;
	protected String neighborhoodOverview;
	protected String pictureURL;
	protected Hosts hosts;
	protected Neighborhoods neighborhood;
	protected Double latitude;
	protected Double longtitude;
	protected PropertyTypes propertyType;
	protected RoomType roomType;
	protected int acommodates;
	protected String bathroomsText;
	protected int bedrooms;
	protected int beds;
	protected int minNights;
	protected int maxNights;
	protected Double reviewScore;
	protected Boolean instantBookable;
	protected Cities cities;

	public enum RoomType {
		ENTIRE("Entire Home/Apt"), HOTEL("Hotel Room"), PRIVATE("Private Room"), SHARED("Shared Room");

		private final String roomType;

		RoomType(String roomType) {
			this.roomType = roomType;
		}
		
		public String getRoomType() {
			return this.roomType;
		}
		public static RoomType getRoomTypeByValue(String value) {			
			switch (value) {
			case "Entire Home/Apt":
				return RoomType.ENTIRE;
			case "Hotel Room":
				return RoomType.HOTEL;
			case "Private Room":
				return RoomType.PRIVATE;
			case "Shared Room":
				return RoomType.SHARED;
			default:
				return null;
			}
		}
		@Override
		public String toString() {
			return this.roomType;
		}				
	}

	public Listings(int listingId, String name, String listingURL, String description, String neighborhoodOverview,
			String pictureURL, Hosts hosts, Neighborhoods neighborhood, Double latitude, Double longtitude,
			PropertyTypes propertyType, RoomType roomType, int acommodates, String bathroomsText, int bedrooms,int beds,int minNights,
			int maxNights, Double reviewScore, Boolean instantBookable, Cities cities) {

		this.listingId = listingId;
		this.name = name;
		this.listingURL = listingURL;
		this.description = description;
		this.neighborhoodOverview = neighborhoodOverview;
		this.pictureURL = pictureURL;
		this.hosts = hosts;
		this.neighborhood = neighborhood;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.propertyType = propertyType;
		this.roomType = roomType;
		this.acommodates = acommodates;
		this.bathroomsText = bathroomsText;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.minNights = minNights;
		this.maxNights = maxNights;
		this.reviewScore = reviewScore;
		this.instantBookable = instantBookable;
		this.cities = cities;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getListingURL() {
		return listingURL;
	}

	public void setListingURL(String listingURL) {
		this.listingURL = listingURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNeighborhoodOverview() {
		return neighborhoodOverview;
	}

	public void setNeighborhoodsOverview(String neighborhoodOverview) {
		this.neighborhoodOverview = neighborhoodOverview;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Hosts getHosts() {
		return hosts;
	}

	public void setHosts(Hosts host) {
		this.hosts = host;
	}

	public Neighborhoods getNeighborhoods() {
		return neighborhood;
	}

	public void setNeighborhoods(Neighborhoods neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public PropertyTypes getPropertyTypes() {
		return propertyType;
	}

	public void setPropertyTypes(PropertyTypes propertyType) {
		this.propertyType = propertyType;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getAcommodates() {
		return acommodates;
	}

	public void setAcommodates(int acommodates) {
		this.acommodates = acommodates;
	}

	public String getBathroomsText() {
		return bathroomsText;
	}

	public void setBathroomsText(String bathroomsText) {
		this.bathroomsText = bathroomsText;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public int getBeds() {
		return beds;
	}
	
	public void setBeds(int beds) {
		this.beds = beds;
		
	}
	public int getMinNights() {
		return minNights;
	}

	public void setMinNights(int minNights) {
		this.minNights = minNights;
	}

	public int getMaxNights() {
		return maxNights;
	}

	public void setMaxNights(int maxNights) {
		this.maxNights = maxNights;
	}

	public Double getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(Double reviewScore) {
		this.reviewScore = reviewScore;
	}

	public Boolean getInstantBookable() {
		return instantBookable;
	}

	public void setInstantBookable(Boolean instantBookable) {
		this.instantBookable = instantBookable;
	}

	public Cities getCities() {
		return cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}
	
	@Override
	public String toString() {		
		/*
		return String.format("Listings: id: %d u: %s n: %s d: %s ne: %s p: %s h: %s nb: %s la: %f lo: %f pr: %s rt: %s ac: %d ba: %s \n",
				this.getListingId(), 
				this.getListingURL(), 
				this.getName(), 
				this.getDescription(),
				this.getNeighborhoodOverview(), 
				this.getPictureURL(), 
				this.getHosts().getName(), 
				this.getNeighborhoods().getName(),
				this.getLatitude(), 
				this.getLongtitude(),
				this.getPropertyTypes().toString(), 
				this.getRoomType().toString(), 
				this.getAcommodates(),
				this.getBathroomsText());
		*/
		
		return String.format("Listings: id: %d u: %s n: %s d: %s ne: %s p: %s h: %s nb: %s la: %f lo: %f pr: %s rt: %s ac: %d ba: %s br: %d b: %d mi: %d ma: %d re: %f ins: %b s: %s \n",
				this.getListingId(), 
				this.getListingURL(), 
				this.getName(), 
				this.getDescription(),
				this.getNeighborhoodOverview(), 
				this.getPictureURL(), 
				this.getHosts().getName(), 
				this.getNeighborhoods().getName(),
				this.getLatitude(), 
				this.getLongtitude(), 
				this.getPropertyTypes().toString(), 
				this.getRoomType().toString(), 
				this.getAcommodates(),
				this.getBathroomsText(), 
				this.getBedrooms(), 
				this.getBeds(), 
				this.getMinNights(), 
				this.getMaxNights(),
				this.getReviewScore(), 
				this.getInstantBookable(), 
				this.getCities().toString());		
	}
}
