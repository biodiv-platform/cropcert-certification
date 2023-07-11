package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.Animal;

public class AnimalDao extends AbstractDao<Animal, Long> {

	private static final Logger logger = LoggerFactory.getLogger(AnimalDao.class);

	@Inject
	protected AnimalDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Animal findById(Long id) {
		Session session = sessionFactory.openSession();
		Animal entity = null;
		try {
			entity = session.get(Animal.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}
}
