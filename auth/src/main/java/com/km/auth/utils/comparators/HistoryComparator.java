package com.km.auth.utils.comparators;

import com.km.auth.models.History;
import lombok.NoArgsConstructor;

import java.util.Comparator;
@NoArgsConstructor
public class HistoryComparator implements Comparator<History> {
    @Override
    public int compare(History o1, History o2) {
        return o2.getReturnDate().compareTo(o1.getReturnDate());
    }
}