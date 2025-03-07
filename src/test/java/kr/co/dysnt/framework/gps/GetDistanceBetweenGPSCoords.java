package kr.co.dysnt.framework.gps;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StopWatch;

@SpringBootTest
@ActiveProfiles({ "local" })
@Slf4j
public class GetDistanceBetweenGPSCoords {

    private static double deg2Rad(double angle) {
        return angle * Math.PI / 180f;
    }

    public static double rad2Deg(double angle) {
        return angle * 180.0f / Math.PI;
    }

    @Test
    public void TestGetDistanceBetweenGPSCoords() {

        StopWatch stopWatch = new StopWatch("GetDistanceBetweenGPSCoords - TestGetDistanceBetweenGPSCoords");
        stopWatch.start();

        double lat1 = 35.16582777777778;
        double lon1 = 129.06523055555556;

        double lat2 = 35.17110555555556;
        double lon2 = 129.06745833333332;

        // get distance case 01

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2Rad(lat1))
                * Math.sin(deg2Rad(lat2))
                + Math.cos(deg2Rad(lat1))
                        * Math.cos(deg2Rad(lat2))
                        * Math.cos(deg2Rad(theta));

        dist = Math.acos(dist);
        dist = rad2Deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344 * 1000.0f;

        log.info("dist : " + dist);
        stopWatch.stop();
        log.info("stopWatch case 01 : " + stopWatch.getLastTaskTimeNanos());

        // get distance case 02

        // double SEMI_MAJOR_AXIS_MT = 6378137;
        // double SEMI_MINOR_AXIS_MT = 6356752.314245;
        // double FLATTENING = 1 / 298.257223563;
        // double ERROR_TOLERANCE = 1e-12;
        //
        //
        // double U1 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(lat1)));
        // double U2 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(lat2)));
        //
        // double sinU1 = Math.sin(U1);
        // double cosU1 = Math.cos(U1);
        // double sinU2 = Math.sin(U2);
        // double cosU2 = Math.cos(U2);
        //
        // double longitudeDifference = Math.toRadians(lon2 - lon1);
        // double previousLongitudeDifference;
        //
        // double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;
        //
        // do {
        // sinSigma = Math.sqrt(Math.pow(cosU2 * Math.sin(longitudeDifference), 2) +
        // Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * Math.cos(longitudeDifference), 2));
        // cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * Math.cos(longitudeDifference);
        // sigma = Math.atan2(sinSigma, cosSigma);
        // sinAlpha = cosU1 * cosU2 * Math.sin(longitudeDifference) / sinSigma;
        // cosSqAlpha = 1 - Math.pow(sinAlpha, 2);
        // cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
        // if (Double.isNaN(cos2SigmaM)) {
        // cos2SigmaM = 0;
        // }
        // previousLongitudeDifference = longitudeDifference;
        // double C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 *
        // cosSqAlpha));
        // longitudeDifference = Math.toRadians(lon2 - lon1) + (1 - C) * FLATTENING *
        // sinAlpha *
        // (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 *
        // Math.pow(cos2SigmaM, 2))));
        // } while (Math.abs(longitudeDifference - previousLongitudeDifference) >
        // ERROR_TOLERANCE);
        //
        // double uSq = cosSqAlpha * (Math.pow(SEMI_MAJOR_AXIS_MT, 2) -
        // Math.pow(SEMI_MINOR_AXIS_MT, 2)) / Math.pow(SEMI_MINOR_AXIS_MT, 2);
        //
        // double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        // double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        //
        // double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2
        // * Math.pow(cos2SigmaM, 2))
        // - B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 *
        // Math.pow(cos2SigmaM, 2))));
        //
        // double distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);
        //
        // log.info("dist : " + distanceMt);
        // stopWatch.stop();
        // log.info("stopWatch case 02 : " + stopWatch.getLastTaskTimeNanos());

    }
}
