package duke.exceptions;

public class MarkerMissingException extends ChatboxException {
    private String marker;
    
    public MarkerMissingException(String marker) {
        this.marker = marker;
    }
    
    public String getMarker() {
        return this.marker;
    }
}
