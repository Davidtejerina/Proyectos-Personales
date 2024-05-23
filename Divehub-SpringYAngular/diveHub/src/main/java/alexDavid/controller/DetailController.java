package alexDavid.controller;

import alexDavid.dtos.DetailDTO.DetailRequestDto;
import alexDavid.dtos.DetailDTO.DetailResponseDto;
import alexDavid.mappers.DetailMapper;
import alexDavid.models.Detail;
import alexDavid.service.DetailService.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/divehub/details")
public class DetailController {
    private final DetailService detailService;
    private final DetailMapper detailMapper;

    @GetMapping("/all/{email}")
    public ResponseEntity<List<Detail>> getDetailsByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(detailService.findByUser(email));
    }

    @GetMapping("/details/{order_id}")
    public ResponseEntity<List<Detail>> getDetailsByOrder(
            @PathVariable Long order_id
    ){
        return ResponseEntity.ok(detailService.findByOrder(order_id));
    }

    @PostMapping("/addDetail")
    public ResponseEntity<DetailResponseDto> postDetails(
            @RequestBody DetailRequestDto detailRequestDto
    ){
        return ResponseEntity.ok(detailMapper.toResponse(detailService.addDetail(detailMapper.toModel(detailRequestDto))));
    }

    @DeleteMapping("/clean/{email}")
    public ResponseEntity<?> removeDetails(
            @PathVariable String email
    ){
        detailService.deleteByUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
