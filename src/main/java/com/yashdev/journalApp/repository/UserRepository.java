package com.yashdev.journalApp.repository;


import com.yashdev.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, ObjectId> {

    User findByUserName(String userName);
    //why did we create this method? because we want to find a user by their username, and this method will allow us to do that.How ?? because Spring Data MongoDB will automatically generate the implementation of this method based on its name. The method name follows a specific convention that Spring Data understands, and it will create the necessary query to find a user by their username in the MongoDB database. So, when we call this method, it will return the user object that matches the provided username.
}
