package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.FarmPlot;

public class FarmPlotDao extends AbstractDao<FarmPlot, Long>{

	@Inject
	protected FarmPlotDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public FarmPlot findById(Long id) {
		Session session = sessionFactory.openSession();
		FarmPlot entity = null;
		try {
			entity = session.get(FarmPlot.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}
}
