package com.vianny.dverivariant.utils.product;

import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.models.products.others.Hardware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ProductDetailsHelper {

    public HashMap<String, String> getDetailsInteriorDoor(Optional<InteriorDoor> interiorDoor) {
        HashMap<String, String> details = new HashMap<>();
        details.put("construction", interiorDoor.get().getConstruction().getDescription());
        details.put("glazing", interiorDoor.get().getGlazing().getDescription());
        details.put("manufacturer", interiorDoor.get().getManufacturer().getDescription());
        details.put("material", interiorDoor.get().getMaterial().getDescription());
        details.put("modification", interiorDoor.get().getModification().getDescription());

        return details;
    }

    public HashMap<String, String> getDetailsEntranceDoor(Optional<EntranceDoor> entranceDoor) {
        HashMap<String, String> details = new HashMap<>();
        details.put("glazing", entranceDoor.get().getGlazing().getDescription());
        details.put("additionalProperties", entranceDoor.get().getAdditionalProperties().getDescription());
        details.put("installationPlace", entranceDoor.get().getInstallationPlace().getDescription());

        return details;
    }

    public HashMap<String, String> getDetailsLaminate(Optional<Laminate> laminate) {
        HashMap<String, String> details = new HashMap<>();
        details.put("bevel", laminate.get().getBevel().getDescription());
        details.put("countryOfOrigin", laminate.get().getCountryOfOrigin().getDescription());
        details.put("thickness", laminate.get().getThickness().getDescription());
        details.put("waterResistance", laminate.get().getWaterResistance().getDescription());
        details.put("classType", laminate.get().getClassType().getValue());

        return details;
    }

    public HashMap<String, String> getDetailsQuartzvinyl(Optional<Quartzvinyl> quartzvinyl) {
        HashMap<String, String> details = new HashMap<>();
        details.put("base", quartzvinyl.get().getBase().getDescription());
        details.put("bevel", quartzvinyl.get().getBevel().getDescription());
        details.put("manufacturer", quartzvinyl.get().getManufacturer().getDescription());
        details.put("installationType", quartzvinyl.get().getInstallationType().getDescription());

        return details;
    }

    public HashMap<String, String> getDetailsHardware(Optional<Hardware> hardware) {
        HashMap<String, String> details = new HashMap<>();
        details.put("hardwareType", hardware.get().getHardwareType().getDescription());

        return details;
    }

}
