package com.growingitskill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post Not Found")
public class PostNotFoundException extends RuntimeException {

}
