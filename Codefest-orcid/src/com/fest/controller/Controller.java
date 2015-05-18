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
		response.setContentType("application/json");
		OrcidSearchResults searchRes = (OrcidSearchResults) session.getAttribute("searchRes");
        final String param = request.getParameter("term");
        final List<OrcidDesc> result = new ArrayList<OrcidDesc>();
        for(OrcidSearchResult res : searchRes.getOrcidSearchResult()) {
            if (res.getOrcidProfile().retrieveOrcidPath().startsWith(param)) {
                result.add(new OrcidDesc(res.getOrcidProfile().retrieveOrcidPath().toString(), res.getOrcidProfile().toString()));
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
