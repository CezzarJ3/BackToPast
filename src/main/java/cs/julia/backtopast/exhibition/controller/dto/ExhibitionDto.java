package cs.julia.backtopast.exhibition.controller.dto;

public record ExhibitionDto(String name,
                            String start_date,
                            String end_date,
                            String country,
                            String city,
                            String place,
                            String organizer) {
}
