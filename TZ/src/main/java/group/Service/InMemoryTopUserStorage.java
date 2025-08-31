package group.Service;

import group.Entity.DAO.User;
import group.Service.TopUserStorage.TopUserStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

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

	private final ConcurrentHashMap<Integer, TreeSet<User>> userInfoStorage = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<Integer, TreeSet<User>> levelInfoStorage = new ConcurrentHashMap<>();

	@Override
	public void cache(User user) {
		userInfoStorage.compute(user.getId(), (key, set) -> {
			if (set == null) {
				set = new TreeSet<>(userInfoComparator);
			}
			set.add(user);
			trimTop(set);
			return set;
		});

		levelInfoStorage.compute(user.getLevelId(), (key, set) -> {
			if (set == null) {
				set = new TreeSet<>(levelInfoComparator);
			}
			set.add(user);
			trimTop(set);
			return set;
		});
	}

	@Override
	public List<User> getUserInfo(Integer userId) {
		TreeSet<User> set = userInfoStorage.get(userId);
		if(set == null) {
			return List.of();
		}
		return new ArrayList<>(set);
	}

	@Override
	public List<User> getLevelInfo(Integer levelId) {
		TreeSet<User> set = levelInfoStorage.get(levelId);
		if(set == null) {
			return List.of();
		}
		return new ArrayList<>(set);
	}

	private void trimTop(TreeSet<User> set) {
		while (set.size() > topOffside) {
			set.pollLast();
		}
	}
}



