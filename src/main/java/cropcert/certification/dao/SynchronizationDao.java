package cropcert.certification.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import cropcert.certification.pojo.Synchronization;

public class SynchronizationDao extends AbstractDao<Synchronization, Long> {

	private static final Logger logger = LoggerFactory.getLogger(SynchronizationDao.class);

	private static final String FARMER_ID = "farmerId";

	private static final String TABLE_NAME = "TABLE_NAME";

	private String table = daoType.getSimpleName();

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
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Synchronization getCurrentPartialReport(Long inspectorId, Long farmerId) {

		String queryStr = "from  TABLE_NAME t " + " where farmerId = :farmerId and updatedBy = :inspectorId "
				+ "and isReportFinalized = false and isDeleted = false";

		queryStr = queryStr.replace(TABLE_NAME, table);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryStr);
		query.setParameter("inspectorId", inspectorId);
		query.setParameter(FARMER_ID, farmerId);

		List<Synchronization> resultList = new ArrayList<>();
		try {
			resultList = query.getResultList();
		} catch (NoResultException e) {
			logger.error(e.getMessage());
		}
		session.close();

		if (resultList == null || resultList.isEmpty())
			return null;

		return resultList.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Synchronization> getSynchronizationForFarmers(Integer limit, Integer offset, Set<Long> farmerIds) {
		StringBuilder farmerIdsStringBuilder = new StringBuilder("(");
		for (Long farmerId : farmerIds) {
			farmerIdsStringBuilder.append(farmerId).append(",");
		}
		farmerIdsStringBuilder.append("-1)");
		String farmerIdsString = farmerIdsStringBuilder.toString();

		String queryStr = "select distinct on(farmer_id) * from " + " TABLE_NAME " + " where farmer_id in "
				+ farmerIdsString + " order by farmer_id, version desc, sub_version desc";

		queryStr = queryStr.replace("farmerIdsString", farmerIdsString);
		queryStr = queryStr.replace(TABLE_NAME, table);

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createNativeQuery(queryStr, Synchronization.class);

		List<Synchronization> resultList = new ArrayList<>();
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

	public Synchronization getReport(Integer version, Integer subVersion, Long farmerId) {

		String queryStr = "from  TABLE_NAME t "
				+ " where farmerId = :farmerId and version = :version and subVersion = :subVersion";
		queryStr = queryStr.replace(TABLE_NAME, table);

		try (Session session = sessionFactory.openSession()) {
			Query<Synchronization> query = session.createQuery(queryStr, Synchronization.class);
			query.setParameter(FARMER_ID, farmerId);
			query.setParameter("version", version);
			query.setParameter("subVersion", subVersion);
			return query.getSingleResult();

		} catch (NoResultException e) {
			logger.error(e.getMessage());

		}
		return null;
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

		query.setParameter(FARMER_ID, farmerId);

		List<Synchronization> resultList = new ArrayList<>();
		try {
			resultList = query.getResultList();

		} catch (NoResultException e) {
			logger.error(e.getMessage());
		}
		session.close();
		return resultList;
	}
}
