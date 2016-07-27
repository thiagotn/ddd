package ldevpp.webinar.ddd.billing;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LedgerEntry extends Comparable<LedgerEntry> {

    LocalDate getEntryDate();

    BigDecimal getEntryAmount();

    String getEntryDescription();

    @Override
    default int compareTo(LedgerEntry o) {
        return getEntryDate().compareTo(o.getEntryDate());
    }
}
