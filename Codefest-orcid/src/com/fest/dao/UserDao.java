package com.fest.dao;

import java.util.ArrayList;
import java.util.List;

import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.message.OrcidWorks;
import org.orcid.jaxb.model.message.WorkExternalIdentifier;

import com.fest.utils.HttpUtil;

public class UserDao {

	public OrcidMessage getUserData(String searchAPI) throws Exception {
		return HttpUtil.runHttpGet(searchAPI);
	}

	public List<String> getUserDoiList(OrcidMessage messg) {
		List<String> doiList = new ArrayList<String>();
		if(messg != null && messg.getOrcidProfile() != null) {
			OrcidWorks orcWorks = messg.getOrcidProfile().getOrcidActivities().getOrcidWorks();
			if(orcWorks != null) {
				for(OrcidWork work : orcWorks.getOrcidWork()) {
					for(WorkExternalIdentifier extId : work.getWorkExternalIdentifiers().getWorkExternalIdentifier()) {
						if("doi".equals(extId.getWorkExternalIdentifierType().value())) {
							doiList.add(extId.getWorkExternalIdentifierId().getContent());
						}
					}
				}
			}
		}
		return doiList;
	}
}
