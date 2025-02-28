package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void saveEntry(User entry){
        try{
            userRepo.save(entry);
        } catch(Exception e){
            log.error("Exception ",e);
        }

    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepo.findById(id);
    }

    public boolean deleteJournalById(ObjectId id){
        userRepo.deleteById(id);
        return true;
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }


}
