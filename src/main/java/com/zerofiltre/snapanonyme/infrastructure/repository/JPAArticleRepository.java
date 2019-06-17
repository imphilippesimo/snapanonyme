package com.zerofiltre.snapanonyme.infrastructure.repository;

import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import com.zerofiltre.snapanonyme.infrastructure.mapper.SnapArticleMapper;
import com.zerofiltre.snapanonyme.infrastructure.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JPAArticleRepository extends JPARepository implements Snaps {

    private SnapArticleMapper mapper = SnapArticleMapper.getMapper();

    private static Logger logger = LoggerFactory.getLogger(JPAArticleRepository.class);

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
    public List<Snap> closest(Location location, double distanceAsMiles) {
        /**
         * TODO
         * 1) write the query to load the closest snaps
         * 2) create query with entity manager
         * 3) call it
         */

        List<Snap> snaps = new ArrayList<>();
        String query = "SELECT \n" +
                "    POW(69.1 * (a.latitude - :current_latitude), 2) +\n" +
                "    POW(69.1 * (:current_longitude - a.longitude) * COS(a.latitude / 57.3), 2) AS distance,\n" +
                "a.id,a.posted_on,a.is_visible,a.reports_number,a.file_id,a.longitude,a.latitude,f.id as fileId,f.mime_type\n" +
                "FROM article a JOIN file f on a.file_id = f.id HAVING distance <= :max_distance ORDER BY distance;";

//        List<Object[]> results = entityManager.createNativeQuery(query, Article.class)
//                .setParameter("current_latitude", location.getLatitude())
//                .setParameter("current_longitude", location.getLongitude())
//                .setParameter("max_distance", distanceAsMeters).getResultList();
//
//        results.stream().forEach(record -> {
//            Article article = (Article) record[0];
//            File file = (File) record[1];
//            article.setFile(file);
//            snaps.add(mapper.toSnap(article));
//        });

        //return snaps;


////        "SELECT b.id, b.title, b.author_id, b.version, a.id as authorId, a.firstName, a.lastName, a.version as authorVersion FROM Book b JOIN Author a ON b.author_id = a.id"
        snaps = mapper.toSnaps(entityManager.createNativeQuery(query, Article.class)
                .setParameter("current_latitude", location.getLatitude())
                .setParameter("current_longitude", location.getLongitude())
                .setParameter("max_distance", Math.pow(distanceAsMiles, 2)).getResultList());
        logger.info(snaps.toString());

        return snaps.stream().map(snap -> {
            snap.setMilesAway(LocationUtils.distanceAsMiles(snap.getPostedAt(), location, 0, 0));
            return snap;
        }).collect(Collectors.toList());


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
