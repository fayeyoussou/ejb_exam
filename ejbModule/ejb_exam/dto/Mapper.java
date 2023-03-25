package ejb_exam.dto;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.DiscussionListResponse;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.dto.response.UserResponse;
import ejb_exam.entities.Discussion;
import ejb_exam.entities.Message;
import ejb_exam.entities.Role;
import ejb_exam.entities.User;

public abstract class Mapper {
	public static RoleResponse roleToResponse(Role role) {
		RoleResponse roleResponse = new RoleResponse();
		roleResponse.setId(role.getId());
		roleResponse.setNom(role.getNom());
		return roleResponse;
	}
	public static Role roleRequestToRole(RoleRequest roleRequest) {
		Role role = new Role();
		role.setNom(roleRequest.getNom());
		return role;
	}
	public static List<RoleResponse> rolesToRolesResponses(List<Role> roles){
		List<RoleResponse> roleResponses = new ArrayList<>();
		roles.forEach(role->roleResponses.add(roleToResponse(role)));
		return roleResponses;
	}
	public static DiscussionListResponse discussionTDiscussionListResponse(Discussion discussion) {
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
		return discli;
	}
	public static List<DiscussionListResponse> discussionsToDiscussionListResponses(List<Discussion> discussions){
		List<DiscussionListResponse> discussionListResponses = new ArrayList<>();
		for (Discussion discussion : discussions) {
			discussionListResponses.add(discussionTDiscussionListResponse(discussion));
		}
		return discussionListResponses;
	}
}
