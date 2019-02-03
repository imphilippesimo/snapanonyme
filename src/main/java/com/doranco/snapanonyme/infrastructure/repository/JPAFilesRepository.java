package com.doranco.snapanonyme.infrastructure.repository;

import com.doranco.snapanonyme.domain.model.Picture;
import com.doranco.snapanonyme.domain.repository.Pictures;
import com.doranco.snapanonyme.infrastructure.mapper.FilePictureMapper;
import com.doranco.snapanonyme.infrastructure.model.File;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAFilesRepository extends JPARepository implements Pictures {

    private static FilePictureMapper mapper = FilePictureMapper.getMapper();

    @Override
    public List<Pictures> all() {
        return mapper.toPictures(entityManager.createQuery(
                "Select t from " + File.class.getSimpleName() + " t").getResultList());
    }

    @Override
    public Picture getById(int id) {
        return mapper.toPicture(entityManager.find(File.class, id));
    }


    @Override
    public void save(Picture picture) {
        File file = mapper.toFile(picture);
        entityManager.persist(file);
    }

    @Override
    public Picture update(Picture picture) {
        File file = mapper.toFile(picture);
        file = entityManager.merge(file);
        return mapper.toPicture(file);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(id);

    }
}
