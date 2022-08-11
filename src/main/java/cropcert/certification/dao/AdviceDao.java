package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.Advice;

public class AdviceDao extends AbstractDao<Advice, Long>{

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
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}
}
