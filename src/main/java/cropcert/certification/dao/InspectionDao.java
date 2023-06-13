package cropcert.certification.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import cropcert.certification.pojo.Inspection;

public class InspectionDao extends AbstractDao<Inspection, Long> {

	private static final Logger logger = LoggerFactory.getLogger(InspectionDao.class);

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
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Inspection> getResultSet(Integer limit, Integer offset, Query query) {

		Session session = sessionFactory.openSession();
		List<Inspection> resultList = new ArrayList<>();
		try {
			if (limit > 0 && offset >= 0)
				query = query.setFirstResult(offset).setMaxResults(limit);
			resultList = query.getResultList();

		} catch (NoResultException e) {
			logger.error(e.getMessage());
		}
		session.close();
		return resultList;

	}

	@SuppressWarnings("rawtypes")
	public List<Inspection> getReportsForInspector(Integer limit, Integer offset, Long inspectorId, Long farmerId) {

		String queryStr = "from " + daoType.getSimpleName() + " t where "
				+ (inspectorId == -1 ? "" : "t.inspectorId =  :inspectorId and ")
				+ (farmerId == -1 ? "" : "t.farmerId = :farmerId ") + "order by id";

		try (Session session = sessionFactory.openSession()) {
			org.hibernate.query.Query query = session.createQuery(queryStr);
			if (inspectorId == -1)
				query.setParameter("inspectorId", inspectorId);
			if (farmerId == -1)
				query.setParameter("farmerId", farmerId);

			return getResultSet(limit, offset, query);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<Inspection> getLatestReportForFarmers(Integer limit, Integer offset, List<Long> farmerIds) {

		StringBuilder farmerIdsString = new StringBuilder("(");
		for (Long farmerId : farmerIds) {
			farmerIdsString.append(farmerId).append(",");
		}
		farmerIdsString.setLength(farmerIdsString.length() - 1);

		farmerIdsString.append("-1)");

		String queryStr = "select * from " + daoType.getSimpleName() + " t " + " where farmer_id in " + farmerIdsString
				+ " and " + " date = (select max(date) from inspection i where i.farmer_id = t.farmer_id)";

		try (Session session = sessionFactory.openSession()) {
			org.hibernate.query.Query query = session.createNativeQuery(queryStr, Inspection.class);
			return getResultSet(limit, offset, query);
		}
	}
}
