package hotelManagementServicePublihser;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<?> hotelServiceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Hotel Service Bundle Activated!");
        
        // Register the HotelService implementation
        hotelServiceRegistration = context.registerService(HotelService.class.getName(), new HotelServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Hotel Service Bundle Deactivated!");
        
        // Unregister the HotelService
        if (hotelServiceRegistration != null) {
            hotelServiceRegistration.unregister();
        }
    }
}
