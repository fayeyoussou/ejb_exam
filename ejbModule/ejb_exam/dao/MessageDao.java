package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;

import ejb_exam.entities.Message;
@RequestScoped
public class MessageDao extends DaoEntite<Message, Long> {

	@Override
	protected Class<Message> getTypeClass() {
		return Message.class;
	}

}
