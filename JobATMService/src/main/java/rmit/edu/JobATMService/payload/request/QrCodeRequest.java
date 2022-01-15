package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QrCodeRequest {
    private String urlLink;
}
