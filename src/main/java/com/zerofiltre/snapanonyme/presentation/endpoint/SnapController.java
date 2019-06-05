package com.zerofiltre.snapanonyme.presentation.endpoint;

import com.zerofiltre.snapanonyme.application.Snaps.CreateSnap;
import com.zerofiltre.snapanonyme.application.Snaps.GetSnaps;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.lang.System.out;

@RestController
@RequestMapping(value = "/user")
public class SnapController {

    private CreateSnap createSnap;
    private GetSnaps getSnaps;

    public SnapController(CreateSnap createSnap, GetSnaps getSnaps) {
        this.createSnap = createSnap;
        this.getSnaps = getSnaps;
    }


    @RequestMapping(value = "/snaps", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
//    @Timed
    public ResponseEntity<SnapDTO> createSnap(@RequestParam("longitude") @Valid String longitude,
                                              @RequestParam("latitude") @Valid String latitude,
                                              @RequestPart(value = "picture", required = false) MultipartFile uploadedFile) throws URISyntaxException, IOException {
//        log.debug("REST request to save Travel : {}", travelDTO);
//        if (Integer.valueOf(snap.getId()) != null) {
//            throw new BadRequestAlertException("A new travel cannot already have an ID", ENTITY_NAME, "idexists");
//        }

        if (uploadedFile != null)
            out.print(uploadedFile.toString());
        Location location = new Location(Double.valueOf(longitude), Double.valueOf(latitude));
        SnapDTO result = createSnap.create(location, uploadedFile);
        return ResponseEntity.created(new URI("/snaps/" + result.getId())).body(result);
    }

    @GetMapping(value = "/snaps")
    public ResponseEntity<List<SnapDTO>> getAllSnaps(@RequestParam(value = "longitude") double longitude,
                                                     @RequestParam(value = "latitude") double latitude,
                                                     @RequestParam(value = "distance") double distanceAsMeters) {


        Location location = new Location(longitude, latitude);

        return ResponseEntity.ok(getSnaps.closest(location,distanceAsMeters));
    }


}
