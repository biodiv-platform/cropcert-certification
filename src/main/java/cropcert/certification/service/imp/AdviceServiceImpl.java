package cropcert.certification.service.imp;

import javax.inject.Inject;
import cropcert.certification.dao.AdviceDao;
import cropcert.certification.pojo.Advice;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.AdviceService;

public class AdviceServiceImpl extends AbstractService<Advice> implements AdviceService {

	@Inject
	public AdviceServiceImpl(AdviceDao dao) {
		super(dao);
	}

}
