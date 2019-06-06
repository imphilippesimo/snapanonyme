package com.zerofiltre.snapanonyme.infrastructure.repository;

import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import com.zerofiltre.snapanonyme.infrastructure.mapper.SnapArticleMapper;
import com.zerofiltre.snapanonyme.infrastructure.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
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
    public List<Snap> closest(Location location, double distanceAsMeters) {
        /**
         * TODO
         * 1) write the query to load the closest snaps
         * 2) create query with entity manager
         * 3) call it
         */

        String query = "SELECT SQRT(\n" +
                "    POW(69.1 * (article.latitude - :current_latitude), 2) +\n" +
                "    POW(69.1 * (:current_longitude - article.longitude) * COS(article.latitude / 57.3), 2)) AS distance,\n" +
                "article.id\n" +
                "FROM article HAVING distance <= :max_distance ORDER BY distance;";


        List<Snap> results = new ArrayList<Snap>();

        List<Object[]> articlesIds = entityManager.createNativeQuery(query)
                .setParameter("current_latitude", location.getLatitude())
                .setParameter("current_longitude", location.getLongitude())
                .setParameter("max_distance", distanceAsMeters).getResultList();



        for (Object[] record :
                articlesIds) {
            results.add(getById((Integer) record[1]));

        }
        logger.debug(String.valueOf(results.size()));
        return results;


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
