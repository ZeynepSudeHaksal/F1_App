package com.example.f1_app.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RaceResponse {

    @SerializedName("MRData")
    private MRData mRData;

    public List<Race> getRaceData() {
        return mRData.raceTable.races;
    }

    public static class MRData {

        @SerializedName("RaceTable")
        private RaceTable raceTable;
    }

    public static class RaceTable {

        @SerializedName("Races")
        private List<Race> races;
    }
}
