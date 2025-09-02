package group.Service;

import group.Entity.DAO.User;
import group.Service.TopUserStorage.TopUserStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class InMemoryTopUserStorage implements TopUserStorage {
	@Value("${top.offside}")
	private Integer topOffside;

	private final Comparator<User> userInfoComparator = Comparator.comparing(User::getResult)
			.reversed()
			.thenComparing(User::getLevelId);

	private final Comparator<User> levelInfoComparator = Comparator.comparing(User::getResult)
			.reversed()
			.thenComparing(User::getId);

	private final ConcurrentHashMap<Integer, ConcurrentSkipListSet<User>> userInfoStorage = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<Integer, ConcurrentSkipListSet<User>> levelInfoStorage = new ConcurrentHashMap<>();

	@Override
	public void cache(User user) {
		cacheToMap(userInfoStorage, user.getId(), user, userInfoComparator);
		cacheToMap(levelInfoStorage, user.getLevelId(), user, levelInfoComparator);
	}

	@Override
	public List<User> getUserInfo(Integer userId) {
		Set<User> set = userInfoStorage.get(userId);
		if(set == null) {
			return List.of();
		}
		return new ArrayList<>(set);
	}

	@Override
	public List<User> getLevelInfo(Integer levelId) {
		Set<User> set = levelInfoStorage.get(levelId);
		if(set == null) {
			return List.of();
		}
		return new ArrayList<>(set);
	}

	private void cacheToMap(
			ConcurrentHashMap<Integer, ConcurrentSkipListSet<User>> map,
			Integer key,
			User user,
			Comparator<User> comparator) {

		map.compute(key, (k, set) -> {
			if (set == null) {
				set = new ConcurrentSkipListSet<>(comparator);
			}
			set.add(user);
			if (set.size() > topOffside) {
				set.pollLast();
			}

			return set;
		});
	}
}



