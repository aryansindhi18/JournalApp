package net.engineeringdigest.journalApp.controllers;
import java.time.LocalDateTime;
import java.util.*;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//JPA
//Hibernate

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    JournalEntryService journalService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newJournal){
        try{
            journalService.saveEntry(newJournal);
            return new ResponseEntity<>(newJournal,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId ){
        JournalEntry entry = journalService.getJournalById(myId).orElse(null);
        if(entry != null){
            return new ResponseEntity<JournalEntry>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody  JournalEntry updatedEntry){
        JournalEntry old = journalService.getJournalById(myId).orElse(null);
        if(old != null){
            old.setTitle((updatedEntry.getTitle()!=null && !updatedEntry.getTitle().trim().equals("")) ? updatedEntry.getTitle() : old.getTitle());
            old.setContent((updatedEntry.getContent()!=null && !updatedEntry.getContent().trim().equals("")) ? updatedEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId){
        journalService.deleteJournalById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
