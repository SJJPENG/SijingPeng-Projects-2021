package voyage.model;

import java.sql.Date;  

public class PriceCalendar {
	
	protected int PriceCalendarId;
	protected Listings listings;
	protected Date Date;
	protected double Price;
	
	
	public PriceCalendar(int priceCalendarId, Listings listings, java.sql.Date date, double price) {
		this.PriceCalendarId = priceCalendarId;
		this.listings = listings;
		this.Date = date;
		this.Price = price;
	}


	public int getPriceCalendarId() {
		return PriceCalendarId;
	}

	public PriceCalendar(Listings listings, java.sql.Date date, double price) {
		this.listings = listings;
		this.Date = date;
		this.Price = price;
	}


	public Listings getListings() {
		return listings;
	}


	public void setListings(Listings listings) {
		this.listings = listings;
	}


	public Date getDate() {
		return Date;
	}


	public void setDate(Date date) {
		this.Date = date;
	}


	public double getPrice() {
		return Price;
	}


	public void setPrice(double price) {
		this.Price = price;
	}


	@Override
	public String toString() {
		return "PriceCalendar [PriceCalendarId=" + PriceCalendarId + ", listings=" + listings + ", Date=" + Date
				+ ", Price=" + Price + "]";
	}
}
