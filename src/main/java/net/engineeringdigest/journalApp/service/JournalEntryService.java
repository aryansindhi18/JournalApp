package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
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
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalRepo;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry entry, String username){
        try{
            User user = userService.findByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepo.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch(Exception e){
            log.error("Exception ",e);
        }

    }
    public void saveEntry(JournalEntry entry){
        try{
//            entry.setDate(LocalDateTime.now());
            journalRepo.save(entry);
        } catch(Exception e){
            log.error("Exception ",e);
        }

    }

    public List<JournalEntry> getAll(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntry> getJournalById(ObjectId id){
        return journalRepo.findById(id);
    }

    public boolean deleteJournalById(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalRepo.deleteById(id);
        return true;
    }


}
