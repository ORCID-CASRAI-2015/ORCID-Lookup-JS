package com.fest.model;

import java.util.ArrayList;
import java.util.List;

import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.message.ResearcherUrl;

import com.fest.dao.UserDao;
import com.fest.utils.Constants;
import com.fest.utils.StringUtils;


public class OrcidDesc {

	private String label;
	private OrcidProfile value;
	private String xml;
	private String otherNames;
	private String keywords;
	private String urls;
	private String works;
	
	public OrcidDesc(String label, OrcidProfile value) {
		this.label = label;
		this.value = value;
		this.xml = value.toString();
		this.otherNames = StringUtils.convertListToStringCS(value.getOrcidBio().getPersonalDetails().getOtherNames().getOtherNamesAsStrings());
		this.keywords = StringUtils.convertListToStringCS(value.getOrcidBio().getKeywords().getKeywordsAsStrings());
		this.urls = getUrlAsString(value);
		StringBuffer searchAPI = new StringBuffer(Constants.BASE_URI).append(value.retrieveOrcidPath()).append("/orcid-works/");
		try {
			this.setWorks(extractUserWorks(new UserDao().getUserData(searchAPI.toString())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String extractUserWorks(OrcidMessage userData) {
		int i = 1;
		List<String> listWorks = new ArrayList<String>();
		for(OrcidWork work : userData.getOrcidProfile().getOrcidActivities().getOrcidWorks().getOrcidWork()) {
			StringBuffer workBuf = new StringBuffer(String.valueOf(i)).append(".\n #Title :").append(work.getWorkTitle().getTitle().getContent()).append("\n #ExternalIDs :")
					.append(work.getWorkType().name()).append("#").append(work.getWorkType().value()).append("")
					.append("\n #Source :").append(work.getSource().getSourceName().getContent());
			listWorks.add(workBuf.toString());
			i ++;
		}
		return StringUtils.convertListToStringCS(listWorks);
	}

	private String getUrlAsString(OrcidProfile value) {
		StringBuffer strBuf = new StringBuffer();
		if(value.getOrcidBio().getResearcherUrls().getResearcherUrl() != null) {
			int length = value.getOrcidBio().getResearcherUrls().getResearcherUrl().size();
			int count = 0;
			for(ResearcherUrl resUrl : value.getOrcidBio().getResearcherUrls().getResearcherUrl()) {
				strBuf.append(resUrl.getUrlName().getContent()).append("-").append(resUrl.getUrl().getValue());
				if(count != length - 1) {
					strBuf.append(", ");
				}
				count ++;
			}
		}
		return strBuf.toString();
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public OrcidProfile getValue() {
		return value;
	}

	public void setValue(OrcidProfile value) {
		this.value = value;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getWorks() {
		return works;
	}

	public void setWorks(String works) {
		this.works = works;
	}

}
