package net.engineeringdigest.journalApp.controllers;
import java.util.*;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//JPA
//Hibernate

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    JournalEntryService journalService;
    @Autowired
    UserService userService;

    /**
     * Retrieves all journal entries for a given user.
     *
     * Steps involved:
     * 1. Fetches the user details from the database using the provided username.
     * 2. Retrieves the list of journal entries associated with that user.
     * 3. If entries exist, returns them with HTTP 200 (OK) status.
     * 4. If no entries are found, returns HTTP 404 (NOT FOUND) status.
     *
     * @param username The username whose journal entries need to be retrieved.
     * @return ResponseEntity containing the list of journal entries (if found) or 404 status if no entries exist.
     */
    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty())
            return new ResponseEntity<>(all,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newJournal, @PathVariable String username){
        try{
            //save journal _id in user.journalEntries as well
            journalService.saveEntry(newJournal, username);
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

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody  JournalEntry updatedEntry,@PathVariable String username){
        JournalEntry old = journalService.getJournalById(myId).orElse(null);
        if(old != null){
            old.setTitle((updatedEntry.getTitle()!=null && !updatedEntry.getTitle().trim().equals("")) ? updatedEntry.getTitle() : old.getTitle());
            old.setContent((updatedEntry.getContent()!=null && !updatedEntry.getContent().trim().equals("")) ? updatedEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId, @PathVariable String username){
        //have to delete reference from users as well
        //cascade delete
        journalService.deleteJournalById(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
