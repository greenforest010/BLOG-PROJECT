package com.growingitskill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Tag Not Found")
public class TagNotFoundException extends RuntimeException {

}
