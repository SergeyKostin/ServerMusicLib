
package servermusiclibary;

import model.Track;
import model.TrackList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class ControllerServer implements Controller {
    private TrackList model;
   public TrackList getModel(){
       return model;
   }
   public void setModel(TrackList model){
       this.model=model;
   }
    @Override
    public void createTrack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createPlayList(ArrayList<Track> tracks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Track getTrackByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Track> getTracksByPlayList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Track> scanForTracks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public  void Comands(String comand,String locFaile) throws IOException{
       
        switch(comand){
           
            case "readTXT":
            {
                System.out.println("readTXT");
                model=ReadTxtTrackList(locFaile);
               
                break;
            }   
            case"readXML":
            {
            model= RepositoryClass.ReadXmlTrackList(locFaile);
                System.out.println("readXML");
            break;
            }   
            case"writeTXT":
            {
            this.WriterTxtTrackList(model,locFaile);
                System.out.println("writeTXT");
            break;
            }
            case"writeXML":
            {
                System.out.println("writeXML");
            RepositoryClass.WriterXmlTrackList(model, locFaile);
                
            break;
            }
        }
    }
    
    public TrackList ReadTxtTrackList(String str) throws FileNotFoundException, IOException{
        TrackList trackList=new TrackList();
        BufferedReader read = new BufferedReader(new FileReader(str+".txt"));
        String tracks;
        int index=0;
       
        while((tracks=read.readLine())!=null){
            String [] tr=tracks.split(";");
            Track track=new Track(tr[0],tr[1],tr[2],tr[3],tr[4]);
            trackList.addTrack(index, track);
            index++;
        }
        read.close();
        return trackList;
    }
    
    public void WriterTxtTrackList(TrackList tracList, String str) throws FileNotFoundException, IOException{
       PrintWriter writ=new PrintWriter(new BufferedWriter(new FileWriter(str+".txt")));
     
       for(int i=0;i<tracList.getTracks().size();i++){
           writ.write(tracList.getTrack(i).getName()+";"+tracList.getTrack(i).getAlbum()+";"+tracList.getTrack(i).getBand()+";"+tracList.getTrack(i).getDuration()+";"+tracList.getTrack(i).getGenre()+";");
           writ.append("\n");
       }
       writ.close();
     }
}
