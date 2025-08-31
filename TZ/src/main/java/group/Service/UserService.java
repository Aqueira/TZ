package group.Service;


import group.Entity.DTO.RequestUserDTO;
import group.Entity.DTO.ResponseUserDTO;
import group.Entity.Mappers.UserMapper;
import group.Service.TopUserStorage.TopUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final TopUserStorage topUserStorage;

	@Autowired
	public UserService(TopUserStorage topUserStorage) {
		this.topUserStorage = topUserStorage;
	}

	public void store(RequestUserDTO user) {
		topUserStorage.cache(UserMapper.toEntity(user));
	}

	public List<ResponseUserDTO> getUserInfo(Integer id) {
		return UserMapper.toResponse(topUserStorage.getUserInfo(id));
	}

	public List<ResponseUserDTO> getLevelInfo(Integer id) {
		return UserMapper.toResponse(topUserStorage.getLevelInfo(id));
	}
}
