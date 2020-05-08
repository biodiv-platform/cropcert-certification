package com.strandls.certification.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class DaoModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(InspectionDao.class).in(Scopes.SINGLETON);
	}
}
