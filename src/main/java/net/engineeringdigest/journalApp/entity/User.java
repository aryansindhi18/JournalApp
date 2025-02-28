package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//POJO - Plain Old Java Object
//Mapping it to collection for mongoDb
@Document(collection = "journal_users")
@Data

public class User {
    //Unique Key for this collection
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    //foreign key - store reference of journalEntries
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();



}
