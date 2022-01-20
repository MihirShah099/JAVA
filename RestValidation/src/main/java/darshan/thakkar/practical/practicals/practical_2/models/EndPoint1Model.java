package darshan.thakkar.practical.practicals.practical_2.models;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class EndPoint1Model implements Serializable {

	@NotNull(message = "ID can't be null ")
	private Long id;
	@NotBlank(message = "Name should not be empty ")
	private String name;
	@NotNull(message = "E-mail can't be null ")
	@Email(message = "E-mail should be valid ")
	private String email;
	@NotNull(message = "Age can't be null ")
	@Min(message = "Age should be greater than 18 for eligibility ", value = 18)
	@Max(message = "Age should be less than 50 for eligibility ", value = 50)
	private Integer age;
	@NotNull(message = "Address can't be null ")
	@Size(message = "Address shouldn't have more than 150 characters ", max = 150)
	private String address;
	@AssertTrue(message = "Registering user must be an indian candidate ")
	private boolean indianCandidate;
}
