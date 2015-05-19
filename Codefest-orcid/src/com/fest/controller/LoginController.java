package com.fest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.OrcidSearchResults;

import com.fest.dao.SourceDao;
import com.fest.dao.UserDao;
import com.fest.utils.Constants;
import com.fest.utils.PropertiesUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrcidSearchResults searchRes = null;
		String orcid = request.getParameter("orcid");
		HttpSession session = request.getSession();
		List<String> myDoiList = null;
		if(orcid != null && !"".equals(orcid)) {
			StringBuffer searchAPI = new StringBuffer(Constants.BASE_URI).append(orcid).append("/orcid-works/");
			try {
				UserDao userDao = new UserDao();
				OrcidMessage profile = userDao.getUserData(searchAPI.toString());
				session.setAttribute("loggedUser", profile);
				myDoiList = userDao.getUserDoiList(profile);
			} catch (Exception e1) {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			searchAPI = createSearchAPI(myDoiList);
			;
			try {
					searchRes = new SourceDao().getSource(searchAPI.toString()).getOrcidSearchResults();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("searchRes", searchRes);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

	private StringBuffer createSearchAPI(List<String> myDoiList) {
		StringBuffer searchAPI = new StringBuffer();
		if(myDoiList != null) {
			int length = myDoiList.size();
			int count = 0;
			for(String myDoi : myDoiList) {
				searchAPI = new StringBuffer(Constants.BASE_URI).append("search/orcid-bio/?q=digital-object-ids:(").append(myDoi);
				if(count != length - 1) {
					searchAPI.append(",");
				} else {
					searchAPI.append(")");
				}
				count ++;
			}
		}
		return searchAPI;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
