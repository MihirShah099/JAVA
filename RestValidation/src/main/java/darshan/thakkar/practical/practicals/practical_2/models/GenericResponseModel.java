package darshan.thakkar.practical.practicals.practical_2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponseModel {
	private int statusCode = 0;
	private String message;
	private Object data;

	public GenericResponseModel(int statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "[" +
				       "statusCode=" + statusCode +
				       ", message='" + message + '\'' +
				       ", data=" + data +
				       ']';
	}
}
