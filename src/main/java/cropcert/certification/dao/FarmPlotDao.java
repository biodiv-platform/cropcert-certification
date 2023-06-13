package cropcert.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import cropcert.certification.pojo.FarmPlot;

public class FarmPlotDao extends AbstractDao<FarmPlot, Long> {
	private static final Logger logger = LoggerFactory.getLogger(FarmPlotDao.class);

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
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}
}
