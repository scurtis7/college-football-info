package com.scurtis.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatsDto {
    private long gameId;
    private int week;
    private String team;
    private String opponent;
    private OffenseDefense offense;
    private OffenseDefense defense;

    @Getter
    @Setter
    public static class OffenseDefense {
        private int plays;
        private int drives;
        private int powerSuccess;
        private double ppa;
        private double totalPPA;
        private double successRate;
        private double explosiveness;
        private double stuffRate;
        private double lineYards;
        private int lineYardsTotal;
        private double secondLevelYards;
        private int secondLevelYardsTotal;
        private double openFieldYards;
        private int openFieldYardsTotal;
        private Downs standardDowns;
        private Downs passingDowns;
        private Plays rushingPlays;
        private Plays passingPlays;

    }

    @Getter
    @Setter
    public static class Downs {
        private double ppa;
        private double successRate;
        private double explosiveness;

    }

    @Getter
    @Setter
    public static class Plays extends Downs {
        private double totalPPA;

    }

}
