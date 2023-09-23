-- Drop tables
DROP TABLE IF EXISTS Conference;
DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS Venue;

-- Create Conference Table
CREATE TABLE Conference
(
    Id             int NOT NULL,
    Name           varchar(255),
    Short_Name     varchar(255),
    Abbreviation   varchar(255),
    Classification varchar(255),
    PRIMARY KEY (Id)
);

-- Create Venue Table
CREATE TABLE Venue
(
    Id               int NOT NULL,
    Name             varchar(255),
    City             varchar(255),
    State            varchar(255),
    Zip              varchar(255),
    Country_Code     varchar(255),
    Timezone         varchar(255),
    Latitude         numeric(20, 10),
    Longitude        numeric(20, 10),
    Elevation        varchar(255),
    Capacity         int,
    Year_Constructed int,
    Grass            bool,
    Dome             bool,
    PRIMARY KEY (Id)
);

-- Create Team Table
CREATE TABLE Team
(
    Id              int NOT NULL,
    School          varchar(255),
    Mascot          varchar(255),
    Abbreviation    varchar(255),
    Alternate_Name1 varchar(255),
    Alternate_Name2 varchar(255),
    Alternate_Name3 varchar(255),
    Classification  varchar(255),
    Conference      varchar(255),
    Division        varchar(255),
    Color           varchar(255),
    Alternate_Color varchar(255),
    Logos           varchar(510),
    Twitter         varchar(255),
    Venue_Id        int REFERENCES Venue (Id),
    PRIMARY KEY (Id)
);


