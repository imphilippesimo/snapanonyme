package com.zerofiltre.snapanonyme.application.Snaps;

import com.zerofiltre.snapanonyme.application.mapper.SnapDTOMapper;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GetSnaps {

    Snaps snaps;
    public SnapDTOMapper mapper = SnapDTOMapper.INSTANCE;


    public GetSnaps(Snaps snaps) {
        this.snaps = snaps;
    }

    public List<SnapDTO> all() {
        return mapper.snapsToSnapDTOs(snaps.all());
    }

    public List<SnapDTO> closest(Location location, double distanceAsMeters) {
        List<SnapDTO> snapDTOs = new ArrayList<SnapDTO>();
        return mapper.snapsToSnapDTOs(snaps.closest(location,distanceAsMeters));


    }
}
