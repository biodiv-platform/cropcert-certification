package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.Advice;

public class AdviceDao extends AbstractDao<Advice, Long> {

	private static final Logger logger = LoggerFactory.getLogger(AdviceDao.class);

	@Inject
	protected AdviceDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Advice findById(Long id) {
		Session session = sessionFactory.openSession();
		Advice entity = null;
		try {
			entity = session.get(Advice.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}
}
