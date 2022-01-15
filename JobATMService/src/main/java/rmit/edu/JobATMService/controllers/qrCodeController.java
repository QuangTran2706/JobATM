package rmit.edu.JobATMService.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.edu.JobATMService.payload.request.QrCodeRequest;
import rmit.edu.JobATMService.service.Impl.QrCodeServiceImpl;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class qrCodeController {
    private final int WIDTH = 250;
    private final int HEIGHT = 250;

    private final QrCodeServiceImpl qrCodeService;

    @PostMapping("/qr-code")
    public ResponseEntity<byte[]> getQrCode(@RequestBody QrCodeRequest qrCodeRequest) {

        byte[] qrImage = qrCodeService.generate(qrCodeRequest.getUrlLink(), WIDTH, HEIGHT);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }

}
