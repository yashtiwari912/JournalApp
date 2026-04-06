package com.yashdev.journalApp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")// This annotation is used to indicate that this class is a MongoDB document
//agar collection nhi dala to class name se collection create ho jayegi by default
//@Getter
//@Setter
@Data
public class JournalEntry {//it is basically a POJO class that represents the structure of the data we want to store in MongoDB.
    // It has fields for id, title, content, and date. The @Document annotation indicates that this class is a MongoDB document, and the @Id annotation marks the id field as the primary key.

    @Id // This annotation is used to mark the field as the primary key in MongoDB
    private ObjectId id;
    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;


}
