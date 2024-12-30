package com.example.f1_app.model;

import com.google.gson.annotations.SerializedName;

public class Race {

    @SerializedName("raceName")
    private String raceName;

    @SerializedName("Circuit")
    private Circuit circuit;

    @SerializedName("date")
    private String raceDate;

    // Constructor
    public Race(String raceName, Circuit circuit, String raceDate) {
        this.raceName = raceName;
        this.circuit = circuit;
        this.raceDate = raceDate;
    }

    // Getters and Setters
    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    // Nested class for Circuit
    public static class Circuit {

        @SerializedName("circuitName")
        private String circuitName;

        @SerializedName("Location")
        private Location location;

        // Constructor
        public Circuit(String circuitName, Location location) {
            this.circuitName = circuitName;
            this.location = location;
        }

        // Getters and Setters
        public String getCircuitName() {
            return circuitName;
        }

        public void setCircuitName(String circuitName) {
            this.circuitName = circuitName;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        // Nested class for Location
        public static class Location {

            @SerializedName("locality")
            private String locality;

            @SerializedName("country")
            private String country;

            // Constructor
            public Location(String locality, String country) {
                this.locality = locality;
                this.country = country;
            }

            // Getters and Setters
            public String getLocality() {
                return locality;
            }

            public void setLocality(String locality) {
                this.locality = locality;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }
    }
}
