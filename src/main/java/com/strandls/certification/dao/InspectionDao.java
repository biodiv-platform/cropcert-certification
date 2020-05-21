package com.strandls.certification.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.inject.Inject;
import com.strandls.certification.pojo.Inspection;

public class InspectionDao extends AbstractDao<Inspection, Long> {

	@Inject
	protected InspectionDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Inspection findById(Long id) {
		Session session = sessionFactory.openSession();
		Inspection entity = null;
		try {
			entity = session.get(Inspection.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public List<Inspection> getResultSet(Integer limit, Integer offset, Query query) {

		Session session = sessionFactory.openSession();
		List<Inspection> resultList = new ArrayList<Inspection>();
		try {
			if (limit > 0 && offset >= 0)
				query = query.setFirstResult(offset).setMaxResults(limit);
			resultList = query.getResultList();

		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return resultList;

	}

	public List<Inspection> getReportsForInspector(Integer limit, Integer offset, Long inspectorId, Long farmerId) {

		String queryStr = "from " + daoType.getSimpleName() + " t where "
				+ (inspectorId == -1 ? "" : "t.inspectorId =  :inspectorId and ") + 
				(farmerId == -1 ? "" : "t.farmerId = :farmerId ")
				+ "order by id";

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createQuery(queryStr);
		if (inspectorId == -1)
			query.setParameter("inspectorId", inspectorId);
		if (farmerId == -1)
			query.setParameter("farmerId", farmerId);

		return getResultSet(limit, offset, query);
	}

	public List<Inspection> getReportsForCollectionCenter(Integer limit, Integer offset, Long ccCode, List<Long> farmerIds) {

		
		
		String queryStr = "from " + daoType.getSimpleName() + " t where farmerId in :farmerIds "
				+ "order by id";

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createQuery(queryStr);
		query.setParameterList("farmerIds", farmerIds);

		return getResultSet(limit, offset, query);
	}
}
