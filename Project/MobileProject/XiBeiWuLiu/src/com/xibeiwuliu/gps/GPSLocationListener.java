package com.xibeiwuliu.gps;

import android.location.Location;

/**
 * Listener for when an Entity object is clicked.
 * @author rbrundritt
 */
public interface GPSLocationListener {
	public abstract void onLocationChanged(Location location);
}
