package com.vti.templaterestfulapi.service;

import com.vti.templaterestfulapi.models.Tour;
import com.vti.templaterestfulapi.models.TourStatus;
import com.vti.templaterestfulapi.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Configurable
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Tour insert(Tour tour){
        tour.setId(sequenceGeneratorService.generateSequence(Tour.SEQUENCE_NAME));
        tour.setCreatedDate(new Date());
        return tourRepository.insert(tour);
    }

    public void cancelTour(Long tourID){
        Optional<Tour> optionalTour = tourRepository.findById(tourID);
        if(optionalTour.isPresent()){
            Tour tour = optionalTour.get();
            tour.setStatus(TourStatus.TOUR_CANCEL);
            tourRepository.save(tour);
        }
    }

    public List<Tour> findAllOpenTour()
    {
        List<Tour> listAll =  tourRepository.findAll();
        return listAll.stream()
                .filter(tour -> tour.getStatus()== TourStatus.TOUR_OPEN)
                .collect(Collectors.toList());
    }

    // Tim = using MongoDB
    public List<Tour> findAllOpenTour_UsingMongoDB()
    {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("status").is(TourStatus.TOUR_OPEN));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Tour.class);
    }
    public List<Tour> findAllCancelTour()
    {
        return tourRepository.findAll();
    }

    public List<Tour> findAllClosedTour()
    {
        return tourRepository.findAll();
    }

    public List<Tour> findAllDoingTour()
    {
        return tourRepository.findAll();
    }
}
