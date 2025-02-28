package net.engineeringdigest.journalApp.controllers;
import java.util.*;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//JPA
//Hibernate

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntry> journalDb= new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalDb.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newJournal){
        journalDb.put(newJournal.getId(), newJournal);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId ){
        return journalDb.get(myId);
    }

    @PutMapping("/id/{myId}")
    public boolean updateById(@PathVariable ObjectId myId, @RequestBody  JournalEntry updatedEntry){
        journalDb.put(myId,updatedEntry);
        return true;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable long myId){
        return journalDb.remove(myId) != null;
    }

}
