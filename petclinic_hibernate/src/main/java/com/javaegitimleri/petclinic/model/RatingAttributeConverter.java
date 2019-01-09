package com.javaegitimleri.petclinic.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=false)
public class RatingAttributeConverter implements AttributeConverter<Rating, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Rating attribute) {
		if(attribute == null) return null;
		return attribute.getValue();
	}

	@Override
	public Rating convertToEntityAttribute(Integer dbData) {
		if(dbData == null) return null;
		if(dbData == 100) return Rating.STANDART;
		else return Rating.PREMIUM;
	}

}
