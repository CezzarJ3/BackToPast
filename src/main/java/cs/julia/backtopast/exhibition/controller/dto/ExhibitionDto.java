package cs.julia.backtopast.exhibition.controller.dto;

import java.util.Date;

public record ExhibitionDto(String name,
                            Date start_date,
                            Date end_sate,
                            String country,
                            String city,
                            String place,
                            String organizer) {
}
