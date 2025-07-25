package ch.unisg.sat.controller.http.dto.request.aggregate;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateSystemRequestDto {

    public final static String MEDIA_TYPE = "application/system+json";

    private UUID id;
    private String title;

}
