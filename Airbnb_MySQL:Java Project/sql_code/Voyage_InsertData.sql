USE Voyage;

# Users
INSERT INTO Users(UserName,Email,Password,FirstName,LastName)
  VALUES('user1','user1@fake.com','password1','First1','Last1');
INSERT INTO Users(UserName,Email,Password,FirstName,LastName)
  VALUES('user2','user2@fake.com','password2','First2','Last2');
  
# Hosts
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/listings.csv' 
  IGNORE INTO TABLE Hosts
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, 
  HostId, HostURL, Name, @dummy, Location, About, @dummy, @dummy, @dummy, @dummy, ThumbnailURL, PictureURL,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy);

# Cities
INSERT INTO Cities(Name,State,Country)
  VALUES('Seattle','Washington','United States');

# NeighborhoodGroups
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/neighbourhoods.csv' 
  IGNORE INTO TABLE NeighborhoodGroups
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (Name, @dummy)
  SET CityId = (SELECT CityId FROM Cities WHERE Name = 'Seattle');

# Neighborhoods
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/neighbourhoods.csv' 
  INTO TABLE Neighborhoods
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@NeighborhoodGroupName, Name)
  SET NeighborhoodGroupId = (SELECT NeighborhoodGroupId FROM NeighborhoodGroups WHERE Name = @NeighborhoodGroupName);

# PropertyTypes
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/listings.csv' 
  IGNORE INTO TABLE PropertyTypes
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @ProType, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy)
  SET PropertyType = TRIM(@ProType);

# Listings
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/listings.csv' 
  INTO TABLE Listings
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (ListingId, ListingURL, @dummy, @dummy, Name, Description, NeighborhoodOverview, PictureURL, HostId, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @NeighborhoodName, @dummy, Latitude,
  Longtitude, @ProType, RoomType, Acommodates, @dummy, BathroomsText, @Bedrooms, @Beds, @dummy, @dummy,
  MinNights, MaxNights, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy,
  @ReviewScore, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @InstantBookable, @dummy,
  @dummy, @dummy, @dummy, @dummy)
  SET NeighborhoodId = (SELECT NeighborhoodId FROM Neighborhoods WHERE Name = @NeighborhoodName),
      PropertyTypeId = (SELECT PropertyTypeId FROM PropertyTypes WHERE PropertyType = @ProType),
      Bedrooms = NULLIF(@Bedrooms,""),
      Beds = NULLIF(@Beds,""),
      ReviewScore = NULLIF(@ReviewScore,""),
      InstantBookable = IF (@InstantBookable = "t", True, False),
      CityName = "Seattle";

# Amenities
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/amenities_output.csv' 
  IGNORE INTO TABLE Amenities
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy, AmenityId, Type);

# ListingAmenityAssociation
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/amenities_output.csv' 
  INTO TABLE ListingAmenityAssociation
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (ListingId, AmenityId, @dummy);

# PriceCalendar
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/calendar.csv' 
  INTO TABLE PriceCalendar
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (ListingId, Date, @dummy, @price, @dummy, @dummy, @dummy)
  SET Price = SUBSTR(REPLACE(@price, ',',''), 2);


# AirbnbReviewers
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/reviews.csv' 
  IGNORE INTO TABLE AirbnbReviewers
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy, @dummy, @dummy, ReviewerId, ReviewerName, @dummy);

# AirbnbReviews
LOAD DATA INFILE '/Users/jia/CS5200/project/Data_Seattle_2021-09-25/reviews.csv' 
  INTO TABLE AirbnbReviews
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (ListingId, ReviewId, Date, ReviewerId, @dummy, Comments);

# Reviews
INSERT INTO Reviews(ListingId, UserName, Date, Comments, Rating)
  VALUES(2318,'user1','2021-10-12', '"I like it!"', 5);
INSERT INTO Reviews(ListingId, UserName, Date, Comments, Rating)
  VALUES(6606,'user1','2021-10-12', '"I don\'t like it!"', 1);
  
# Likes
INSERT INTO Likes(UserName,ListingId)
  VALUES('user1',2318);
INSERT INTO Likes(UserName,ListingId)
  VALUES('user1',9534);
INSERT INTO Likes(UserName,ListingId)
  VALUES('user2',6606);

# Dislikes
INSERT INTO Dislikes(UserName,ListingId)
  VALUES('user1',6606);
INSERT INTO Dislikes(UserName,ListingId)
  VALUES('user1',9419);
INSERT INTO Dislikes(UserName,ListingId)
  VALUES('user2',2318);
