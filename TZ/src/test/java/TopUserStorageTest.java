import group.Entity.DAO.User;
import group.Main;
import group.Service.TopUserStorage.TopUserStorage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class TopUserStorageTest {
	@Autowired
	private TopUserStorage topUserStorage;

	@Test
	public void getUserInfoTest(){
		addUsers(topUserStorage);

		List<User> users = topUserStorage.getUserInfo(1);
		assertEquals(3, users.size());
		assertEquals(55, users.get(0).getResult());
		assertEquals(15, users.get(1).getResult());
		assertEquals(8, users.get(2).getResult());
	}

	@Test
	public void getLevelInfoTest(){
		addUsers(topUserStorage);

		List<User> users = topUserStorage.getLevelInfo(3);
		assertEquals(2, users.size());
		assertEquals(2, users.get(0).getId());
		assertEquals(1, users.get(1).getId());
	}

	private void addUsers(TopUserStorage topUserStorage){
		topUserStorage.cache(new User(1, 1, 55));
		topUserStorage.cache(new User(1, 2, 8));
		topUserStorage.cache(new User(1, 3, 15));
		topUserStorage.cache(new User(2, 3, 22));
	}
}
