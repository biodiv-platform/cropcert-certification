package cropcert.certification.service.imp;

import javax.inject.Inject;
import cropcert.certification.dao.AnimalDao;
import cropcert.certification.pojo.Animal;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.AnimalService;

public class AnimalServiceImpl extends AbstractService<Animal> implements AnimalService {

	@Inject
	public AnimalServiceImpl(AnimalDao dao) {
		super(dao);
	}

}
