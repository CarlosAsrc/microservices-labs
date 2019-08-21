package com.carlosdione.jtscloudnative.temafinal2.appservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.carlosdione.jtscloudnative.temafinal2.appservice.discovery.EurekaHttpMethods;
import com.carlosdione.jtscloudnative.temafinal2.appservice.hystrixcommand.PlaylistCommand;
import com.carlosdione.jtscloudnative.temafinal2.appservice.hystrixcommand.SongCommand;
import com.carlosdione.jtscloudnative.temafinal2.appservice.ribbon.ClientLoadBalancer;
import com.carlosdione.jtscloudnative.temafinal2.appservice.util.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.loadbalancer.Server;

import feign.Feign;

@Controller
public class AppService {

	private static final Gson GSON = new Gson();
	private static final JsonParser JSONPARSER = new JsonParser();
	private static final String PLAYLIST_SERVICE = "PLAYLIST-SERVICE";
	private static final String SONG_SERVICE = "SONG-SERVICE";
	
	private ClientLoadBalancer songLoadBalancer;
	private ClientLoadBalancer playlistLoadBalancer;
	private EurekaHttpMethods eurekaHttpMethodsService;

	@Value("${eureka.vips}")
	private String eurekaVips;
	
    public String getSongDetailsByPlaylist(String id) {
    	List<Server> playlistHosts = getHosts(PLAYLIST_SERVICE);
    	List<Server> songHosts = getHosts(SONG_SERVICE);
    	songLoadBalancer = new ClientLoadBalancer(songHosts);
    	playlistLoadBalancer = new ClientLoadBalancer(playlistHosts);
    	
    	if (!playlistHosts.isEmpty()) {
	        ResponseEntity<String> playlistResponse = new PlaylistCommand(id, playlistLoadBalancer.getServer()).execute();
	    	
	        String playlistRespondeBody = playlistResponse.getBody();
	        JsonObject playlistJson = JSONPARSER.parse(playlistRespondeBody).getAsJsonObject();
	        JsonElement playlistIdJson = playlistJson.get("id");
	        
	        if (playlistIdJson != null) {
		        JsonElement songsIdJson = playlistJson.get("songsId");
		        
		        if (songsIdJson != null && !songHosts.isEmpty()) {
			        JsonArray songs = songsIdJson.getAsJsonArray();
			        
			        List<String> songsTitles = new ArrayList<>();
			        for (JsonElement value : songs) {
			            ResponseEntity<String> songResponse = new SongCommand(value.getAsString(), songLoadBalancer.getServer()).execute();
			            if (songResponse.getStatusCode() == HttpStatus.ACCEPTED) {
			                JsonObject songJson = JSONPARSER.parse(songResponse.getBody()).getAsJsonObject();
			                JsonElement songTitleSong = songJson.get("title");
			                 if (songTitleSong != null) {
			                    songsTitles.add(songTitleSong.getAsString());
			                 }
			            }
			        }	
			        return createJsonResonse(playlistIdJson.getAsString(), songsTitles);
		        }
		        return createJsonResonse(playlistIdJson.getAsString(), new ArrayList<>());
	        }
	        return playlistResponse.getBody();
    	}
    	return createJsonResonse("0", new ArrayList<>());
    }
    
    public List<Server> getHosts(String serviceName) {
		this.eurekaHttpMethodsService = Feign.builder().target(EurekaHttpMethods.class, eurekaVips);
        List<Server> hosts = new ArrayList<>();
        JsonObject json = JSONPARSER.parse(eurekaHttpMethodsService.getInstances(serviceName)).getAsJsonObject();
        JsonObject jsonApplications = json.get("applications").getAsJsonObject();
        JsonArray jsonApplication = jsonApplications.get("application").getAsJsonArray();
        if (jsonApplication.size() > 0) {
	        JsonObject jsonApplicationObj = jsonApplication.get(0).getAsJsonObject();   
	        JsonArray jsonInstance = jsonApplicationObj.get("instance").getAsJsonArray();
	        
	        for (JsonElement  jsonElement : jsonInstance) { 
	            JsonObject jsonIntanceObj = jsonElement.getAsJsonObject();
	            String hostUrl = jsonIntanceObj.get("homePageUrl").getAsString();
	            String[] hostUrlParameters = hostUrl.replace("http://", "").split(":");
	            Server host = new Server("http://" + hostUrlParameters[0], Integer.parseInt(hostUrlParameters[1]));
	            hosts.add(host);
			}
        }
        return hosts;
    }
    
    private String createJsonResonse(String playlistIdValue, List<String> musicsValue) {
    	JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.add("Playlist ID", playlistIdValue);
        jsonResponse.add("Musics", GSON.toJson(musicsValue));
        return GSON.toJson(jsonResponse.getResponseJson());
    }
    
}
