package voyage.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.dal.HostsDao;
import voyage.dal.ListingsDao;
import voyage.dal.PriceCalendarDao;
import voyage.model.Hosts;
import voyage.model.Listings;
import voyage.model.ListingsWithMinPrice;

/**
 * Servlet implementation class HostListings
 */
@WebServlet("/hostlistings")
public class HostListings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected ListingsDao listingsDao;
	protected HostsDao hostsDao;
	protected PriceCalendarDao priceCalendarDao;

	@Override
	public void init() throws ServletException {
		listingsDao = ListingsDao.getInstance();
		hostsDao = HostsDao.getInstance();
		priceCalendarDao = PriceCalendarDao.getInstance();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO get listings by host, see UserBlogPosts
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String hostIdStr = request.getParameter("hostid");
        long hostId;
        try {
        	hostId = Long.parseLong(hostIdStr);
        } catch (NumberFormatException e) {
        	hostId = -1;
        }
        
        if (hostId == -1) {
            messages.put("title", "Invalid hostid.");
        } else {
        	Hosts host;
			try {
				host = hostsDao.getHostsByHostId(hostId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	if (host == null) {
        		messages.put("title", "No record for Host ID: " + hostId);
        	} else {
        		messages.put("title", "Listings for " + host.getName());
        	}
        	
        }
        
        // Retrieve Listings, and store in the request.
        List<ListingsWithMinPrice> listingsWithMinPrices = new ArrayList<>();
        try {
        	List<Listings> listings = listingsDao.getListingsByHostId(hostId);
        	for (Listings lst : listings) {
				double minPrice = priceCalendarDao.getMinPCByListingId(lst.getListingId());
				ListingsWithMinPrice lstWithMP = new ListingsWithMinPrice(lst, minPrice);
				listingsWithMinPrices.add(lstWithMP);
			}
        } catch (SQLException e) {
			e.printStackTrace();
			
        }
        request.setAttribute("listingsWithMinPrices", listingsWithMinPrices);
        request.getRequestDispatcher("/HostListings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 
	}

}
