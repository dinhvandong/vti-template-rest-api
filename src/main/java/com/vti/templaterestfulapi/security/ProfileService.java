package com.vti.templaterestfulapi.security;

import com.vti.templaterestfulapi.models.Profile;
import com.vti.templaterestfulapi.models.Tour;
import com.vti.templaterestfulapi.repositories.ProfileRepository;
import com.vti.templaterestfulapi.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public Profile insert(Profile profile){
        Long id = sequenceGeneratorService.generateSequence(Profile.SEQUENCE_NAME);
        profile.setId(id);
        return profileRepository.insert(profile);
    }


    public Profile update(Profile profile){
        Long id = profile.getId();
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if(optionalProfile.isPresent()){

            Profile profile1 = optionalProfile.get();
            return profileRepository.save(profile);
        }
        return null;
    }
    public List<Profile> findAll()
    {
        return profileRepository.findAll();
    }


}
