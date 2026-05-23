package com.yashdev.journalApp.repository;


import com.yashdev.journalApp.entity.ConfigJournalAppEntity;
import com.yashdev.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository <ConfigJournalAppEntity, ObjectId> {


}
