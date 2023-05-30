package ru.itis.rusteam.converters;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import ru.itis.rusteam.models.Category;

import java.util.HashSet;
import java.util.Set;

public class StringToCategoryConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        HashSet<ConvertiblePair> pairSet = new HashSet<>();
        pairSet.add(new ConvertiblePair(String.class, Category.class));
        pairSet.add(new ConvertiblePair(Category.class, String.class));
        return pairSet;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.equals(sourceType.getType()) && Category.class.equals(targetType.getType())) {
            String category = source.toString();
            return Category.builder().name(category).build();
        }
        if (Category.class.equals(sourceType.getType()) && String.class.equals(targetType.getType())) {
            Category category = (Category) source;
            return category.getName();
        }
        throw new IllegalArgumentException("Invalid types to convert");
    }
}
