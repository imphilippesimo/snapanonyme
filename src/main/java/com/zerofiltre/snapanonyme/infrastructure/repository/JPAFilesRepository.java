package com.zerofiltre.snapanonyme.infrastructure.repository;

import com.zerofiltre.snapanonyme.domain.model.Picture;
import com.zerofiltre.snapanonyme.domain.repository.Pictures;
import com.zerofiltre.snapanonyme.infrastructure.mapper.FilePictureMapper;
import com.zerofiltre.snapanonyme.infrastructure.model.File;
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
    public Picture save(Picture picture) {
        File file = mapper.toFile(picture);

        entityManager.persist(file);
        return mapper.toPicture(file);
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
