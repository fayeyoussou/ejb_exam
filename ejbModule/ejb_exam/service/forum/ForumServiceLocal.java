package ejb_exam.service.forum;

import java.util.List;

import javax.ejb.Local;

import ejb_exam.dto.response.DiscussionListResponse;
import ejb_exam.dto.response.DiscussionMessagesRes;
import ejb_exam.entities.Discussion;
import ejb_exam.entities.Message;

@Local
public interface ForumServiceLocal {
	 List<DiscussionListResponse> listDiscussions();
	 Discussion getDiscussion(Long id);
	 DiscussionListResponse getDiscussionById(Long id);
	 Message getMessage(Long id);
	 DiscussionListResponse saveDiscussion(Discussion discussion);
	 DiscussionListResponse editDiscussion(Discussion discussion);
	 Boolean deleteDiscussion(Long id);
	 Message addMessage(Long idDiscussion,Message message);
	 DiscussionMessagesRes listMessages(Long idDiscussion);
	 Message editMessage(Message message);
	 Boolean deleteMessage(Long id);
	 
}
