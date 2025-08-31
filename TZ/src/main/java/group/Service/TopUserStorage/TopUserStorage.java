package group.Service.TopUserStorage;

import group.Entity.DAO.User;


import java.util.List;

public interface TopUserStorage {
	void cache(User user);

	List<User> getUserInfo(Integer userId);

	List<User> getLevelInfo(Integer levelId);
}
