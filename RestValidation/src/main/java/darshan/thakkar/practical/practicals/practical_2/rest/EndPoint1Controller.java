package darshan.thakkar.practical.practicals.practical_2.rest;

import darshan.thakkar.practical.practicals.practical_2.models.EndPoint1Model;
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
public class EndPoint1Controller extends RestControllerUtility<EndPoint1Model> {


	/**
	 * This method is used for validating request demo practical
	 *
	 * @param reqModel EndPoint1Model
	 * @param errors   Errors
	 * @return ResponseEntity<GenericResponseModel>
	 * @author Darshan Thakkar
	 */

	@PostMapping(path = "/validate_end_point_1", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<GenericResponseModel> getResponseFromReq(@Valid @RequestBody EndPoint1Model reqModel, Errors errors) {
		return super.getResponseFromReq(reqModel, errors);
	}

	@Override
	protected String getModuleName() {
		return " [End Point 1 Controller] ";
	}
}
