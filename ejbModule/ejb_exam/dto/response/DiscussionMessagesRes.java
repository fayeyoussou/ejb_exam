package ejb_exam.dto.response;

import java.util.List;

import ejb_exam.entities.Discussion;
import ejb_exam.entities.Message;
import lombok.Data;

@Data
public class DiscussionMessagesRes {
	private Discussion discussion;
	private List<Message> messages;
}
