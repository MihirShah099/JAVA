package darshan.thakkar.practical.practicals.practical_2.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class EndPoint2Model implements Serializable {

	@NotBlank(message = "GST Number can't be null")
	@Size(message = "GST number should have 24 characters", max = 24, min = 24)
	private String gstNumber;
}
