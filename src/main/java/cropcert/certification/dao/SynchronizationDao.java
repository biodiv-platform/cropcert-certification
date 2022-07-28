package cropcert.certification.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;

import cropcert.certification.pojo.Synchronization;

public class SynchronizationDao extends AbstractDao<Synchronization, Long> {

	@Inject
	protected SynchronizationDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Synchronization findById(Long id) {
		Session session = sessionFactory.openSession();
		Synchronization entity = null;
		try {
			entity = session.get(Synchronization.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Synchronization getCurrentPartialReport(Long inspectorId, Long farmerId) {

		String queryStr = "from " + daoType.getSimpleName() + " t "
				+ " where farmerId = :farmerId and updatedBy = :inspectorId "
				+ "and isReportFinalized = false and isDeleted = false";

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryStr);
		query.setParameter("inspectorId", inspectorId);
		query.setParameter("farmerId", farmerId);

		List<Synchronization> resultList = new ArrayList<Synchronization>();
		try {
			resultList = query.getResultList();
		} catch (NoResultException e) {
			throw e;
		}
		session.close();

		if (resultList == null || resultList.size() == 0)
			return null;

		return resultList.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Synchronization> getSynchronizationForFarmers(Integer limit, Integer offset,
			Set<Long> farmerIds) {
		String farmerIdsString = "(";
		for (Long farmerId : farmerIds) {
			farmerIdsString += farmerId + ",";
		}
		farmerIdsString += "-1)";

		String queryStr = "select distinct on(farmer_id) * from " + daoType.getSimpleName() + " where farmer_id in "
				+ farmerIdsString + " order by farmer_id, version desc, sub_version desc";
		
		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createNativeQuery(queryStr, Synchronization.class);

		List<Synchronization> resultList = new ArrayList<Synchronization>();
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

	@SuppressWarnings("rawtypes")
	public Synchronization getReport(Integer version, Integer subVersion, Long farmerId) {

		String queryStr = " from " + daoType.getSimpleName() + " t "
				+ " where farmerId = :farmerId and version = :version and subVersion = :subVersion";

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryStr, Synchronization.class);
		query.setParameter("farmerId", farmerId);
		query.setParameter("version", version);
		query.setParameter("subVersion", subVersion);
		try {
			Synchronization result = (Synchronization) query.getSingleResult();
			return result;
		} catch (NoResultException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Synchronization> getRecentSubversionEntry(Integer version, Long farmerId) {

		String queryStr = "select * from " + daoType.getSimpleName() + " t " + " where farmer_id = :farmerId "
				+ " and  sub_version > 0 and " + " version = "
				+ (version == -1 ? "(select max(version) from synchronization s where s.farmer_id = t.farmer_id)"
						: version)
				+ " order by sub_version";

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createNativeQuery(queryStr, Synchronization.class);

		query.setParameter("farmerId", farmerId);

		List<Synchronization> resultList = new ArrayList<Synchronization>();
		try {
			resultList = query.getResultList();

		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return resultList;
	}
}
