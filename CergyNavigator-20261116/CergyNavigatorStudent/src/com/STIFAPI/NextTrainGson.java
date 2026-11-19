package com.STIFAPI;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NextTrainGson {
    Siri Siri;

    public static class Siri {
        ServiceDelivery ServiceDelivery;
    }

    public static class ServiceDelivery {
        List<StopMonitoringDelivery> StopMonitoringDelivery;
    }

    public static class StopMonitoringDelivery {
        List<MonitoredStopVisit> MonitoredStopVisit;
    }

    public static class MonitoredStopVisit {
        MonitoredVehicleJourney MonitoredVehicleJourney;
    }

    public static class MonitoredVehicleJourney {
        @SerializedName("DestinationDisplay")
        List<ValueWrapper> DestinationDisplay;

        MonitoredCall MonitoredCall;
    }

    public static class MonitoredCall {
        @SerializedName("ExpectedDepartureTime")
        String ExpectedDepartureTime;
        @SerializedName("DestinationDisplay")
        List<ValueWrapper> DestinationDisplay;
    }

    public static class ValueWrapper {
        String value;
    }
}
