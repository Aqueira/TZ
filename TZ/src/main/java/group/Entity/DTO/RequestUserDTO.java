package group.Entity.DTO;


public final class RequestUserDTO {
	private final Integer id;
	private final Integer levelId;
	private final Integer result;

	public RequestUserDTO(Integer id, Integer levelId, Integer result) {
		this.id = id;
		this.levelId = levelId;
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public Integer getResult() {
		return result;
	}
}
