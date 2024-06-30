package com.registeruser.spring.register_user.exceptions.UserException.UserNotFoundException;

import com.registeruser.spring.register_user.exceptions.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserNotFoundDetails extends ExceptionDetails {

}
