package rmit.edu.JobATMService.service;

public interface QrCodeService {
    byte[] generate(String text, int width, int height);
}
