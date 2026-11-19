package com.STIFAPI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class STIFAPIRequest {
    private String APIToken;
    private ArrayList<String> information = new ArrayList<>();
    public ArrayList<String> getInformation(){
        String response = APIHTTPRequest();
        analyseResponse(response);
        return information;
    }
    public STIFAPIRequest(){
        APIToken=APITokenConstant.getToken();
    }
    public STIFAPIRequest(String APIToken){
        this.APIToken=APIToken;
    }

    public String APIHTTPRequest(){
        String response="";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APITokenConstant.getUrl()+"?MonitoringRef=STIF%3AStopArea%3ASP%3A"+APITokenConstant.getCergyCode()+"%3A"))
                .header("APIKey",APIToken)
                .build();
        try{
            HttpResponse httpresponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response=httpresponse.body().toString();
            Thread.sleep(2000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return response.toString();
    }

    public void analyseResponse(String response){
        information.clear();
        Gson gson = new Gson();
        NextTrainGson nextTrain = gson.fromJson(response, NextTrainGson.class);
        List<NextTrainGson.MonitoredStopVisit> visits =
                nextTrain.Siri.ServiceDelivery.StopMonitoringDelivery.get(0).MonitoredStopVisit;
        for (int i = 0; i < visits.size(); i++) {
            NextTrainGson.MonitoredVehicleJourney journey = visits.get(i).MonitoredVehicleJourney;
            NextTrainGson.MonitoredCall call = journey.MonitoredCall;

            String destination = call.DestinationDisplay.get(0).value;
            String departureTime = call.ExpectedDepartureTime;

            try{
                ZonedDateTime utcDateTime = ZonedDateTime.parse(departureTime);  // depuis le JSON
                ZonedDateTime parisTime = utcDateTime.withZoneSameInstant(ZoneId.of("Europe/Paris"));
                String formatted = parisTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                if (parisTime.isAfter(ZonedDateTime.now())){
                    System.out.println("Train vers " + destination + " à " + formatted);
                    information.add(formatted + "   "+ destination);
                }
            }
            catch (Exception e){
                System.out.println("Wrong time");
            }
        }
    }
}
