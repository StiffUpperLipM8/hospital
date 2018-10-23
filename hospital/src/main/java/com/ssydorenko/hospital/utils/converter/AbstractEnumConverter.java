package com.ssydorenko.hospital.utils.converter;

import javax.persistence.AttributeConverter;
import java.util.Objects;


public abstract class AbstractEnumConverter<X> implements AttributeConverter<X, String> {

    @Override
    public String convertToDatabaseColumn(X attribute) {

        return Objects.isNull(attribute) ? null : attribute.toString();
    }

}