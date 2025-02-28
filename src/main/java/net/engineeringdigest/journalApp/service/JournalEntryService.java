package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
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

    public void saveEntry(JournalEntry entry){
        try{
            entry.setDate(LocalDateTime.now());
            journalRepo.save(entry);
//            return entry;
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

    public boolean deleteJournalById(ObjectId id){
        journalRepo.deleteById(id);
        return true;
    }


}
