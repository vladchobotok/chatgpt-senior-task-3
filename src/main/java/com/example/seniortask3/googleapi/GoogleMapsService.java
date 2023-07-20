package com.example.seniortask3.googleapi;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleMapsService {

    private final GeoApiContext geoApiContext;

    // Inject the Google Maps API key here
    public GoogleMapsService(@Value("${google.maps.api.key}") String apiKey) {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

//    // Method to get directions between two locations
//    public DirectionsResult getDirections(String origin, String destination) {
//        try {
//            return DirectionsApi.newRequest(geoApiContext)
//                    .origin(origin)
//                    .destination(destination)
//                    .mode(TravelMode.DRIVING)
//                    .await();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // Method to search for nearby healthcare facilities
//    public PlacesSearchResponse findHealthcareFacilities(double latitude, double longitude, String keyword) {
//        try {
//            LatLng location = new LatLng(latitude, longitude);
//            NearbySearchRequest nearbySearchRequest = PlacesApi.nearbySearchQuery(geoApiContext, location);
//            nearbySearchRequest.keyword(keyword); // Search for healthcare facilities
//            nearbySearchRequest.radius(5000); // Search within a 5 km radius
//
//            return nearbySearchRequest.await();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public String getDirectionsForPatients(String origin, String destination) {
        try {
            DirectionsResult directionsResult = DirectionsApi.newRequest(geoApiContext)
                    .origin(origin)
                    .destination(destination)
                    .mode(TravelMode.DRIVING)
                    .await();

            if (directionsResult != null && directionsResult.routes.length > 0) {
                StringBuilder directions = new StringBuilder();
                for (DirectionsStep step : directionsResult.routes[0].legs[0].steps) {
                    directions.append(step.htmlInstructions).append("<br>");
                }
                return directions.toString();
            } else {
                return "No directions found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while fetching directions.";
        }
    }
    public List<String> findNearbyHealthcareFacilities(double latitude, double longitude, String keyword) {
        try {
            LatLng location = new LatLng(latitude, longitude);
            NearbySearchRequest nearbySearchRequest = PlacesApi.nearbySearchQuery(geoApiContext, location);
            nearbySearchRequest.keyword(keyword);
            nearbySearchRequest.radius(5000);

            PlacesSearchResponse response = nearbySearchRequest.await();
            List<String> healthcareFacilities = new ArrayList<>();

            if (response != null && response.results != null) {
                for (PlacesSearchResult result : response.results) {
                    healthcareFacilities.add(result.name + " - " + result.vicinity);
                }
            }

            return healthcareFacilities;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

