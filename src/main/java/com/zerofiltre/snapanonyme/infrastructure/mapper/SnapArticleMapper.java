package com.zerofiltre.snapanonyme.infrastructure.mapper;

import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.infrastructure.model.Article;

import java.util.ArrayList;
import java.util.List;

public class SnapArticleMapper {

    private static SnapArticleMapper mapper;
    private FilePictureMapper filePictureMapper = FilePictureMapper.getMapper();

    private SnapArticleMapper() {
    }

    public static SnapArticleMapper getMapper() {
        if (mapper == null)
            mapper = new SnapArticleMapper();
        return mapper;
    }

    public Snap toSnap(Article article) {
        if (article == null)
            return null;

        Snap snap = new Snap();
        snap.setVisible(article.isVisible());
        snap.setReportsNumber(article.getReportsNumber());
        snap.setPicture(filePictureMapper.toPicture(article.getFile()));
        snap.setId(article.getId());
        snap.setPostedOn(article.getPostedOn());
        snap.setPostedAt(new Location(article.getLongitude(), article.getLatitude()));

        return snap;

    }

    public Article toArticle(Snap snap) {
        if (snap == null)
            return null;

        Article article = new Article();

        article.setId(snap.getId());
        article.setFile(filePictureMapper.toFile(snap.getPicture()));
        article.setReportsNumber(snap.getReportsNumber());
        article.setVisible(snap.isVisible());
        if (snap.getPostedAt() != null) {
            article.setLongitude(snap.getPostedAt().getLongitude());
            article.setLatitude(snap.getPostedAt().getLatitude());
        }
        article.setPostedOn(snap.getPostedOn());

        return article;


    }

    public List<Snap> toSnaps(List<Article> articles) {
        List<Snap> snaps = new ArrayList<Snap>();
        for (Article article : articles) {
            snaps.add(toSnap(article));
        }
        return snaps;

    }

    public List<Article> toArticles(List<Snap> snaps) {
        List<Article> snapsData = new ArrayList<Article>();
        for (Snap snap : snaps) {
            snapsData.add(toArticle(snap));
        }
        return snapsData;
    }


}
