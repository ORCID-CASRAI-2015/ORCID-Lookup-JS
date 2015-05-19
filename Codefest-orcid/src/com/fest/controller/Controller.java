package com.fest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.orcid.jaxb.model.message.OrcidSearchResult;
import org.orcid.jaxb.model.message.OrcidSearchResults;

import com.fest.model.OrcidDesc;
import com.fest.utils.PropertiesUtil;
import com.google.gson.Gson;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String loginOrcid = new PropertiesUtil().loadPropertiesFile("db.properties").getProperty("login-orcid");
		response.setContentType("application/json");
		OrcidSearchResults searchRes = (OrcidSearchResults) session.getAttribute("searchRes");
        final String param = request.getParameter("term");
        final List<OrcidDesc> result = new ArrayList<OrcidDesc>();
        for(OrcidSearchResult res : searchRes.getOrcidSearchResult()) {
        	StringBuffer label = new StringBuffer(res.getOrcidProfile().retrieveOrcidPath()).append(" # ")
        			.append(res.getOrcidProfile().getOrcidBio().getPersonalDetails().getGivenNames().getContent())
        			.append(" # ").append(res.getOrcidProfile().getOrcidBio().getContactDetails().getAddress().getCountry().getValue());
            if (label.toString().contains(param) && !res.getOrcidProfile().retrieveOrcidPath().equals(loginOrcid)) {
            	result.add(new OrcidDesc(label.toString(), res.getOrcidProfile()));
            }
        }
        response.getWriter().write(new Gson().toJson(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
