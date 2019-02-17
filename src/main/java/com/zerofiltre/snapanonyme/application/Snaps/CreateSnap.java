package com.zerofiltre.snapanonyme.application.Snaps;

import com.zerofiltre.snapanonyme.application.mapper.SnapDTOMapper;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.domain.repository.Pictures;
import com.zerofiltre.snapanonyme.domain.repository.Snaps;
import com.zerofiltre.snapanonyme.presentation.dto.PictureDTO;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

@Service
@Transactional
public class CreateSnap {


    Pictures pictures;
    Snaps snaps;
    public SnapDTOMapper mapper = SnapDTOMapper.INSTANCE;

    public CreateSnap(Pictures pictures, Snaps snaps) {
        this.pictures = pictures;
        this.snaps = snaps;
    }


    public SnapDTO create(Location location, MultipartFile uploadedFile) throws IOException {

        //Construct the DTO
        SnapDTO snapDTO = new SnapDTO();
        snapDTO.setPostedAt(location);
        snapDTO.setPostedOn(Instant.now());
        //extract the picture from the uploadedFile
        if (uploadedFile != null) {
            PictureDTO pictureDTO = new PictureDTO();
            pictureDTO.setName(StringUtils.cleanPath(uploadedFile.getOriginalFilename()));
            pictureDTO.setContent(uploadedFile.getBytes());
            pictureDTO.setMimeType(uploadedFile.getContentType());
            pictureDTO.setSize(uploadedFile.getSize());
            snapDTO.setPicture(pictureDTO);
        }

        final Snap snap = mapper.snapDTOToSnap(snapDTO);
        final Snap save = snaps.save(snap);
        SnapDTO result = mapper.snapToSnapDTO(save);

        return result;

    }


}
