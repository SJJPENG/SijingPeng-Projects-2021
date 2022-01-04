CREATE SCHEMA IF NOT EXISTS Voyage;
USE Voyage;

DROP TABLE IF EXISTS Dislikes;
DROP TABLE IF EXISTS Likes;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS AirbnbReviews;
DROP TABLE IF EXISTS AirbnbReviewers;
DROP TABLE IF EXISTS PriceCalendar;
DROP TABLE IF EXISTS ListingAmenityAssociation;
DROP TABLE IF EXISTS Amenities;
DROP TABLE IF EXISTS Listings;
DROP TABLE IF EXISTS PropertyTypes;
DROP TABLE IF EXISTS Neighborhoods;
DROP TABLE IF EXISTS NeighborhoodGroups;
DROP TABLE IF EXISTS Cities;
DROP TABLE IF EXISTS Hosts;
DROP TABLE IF EXISTS Users;


CREATE TABLE Users (
  UserName VARCHAR(255),
  Email VARCHAR(255),
  Password VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);

CREATE TABLE Hosts (
  HostId BIGINT,
  HostURL VARCHAR(255),
  Name VARCHAR(255),
  Location VARCHAR(255),
  About TEXT,
  ThumbnailURL VARCHAR(255),
  PictureURL VARCHAR(255),
  CONSTRAINT pk_Hosts_HostId PRIMARY KEY (HostId)
);

CREATE TABLE Cities (
  CityId INT AUTO_INCREMENT,
  Name VARCHAR(255),
  State VARCHAR(255),
  Country VARCHAR(255),
  CONSTRAINT pk_Cities_CityId PRIMARY KEY (CityId)
);

CREATE TABLE NeighborhoodGroups (
  NeighborhoodGroupId INT AUTO_INCREMENT,
  Name VARCHAR(255),
  CityId INT,
  CONSTRAINT pk_NeighborhoodGroups_NeighborhoodGroupId PRIMARY KEY (NeighborhoodGroupId),
  CONSTRAINT uq_NeighborhoodGroups UNIQUE (Name, CityId),
  CONSTRAINT fk_NeighborhoodGroups_CityId FOREIGN KEY (CityId)
    REFERENCES Cities(CityId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Neighborhoods (
  NeighborhoodId INT AUTO_INCREMENT,
  Name VARCHAR(255),
  NeighborhoodGroupId INT,
  CONSTRAINT pk_Neighborhoods_NeighborhoodId PRIMARY KEY (NeighborhoodId),
  CONSTRAINT fk_Neighborhoods_NeighborhoodGroupId FOREIGN KEY (NeighborhoodGroupId)
     REFERENCES NeighborhoodGroups(NeighborhoodGroupId)
     ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE PropertyTypes (
  PropertyTypeId INT AUTO_INCREMENT,
  PropertyType VARCHAR(255) UNIQUE,
  CONSTRAINT pk_PropertyTypes_PropertyTypeId PRIMARY KEY (PropertyTypeId)
  );

CREATE TABLE Listings (
  ListingId INT,
  ListingURL VARCHAR(255),
  Name VARCHAR(255),
  Description TEXT,
  NeighborhoodOverview TEXT,
  PictureURL VARCHAR(255),
  HostId BIGINT,
  NeighborhoodId INT,
  Latitude DOUBLE,
  Longtitude DOUBLE,
  PropertyTypeId INT,
  RoomType ENUM ('Entire Home/Apt','Hotel Room','Private Room','Shared Room'),
  Acommodates INT,
  BathroomsText VARCHAR(255),
  Bedrooms INT,
  Beds INT,
  MinNights INT,
  MaxNights INT,
  ReviewScore DOUBLE,
  InstantBookable BOOLEAN,
  CityName VARCHAR(255),
  CONSTRAINT pk_Listings_ListingId PRIMARY KEY (ListingId),
  CONSTRAINT fk_Listings_HostId FOREIGN KEY (HostId)
    REFERENCES Hosts(HostId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Listings_NeighborhoodId FOREIGN KEY (NeighborhoodId)
    REFERENCES Neighborhoods(NeighborhoodId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Listings_PropertyTypeI FOREIGN KEY (PropertyTypeId)
    REFERENCES PropertyTypes(PropertyTypeId)
    ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE Amenities (
  AmenityId INT AUTO_INCREMENT,
  Type VARCHAR(255),
  CONSTRAINT pk_Amenities_AmenityId PRIMARY KEY (AmenityId)
);

CREATE TABLE ListingAmenityAssociation (
  AmenityAssoId INT AUTO_INCREMENT,
  ListingId INT,
  AmenityId INT,
  CONSTRAINT pk_PropertyAmenityAssociation_AmenityAssoId PRIMARY KEY (AmenityAssoId),
  CONSTRAINT fk_PropertyAmenityAssociation_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_PropertyAmenityAssociation_AmenityId FOREIGN KEY (AmenityId) 
    REFERENCES Amenities(AmenityId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE PriceCalendar (
  PriceCalendarId INT AUTO_INCREMENT,
  ListingId INT,
  Date DATE,
  Price DOUBLE,
  CONSTRAINT pk_PriceCalendar_PriceCalendarId PRIMARY KEY (PriceCalendarId),
  CONSTRAINT fk_PriceCalendar_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE AirbnbReviewers (
  ReviewerId BIGINT,
  ReviewerName VARCHAR(255),
  CONSTRAINT pk_AirbnbReviewers_ReviewerId PRIMARY KEY (ReviewerId)
);

CREATE TABLE AirbnbReviews (
  ListingId INT,
  ReviewId BIGINT,
  Date DATE,
  ReviewerId BIGINT,
  Comments TEXT,
  CONSTRAINT pk_AirbnbReviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_AirbnbReviews_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_AirbnbReviews_ReviewerId FOREIGN KEY (ReviewerId)
    REFERENCES AirbnbReviewers(ReviewerId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Reviews (
  ReviewId INT AUTO_INCREMENT,
  ListingId INT,
  UserName VARCHAR(255),
  Date DATE,
  Comments TEXT,
  Rating DOUBLE,
  CONSTRAINT pk_Review_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_Review_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Review_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Likes (
  LikeId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  ListingId INT,
  CONSTRAINT pk_Likes_LikeId PRIMARY KEY (LikeId),
  CONSTRAINT uq_Likes_LikeId UNIQUE (ListingId,UserName),
  CONSTRAINT fk_Likes_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Likes_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Dislikes (
  DislikeId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  ListingId INT,
  CONSTRAINT pk_Dislikes_DislikeId PRIMARY KEY (DislikeId),
  CONSTRAINT uq_Dislikes_DislikeId UNIQUE (ListingId,UserName),
  CONSTRAINT fk_Dislikes_ListingId FOREIGN KEY (ListingId)
    REFERENCES Listings(ListingId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Dislikes_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

