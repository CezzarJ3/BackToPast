package cs.julia.backtopast.exhibition.controller.dto;

import java.sql.Timestamp;

public record ExhibitionDto(String name,
                            Timestamp start_date,
                            Timestamp end_date,
                            String country,
                            String city,
                            String place,
                            String organizer) {
}
