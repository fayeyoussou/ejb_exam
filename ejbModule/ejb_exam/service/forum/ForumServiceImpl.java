package ejb_exam.service.forum;

import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ejb_exam.dao.DiscussionDao;
import ejb_exam.dao.MessageDao;
import ejb_exam.dao.UserDao;
import ejb_exam.dto.Mapper;
import ejb_exam.dto.response.DiscussionListResponse;
import ejb_exam.dto.response.DiscussionMessagesRes;
import ejb_exam.entities.Discussion;
import ejb_exam.entities.Message;
import ejb_exam.entities.User;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@Stateless
public class ForumServiceImpl implements ForumServiceLocal {
	private static final Logger log = Logger.getLogger(ForumServiceImpl.class);
	@Inject private DiscussionDao discussionDao;
	@Inject private MessageDao messageDao;
	@Inject private UserDao userDao;
	@Override
	public List<DiscussionListResponse> listDiscussions() {
		try {
			List<DiscussionListResponse>  discussions = new ArrayList<>();
			for (Discussion discussion : discussionDao.list()) {
				DiscussionListResponse discli = new DiscussionListResponse();
				User createur =discussion.getCreateur();
				discli.setCreateur(createur.getPrenom() + " " + createur.getNom());
				OffsetDateTime dateMessage = discussion.getDateMessage();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
		        String formattedDate = formatter.format(dateMessage);
				discli.setDateMessage(formattedDate);
				List<Message> messages = discussion.getMessages();
				Message message = null;
				if(!messages.isEmpty()) {
					message  = messages.get(0);
					for (Message sms : messages) {
						if(sms.getDateMessage().compareTo(message.getDateMessage())>0) {
							message = sms;
						}
					}
				}
				if(message == null) {
					discli.setDernierMessageDate("");
					discli.setDernierMessageUser("pas encore de message");
				}else {
					discli.setDernierMessageDate(formatter.format(message.getDateMessage()));
					User du = message.getEnvoyeur();
					discli.setDernierMessageUser(du.getPrenom()+" "+du.getNom());
				}
				discli.setId(discussion.getId());
				discli.setNombreMessages(message != null ? messages.size() : 0);
				discli.setSujet(discussion.getSujet());
				discli.setVue(discussion.getVue());
				discussions.add(discli);
			}
			return discussions;
		} catch (Exception e) {
			log.error("list discussion",e);
			return Collections.emptyList();
		}
		
	}
	@Override
	public DiscussionListResponse saveDiscussion(Discussion discussion) {
		try {
			discussion.setCreateur(userDao.getById(discussion.getCreateur().getId()));
			Discussion discussion2 = discussionDao.save(discussion);
			return Mapper.discussionTDiscussionListResponse(discussion2);
		} catch (Exception e) {
			log.error("save discussion",e);
			return null;
		}
	}
	@Override
	public DiscussionListResponse editDiscussion(Discussion discussion) {
		try {
			Discussion discussion2 =  discussionDao.edit(discussion);
			return Mapper.discussionTDiscussionListResponse(discussion2);
		} catch (Exception e) {
			log.error("edit discussion",e);
			return null;
		}
	}
	@Override
	public Boolean deleteDiscussion(Long id) {
		try {
			return discussionDao.delete(id);
		} catch (Exception e) {
			log.error("delete discussion",e);
			return null;
		}
	}
	@Override
	public Message addMessage(Long idDiscussion, Message message) {
		try {
			Discussion discussion = discussionDao.getById(idDiscussion);
			message.setDiscussion(discussion);
			return messageDao.save(message);
		} catch (Exception e) {
			log.error("save message",e);
			return null;
		}
	}
	@Override
	public DiscussionMessagesRes listMessages(Long idDiscussion) {
		try {
			Discussion discussion = discussionDao.getById(idDiscussion);
			List<Message> messages = discussion.getMessages();
			DiscussionMessagesRes res = new DiscussionMessagesRes();
			res.setDiscussion(discussion);
			res.setMessages(messages);
			return res;
		} catch (Exception e) {
			log.error("list message",e);
			return null;
		}
		
	}
	@Override
	public Message editMessage(Message message) {
		try {
			return messageDao.edit(message);
		} catch (Exception e) {
			log.error("save message",e);
			return null;
		}
		
	}
	@Override
	public Boolean deleteMessage(Long id) {
		try {
			return messageDao.delete(id);
		} catch (Exception e) {
			log.error("save message",e);
			return false;
		}
	}
	@Override
	public Discussion getDiscussion(Long id) {
		try {
			return discussionDao.getById(id);
		} catch (Exception e) {
			log.error("save message",e);
			return null;
		}
		
	}
	@Override
	public Message getMessage(Long id) {
		try {
			return messageDao.getById(id);
		} catch (Exception e) {
			log.error("get message",e);
			return null;
		}
	}
	@Override
	public DiscussionListResponse getDiscussionById(Long id) {
		try {
			return Mapper.discussionTDiscussionListResponse(getDiscussion(id));
		} catch (Exception e) {
			log.error("get message",e);
			return null;
		}
	}

}
