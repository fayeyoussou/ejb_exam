package ejb_exam.dao;

import ejb_exam.entities.Cv;

public class CvDao extends DaoEntite<Cv, Long> {

	@Override
	protected Class<Cv> getTypeClass() {
		return Cv.class;
	}

}
