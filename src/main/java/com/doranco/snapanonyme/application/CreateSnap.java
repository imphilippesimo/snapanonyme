package com.doranco.snapanonyme.application;

import com.doranco.snapanonyme.domain.model.Snap;
import com.doranco.snapanonyme.domain.repository.Pictures;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.PathIterator;

@Service
@Transactional
public class CreateSnap {


    Pictures pictures;

    public CreateSnap(Pictures pictures) {
        this.pictures = pictures;
    }

    public Snap create(Snap snap){
        //To create a snap we need to first of all create the picture assigned to that snap object
        pictures.save(snap.getPicture());

        //And then create the create the snap
        //TODO

        return snap;


    }





}
