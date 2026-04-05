package com.yashdev.journalApp.entity;


import lombok.Data;
import lombok.Getter;
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
public class JournalEntry {

    @Id // This annotation is used to mark the field as the primary key in MongoDB
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;


}
