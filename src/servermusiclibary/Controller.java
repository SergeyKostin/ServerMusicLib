package servermusiclibary;
import model.Track;
import java.util.ArrayList;
import java.util.List;


public interface Controller {
    
   void createTrack();
   void createPlayList(ArrayList<Track> tracks);
   Track getTrackByName(String name);
   ArrayList<Track> getTracksByPlayList();
   List<Track> scanForTracks(); 
    
}
