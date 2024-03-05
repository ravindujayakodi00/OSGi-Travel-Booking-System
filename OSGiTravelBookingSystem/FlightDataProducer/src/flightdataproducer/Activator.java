package flightdataproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Flight Data Producer Bundle started");
        context.registerService(FlightDataProducerService.class.getName(), new FlightDataProducerServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Flight Data Producer Bundle stopped");
    }

}
