package nextstep.subway.station.dto;

import nextstep.subway.station.domain.Station;

public class StationRequest {
    private String name;

    protected StationRequest() {
        // for serialize
    }

    public StationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Station toStation() {
        return new Station(name);
    }
}