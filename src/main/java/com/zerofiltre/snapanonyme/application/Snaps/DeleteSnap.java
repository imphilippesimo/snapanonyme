package com.zerofiltre.snapanonyme.application.Snaps;

import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteSnap {

    Snaps snaps;

    public DeleteSnap(Snaps snaps) {
        this.snaps = snaps;
    }

    public void delete(int id) {
        snaps.delete(id);
    }

    public void all() {
        snaps.deleteAll();
    }
}
