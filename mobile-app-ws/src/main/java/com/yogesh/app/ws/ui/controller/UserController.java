package com.yogesh.app.ws.ui.controller;

import com.yogesh.app.ws.exceptions.UserServiceException;
import com.yogesh.app.ws.ui.model.response.ErrorMessages;
import com.yogesh.app.ws.ui.model.response.OperationStatusModel;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogesh.app.ws.service.UserService;
import com.yogesh.app.ws.shared.dto.UserDto;
import com.yogesh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yogesh.app.ws.ui.model.response.UserRest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDto dto = userService.getUserByID(id);
        BeanUtils.copyProperties(dto, returnValue);
        return returnValue;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws JsonProcessingException, Exception {
        UserRest returnValue = new UserRest();// to return the value
        UserDto userDto = new UserDto();// to transfer the user
        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        BeanUtils.copyProperties(userDetails, userDto);// (source,target)
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        logger.info("Return Value=" + new ObjectMapper().writeValueAsString(returnValue));
        return returnValue;
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) throws JsonProcessingException{

        UserRest returnRes = new UserRest();// to return the value
        UserDto userDto = new UserDto();// to transfer the user

        BeanUtils.copyProperties(userDetails, userDto);// (source,target)
        UserDto createdUser = userService.updateUser(userDto,id);
        BeanUtils.copyProperties(createdUser, returnRes);
        logger.info("Return Value=" + new ObjectMapper().writeValueAsString(returnRes));
        return returnRes;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable String id) {
        OperationStatusModel statusModel=new OperationStatusModel();
        statusModel.setOperationName("Delete");
        statusModel.setOperationResult("Successful");
        return "delete user called";
    }
    @GetMapping (produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value="limit",defaultValue = "2") int limit)
    {
        if(page>0) page=page-1;
        List<UserRest> returnValue=new ArrayList<>();
        List<UserDto> list=userService.getUsers(page,limit);
        for(UserDto userDto:list)
        {
            UserRest userModel=new UserRest();
            BeanUtils.copyProperties(userDto,userModel);
            returnValue.add(userModel);
        }
        return returnValue;
    }

}
