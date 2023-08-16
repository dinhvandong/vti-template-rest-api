package com.vti.templaterestfulapi.repositories;


import com.vti.templaterestfulapi.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
}
