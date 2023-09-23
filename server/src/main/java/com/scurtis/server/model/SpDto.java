package com.scurtis.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpDto {

    private int year;
    private String team;
    private String conference;
    private double rating;
    private int ranking;
    private double secondOrderWins;
    private double sos;
    private Offense offense;
    private Defense defense;
    private SpecialTeams specialTeams;

    @Getter
    @Setter
    public static class Offense {
        private int ranking;
        private double rating;
        private double success;
        private double explosiveness;
        private double rushing;
        private double passing;
        private double standardDowns;
        private double passingDowns;
        private double runRate;
        private double pace;
    }

    @Getter
    @Setter
    public static class Defense {
        private int ranking;
        private double rating;
        private double success;
        private double explosiveness;
        private double rushing;
        private double passing;
        private double standardDowns;
        private double passingDowns;
        private Havoc havoc;

        @Getter
        @Setter
        public static class Havoc {
            private double total;
            private double frontSeven;
            private double db;
        }

    }

    @Getter
    @Setter
    public static class SpecialTeams {
        private double rating;
    }

}
