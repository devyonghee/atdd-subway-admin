package nextstep.subway.line.dto;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.stationAdapter.SafeStationInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private List<StationInLineResponse> stations;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LineResponse(
            Long id, String name, String color, List<StationInLineResponse> stations,
            LocalDateTime createdDate, LocalDateTime modifiedDate
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static LineResponse of(Line line, List<SafeStationInfo> safeStationInfos) {
        return new LineResponse(line.getId(), line.getName(), line.getColor(), parseSafeStationInfos(safeStationInfos),
                line.getCreatedDate(), line.getModifiedDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<StationInLineResponse> getStations() {
        return stations;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    private static List<StationInLineResponse> parseSafeStationInfos(final List<SafeStationInfo> safeStationInfos) {
        if (safeStationInfos == null) {
            return new ArrayList<>();
        }

        return safeStationInfos.stream()
                .map(StationInLineResponse::of)
                .collect(Collectors.toList());
    }
}