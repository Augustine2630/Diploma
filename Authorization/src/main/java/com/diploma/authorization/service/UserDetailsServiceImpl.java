package com.diploma.authorization.service;

import com.diploma.authorization.model.Addresses;
import com.diploma.authorization.model.User;
import com.diploma.authorization.repository.AddressesRepository;
import com.diploma.authorization.repository.UserRepository;
import com.diploma.authorization.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final AddressesRepository addressesRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, AddressesRepository addressesRepository) {
        this.userRepository = userRepository;
        this.addressesRepository = addressesRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        System.out.println(user);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user.get());
    }

    public void saveNewUser(User user){
        userRepository.save(user);
    }

    public List<Addresses> getByUser(String user){
        return addressesRepository.findAllByUser(user);
    }

    public void addNewAddress(String address, String user){
        addressesRepository.addNewAddress(address, user);
    }

    public Long getUserId(String username){
        return userRepository.findByUsername(username).get().getId();
    }
}
