package com.fest.dao;

import org.orcid.jaxb.model.message.OrcidMessage;

import com.fest.utils.HttpUtil;

public class SourceDao {
	
	public OrcidMessage getSource(String searchAPI) throws Exception {
		return HttpUtil.runHttpGet(searchAPI);
	}
}
