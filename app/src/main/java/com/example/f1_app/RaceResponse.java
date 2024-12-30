package com.example.f1_app;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RaceResponse {

    @SerializedName("MRData")  // The root element in the JSON response
    private MRData mrData;

    public MRData getMrData() {
        return mrData;
    }

    public void setMrData(MRData mrData) {
        this.mrData = mrData;
    }

    public List<Race> getRaceData() {
        // Return the list of races from MRData
        return mrData != null ? mrData.getRaces() : null;
    }

    public static class MRData {
        @SerializedName("RaceTable")  // The table of races in the response
        private RaceTable raceTable;

        public RaceTable getRaceTable() {
            return raceTable;
        }

        public void setRaceTable(RaceTable raceTable) {
            this.raceTable = raceTable;
        }

        public List<Race> getRaces() {
            return raceTable != null ? raceTable.getRaces() : null;
        }
    }

    public static class RaceTable {
        @SerializedName("Races")  // The list of races
        private List<Race> races;

        public List<Race> getRaces() {
            return races;
        }

        public void setRaces(List<Race> races) {
            this.races = races;
        }
    }
}
