package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;

import ejb_exam.entities.CV;

@RequestScoped
public class CVDao extends DaoEntite<CV, Long>{

	@Override
	protected Class<CV> getTypeClass() {
		// TODO Auto-generated method stub
		return CV.class;
	}

}
