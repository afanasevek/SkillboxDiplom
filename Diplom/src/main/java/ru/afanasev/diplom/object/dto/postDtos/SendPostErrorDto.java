package ru.afanasev.diplom.object.dto.postDtos;

import java.util.HashMap;
import java.util.Map;

public class SendPostErrorDto extends SendPostDtoResponse{


	Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}
	public void addErrors(String field, String error) {
		errors.put(field, error);
	}
	
	
	
}
