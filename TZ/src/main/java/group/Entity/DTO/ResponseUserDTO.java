package group.Entity.DTO;


public final class ResponseUserDTO {
	private final Integer id;
	private final Integer levelId;
	private final Integer result;

	public ResponseUserDTO(Integer id, Integer levelId, Integer result) {
		this.id = id;
		this.levelId = levelId;
		this.result = result;
	}
}
