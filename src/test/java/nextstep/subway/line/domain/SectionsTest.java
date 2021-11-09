package nextstep.subway.line.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nextstep.subway.common.domain.Name;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("구간들")
class SectionsTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
            .isThrownBy(() -> Sections.from(Collections.singletonList(mock(Section.class))));
    }

    @Test
    @DisplayName("목록이 비어있는 상태로 객체화하면 IllegalArgumentException")
    void instance_emptyList_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Sections.from(Collections.emptyList()))
            .withMessage("section list must not be empty");
    }


    @Test
    @DisplayName("지하철 역들")
    void stations() {
        //given
        Name gangnam = Name.from("강남");
        Name yeoksam = Name.from("역삼");
        Name jamsil = Name.from("잠실");

        Sections sections = Sections.from(Arrays.asList(
            section(gangnam, yeoksam),
            section(yeoksam, jamsil)
        ));

        //when
        List<Station> stations = sections.stations();

        //then
        hasThreeAndNotHasDuplicates(stations, gangnam, yeoksam, jamsil);
    }

    private void hasThreeAndNotHasDuplicates(
        List<Station> stations, Name expectedFirst, Name expectedSecond, Name expectedThird) {
        assertThat(stations)
            .hasSize(3)
            .doesNotHaveDuplicates()
            .extracting(Station::name)
            .containsExactly(expectedFirst, expectedSecond, expectedThird);
    }


    private Section section(Name upStation, Name downStation) {
        return Section.of(Station.from(upStation),
            Station.from(downStation),
            Distance.from(Integer.MAX_VALUE));
    }


}
