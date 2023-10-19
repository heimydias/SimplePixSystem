package com.heimydias.pixdemo.service;

import com.heimydias.pixdemo.controller.exception.GenericException;
import com.heimydias.pixdemo.model.CreateTransactionRequest;
import com.heimydias.pixdemo.model.CreateUserRequest;
import com.heimydias.pixdemo.model.Pix;
import com.heimydias.pixdemo.model.User;
import com.heimydias.pixdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest userCreate) {
        if (userRepository.existsByChavePix(userCreate.getChavePix())) {
            throw new GenericException("This Key already exists.");
        }

        User userObj = new User();
        userObj.setName(userCreate.getName());
        userObj.setSaldo(userCreate.getSaldo());
        userObj.setChavePix(userCreate.getChavePix());

        return userRepository.save(userObj);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new GenericException("User not found");
        }
        return user.get();
    }


    public String createTransaction(CreateTransactionRequest createTransactionRequest) {
        if (!userRepository.existsByChavePix(createTransactionRequest.getChavePix())) {
            throw new GenericException("This Key not exists.");
        }
        Optional<User> user = userRepository.findById(createTransactionRequest.getRemetenteId());
        if (user.isEmpty()){
            throw new GenericException("sender not found");
        }

        User remetente = user.get();
        remetente.setSaldo(remetente.getSaldo().subtract(createTransactionRequest.getValor()));
        User userDestine = userRepository.findByChavePix(createTransactionRequest.getChavePix());
        Pix pix = new Pix(createTransactionRequest.getValor());
        userDestine.getPix().add(pix);
        userDestine.setSaldo(userDestine.getSaldo().add(createTransactionRequest.getValor()));
        userRepository.saveAll(List.of(userDestine,remetente));
        return String.format("""
                TRANSFERIDO VALOR
                R$ %.2f
                """, createTransactionRequest.getValor());
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new GenericException("This user not exists.");
        }
        userRepository.deleteById(id);
    }
}
