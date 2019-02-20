package com.zerofiltre.snapanonyme.infrastructure.repository;

import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import com.zerofiltre.snapanonyme.infrastructure.mapper.SnapArticleMapper;
import com.zerofiltre.snapanonyme.infrastructure.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JPAArticleRepository extends JPARepository implements Snaps {

    private SnapArticleMapper mapper = SnapArticleMapper.getMapper();

    @Override
    public List<Snap> all() {
        return mapper.toSnaps(entityManager.createQuery(
                "Select s from " + Article.class.getSimpleName() + " s").getResultList());
    }

    @Override
    public Snap getById(int id) {
        return mapper.toSnap(entityManager.find(Article.class, id));
    }

    @Override
    public List<Snap> getByUserId() {
        return null;
    }

    @Override
    public Snap save(Snap snap) {
        Article article = mapper.toArticle(snap);
        entityManager.persist(article);
        return mapper.toSnap(article);


    }

    @Override
    public List<Snap> closest(Location location, double distanceAsMeters) {
        return all().stream()
                .filter(snap -> (LocationUtils.distanceAsMeters(location, snap.getPostedAt(), 0, 0) < distanceAsMeters))
                .collect(Collectors.toList());
    }

    /**
     * Drop all data in the table for done, can not be undone
     */
    @Override
    public void deleteAll() {
        entityManager.createQuery("delete from " + Article.class.getSimpleName()).executeUpdate();

    }

    @Override
    public Snap update(Snap snap) {
        return null;
    }

    @Override
    public void delete(int id) {
        entityManager.createQuery("delete a from " + Article.class.getSimpleName() + " a where a.id=" + id).executeUpdate();

    }
}
