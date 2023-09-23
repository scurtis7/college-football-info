package com.scurtis.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FpiDto {

    private int year;
    private String team;
    private String conference;
    private double fpi;
    private ResumeRanks resumeRanks;
    private Efficiencies efficiencies;

    @Getter
    @Setter
    private static class ResumeRanks {
        private int strengthOfRecord;
        private int fpi;
        private int averageWinProbability;
        private int strengthOfSchedule;
        private int remaningStrengthOfSchedule;
        private int gameControl;
    }

    @Getter
    @Setter
    private static class Efficiencies {
        private double overall;
        private double offense;
        private double defense;
        private double specialTeams;
    }

}
