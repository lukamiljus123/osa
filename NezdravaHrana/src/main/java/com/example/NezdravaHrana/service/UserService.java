package com.example.NezdravaHrana.service;

import com.example.NezdravaHrana.entity.DTO.KorisnikDTO;
import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Roles;
import com.example.NezdravaHrana.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private KorisnikRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Korisnik findOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Korisnik findByUsername(String username) {
        Optional<Korisnik> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    public Korisnik save(Korisnik korisnik) {
        return userRepository.save(korisnik);
    }

    public Korisnik createUser(KorisnikDTO korisnikDTO) {

        Optional<Korisnik> user = userRepository.findFirstByUsername(korisnikDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        Korisnik newUser = new Korisnik();
        newUser.setUsername(korisnikDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(korisnikDTO.getPassword()));
        newUser.setRoles(Roles.KUPAC);
        newUser = userRepository.save(newUser);
        return newUser;
    }
}
