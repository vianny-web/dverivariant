package com.vianny.dverivariant.controllers;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.InteriorDoor;
import com.vianny.dverivariant.services.doors.InteriorDoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm")
public class AdminController {
    private InteriorDoorService interiorDoorService;
    @Autowired
    public void setInteriorDoorService(InteriorDoorService interiorDoorService) {
        this.interiorDoorService = interiorDoorService;
    }

    @PostMapping("/interior-doors")
    private ResponseEntity<ResponseMainMessage> createInteriorDoor(@RequestBody InteriorDoor interiorDoor) {
        try {
            interiorDoorService.addInteriorDoor(interiorDoor);
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар успешно добавлен в каталог");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }
}
