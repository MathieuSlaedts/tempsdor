package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface UserService extends CrudService<UserDTO, UserPertinentDTO, Integer> {
    UserPertinentDTO updateEmailById(UserEmailOnlyDTO updatedDatas, Integer id) throws ElementNotFoundException;
    UserPertinentDTO updatePasswordById(UserPasswordOnlyDTO updatedDatas, Integer id) throws ElementNotFoundException;
}