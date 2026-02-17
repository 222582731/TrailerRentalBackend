package tr.ac.co.factory;

import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.enums.Status;
import tr.ac.co.domain.enums.TrailerType;
import tr.ac.co.util.Helper;

import java.math.BigDecimal;

public class TrailerFactory {

    public static Trailer createTrailer(String name,
                                        TrailerType type,
                                        String description,
                                        BigDecimal pricePerDay,
                                        int capacityKg,
                                        String size,
                                        String imageUrl,
                                        Status status) {

        // Validate required fields
        if (Helper.isNullOrEmpty(name)
                || type == null
                || pricePerDay == null
                || pricePerDay.compareTo(BigDecimal.ZERO) <= 0
                || capacityKg <= 0
                || status == null) {
            return null;
        }

        return new Trailer.Builder()
                .setName(name)
                .setType(type)
                .setDescription(description)
                .setPricePerDay(pricePerDay)
                .setCapacityKg(capacityKg)
                .setSize(size)
                .setImageUrl(imageUrl)
                .setStatus(status)
                .build();
    }

    public static Trailer createAvailableTrailer(String name,
                                                 TrailerType type,
                                                 BigDecimal pricePerDay,
                                                 int capacityKg) {
       //setting default status
        return createTrailer(name, type, null, pricePerDay, capacityKg, null, null, Status.AVAILABLE);
    }
}
