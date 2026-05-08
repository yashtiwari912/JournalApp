  package com.yashdev.journalApp.entity;


import lombok.Builder;
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


@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id;
//Spring does not create indexes by default, you need to enable it explicitly. in application.properties file we have to add spring.data.mongodb.auto-index-creation=true
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    private List<String>roles;

    @DBRef
    private List<JournalEntry>journalEntries = new ArrayList<>();

}
