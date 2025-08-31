package group.Entity.Mappers;


import group.Entity.DAO.User;
import group.Entity.DTO.RequestUserDTO;
import group.Entity.DTO.ResponseUserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
	public static User toEntity(RequestUserDTO dto) {
		if (dto == null) return null;
		return new User(dto.getId(), dto.getLevelId(), dto.getResult());
	}

	public static ResponseUserDTO toResponse(User user) {
		if (user == null) return null;
		return new ResponseUserDTO(user.getId(), user.getLevelId(), user.getResult());
	}

	public static List<ResponseUserDTO> toResponse(List<User> users) {
		if (users == null) return new ArrayList<>();
		List<ResponseUserDTO> result = new ArrayList<>(users.size());
		for (User user : users) {
			result.add(toResponse(user));
		}
		return result;
	}
}


