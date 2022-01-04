package voyage.tools;

import voyage.dal.*;
import voyage.model.*;
import voyage.model.Listings.RoomType;

import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions: 1. Create a new MySQL schema and then run the CREATE TABLE
 * statements from lecture: http://goo.gl/86a11H. 2. Update ConnectionManager
 * with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		HostsDao hostsDao = HostsDao.getInstance();
		PropertyTypesDao propertyTypesDao = PropertyTypesDao.getInstance();

		CitiesDao citiesDao = CitiesDao.getInstance();
		NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
		NeighborhoodGroupsDao neighborhoodGroupsDao = NeighborhoodGroupsDao.getInstance();

		UsersDao usersDao = UsersDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		LikesDao likesDao = LikesDao.getInstance();
		DislikesDao dislikesDao = DislikesDao.getInstance();

		ListingsDao listingsDao = ListingsDao.getInstance();
		
		AmenitiesDao amenitiesDao = AmenitiesDao.getInstance();
		ListingAmenityAssociationDao listingAmenityAssociationDao = 
				ListingAmenityAssociationDao.getInstance();
		PriceCalendarDao priceCalendarDao = PriceCalendarDao.getInstance();

		// INSERT objects from our model.
		Hosts host = new Hosts(1, "a", "a", "a", "a", "a", "a");
		host = hostsDao.create(host);
		Hosts host2 = new Hosts(2, "b", "b", "b", "b", "b", "b");
		host2 = hostsDao.create(host2);
		Hosts host3 = new Hosts(3, "c", "c", "c", "c", "c", "c");
		host3 = hostsDao.create(host3);

		PropertyTypes pro1 = new PropertyTypes(1, "Boat");
		pro1 = propertyTypesDao.create(pro1);
		PropertyTypes pro2 = new PropertyTypes(2, "Entire guestHouse");
		pro2 = propertyTypesDao.create(pro2);
		PropertyTypes pro3 = new PropertyTypes(3, "Entire cabin");
		pro3 = propertyTypesDao.create(pro3);

		Cities city1 = new Cities(1, "Seattle", "Washington", "United States");
		city1 = citiesDao.create(city1);

		NeighborhoodGroups neighborhoodGroup1 = new NeighborhoodGroups(1, "Ballard", city1);
		neighborhoodGroup1 = neighborhoodGroupsDao.create(neighborhoodGroup1);
		NeighborhoodGroups neighborhoodGroup2 = new NeighborhoodGroups(6, "Beacon Hill", city1);
		neighborhoodGroup2 = neighborhoodGroupsDao.create(neighborhoodGroup2);

		Neighborhoods neighborhood1 = new Neighborhoods(1, "Adams", neighborhoodGroup1);
		neighborhood1 = neighborhoodsDao.create(neighborhood1);
		Neighborhoods neighborhood2 = new Neighborhoods(2, "Loyal Heights", neighborhoodGroup2);
		neighborhood2 = neighborhoodsDao.create(neighborhood2);

		Users user1 = new Users("hzuo", "hzuo@123.com", "abc000", "Huizhong", "Zuo");
		user1 = usersDao.create(user1);
		Users user2 = new Users("emma", "emma@gmail,com", "123456", "Emma", "Chen");
		user2 = usersDao.create(user2);
		

		// listings
		// listings creation need to be put at the end of the insert as it have FK.
		Listings listings1 = new Listings(1, "Casa Madrona 1 House", "https://www.airbnb.com/rooms/2318",
				"Gorgeous, architect remodeled", "Madrona is a hidden", "https://a0.muscache.com", host, neighborhood1,
				47.00, -122.38663, pro1, RoomType.ENTIRE, 9, "2.5 baths", 4, 1, 7, 1125, 4.7, true, city1);
		listingsDao.create(listings1);
		Listings listings2 = new Listings(2, "Casa Madrona 2 House", "https://www.airbnb.com/rooms/2318",
				"Gorgeous, architect remodeled", "Madrona is a hidden", "https://a0.muscache.com", host2, neighborhood2,
				47.00, -122.38663, pro2, RoomType.HOTEL, 9, "2.5 baths", 4, 1, 7, 1125, 4.7, true, city1);
		listingsDao.create(listings2);
		
		
		Amenities amen1 = new Amenities(1, "Coffee Maker");
		amen1 = amenitiesDao.create(amen1);
		Amenities amen2 = new Amenities(2, "Cable TV");
		amen2 = amenitiesDao.create(amen2);
		Amenities amen3 = new Amenities(3, "Cable TV");
		amen3 = amenitiesDao.create(amen3);
		
		ListingAmenityAssociation asso1 = new ListingAmenityAssociation(1, listings1, amen1);
		asso1 = listingAmenityAssociationDao.create(asso1);
		ListingAmenityAssociation asso2 = new ListingAmenityAssociation(2, listings1, amen2);
		asso2 = listingAmenityAssociationDao.create(asso2);
		ListingAmenityAssociation asso3 = new ListingAmenityAssociation(3, listings2, amen2);
		asso3 = listingAmenityAssociationDao.create(asso3);
		
		String strDate = "2020-03-31";  
		Date date1 = Date.valueOf(strDate);
		String strDate2 = "2021-01-01";  
		Date date2 = Date.valueOf(strDate2);
		
		PriceCalendar pc1 = new PriceCalendar(1, listings1, date1, 100.1);
		pc1 = priceCalendarDao.create(pc1);
		
		PriceCalendar pc2 = new PriceCalendar(2, listings1, date2, 200.0);
		pc2 = priceCalendarDao.create(pc2);
		
		Reviews review1 = new Reviews(listings1, user1, date1, "highly recommend!", 10.0);
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(listings2, user2, date2, "nice host", 9.5);
		review2 = reviewsDao.create(review2);
		
		Likes like1 = new Likes(user1, listings1);
		like1 = likesDao.create(like1);
		Likes like2 = new Likes(user2, listings2);
		like2 = likesDao.create(like2);
		
		Dislikes dislike1 = new Dislikes(user1, listings2);
		dislike1 = dislikesDao.create(dislike1);
		Dislikes dislike2 = new Dislikes(user2, listings1);
		dislike2 = dislikesDao.create(dislike2);
		
	

		// READ.

		Hosts host1 = hostsDao.getHostsByHostId(2);
		System.out.print(host1 + "\n");
		// System.out.format(" host: h:%d hu:%s name:%s loc:%s about: %s tu:%s pu:%s
		// \n",
		// host1.getHostId(),host1.getHostURL(),host1.getName(),host1.getLocation(),
		// host1.getAbout(),host1.getThumbnailURL(),host1.getPictureURL());

		Hosts h2 = hostsDao.getHostsByHostsName("b");
		System.out.print(h2 + "\n");
		// System.out.format(" host: h:%d hu:%s name:%s loc:%s about: %s tu:%s pu:%s
		// \n",
		// h2.getHostId(),h2.getHostURL(),h2.getName(),h2.getLocation(),
		// h2.getAbout(),h2.getThumbnailURL(),h2.getPictureURL());

		PropertyTypes p1 = propertyTypesDao.getPropertyTypesByPropertyTypeId(1);
		//
		//
		// System.out.format(" propertyType: id:%d type:%s \n",
		// p1.getPropertyId(),p1.getPropertyType());
		System.out.print(p1 + "\n");

		// cities
		Cities c1 = citiesDao.getCitiesByCityId(1);
		List<Cities> cList1 = citiesDao.getCitiesByName("Seattle");
		System.out.format("Reading city: u:%d n:%s s:%s cty:%s \n", c1.getCityId(), c1.getName(), c1.getState(),
				c1.getCountry());
		for (Cities c : cList1) {
			System.out.format("Looping cities: u:%d n:%s s:%s cty:%s \n", c.getCityId(), c.getName(), c.getState(),
					c.getCountry());
		}

		NeighborhoodGroups ng1 = neighborhoodGroupsDao.getNeighborhoodGroupsByNeighborhoodGroupsId(1);
		List<NeighborhoodGroups> ngList1 = neighborhoodGroupsDao.getNeighborhoodGroupsByName("Adams");
		System.out.format("Reading neighborhoodgroup: nbh:%d n:%s c:%s \n", ng1.getNeighborhoodGroupId(), ng1.getName(),
				ng1.getCity().getName());
		for (NeighborhoodGroups ng : ngList1) {
			System.out.format("Looping neighborhoodgroup: nbh:%d n:%s c:%s \n", ng.getNeighborhoodGroupId(),
					ng.getName(), ng.getCity().getName());
		}

		Neighborhoods n1 = neighborhoodsDao.getNeighborhoodsByNeighborhoodId(1);
		List<Neighborhoods> nList1 = neighborhoodsDao.getNeighborhoodsByName("Adams");
		System.out.format("Reading neighborhood: nbh:%d n:%s ng:%d ", n1.getNeighborhoodId(), n1.getName(),
				n1.getNeighborhoodGroupId());
		for (Neighborhoods n : nList1) {
			System.out.format("Looping neighborhoods: nbh:%d n:%s ng:%d \n", n.getNeighborhoodId(), n.getName(),
					n.getNeighborhoodGroupId());
		}

		Users user = usersDao.getUsersByUserName("emma");
		System.out.println(user);

		Listings ls1 = listingsDao.getListingByListingId(1);
		System.out.format("Reading %s", ls1.toString());
		
		
		List<Listings> lList1 = listingsDao.getListingsByNameKeywords("House");
		
		for (Listings ls : lList1) {
			System.out.format("Looping %s", ls.toString());
		}
		
		Amenities a1 = amenitiesDao.getAmenitiesById(1);
		System.out.print("\n");
		System.out.print("Print a1: "+a1+"\n");
		Amenities a2 = amenitiesDao.getAmenitiesById(2);
		System.out.print("Print a2: "+a2+"\n");
		
		
		List<Amenities> amenList = amenitiesDao.getAmenitiesByType("Cable TV");
		
		for (Amenities amen : amenList) {
			System.out.print("Amen with Cable TV: "+amen+"\n");
		}
		
		ListingAmenityAssociation as1 = listingAmenityAssociationDao.getAmenityAssoById(1);
		System.out.print("Associate where assoid = 1: "+a1+"\n");
		
		List<ListingAmenityAssociation> asList = listingAmenityAssociationDao.getAmenityAssoByListingId(1);
		
		for (ListingAmenityAssociation as : asList) {
			System.out.print("Associate where listingid = 1: "+as+"\n");
		}
		
		List<ListingAmenityAssociation> asList2 = listingAmenityAssociationDao.getAmenityAssoByAmenityId(1);
		
		for (ListingAmenityAssociation as : asList2) {
			System.out.print("Associate where amenityid = 1: "+as+"\n");
		}
		
		PriceCalendar pc01 = priceCalendarDao.getPCById(1);
		System.out.print("Print pc1: "+pc01+"\n");
		PriceCalendar pc02 = priceCalendarDao.getPCById(2);
		System.out.print("Print pc2: "+pc02+"\n");
		
		
		List<PriceCalendar> pcList = priceCalendarDao.getPCByListingId(1);
		
		for (PriceCalendar pc : pcList) {
			System.out.print("Price Calendar of listing1: "+pc+"\n");
		}
		
		List<Reviews> listing1Reviews = reviewsDao.getReviewsByListing(listings1);
		for (Reviews review : listing1Reviews) {
			System.out.println(review);
		}
		List<Reviews> user1Reviews = reviewsDao.getReviewsByUser(user1);
		for (Reviews review : user1Reviews) {
			System.out.println(review);
		}
		
		List<Likes> user1Likes = likesDao.getLikesByUser(user1);
		for (Likes like : user1Likes) {
			System.out.println(like);
		}
		List<Likes> listing1Likes = likesDao.getLikesByListing(listings1);
		for (Likes like : listing1Likes) {
			System.out.println(like);
		}
		
		List<Dislikes> user1Disikes = dislikesDao.getDislikesByUser(user1);
		for (Dislikes dislike : user1Disikes) {
			System.out.println(dislike);
		}
		List<Dislikes> listing1Dislikes = dislikesDao.getDislikesByListing(listings1);
		for (Dislikes dislike : listing1Dislikes) {
			System.out.println(dislike);
		}
		

		// update
		usersDao.updateLastName(user2, "Kim");

		// delete

		Listings ls11 = listingsDao.delete(ls1);
		System.out.print(ls11 + "\n");

		Hosts ho1 = hostsDao.delete(host);
		System.out.print(ho1 + "\n");

		PropertyTypes proper1 = propertyTypesDao.delete(pro3);
		System.out.print(proper1 + "\n");

		citiesDao.delete(city1);
		neighborhoodsDao.delete(neighborhood2);
		neighborhoodGroupsDao.delete(neighborhoodGroup1);

		usersDao.delete(user);
		
		amenitiesDao.delete(amen1);
		amenitiesDao.delete(amen2);
		amenitiesDao.delete(amen3);
		
		listingAmenityAssociationDao.delete(asso1);
		listingAmenityAssociationDao.delete(asso2);
		listingAmenityAssociationDao.delete(asso3);
		
		priceCalendarDao.delete(pc1);
		priceCalendarDao.delete(pc2);
		
		reviewsDao.delete(review1);
		reviewsDao.delete(review2);
		
		likesDao.delete(like1);
		dislikesDao.delete(dislike1);

	}
}
