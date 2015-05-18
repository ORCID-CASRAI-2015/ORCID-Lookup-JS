package com.fest.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.orcid.jaxb.model.message.OrcidSearchResults;

import com.fest.dao.SourceDao;
import com.fest.utils.Constants;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrcidSearchResults searchRes = null;
		String myDoi = "123123"; // Belongs to another 2 users in sandbox.
		StringBuffer searchAPI = new StringBuffer(Constants.BASE_URI).append("search/orcid-bio/?q=digital-object-ids:").append(myDoi);
		HttpSession session = request.getSession();
		try {
				searchRes = new SourceDao().getSource(searchAPI.toString()).getOrcidSearchResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("searchRes", searchRes);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
