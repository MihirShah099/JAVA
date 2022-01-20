package darshan.thakkar.practical.practicals.practical_2.rest;

import darshan.thakkar.practical.practicals.practical_2.models.EndPoint2Model;
import darshan.thakkar.practical.practicals.practical_2.models.GenericResponseModel;
import darshan.thakkar.practical.practicals.practical_2.utility.RestControllerUtility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/rest_validation")
@ControllerAdvice
public class EndPoint2Controller extends RestControllerUtility<EndPoint2Model> {

	/**
	 * This method is used for validating request demo practical
	 *
	 * @param reqModel EndPoint2Model
	 * @param errors   Errors
	 * @return ResponseEntity<GenericResponseModel>
	 * @author Darshan Thakkar
	 */

	@PostMapping(path = "validate_end_point_2", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<GenericResponseModel> getResponseFromReq(@Valid @RequestBody EndPoint2Model reqModel, Errors errors) {
		return super.getResponseFromReq(reqModel, errors);
	}

	@Override
	protected String getModuleName() {
		return " [End Point 2 Controller] ";
	}
}
