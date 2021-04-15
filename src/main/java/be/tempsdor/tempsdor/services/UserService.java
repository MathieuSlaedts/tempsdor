package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;

import java.util.List;

public interface UserService extends CrudService<UserDTO, UserPertinentDTO, Long> {
    UserPertinentDTO updateEmailById(UserEmailOnlyDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException;
    UserPertinentDTO updatePasswordById(UserPasswordOnlyDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException;
}