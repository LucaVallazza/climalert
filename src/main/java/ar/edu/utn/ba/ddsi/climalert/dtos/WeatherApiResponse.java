package ar.edu.utn.ba.ddsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(Location location, Current current) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Location(String name, double lat, double lon) {
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Current(Double temp_c, Integer humidity) {
	}

}