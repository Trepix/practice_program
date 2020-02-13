package alert_service.acceptance.config;

import alert_service.detection.Payment;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Locale;

public class DataTableConfigurator implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(Payment.class,
                (TableEntryTransformer<Payment>) entry ->
                        new Payment(
                                Integer.valueOf(entry.get("amount")),
                                entry.get("category"),
                                entry.get("date"))));
    }
}
