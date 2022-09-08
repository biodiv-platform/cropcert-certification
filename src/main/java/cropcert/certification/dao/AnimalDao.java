package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.Animal;

public class AnimalDao extends AbstractDao<Animal, Long>{

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
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}
}
